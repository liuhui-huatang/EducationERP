package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherPXEntity;

/**
 * 教师培训详情
 * Created by wrb on 2016/12/19.
 */
public class TeacherTrainDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView traindetails_xmmc_tv,traindetails_ssjh_tv,traindetails_xh_tv,traindetails_pxjd_tv,
            traindetails_starttime_tv,traindetails_endtime_tv,traindetails_fzdw_tv,traindetails_zsmcjjb_tv,
            traindetails_pxrs_tv,traindetails_status_tv;
    private TextView traindetails_add_tv;
    private TeacherPXEntity pxEntity;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachertraindetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        pxEntity = (TeacherPXEntity)getIntent().getSerializableExtra("entity");
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        traindetails_xmmc_tv= (TextView)this.findViewById(R.id.traindetails_xmmc_tv);
        traindetails_ssjh_tv= (TextView)this.findViewById(R.id.traindetails_ssjh_tv);
        traindetails_xh_tv= (TextView)this.findViewById(R.id.traindetails_xh_tv);
        traindetails_pxjd_tv= (TextView)this.findViewById(R.id.traindetails_pxjd_tv);
        traindetails_starttime_tv= (TextView)this.findViewById(R.id.traindetails_starttime_tv);
        traindetails_endtime_tv= (TextView)this.findViewById(R.id.traindetails_endtime_tv);
        traindetails_fzdw_tv= (TextView)this.findViewById(R.id.traindetails_fzdw_tv);
        traindetails_zsmcjjb_tv= (TextView)this.findViewById(R.id.traindetails_zsmcjjb_tv);
        traindetails_pxrs_tv= (TextView)this.findViewById(R.id.traindetails_pxrs_tv);
        traindetails_status_tv= (TextView)this.findViewById(R.id.traindetails_status_tv);
        traindetails_add_tv= (TextView)this.findViewById(R.id.traindetails_add_tv);
    }
    public void initViewValues(){
        title.setText("教师培训详情");
        if(pxEntity!=null){
            traindetails_xmmc_tv.setText(pxEntity.getPei_name());
            traindetails_ssjh_tv.setText(pxEntity.getName());
            traindetails_pxjd_tv.setText(pxEntity.getPei_jidi());
            traindetails_xh_tv.setText(pxEntity.getPei_xuhao());
            traindetails_starttime_tv.setText(pxEntity.getPei_atime());
            traindetails_endtime_tv.setText(pxEntity.getPei_btime());
            traindetails_fzdw_tv.setText(pxEntity.getPei_danwei());
            traindetails_zsmcjjb_tv.setText(pxEntity.getPei_con());
            traindetails_pxrs_tv.setText(pxEntity.getPei_num());
            traindetails_status_tv.setText(pxEntity.getBaoming_status());
            if("1".equals(pxEntity.getBaoming_sta())||"2".equals(pxEntity.getBaoming_sta())){
                traindetails_add_tv.setVisibility(View.GONE);
            }else{
                traindetails_add_tv.setVisibility(View.VISIBLE);
            }
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        traindetails_add_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.traindetails_add_tv:
                //报名
                if(pxEntity!=null){
                    waitDialog.show();
                    teacherInternet.uploadTeacherTrainPlanADDDatas(pxEntity.getId());
                }
                break;
        }
    }
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(teacherPersener.parserTeacherTrainPlanBMDATAS((String)msg.obj)){
                        ToastUtil.showToast("报名成功",TeacherTrainDetailsActivity.this);
                        Intent intent = new Intent();
                                setResult(100,intent);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新报名",TeacherTrainDetailsActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeacherTrainDetailsActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",TeacherTrainDetailsActivity.this);
                    break;
            }
        }
    };
}
