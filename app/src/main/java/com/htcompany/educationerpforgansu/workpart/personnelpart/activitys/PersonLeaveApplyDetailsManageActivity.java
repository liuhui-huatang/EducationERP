package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

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
import com.htcompany.educationerpforgansu.internet.workpersonnel.WorkPersonnelManageInternet;
import com.htcompany.educationerpforgansu.internet.workpersonnel.WorkPersonnelManagePersener;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonLeaveApplyEntity;

/**
 * 教职工请假详情
 * Created by wrb on 2017/5/8.
 */
public class PersonLeaveApplyDetailsManageActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView leaveapplydetailsmanage_name_tv,leaveapplydetailsmanage_type_tv,leaveapplydetailsmanage_starttime_tv,
            leaveapplydetailsmanage_endtime_tv,leaveapplydetailsmanage_content_tv,leaveapplydetailsmanage_shzt_tv,leaveapplydetailsmanage_agree_tv,
            leaveapplydetailsmanage_jujue_tv;
    private PersonLeaveApplyEntity applyEntity;
    //网络请求类
    private WorkPersonnelManageInternet personnelInternet;
    private WorkPersonnelManagePersener personnelPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personleaveapplydetailsmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        applyEntity = (PersonLeaveApplyEntity)getIntent().getSerializableExtra("applyEntity");
        personnelInternet = new WorkPersonnelManageInternet(this,tableHanler);
        personnelPersener = new WorkPersonnelManagePersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        leaveapplydetailsmanage_name_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_name_tv);
        leaveapplydetailsmanage_type_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_type_tv);
        leaveapplydetailsmanage_starttime_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_starttime_tv);
        leaveapplydetailsmanage_shzt_tv = (TextView)this.findViewById(R.id.leaveapplydetailsmanage_shzt_tv);
        leaveapplydetailsmanage_endtime_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_endtime_tv);
        leaveapplydetailsmanage_content_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_content_tv);
        //提交、销假
        leaveapplydetailsmanage_agree_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_agree_tv);
        leaveapplydetailsmanage_jujue_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_jujue_tv);
    }
    public void initViewValues(){
        title.setText("请假详情");
        if(applyEntity!=null){
            leaveapplydetailsmanage_name_tv.setText(applyEntity.getUsername());
            leaveapplydetailsmanage_type_tv.setText(applyEntity.getTypename());
            //        status 0,已录入，1，已提交，2：审核未通过，3：审核通过，4：已销假
            if("2".equals(applyEntity.getQj_status())){
                leaveapplydetailsmanage_shzt_tv.setTextColor(getResources().getColor(R.color.ptjs_tv3_color));
                leaveapplydetailsmanage_agree_tv.setVisibility(View.GONE);
                leaveapplydetailsmanage_jujue_tv.setVisibility(View.GONE);
            }else if("3".equals(applyEntity.getQj_status())){
                leaveapplydetailsmanage_shzt_tv.setTextColor(getResources().getColor(R.color.contacts_bs1_color));
                leaveapplydetailsmanage_jujue_tv.setVisibility(View.VISIBLE);
            }else if("1".equals(applyEntity.getQj_status())){
                leaveapplydetailsmanage_shzt_tv.setTextColor(getResources().getColor(R.color.rsgl_ht4_color));
                leaveapplydetailsmanage_agree_tv.setVisibility(View.GONE);
                leaveapplydetailsmanage_jujue_tv.setVisibility(View.GONE);
            }else if("4".equals(applyEntity.getQj_status())){
                leaveapplydetailsmanage_agree_tv.setVisibility(View.GONE);
                leaveapplydetailsmanage_jujue_tv.setVisibility(View.GONE);
            }else{
                leaveapplydetailsmanage_agree_tv.setVisibility(View.VISIBLE);
            }
            leaveapplydetailsmanage_shzt_tv.setText(applyEntity.getStatus());
            leaveapplydetailsmanage_starttime_tv.setText(applyEntity.getStart());
            leaveapplydetailsmanage_endtime_tv.setText(applyEntity.getEnd());
            leaveapplydetailsmanage_content_tv.setText(applyEntity.getReason());
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        leaveapplydetailsmanage_agree_tv.setOnClickListener(this);
        leaveapplydetailsmanage_jujue_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.leaveapplydetailsmanage_agree_tv:
                //提交
                    waitDialog.show();
                    personnelInternet.uploadPersoLeaveApplyTJList(applyEntity.getId());
                break;
            case R.id.leaveapplydetailsmanage_jujue_tv:
                //销假
                if(!"3".equals(applyEntity.getStatus())){
                    ToastUtil.showToast("只有审核通过的才能销假", PersonLeaveApplyDetailsManageActivity.this);
                }else{
                    waitDialog.show();
                    personnelInternet.uploadPersoLeaveApplyXJList(applyEntity.getId());
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
                    if(personnelPersener.parseUploadSucess((String)msg.obj)){
                        ToastUtil.showToast("操作成功",PersonLeaveApplyDetailsManageActivity.this);
                        Intent intent = new Intent();
                        setResult(100,intent);
                        finish();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",PersonLeaveApplyDetailsManageActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",PersonLeaveApplyDetailsManageActivity.this);
                    break;
            }
        }
    };
}
