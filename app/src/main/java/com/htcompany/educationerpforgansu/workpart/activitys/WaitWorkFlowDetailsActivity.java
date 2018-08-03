package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.MyWebActivity;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.entities.WaitFlowEntity;
import com.lidroid.xutils.util.LogUtils;

/**
 * freedom
 * 待办事项详情-固定 、预定义工作流
 * Created by wrb on 2017/1/6.
 */
public class WaitWorkFlowDetailsActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView waitworkflowdetails_title_tv, waitworkflowdetails_code_tv, waitworkflowdetails_fqr_tv,
            waitworkflowdetails_time_tv, waitworkflowdetails_status_tv, waitworkflowdetails_type_tv, waitworkflowdetails_sucess_tv, waitworkflowdetails_failed_tv, waitworkflowdetails_content;
    private EditText waitworkflowdetails_msg_edt;
    private WaitFlowEntity flowEntity;
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitworkflowdetails_activity);
        initDatas();
        initViews();
        initVlaues();
        initOnclicEvents();
    }

    public void initDatas() {
        flowEntity = (WaitFlowEntity) getIntent().getSerializableExtra("flowEntity");
        waitDialog = new WaitDialog(this, "");
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this, myHandler);
    }

    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        waitworkflowdetails_title_tv = (TextView) this.findViewById(R.id.waitworkflowdetails_title_tv);
        waitworkflowdetails_code_tv = (TextView) this.findViewById(R.id.waitworkflowdetails_code_tv);
        waitworkflowdetails_fqr_tv = (TextView) this.findViewById(R.id.waitworkflowdetails_fqr_tv);
        waitworkflowdetails_time_tv = (TextView) this.findViewById(R.id.waitworkflowdetails_time_tv);
        waitworkflowdetails_status_tv = (TextView) this.findViewById(R.id.waitworkflowdetails_status_tv);
        waitworkflowdetails_type_tv = (TextView) this.findViewById(R.id.waitworkflowdetails_type_tv);

        waitworkflowdetails_msg_edt = (EditText) this.findViewById(R.id.waitworkflowdetails_msg_edt);
        waitworkflowdetails_sucess_tv = (TextView) this.findViewById(R.id.waitworkflowdetails_sucess_tv);
        waitworkflowdetails_failed_tv = (TextView) this.findViewById(R.id.waitworkflowdetails_failed_tv);
        waitworkflowdetails_content = (TextView) this.findViewById(R.id.waitworkflowdetails_content);

    }

    public void initVlaues() {
        title.setText("待办事项详情");
        if (flowEntity != null) {
            waitworkflowdetails_title_tv.setText(flowEntity.getTitle());
            waitworkflowdetails_code_tv.setText(flowEntity.getNumber());
            waitworkflowdetails_fqr_tv.setText(flowEntity.getStart_uname());
            waitworkflowdetails_time_tv.setText(flowEntity.getRunendtime());
            waitworkflowdetails_status_tv.setText(flowEntity.getRunstatus_n());
            waitworkflowdetails_type_tv.setText(flowEntity.getRuntype());

            //内容
//            waitworkflowdetails_content.setText("查看详情");
        }
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        waitworkflowdetails_sucess_tv.setOnClickListener(this);
        waitworkflowdetails_failed_tv.setOnClickListener(this);
        waitworkflowdetails_content.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.waitworkflowdetails_sucess_tv:
                //审核通过
                if ("".equals(waitworkflowdetails_msg_edt.getText().toString())) {
                    ToastUtil.showToast("请输入办理意见", WaitWorkFlowDetailsActivity.this);
                } else {
                    waitDialog.show();
                    wokrpersonalInternet.uploadSucessmsg(waitworkflowdetails_msg_edt.getText().toString(), flowEntity.getRun_id(), "2");
                }
                break;
            case R.id.waitworkflowdetails_failed_tv:
                //审核失败
                if ("".equals(waitworkflowdetails_msg_edt.getText().toString())) {
                    ToastUtil.showToast("请输入办理意见", WaitWorkFlowDetailsActivity.this);
                } else {
                    waitDialog.show();
                    wokrpersonalInternet.uploadSucessmsg(waitworkflowdetails_msg_edt.getText().toString(), flowEntity.getRun_id(), "1");
                }
                break;
            case R.id.waitworkflowdetails_content:
                //查看内容详情
//                Intent intent = new Intent(this, AllOneTeacherSelectActivity.class);
//                startActivityForResult(intent, 103);
                LogUtils.d("url=>"+flowEntity.getForm());
                MyWebActivity.startIntent(this,flowEntity.getForm());
                break;
        }
    }

    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 400:
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("请求网络失败", WaitWorkFlowDetailsActivity.this);
                    break;
                case 200:
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    //处理数据
                    if (wokrpersonalPersener.parseWaitWrokFlowUploadSucess((String) msg.obj)) {
                        ToastUtil.showToast("处理完成", WaitWorkFlowDetailsActivity.this);
                        WaitWorkFlowDetailsActivity.this.finish();
                    } else {
                        ToastUtil.showToast("请求网络异常", WaitWorkFlowDetailsActivity.this);
                    }
                    break;
            }
        }
    };
}
