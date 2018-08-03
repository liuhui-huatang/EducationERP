package com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkCarEntity;

/**
 * 车辆使用详情界面
 * Created by wrb on 2016/11/22.
 */
public class WorkCarsUseDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private WorkCarEntity carEntity;
    private TextView workcarusedetail_sqrname_tv,workcarusedetail_carname_tv,workcarusedetail_carcode_tv,workcarusedetail_sqtime_tv,
            workcarusedetail_starttime_tv,workcarusedetail_endtime_tv,workcarusedetail_mdd_tv,workcarusedetail_lcs_tv,
            workcarusedetail_driver_tv,workcarusedetail_content_tv;
    private EditText workcarusedetail_notagrees_edt;
    private TextView workcarusedetail_tg_edt,workcarusedetail_btg_edt;
    private LinearLayout workcarusedetail_caozuo_ll;
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workcarsusedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        carEntity = (WorkCarEntity) getIntent().getSerializableExtra("carEntity");
        workInternet = new CommonWorkInternet(this,myHandler);
        workPersener = new CommonWorkPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        workcarusedetail_sqrname_tv = (TextView)this.findViewById(R.id.workcarusedetail_sqrname_tv);
        workcarusedetail_carname_tv = (TextView)this.findViewById(R.id.workcarusedetail_carname_tv);
        workcarusedetail_carcode_tv = (TextView)this.findViewById(R.id.workcarusedetail_carcode_tv);
        workcarusedetail_sqtime_tv = (TextView)this.findViewById(R.id.workcarusedetail_sqtime_tv);
        workcarusedetail_starttime_tv = (TextView)this.findViewById(R.id.workcarusedetail_starttime_tv);
        workcarusedetail_endtime_tv = (TextView)this.findViewById(R.id.workcarusedetail_endtime_tv);
        workcarusedetail_mdd_tv = (TextView)this.findViewById(R.id.workcarusedetail_mdd_tv);
        workcarusedetail_lcs_tv = (TextView)this.findViewById(R.id.workcarusedetail_lcs_tv);
        workcarusedetail_driver_tv = (TextView)this.findViewById(R.id.workcarusedetail_driver_tv);
        workcarusedetail_content_tv = (TextView)this.findViewById(R.id.workcarusedetail_content_tv);

        workcarusedetail_notagrees_edt=(EditText)this.findViewById(R.id.workcarusedetail_notagrees_edt);
        workcarusedetail_tg_edt= (TextView)this.findViewById(R.id.workcarusedetail_tg_edt);
        workcarusedetail_btg_edt= (TextView)this.findViewById(R.id.workcarusedetail_btg_edt);
        workcarusedetail_caozuo_ll=(LinearLayout)this.findViewById(R.id.workcarusedetail_caozuo_ll);
    }
    public void initViewValues(){
        title.setText("车辆使用详情");
        if(carEntity!=null){
            workcarusedetail_sqrname_tv.setText(carEntity.getApplyer());
            workcarusedetail_carname_tv.setText(carEntity.getName());
            workcarusedetail_carcode_tv.setText(carEntity.getNumber());
//            workcarusedetail_sqtime_tv.setText(carEntity.getCarsqtime());
            workcarusedetail_starttime_tv.setText(carEntity.getUc_start_time());
            workcarusedetail_endtime_tv.setText(carEntity.getUc_end_time());
            workcarusedetail_mdd_tv.setText(carEntity.getUc_end_place());
            workcarusedetail_lcs_tv.setText(carEntity.getUc_mileage());
            workcarusedetail_driver_tv.setText(carEntity.getUsername());
            workcarusedetail_content_tv.setText(carEntity.getUc_resion());
            workcarusedetail_notagrees_edt.setText(carEntity.getUc_audit_resion());
//            0已录入，1已申请，2通过审核，3已归还，4.已取消，5未通过申请，6已结束
           if("2".equals(carEntity.getUc_status_key())){
                workcarusedetail_caozuo_ll.setVisibility(View.GONE);
               workcarusedetail_notagrees_edt.setEnabled(false);
            }else if("3".equals(carEntity.getUc_status_key())){
                workcarusedetail_caozuo_ll.setVisibility(View.GONE);
               workcarusedetail_notagrees_edt.setEnabled(false);
            }else if("4".equals(carEntity.getUc_status_key())){
                workcarusedetail_caozuo_ll.setVisibility(View.GONE);
               workcarusedetail_notagrees_edt.setEnabled(false);
            }else if("5".equals(carEntity.getUc_status_key())){
                workcarusedetail_caozuo_ll.setVisibility(View.GONE);
               workcarusedetail_notagrees_edt.setEnabled(false);
            }else if("6".equals(carEntity.getUc_status_key())){
                workcarusedetail_caozuo_ll.setVisibility(View.GONE);
               workcarusedetail_notagrees_edt.setEnabled(false);
            }
        }

    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        workcarusedetail_tg_edt.setOnClickListener(this);
        workcarusedetail_btg_edt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.workcarusedetail_tg_edt:
              //审核通过
                waitDialog.show();
                workInternet.shCarsUseDatas(carEntity.getId(),"1",workcarusedetail_notagrees_edt.getText().toString());
                break;
            case R.id.workcarusedetail_btg_edt:
               //审核不通过
               if("".equals(workcarusedetail_btg_edt.getText().toString())){
                   ToastUtil.showToast("请输入不通过理由",WorkCarsUseDetailsActivity.this);
               }else{
                   waitDialog.show();
                   workInternet.shCarsUseDatas(carEntity.getId(),"0",workcarusedetail_notagrees_edt.getText().toString());
               }
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
                    ToastUtil.showToast("连接超时",WorkCarsUseDetailsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(workPersener.parserDeleteWorkNotice((String)msg.obj)){
                        Intent intent = new Intent();
                        setResult(101,intent);
                        finish();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",WorkCarsUseDetailsActivity.this);
                    break;
            }
        }
    };
}
