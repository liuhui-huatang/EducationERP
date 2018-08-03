package com.htcompany.educationerpforgansu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFlowStartSendEntity;
import com.lidroid.xutils.http.RequestParams;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 发起请假
 * Created by wrb on 2016/12/30.
 */
public class MyVacationActivity extends BaseActivity implements View.OnClickListener {
    WorkFlowStartSendEntity entity;

    public static void startIntent(Activity activity, WorkFlowStartSendEntity entity) {
        Intent intent = new Intent(activity, MyVacationActivity.class);
        intent.putExtra("flowEntity", entity);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation);
        entity = (WorkFlowStartSendEntity) getIntent().getSerializableExtra("flowEntity");
        initViews();
    }

    TextView tv_title, tv_start, tv_end;
    EditText et_text;

    public void initViews() {
        TextView tle = (TextView) this.findViewById(R.id.title);
        tle.setText("请假和外出");

        tv_title = (TextView) this.findViewById(R.id.tv_title);
        et_text = (EditText) this.findViewById(R.id.et_text);
        tv_start = (TextView) this.findViewById(R.id.tv_start);
        tv_start.setOnClickListener(this);
        tv_end = (TextView) this.findViewById(R.id.tv_end);
        tv_end.setOnClickListener(this);

        tv_title.setOnClickListener(this);
        this.findViewById(R.id.btn_send).setOnClickListener(this);
        this.findViewById(R.id.reback_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title:

                final String[] btString = new String[]{"事假", "病假", "因公外出", "婚假", "产假", "丧假"};
                PopWindowUtil.init().show(this, R.id.layout_vacation, btString, new PopWindowUtil.OnPopupWindowClickLinstener() {

                    @Override
                    public void popClick(Button popButton) {
                        String btStr = popButton.getText().toString();
                        tv_title.setText(btStr);

                    }
                });

                break;
            case R.id.btn_send:
                String title = tv_title.getText().toString().trim();
                String text = et_text.getText().toString().trim();
                String stime = tv_start.getText().toString().trim();
                String etime = tv_end.getText().toString().trim();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(text) || TextUtils.isEmpty(stime) || TextUtils.isEmpty(etime)) {
                    ToastUtil.showToast(this, "信息填写不完整");
                    return;
                }
                uploadQinjia(title, text, stime, etime);
                break;
            case R.id.tv_start:
                TimePickerView pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
//                        if (BaseUtils.DateCompare(BaseUtils.getTime(date), BaseUtils.getSystemTime())) {
//                            tv_start.setText(BaseUtils.getTime(date));
//                        } else {
//                            ToastUtil.showToast("开始时间必须大于当前时间", MyVacationActivity.this);
//                        }
                        tv_start.setText(BaseUtils.getTime(date));
                    }
                }).setType(new boolean[]{true, true, true, true, true, false})
                        .setLabel("", "", "", "点", "分", "")
                        .isCenterLabel(false)
                        .isCyclic(true)
                        .setDividerColor(Color.DKGRAY)
                        .setContentSize(21)
                        .setTitleText("选择时间")
                        .setTitleColor(Color.BLUE)
                        .setTitleBgColor(Color.WHITE)
                        .isDialog(true)
                        .build();
                pickerView.show();
                break;
            case R.id.tv_end:
                if ("".equals(tv_start.getText().toString())) {
                    ToastUtil.showToast("请先选择开始时间", MyVacationActivity.this);
                } else {
                    pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            if (BaseUtils.DateCompare(BaseUtils.getTime(date), tv_start.getText().toString())) {
                                tv_end.setText(BaseUtils.getTime(date));
                            } else {
                                ToastUtil.showToast("结束时间必须大于开始时间", MyVacationActivity.this);
                            }
                        }
                    }).setType(new boolean[]{true, true, true, true, true, false})
                            .setLabel("", "", "", "点", "分", "")
                            .isCenterLabel(false)
                            .isCyclic(true)
                            .setDividerColor(Color.DKGRAY)
                            .setContentSize(21)
                            .setTitleText("选择时间")
                            .setTitleColor(Color.BLUE)
                            .setTitleBgColor(Color.WHITE)
                            .isDialog(true)
                            .build();
                    pickerView.show();
                }
                break;
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }

    /**
     * 提交请假申请
     */
    WaitDialog waitDialog;

    public void uploadQinjia(String title, String content, String stime, String etime) {
        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
//        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(this, "login");
//        RequestParams params = new RequestParams();
//        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
//        params.addBodyParameter("title",title);
//        params.addBodyParameter("content",content);
//        params.addBodyParameter("start_time",stime);
//        params.addBodyParameter("end_time",etime);
//        params.addBodyParameter("process_id",entity.getId());
//        params.addBodyParameter("isshouji","1");
//        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.siteURLIP+"/api/process/qingjia"),200,params,myHandler);

        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(this, "login");
        OkHttpUtils.post()
                .url(InterfaceManager.siteURLIP + "/api/process/qingjia")
                .addParams("token", sharedPrefUtil.getString("token", ""))
                .addParams("title", title)
                .addParams("content", content)
                .addParams("title", title)
                .addParams("start_time", stime)
                .addParams("end_time", etime)
                .addParams("process_id", entity.getId())
                .addParams("isshouji", "1")
                .build()
                .execute(new Callback<String>() {

                    @Override
                    public String parseNetworkResponse(Response response, int id) throws Exception {
                        return response.body().string();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d("onError", e.getMessage());
                        if (waitDialog != null) {
                            waitDialog.dismiss();
                        }
                        ToastUtil.showToast("请求网络失败", MyVacationActivity.this);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("onResponse", response);
                        if (waitDialog != null) {
                            waitDialog.dismiss();
                        }
                        Bean bean = JsonUtils.getObject(response, Bean.class);
                        if ("0".equals(bean.getCode())) {
                            //处理数据
                            ToastUtil.showToast(MyVacationActivity.this, bean.getMsg());
                            finish();
                        } else {
                            ToastUtil.showToast(MyVacationActivity.this, bean.getMsg());
                        }

                    }
                });
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
                    ToastUtil.showToast("请求网络失败", MyVacationActivity.this);
                    break;
                case 200:
                    //处理数据
                    ToastUtil.showToast(MyVacationActivity.this, "您的请假申请发起成功，请等待处理");
                    finish();
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    break;
            }
        }
    };

}
