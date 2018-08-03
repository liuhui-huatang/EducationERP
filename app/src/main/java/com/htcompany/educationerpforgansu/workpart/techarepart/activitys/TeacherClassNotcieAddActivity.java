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
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.activitys.AllOneClassSelectActivity;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNotcieEntity;

/**
 * 教师班级公告添加
 * Created by wrb on 2016/11/29.
 */
public class TeacherClassNotcieAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView classnotcieadd_time_tv,classnotcieadd_xzb_tv;
    private EditText classnotcieadd_title_edt,classnotcieadd_content_edt;
    private TextView classnotcieadd_upload_tv;//提交
    private DateViewDailog dateViewDailog;
    private ClassNotcieEntity notcieEntity;
    private String classkey="";
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherclassnotcieadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicerEvent();
    }
    public void initDatas() {
        teacherInternet = new WorkTeacherInternet(this, myHandler);
        teacherPersener = new WorkTeacherPersener(this, myHandler);
        waitDialog = new WaitDialog(this, "");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        classnotcieadd_time_tv= (TextView)this.findViewById(R.id.classnotcieadd_time_tv);
        classnotcieadd_title_edt=(EditText)this.findViewById(R.id.classnotcieadd_title_edt);
        classnotcieadd_content_edt=(EditText)this.findViewById(R.id.classnotcieadd_content_edt);
        classnotcieadd_upload_tv = (TextView)this.findViewById(R.id.classnotcieadd_upload_tv);
        classnotcieadd_xzb_tv=(TextView)this.findViewById(R.id.classnotcieadd_xzb_tv);
    }
    public void initViewValues(){
        title.setText("班级公告添加");
    }
    public void initOnclicerEvent(){
        reback_btn.setOnClickListener(this);
        classnotcieadd_time_tv.setOnClickListener(this);
        classnotcieadd_upload_tv.setOnClickListener(this);
        classnotcieadd_xzb_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.classnotcieadd_time_tv:
                dateViewDailog = new DateViewDailog(TeacherClassNotcieAddActivity.this,1000,myHandler,false);
                dateViewDailog.show();
                break;
            case R.id.classnotcieadd_xzb_tv:
                Intent intent = new Intent(TeacherClassNotcieAddActivity.this, AllOneClassSelectActivity.class);
                startActivityForResult(intent,101);
                break;
            case R.id.classnotcieadd_upload_tv:
                //提交
                if("".equals(classnotcieadd_title_edt.getText().toString())){
                    ToastUtil.showToast("请输入公告标题",TeacherClassNotcieAddActivity.this);
                }else if("".equals(classnotcieadd_xzb_tv.getText().toString())){
                    ToastUtil.showToast("请选择班级",TeacherClassNotcieAddActivity.this);
                }else if("".equals(classnotcieadd_content_edt.getText().toString())){
                    ToastUtil.showToast("请输入发布内容",TeacherClassNotcieAddActivity.this);
                }else{
                    waitDialog.show();
                    teacherInternet.addClassNoticeListDatas(classnotcieadd_title_edt.getText().toString(),
                            BaseUtils.getSystemDate(),classnotcieadd_content_edt.getText().toString(),classkey);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    AllClassEntity entity=(AllClassEntity) data.getSerializableExtra("entity");
                    if(entity!=null){
                        classnotcieadd_xzb_tv.setText(entity.getName());
                        classkey=entity.getOnecode();
                    }
                    break;
            }
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    classnotcieadd_time_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(teacherPersener.parserJXHD_ANSWERDATAS((String)msg.obj)) {
                        Intent intent1 = new Intent();
                        setResult(101, intent1);
                        finish();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeacherClassNotcieAddActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",TeacherClassNotcieAddActivity.this);
                    break;
            }
        }
    };
}
