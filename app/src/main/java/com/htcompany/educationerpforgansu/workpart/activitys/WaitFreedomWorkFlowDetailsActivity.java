package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
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
import com.htcompany.educationerpforgansu.workpart.entities.AllJZGEntity;
import com.htcompany.educationerpforgansu.workpart.entities.WaitFlowEntity;
import com.lidroid.xutils.util.LogUtils;

/**
 * 自由工作流详情
 * Created by wrb on 2017/1/6.
 */
public class WaitFreedomWorkFlowDetailsActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView waitfreedomworkflowdetails_title_tv, waitfreedomworkflowdetails_code_tv, waitfreedomworkflowdetails_fqr_tv,
            waitfreedomworkflowdetails_time_tv, waitfreedomworkflowdetails_status_tv, waitfreedomworkflowdetails_type_tv, waitfreedomworkflowdetails_nextperson_tv, waitfreedomworkflowdetails_tgnext_tv, waitfreedomworkflowdetails_tgover_tv, waitfreedomworkflowdetails_btgover_tv, aitfreedomworkflowdetails_content;
    private EditText waitfreedomworkflowdetails_nextname_tv, waitfreedomworkflowdetails_blyj_tv;


    private WaitFlowEntity flowEntity;
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitfreedomworkflowdetails_activity);
        initDatas();
        initViews();
        initViewValues();
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

        waitfreedomworkflowdetails_title_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_title_tv);
        waitfreedomworkflowdetails_code_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_code_tv);
        waitfreedomworkflowdetails_fqr_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_fqr_tv);
        waitfreedomworkflowdetails_time_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_time_tv);
        waitfreedomworkflowdetails_status_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_status_tv);
        waitfreedomworkflowdetails_type_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_type_tv);
        waitfreedomworkflowdetails_nextperson_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_nextperson_tv);
        waitfreedomworkflowdetails_nextname_tv = (EditText) this.findViewById(R.id.waitfreedomworkflowdetails_nextname_tv);
        waitfreedomworkflowdetails_blyj_tv = (EditText) this.findViewById(R.id.waitfreedomworkflowdetails_blyj_tv);

        waitfreedomworkflowdetails_tgnext_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_tgnext_tv);
        waitfreedomworkflowdetails_tgover_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_tgover_tv);
        waitfreedomworkflowdetails_btgover_tv = (TextView) this.findViewById(R.id.waitfreedomworkflowdetails_btgover_tv);
        aitfreedomworkflowdetails_content = (TextView) this.findViewById(R.id.aitfreedomworkflowdetails_content);

    }

    public void initViewValues() {
        title.setText("待办事项详情");
        if (flowEntity != null) {
            waitfreedomworkflowdetails_title_tv.setText(flowEntity.getTitle());
            waitfreedomworkflowdetails_code_tv.setText(flowEntity.getNumber());
            waitfreedomworkflowdetails_fqr_tv.setText(flowEntity.getStart_uname());
            waitfreedomworkflowdetails_time_tv.setText(flowEntity.getRunendtime());
            waitfreedomworkflowdetails_status_tv.setText(flowEntity.getRunstatus_n());
            waitfreedomworkflowdetails_type_tv.setText(flowEntity.getRuntype());

            //内容
//            aitfreedomworkflowdetails_content.setText("查看详情");
        }
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        waitfreedomworkflowdetails_tgnext_tv.setOnClickListener(this);
        waitfreedomworkflowdetails_tgover_tv.setOnClickListener(this);
        waitfreedomworkflowdetails_btgover_tv.setOnClickListener(this);
        waitfreedomworkflowdetails_nextperson_tv.setOnClickListener(this);
        aitfreedomworkflowdetails_content.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.waitfreedomworkflowdetails_tgnext_tv:
                //通过下一步
                if ("".equals(waitfreedomworkflowdetails_nextname_tv.getText().toString())) {
                    ToastUtil.showToast("输入下一步骤名称", WaitFreedomWorkFlowDetailsActivity.this);
                } else if ("".equals(waitfreedomworkflowdetails_nextperson_tv.getText().toString())) {
                    ToastUtil.showToast("请选择下一步办理人", WaitFreedomWorkFlowDetailsActivity.this);
                } else if ("".equals(waitfreedomworkflowdetails_blyj_tv.getText().toString())) {
                    ToastUtil.showToast("输入办理意见", WaitFreedomWorkFlowDetailsActivity.this);
                } else {
                    waitDialog.show();
                    wokrpersonalInternet.freeFlowGzliu_TGNEXT(waitfreedomworkflowdetails_blyj_tv.getText().toString(),
                            flowEntity.getRun_id(), waitfreedomworkflowdetails_nextname_tv.getText().toString(), uid);
                }
                break;
            case R.id.waitfreedomworkflowdetails_tgover_tv:
                //通过结束
                if ("".equals(waitfreedomworkflowdetails_blyj_tv.getText().toString())) {
                    ToastUtil.showToast("输入办理意见", WaitFreedomWorkFlowDetailsActivity.this);
                } else {
                    waitDialog.show();
                    wokrpersonalInternet.freeFlowGzliu_TGOVER(waitfreedomworkflowdetails_blyj_tv.getText().toString(), flowEntity.getRun_id());
                }
                break;
            case R.id.waitfreedomworkflowdetails_btgover_tv:
                //不通过结束
                if ("".equals(waitfreedomworkflowdetails_blyj_tv.getText().toString())) {
                    ToastUtil.showToast("输入办理意见", WaitFreedomWorkFlowDetailsActivity.this);
                } else {
                    waitDialog.show();
                    wokrpersonalInternet.freeFlowGzliu_BTGOVER(waitfreedomworkflowdetails_blyj_tv.getText().toString(), flowEntity.getRun_id());
                }
                break;
            case R.id.waitfreedomworkflowdetails_nextperson_tv:
                //选择下一步办里人
                Intent intent = new Intent(this, AllOneTeacherSelectActivity.class);
                startActivityForResult(intent, 103);
                break;
            case R.id.aitfreedomworkflowdetails_content:
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
                    ToastUtil.showToast("请求网络失败", WaitFreedomWorkFlowDetailsActivity.this);
                    break;
                case 200:
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    //处理数据
                    if (wokrpersonalPersener.parseWaitWrokFlowUploadSucess((String) msg.obj)) {
                        ToastUtil.showToast("处理完成", WaitFreedomWorkFlowDetailsActivity.this);
                        WaitFreedomWorkFlowDetailsActivity.this.finish();
                    } else {
                        ToastUtil.showToast("请求网络异常", WaitFreedomWorkFlowDetailsActivity.this);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode) {
                case 103:
                    AllJZGEntity jzgEntity = (AllJZGEntity) data.getSerializableExtra("persondatas");
                    if (jzgEntity != null) {
                        waitfreedomworkflowdetails_nextperson_tv.setText(jzgEntity.getUsername());
                        uid = jzgEntity.getUid();
                    }
                    break;
            }
        }
    }
}
