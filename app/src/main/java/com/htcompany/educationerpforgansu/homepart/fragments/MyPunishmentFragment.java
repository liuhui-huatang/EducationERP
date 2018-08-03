package com.htcompany.educationerpforgansu.homepart.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.homepart.activitys.MyPunishmentDetailsActivity;
import com.htcompany.educationerpforgansu.homepart.adapters.MyPunishmentFragmentAdapter;
import com.htcompany.educationerpforgansu.homepart.entity.MyPunishmentEntity;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomeInternet;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomePersener;

import java.util.ArrayList;
import java.util.List;

/**
 * 惩罚记录
 * Created by wrb on 2016/10/24.
 */
public class MyPunishmentFragment extends Fragment {
    private View punView;
    private ImageView mypunish_wsj_img;
    private ListView mypunish_fragment_lv;
    private MyPunishmentFragmentAdapter punishmentFragmentAdapter;
    private List<MyPunishmentEntity> punishmentEntities;
    //=========================网络请求类==========================
    private MainHomeInternet homeInternet;
    private MainHomePersener homePersener;
    private WaitDialog waitDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        punView = inflater.inflate(R.layout.mypunishment_fragment,null);
        initDatas();
        initViews();
        return punView;
    }
    public void initDatas() {
        punishmentEntities = new ArrayList<MyPunishmentEntity>();
        homeInternet = new MainHomeInternet(getActivity(), myHandler);
        homePersener = new MainHomePersener(getActivity(), myHandler);
        waitDialog = new WaitDialog(getActivity(), "");
        waitDialog.show();
        homeInternet.getMyPunishmentsListMsg();
    }
    public void initViews(){
        mypunish_wsj_img=(ImageView) punView.findViewById(R.id.mypunish_wsj_img);
        mypunish_fragment_lv = (ListView)punView.findViewById(R.id.mypunish_fragment_lv);
        punishmentFragmentAdapter = new MyPunishmentFragmentAdapter(getActivity(),punishmentEntities);
        mypunish_fragment_lv.setAdapter(punishmentFragmentAdapter);
        mypunish_fragment_lv.setOnItemClickListener(punishmentClicer);
    }
    public AdapterView.OnItemClickListener punishmentClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyPunishmentEntity entity = (MyPunishmentEntity) punishmentFragmentAdapter.getItem(position);
            if(entity!=null) {
                Intent intent = new Intent(getActivity(), MyPunishmentDetailsActivity.class);
                intent.putExtra("entity",entity);
                startActivity(intent);
            }
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",getActivity());
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MyPunishmentEntity> datas = homePersener.parseMyPunishments_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        mypunish_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",getActivity());
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<MyPunishmentEntity> datas){
        if(punishmentEntities.size()>0){
            punishmentEntities.clear();
        }
        for(MyPunishmentEntity entity:datas){
            punishmentEntities.add(entity);
        }
        punishmentFragmentAdapter.notifyDataSetChanged();
    }
}
