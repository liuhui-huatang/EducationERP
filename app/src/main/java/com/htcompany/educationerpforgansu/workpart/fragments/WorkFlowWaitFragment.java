package com.htcompany.educationerpforgansu.workpart.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.activitys.WaitFreedomWorkFlowDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.WaitWorkFlowDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.WorkFlowAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.WaitFlowEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 代办工作流
 * Created by wrb on 2017/4/6.
 */
public class WorkFlowWaitFragment extends Fragment implements XListView.IXListViewListener{
    private XListView workflow_dbsx_lv;
    private ImageView workflow_dbsx_wsj_img;
    private WorkFlowAdapter flowAdapter;
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private List<WaitFlowEntity> flowEntities;
    private WaitDialog waitDialog;
    private View waitView;
    private int pageNum;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        waitView = inflater.inflate(R.layout.workflow_wait_fragment,container,false);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
        return waitView;
    }
    public void initDatas(){
        waitDialog = new WaitDialog(getActivity(),"");
        waitDialog.show();
        flowEntities = new ArrayList<WaitFlowEntity>();
        wokrpersonalPersener = new WokrpersonalPersener(getActivity());
        wokrpersonalInternet = new WokrpersonalInternet(getActivity(),myHandler);
        wokrpersonalInternet.getWaitClGZL(String.valueOf(pageNum));
    }
    public void initViews(){
        workflow_dbsx_wsj_img=(ImageView)waitView.findViewById(R.id.workflow_dbsx_wsj_img);
        workflow_dbsx_lv = (XListView) waitView.findViewById(R.id.workflow_dbsx_lv);
        workflow_dbsx_lv.setPullRefreshEnable(true);
        workflow_dbsx_lv.setPullLoadEnable(false);
        workflow_dbsx_lv.setXListViewListener(this);
        flowAdapter = new WorkFlowAdapter(getActivity(),flowEntities);
        workflow_dbsx_lv.setAdapter(flowAdapter);
    }
    public void initViewValues(){
    }
    public void initOnclicEvents(){
        workflow_dbsx_lv.setOnItemClickListener(itemclicer);
    }
    public AdapterView.OnItemClickListener itemclicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            WaitFlowEntity entity = (WaitFlowEntity)flowAdapter.getItem(position-1);
            Intent intent=null;
            if("1".equals(entity.getFlow_type())||"0".equals(entity.getFlow_type())) {
                intent = new Intent(getActivity(), WaitWorkFlowDetailsActivity.class);
            }else if("2".equals(entity.getFlow_type())){
                intent = new Intent(getActivity(), WaitFreedomWorkFlowDetailsActivity.class);
            }
            intent.putExtra("flowEntity",entity);
            startActivity(intent);
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
                    ToastUtil.showToast("请求网络失败",getActivity());
                    break;
                case 200:
                    //处理数据
                    List<WaitFlowEntity> datas= wokrpersonalPersener.parseWrokFlow((String) msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapter(datas);
                    }else{
                        if(flowEntities.size()==0){
                            workflow_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            workflow_dbsx_wsj_img.setVisibility(View.GONE);
                        }
                        workflow_dbsx_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
            }
        }
    };
    public void setAdapter(List<WaitFlowEntity> datas){
        if(pageNum==1){
            if(flowEntities.size()>0){
                flowEntities.clear();
            }
        }
        if(datas.size()>0){
            workflow_dbsx_lv.setPullLoadEnable(true);
        }else{
            workflow_dbsx_lv.setPullLoadEnable(false);
        }
        for(WaitFlowEntity entity:datas){
            flowEntities.add(entity);
        }
        if(flowEntities.size()==0){
            workflow_dbsx_wsj_img.setVisibility(View.VISIBLE);
        }else {
            workflow_dbsx_wsj_img.setVisibility(View.GONE);
        }
        flowAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        wokrpersonalInternet.getWaitClGZL(String.valueOf(pageNum));
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        wokrpersonalInternet.getWaitClGZL(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        workflow_dbsx_lv.stopRefresh();
        workflow_dbsx_lv.stopLoadMore();
        workflow_dbsx_lv.setRefreshTime("刚刚");
    }
    @Override
    public void onResume() {
        super.onResume();
        pageNum=1;
        if(flowEntities.size()>0){
            flowEntities.clear();
            flowAdapter.notifyDataSetChanged();
        }
        wokrpersonalInternet.getWaitClGZL(String.valueOf(pageNum));
    }
}
