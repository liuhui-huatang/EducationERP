package com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters.WorkVotingManageAdapter;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.VotingManageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 投票管理
 * Created by wrb on 2016/11/22.
 * 两种状态 进行中 已结束
 */
public class WorkVotingManageActivity extends BaseActivity implements View.OnClickListener{
    private ListView workvotingmanage_lv;
    private TextView title, rightthree_btn_tv;
    private ImageView workvotingmanage_wsj_img;
    private RelativeLayout reback_btn, right_three_btn;
    private WorkVotingManageAdapter myVotingAdapter;
    private  Intent intent = null;
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    private List<VotingManageEntity> votingManageEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workvotingmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas() {
        votingManageEntities = new ArrayList<VotingManageEntity>();
        workInternet = new CommonWorkInternet(this, myHandler);
        workPersener = new CommonWorkPersener(this, myHandler);
        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        workInternet.getVotingManageListDatas();
    }
    public  void initViews(){
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        workvotingmanage_wsj_img=(ImageView)this.findViewById(R.id.workvotingmanage_wsj_img);
        workvotingmanage_lv = (ListView)this.findViewById(R.id.workvotingmanage_lv);
        myVotingAdapter = new WorkVotingManageAdapter(this,votingManageEntities);
        workvotingmanage_lv.setAdapter(myVotingAdapter);
        workvotingmanage_lv.setOnItemClickListener(votingClicer);
    }
    public void initViewValues() {
        title.setText("投票管理");
    }
    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener votingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //调到添加投票界面
                break;
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",WorkVotingManageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<VotingManageEntity> datas = workPersener.parseVotingManage_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        workvotingmanage_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",WorkVotingManageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<VotingManageEntity> datas){
        if(votingManageEntities.size()>0){
            votingManageEntities.clear();
        }
        for(VotingManageEntity entity:datas){
            votingManageEntities.add(entity);
        }
        myVotingAdapter.notifyDataSetChanged();
    }
}
