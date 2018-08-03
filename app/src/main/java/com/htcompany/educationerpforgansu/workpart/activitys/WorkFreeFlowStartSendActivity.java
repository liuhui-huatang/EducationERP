package com.htcompany.educationerpforgansu.workpart.activitys;

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
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.entities.AllJZGEntity;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFlowStartSendEntity;

/**
 * 自由工作流发起界面
 * Created by wrb on 2017/4/23.
 */
public class WorkFreeFlowStartSendActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private EditText workfreeflowstartsend_title_edt,workfreeflowstartsend_nextname_tv,workfreeflowstartsend_content_tv;
    private TextView workfreeflowstartsend_nextperson_tv,workfreeflowstartsend_send_tv;

    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private AllJZGEntity jzgEntity=null;
    private String teacherUid = "";//下一步骤班里人id
    private WorkFlowStartSendEntity entity=null;//工作流实体
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workfreeflowstartsend_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity =(WorkFlowStartSendEntity)getIntent().getSerializableExtra("flowEntity");
        waitDialog = new WaitDialog(this,"");
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        workfreeflowstartsend_title_edt= (EditText) this.findViewById(R.id.workfreeflowstartsend_title_edt);
        workfreeflowstartsend_nextname_tv= (EditText) this.findViewById(R.id.workfreeflowstartsend_nextname_tv);
        workfreeflowstartsend_content_tv= (EditText) this.findViewById(R.id.workfreeflowstartsend_content_tv);
        workfreeflowstartsend_nextperson_tv=(TextView)this.findViewById(R.id.workfreeflowstartsend_nextperson_tv);
        workfreeflowstartsend_send_tv=(TextView)this.findViewById(R.id.workfreeflowstartsend_send_tv);
    }
    public void initViewValues(){
        title.setText("流程发起");
        if(entity!=null){
            workfreeflowstartsend_title_edt.setText(entity.getName());
        }

    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        workfreeflowstartsend_nextperson_tv.setOnClickListener(this);
        workfreeflowstartsend_send_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.workfreeflowstartsend_nextperson_tv:
               //选额下一步骤办里人
                Intent intent =new Intent(WorkFreeFlowStartSendActivity.this,AllOneTeacherSelectActivity.class);
                startActivityForResult(intent,103);
                break;
            case R.id.workfreeflowstartsend_send_tv:
               //发起工作流
                if("".equals(workfreeflowstartsend_title_edt.getText().toString())){
                    ToastUtil.showToast("请输入流程标题",WorkFreeFlowStartSendActivity.this);
                }else if("".equals(workfreeflowstartsend_nextname_tv.getText().toString())){
                    ToastUtil.showToast("请输入下一步骤名称",WorkFreeFlowStartSendActivity.this);
                }else if("".equals(workfreeflowstartsend_nextperson_tv.getText().toString())){
                    ToastUtil.showToast("请选择下一步骤办里人",WorkFreeFlowStartSendActivity.this);
                }else if("".equals(workfreeflowstartsend_content_tv.getText().toString())){
                    ToastUtil.showToast("请输工作内容",WorkFreeFlowStartSendActivity.this);
                }else{
                    waitDialog.show();
                    wokrpersonalInternet.uploadWorkFlowStartSendTypeList(entity.getId(),workfreeflowstartsend_title_edt.getText().toString(),
                            workfreeflowstartsend_nextname_tv.getText().toString(),
                            workfreeflowstartsend_content_tv.getText().toString(),teacherUid);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 103:
                    jzgEntity = (AllJZGEntity) data.getSerializableExtra("persondatas");
                    if(jzgEntity!=null){
                        workfreeflowstartsend_nextperson_tv.setText(jzgEntity.getUsername());
                        teacherUid=jzgEntity.getUid();
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
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("请求网络失败",WorkFreeFlowStartSendActivity.this);
                    break;
                case 200:
                    //处理数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(wokrpersonalPersener.parseWaitWrokFlowUploadSucess((String)msg.obj)){
                        ToastUtil.showToast("发起成功",WorkFreeFlowStartSendActivity.this);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新发起",WorkFreeFlowStartSendActivity.this);
                    }
                    break;
            }
        }
    };
}
