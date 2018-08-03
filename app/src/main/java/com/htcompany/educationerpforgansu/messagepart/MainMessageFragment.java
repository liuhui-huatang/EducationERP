package com.htcompany.educationerpforgansu.messagepart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenu;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuCreator;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuItem;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuListView;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.messageinternet.MessageInternet;
import com.htcompany.educationerpforgansu.internet.messageinternet.MessagePersener;
import com.htcompany.educationerpforgansu.messagepart.adapter.MainMessageAdapter;
import com.htcompany.educationerpforgansu.messagepart.entity.MainMessageDetailsActivity;
import com.htcompany.educationerpforgansu.messagepart.entity.MainMessageEntity;
import com.htcompany.educationerpforgansu.workpart.activitys.OverWorkFlowDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.WorkFlowActivity;
import com.htcompany.educationerpforgansu.workpart.exampart.activitys.ExampartInvigilateActivity;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExampartInvigilateEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息fragment
 * Created by wrb on 2016/11/2.
 */
public class MainMessageFragment extends Fragment implements XListView.IXListViewListener{
    private View view;
    private ImageView mainmessage_wsj_img;
    private XListView mainmessage_lv;
    private List<MainMessageEntity> mainMessageEntities;
    private MainMessageAdapter messageAdapter;
    private int pageNum=1;//页数
    private Intent intent=null;
    private MessageInternet messageInternet=null;
    private MessagePersener messagePersener=null;
    private RelativeLayout titlebar_rel;
    private TextView mainmessage_title_tv;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mainmessage_fragment,container,false);
        initDatas();
        initViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            initValues();
        }
        return view;
    }
    public void initDatas(){
        mainMessageEntities=new ArrayList<MainMessageEntity>();
        messageInternet = new MessageInternet(getActivity(),myHandler);
        messagePersener=new MessagePersener(getActivity(),myHandler);
        messageInternet.getPersonMsg(String.valueOf(pageNum));
    }

    public void initViews(){
        mainmessage_title_tv=(TextView)view.findViewById(R.id.mainmessage_title_tv);
        titlebar_rel=(RelativeLayout)view.findViewById(R.id.titlebar_rel);
        mainmessage_wsj_img=(ImageView)view.findViewById(R.id.mainmessage_wsj_img);
        mainmessage_lv = (XListView)view.findViewById(R.id.mainmessage_lv);
        mainmessage_lv.setPullRefreshEnable(true);
        mainmessage_lv.setPullLoadEnable(false);
        mainmessage_lv.setXListViewListener(this);
        messageAdapter = new MainMessageAdapter(getActivity(),mainMessageEntities);
        mainmessage_lv.setAdapter(messageAdapter);
        mainmessage_lv.setOnItemClickListener(itemClickListener);
    }
    public void initValues(){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mainmessage_title_tv.getLayoutParams();
        layoutParams.setMargins(0, BaseUtils.getStatusBarHeight(getActivity()),0,0);//4个参数按顺序分别是左上右下
        mainmessage_title_tv.setLayoutParams(layoutParams); //mView是控件
    }
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MainMessageEntity entity = (MainMessageEntity) messageAdapter.getItem(position-1);
                    for(MainMessageEntity entity1:mainMessageEntities){
                        if(entity.getId().equals(entity1.getId())){
                            entity1.setIs_read("已读");
                        }
                    }
                    startActivity(new Intent(getActivity(), MainMessageDetailsActivity.class)
                    .putExtra("entity",entity));
                }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    ToastUtil.showToast("连接超时",getActivity());
                    break;
                case 200:
                    List<MainMessageEntity> datas = messagePersener.parsePersonMsgData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(mainMessageEntities.size()==0){
                            mainmessage_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            mainmessage_wsj_img.setVisibility(View.GONE);
                        }
                        mainmessage_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    ToastUtil.showToast("数据错误",getActivity());
                    break;
            }
        }
    };

/**
 * 更新列表数据
 * @param datas
 */
public void setLVDatas(List<MainMessageEntity> datas) {
    if (pageNum==1) {
        if (datas.size() > 0) {
            mainMessageEntities.clear();
        }
    }
    if (mainMessageEntities.size()<50){
        mainmessage_lv.setPullLoadEnable(false);
    }
    for (MainMessageEntity entity : datas) {
        mainMessageEntities.add(entity);
    }
    if(mainMessageEntities.size()==0){
        mainmessage_wsj_img.setVisibility(View.VISIBLE);
    }else {
        mainmessage_wsj_img.setVisibility(View.GONE);
    }
    messageAdapter.notifyDataSetChanged();
    stopListView();
}
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        messageInternet.getPersonMsg(String.valueOf(pageNum));
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        messageInternet.getPersonMsg(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        mainmessage_lv.stopRefresh();
        mainmessage_lv.stopLoadMore();
        mainmessage_lv.setRefreshTime("刚刚");
    }

    @Override
    public void onResume() {
        super.onResume();

        messageAdapter.notifyDataSetChanged();
    }
}
