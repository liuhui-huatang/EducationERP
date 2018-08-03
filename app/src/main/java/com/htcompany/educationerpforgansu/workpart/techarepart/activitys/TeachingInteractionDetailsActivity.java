package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingInteractionEntity;


/**
 * 教学互动详情
 * Created by wrb on 2016/11/22.
 */
public class TeachingInteractionDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TeachingInteractionEntity interactionEntity;

    private TextView interactiondetails_kc_tv,interactiondetails_type_tv,interactiondetails_content_tv,interactiondetails_twr_tv,
            interactiondetails_twrq_tv,interactiondetails_upload_tv;
    private EditText interactiondetails_wtjd_edt;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachinginteractiondetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        interactionEntity = (TeachingInteractionEntity) getIntent().getSerializableExtra("entity");
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);

        interactiondetails_kc_tv= (TextView)this.findViewById(R.id.interactiondetails_kc_tv);
        interactiondetails_type_tv= (TextView)this.findViewById(R.id.interactiondetails_type_tv);
        interactiondetails_content_tv= (TextView)this.findViewById(R.id.interactiondetails_content_tv);
        interactiondetails_twr_tv= (TextView)this.findViewById(R.id.interactiondetails_twr_tv);
        interactiondetails_twrq_tv= (TextView)this.findViewById(R.id.interactiondetails_twrq_tv);
        interactiondetails_upload_tv= (TextView)this.findViewById(R.id.interactiondetails_upload_tv);
        interactiondetails_wtjd_edt = (EditText)this.findViewById(R.id.interactiondetails_wtjd_edt);
    }
    public void initViewValues(){
        title.setText("详情");
        if(interactionEntity!=null){
            interactiondetails_kc_tv.setText("课程:"+interactionEntity.getKecheng());
            if("0".equals(interactionEntity.getIs_yes())){
                if("".equals(interactionEntity.getQuestion_da())){
                    //未回答
                    interactiondetails_type_tv.setText("待解答");
                    interactiondetails_type_tv.setTextColor(getResources().getColor(R.color.ptjs_tv3_color));
                    interactiondetails_wtjd_edt.setText(interactionEntity.getQuestion_da());
                    interactiondetails_wtjd_edt.setFocusable(true);
                    interactiondetails_upload_tv.setVisibility(View.VISIBLE);
                }else{
                    //已回答
                    interactiondetails_type_tv.setText("已回答");
                    interactiondetails_type_tv.setTextColor(getResources().getColor(R.color.contacts_bs1_color));
                    interactiondetails_wtjd_edt.setText(interactionEntity.getQuestion_da());
                    interactiondetails_wtjd_edt.setFocusable(false);
                    interactiondetails_upload_tv.setVisibility(View.GONE);
                }
            }else if("2".equals(interactionEntity.getIs_yes())){
                interactiondetails_type_tv.setText("未解决");
                interactiondetails_type_tv.setTextColor(getResources().getColor(R.color.ptjs_tv3_color));
                interactiondetails_wtjd_edt.setText(interactionEntity.getQuestion_da());
                interactiondetails_wtjd_edt.setFocusable(false);
                interactiondetails_upload_tv.setVisibility(View.GONE);
            }else if("1".equals(interactionEntity.getIs_yes())){
                interactiondetails_type_tv.setText("已解决");
                interactiondetails_type_tv.setTextColor(getResources().getColor(R.color.contacts_bs1_color));
                interactiondetails_wtjd_edt.setText(interactionEntity.getQuestion_da());
                interactiondetails_wtjd_edt.setFocusable(false);
                interactiondetails_upload_tv.setVisibility(View.GONE);
            }else if("3".equals(interactionEntity.getIs_yes())){
                if("".equals(interactionEntity.getQuestion_da())){
                    //未回答
                    interactiondetails_type_tv.setText("待解答");
                    interactiondetails_type_tv.setTextColor(getResources().getColor(R.color.ptjs_tv3_color));
                    interactiondetails_wtjd_edt.setText(interactionEntity.getQuestion_da());
                    interactiondetails_wtjd_edt.setFocusable(true);
                    interactiondetails_upload_tv.setVisibility(View.VISIBLE);
                }else{
                    //已回答
                    interactiondetails_type_tv.setText("已回答");
                    interactiondetails_type_tv.setTextColor(getResources().getColor(R.color.contacts_bs1_color));
                    interactiondetails_wtjd_edt.setText(interactionEntity.getQuestion_da());
                    interactiondetails_wtjd_edt.setFocusable(false);
                    interactiondetails_upload_tv.setVisibility(View.GONE);
                }


            }
            interactiondetails_content_tv.setText(interactionEntity.getQuestion());
            interactiondetails_twr_tv.setText("提问人:"+interactionEntity.getStu_username());
            interactiondetails_twrq_tv.setText("提问日期:"+interactionEntity.getTwtime());
            interactiondetails_wtjd_edt.setText(interactionEntity.getQuestion_da());
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        interactiondetails_upload_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.interactiondetails_upload_tv:
                //提交
                if("".equals(interactiondetails_wtjd_edt.getText().toString())) {
                    ToastUtil.showToast("请输入回答",TeachingInteractionDetailsActivity.this);
                }else {
                    waitDialog.show();
                    teacherInternet.uploadJXHD_ANSWERS(interactionEntity.getId(), interactiondetails_wtjd_edt.getText().toString());
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
                        interactionEntity.setIs_yes("3");
                        interactionEntity.setQuestion_da(interactiondetails_wtjd_edt.getText().toString());
                        Intent intent = new Intent();
                        intent.putExtra("entity",interactionEntity);
                        setResult(101,intent);
                        finish();
                    }else{
                        ToastUtil.showToast("提交失败，请重新提交",TeachingInteractionDetailsActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingInteractionDetailsActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",TeachingInteractionDetailsActivity.this);
                    break;
            }
        }
    };

}
