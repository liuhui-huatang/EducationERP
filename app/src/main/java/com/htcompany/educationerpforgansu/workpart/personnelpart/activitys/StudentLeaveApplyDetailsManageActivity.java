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
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.StudentLeaveApplyEntity;

/**
 * 学生请假管理详情界面
 * Created by wrb on 2016/11/21.
 */
public class StudentLeaveApplyDetailsManageActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView leaveapplydetailsmanage_name_tv,leaveapplydetailsmanage_type_tv,leaveapplydetailsmanage_starttime_tv,
            leaveapplydetailsmanage_endtime_tv,leaveapplydetailsmanage_content_tv,leaveapplydetailsmanage_shzt_tv,leaveapplydetailsmanage_agree_tv,
            leaveapplydetailsmanage_jujue_tv,leaveapplydetailsmanage_xjzt_tv;
    private StudentLeaveApplyEntity applyEntity;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
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
        applyEntity = (StudentLeaveApplyEntity)getIntent().getSerializableExtra("applyEntity");
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
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
        leaveapplydetailsmanage_xjzt_tv=(TextView)this.findViewById(R.id.leaveapplydetailsmanage_xjzt_tv);
        //提交、销假
        leaveapplydetailsmanage_agree_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_agree_tv);
        leaveapplydetailsmanage_jujue_tv= (TextView)this.findViewById(R.id.leaveapplydetailsmanage_jujue_tv);
    }
    public void initViewValues(){
        title.setText("请假详情");
        if(applyEntity!=null){
            leaveapplydetailsmanage_name_tv.setText(applyEntity.getTruename());
                leaveapplydetailsmanage_type_tv.setText(applyEntity.getTrbd_type());
            //        status 3审核通过，2审核未通过，1已提交
            if("2".equals(applyEntity.getStatus())){
                leaveapplydetailsmanage_shzt_tv.setTextColor(getResources().getColor(R.color.ptjs_tv3_color));
                leaveapplydetailsmanage_agree_tv.setVisibility(View.GONE);
                leaveapplydetailsmanage_jujue_tv.setVisibility(View.GONE);
            }else if("3".equals(applyEntity.getStatus())){
                leaveapplydetailsmanage_shzt_tv.setTextColor(getResources().getColor(R.color.contacts_bs1_color));
                if("1".equals(applyEntity.getXjstatus())) {
                    leaveapplydetailsmanage_agree_tv.setVisibility(View.GONE);
                    leaveapplydetailsmanage_jujue_tv.setVisibility(View.GONE);
                }else{
                    leaveapplydetailsmanage_agree_tv.setVisibility(View.GONE);
                    leaveapplydetailsmanage_jujue_tv.setVisibility(View.VISIBLE);
                }
            }else if("1".equals(applyEntity.getStatus())){
                leaveapplydetailsmanage_shzt_tv.setTextColor(getResources().getColor(R.color.rsgl_ht4_color));
            }else{
                leaveapplydetailsmanage_agree_tv.setVisibility(View.VISIBLE);
            }
            leaveapplydetailsmanage_xjzt_tv.setText(applyEntity.getTrbd_xjstatus());
            leaveapplydetailsmanage_shzt_tv.setText(applyEntity.getTrbd_qjstatus());
            leaveapplydetailsmanage_starttime_tv.setText(applyEntity.getStart_time());
            leaveapplydetailsmanage_endtime_tv.setText(applyEntity.getEnd_time());
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
                teacherInternet.uploadStudentLeaveRecordsDatas(applyEntity.getId());
                break;
            case R.id.leaveapplydetailsmanage_jujue_tv:
                //销假
                if(!"3".equals(applyEntity.getStatus())){
                   ToastUtil.showToast("只有审核通过的才能销假", StudentLeaveApplyDetailsManageActivity.this);
                }else{
                    waitDialog.show();
                    teacherInternet.xiaojiaStudentLeaveRecordsDatas(applyEntity.getId());
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
                    if(teacherPersener.parserJXHD_ANSWERDATAS((String)msg.obj)){
                        ToastUtil.showToast("操作成功",StudentLeaveApplyDetailsManageActivity.this);
                        Intent intent = new Intent();
                        setResult(100,intent);
                        finish();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentLeaveApplyDetailsManageActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",StudentLeaveApplyDetailsManageActivity.this);
                    break;
            }
        }
    };
}
