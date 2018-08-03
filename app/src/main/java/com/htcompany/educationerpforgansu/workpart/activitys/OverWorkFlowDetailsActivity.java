package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.OverWorkFlowDetailsExapnAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.OverFlowItemEntity;
import com.htcompany.educationerpforgansu.workpart.entities.OverFlowStepEntity;
import com.htcompany.educationerpforgansu.workpart.entities.WaitFlowEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 已办工作流详情界面
 * Created by wrb on 2017/4/23.
 */
public class OverWorkFlowDetailsActivity extends BaseActivity implements View.OnClickListener{
   private TextView title;
    private RelativeLayout rebakc_btn;
   private ExpandableListView overflowdetial_lv;
   private OverWorkFlowDetailsExapnAdapter detailsExapnAdapter;
    private WaitFlowEntity entity=null;
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private List<OverFlowStepEntity> flowEntities;
    private List<List<OverFlowItemEntity>> childDatas;
    private WaitDialog waitDialog;
    private String flowid="",run_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overworkflowdetails_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnClicEvent();
    }
    public void initDatas(){
        childDatas = new ArrayList<List<OverFlowItemEntity>>();
        entity = (WaitFlowEntity) getIntent().getSerializableExtra("flowEntity");
        if(entity!=null){
            flowid=entity.getFlow_id();
            run_id=entity.getRun_id();
        }else{
            //推送获取
            flowid=getIntent().getStringExtra("flowid");
            run_id=getIntent().getStringExtra("runid");
        }
        waitDialog = new WaitDialog(this,"");
        flowEntities = new ArrayList<OverFlowStepEntity>();
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        waitDialog.show();
        wokrpersonalInternet.getOverDetialsList(flowid,run_id);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        rebakc_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        overflowdetial_lv=(ExpandableListView)this.findViewById(R.id.overflowdetial_lv);
        overflowdetial_lv.setGroupIndicator(null);
        detailsExapnAdapter=new OverWorkFlowDetailsExapnAdapter(this,flowEntities,childDatas);
        overflowdetial_lv.setAdapter(detailsExapnAdapter);
    }
    public void initViewVlaues(){
        title.setText("详情");
    }
    public void initOnClicEvent(){
        rebakc_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
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
                    ToastUtil.showToast("请求网络失败",OverWorkFlowDetailsActivity.this);
                    break;
                case 200:
                    //处理数据
                    List<OverFlowStepEntity> datas= wokrpersonalPersener.parseOverWrokFlowDetials((String) msg.obj);
                    if(datas!=null&&datas.size()>0){
                        if(flowEntities.size()>0){
                            flowEntities.clear();
                        }
                        for(OverFlowStepEntity entity:datas){
                            flowEntities.add(entity);
                            List<OverFlowItemEntity> itemEntities = new ArrayList<OverFlowItemEntity>();
                            if(entity.getUsers()!=null&&entity.getUsers().size()>0){
                                itemEntities.addAll(entity.getUsers());
                            }
                            childDatas.add(itemEntities);
                        }
                        detailsExapnAdapter.notifyDataSetChanged();
                        for(int i = 0; i < detailsExapnAdapter.getGroupCount(); i++){
                            overflowdetial_lv.expandGroup(i);
                        }
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
            }
        }
    };
}
