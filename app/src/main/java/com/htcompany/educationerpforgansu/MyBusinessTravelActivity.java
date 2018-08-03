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
import com.htcompany.educationerpforgansu.loginpart.LoginBean;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFlowStartSendEntity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 发起公务出差流程
 * Created by wrb on 2016/12/30.
 */
public class MyBusinessTravelActivity extends BaseActivity implements View.OnClickListener {
    WorkFlowStartSendEntity entity;

    public static void startIntent(Activity activity, WorkFlowStartSendEntity entity) {
        Intent intent = new Intent(activity, MyBusinessTravelActivity.class);
        intent.putExtra("flowEntity", entity);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        entity = (WorkFlowStartSendEntity) getIntent().getSerializableExtra("flowEntity");
        initViews();
    }

    TextView tv_title, tv_start, tv_end;
    EditText et_name, et_text, et_yiju, et_location, et_danwei, et_gongju, et_money, et_beizhu;

    public void initViews() {
        TextView tle = (TextView) this.findViewById(R.id.title);
        tle.setText("公务出差");
        this.findViewById(R.id.reback_btn).setOnClickListener(this);
        tv_start = (TextView) this.findViewById(R.id.tv_start);
        tv_start.setOnClickListener(this);
        tv_end = (TextView) this.findViewById(R.id.tv_end);
        tv_end.setOnClickListener(this);

        et_name = (EditText) this.findViewById(R.id.et_name);
        et_text = (EditText) this.findViewById(R.id.et_text);
        et_yiju = (EditText) this.findViewById(R.id.et_yiju);
        et_location = (EditText) this.findViewById(R.id.et_location);
        et_danwei = (EditText) this.findViewById(R.id.et_danwei);
        et_gongju = (EditText) this.findViewById(R.id.et_gongju);
        et_money = (EditText) this.findViewById(R.id.et_money);
        et_beizhu = (EditText) this.findViewById(R.id.et_beizhu);

        this.findViewById(R.id.btn_send).setOnClickListener(this);
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
                String stime = tv_start.getText().toString().trim();
                String etime = tv_end.getText().toString().trim();

                String name = et_name.getText().toString().trim();
                String text = et_text.getText().toString().trim();
                String yiju = et_yiju.getText().toString().trim();
                String location = et_location.getText().toString().trim();
                String danwei = et_danwei.getText().toString().trim();
                String gongju = et_gongju.getText().toString().trim();
                String money = et_money.getText().toString().trim();
                String beizhu = et_beizhu.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(text) || TextUtils.isEmpty(stime) || TextUtils.isEmpty(etime)) {
                    ToastUtil.showToast(this, "信息填写不完整");
                    return;
                }
                submint(name, text, yiju, location, danwei, gongju, money, stime, etime, beizhu);
                break;
            case R.id.tv_start:
                TimePickerView pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
//                        if (BaseUtils.DateCompare(BaseUtils.getTime(date), BaseUtils.getSystemTime())) {
//                            tv_start.setText(BaseUtils.getTime(date));
//                        } else {
//                            ToastUtil.showToast("开始时间必须大于当前时间", MyBusinessTravelActivity.this);
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
                    ToastUtil.showToast("请先选择开始时间", MyBusinessTravelActivity.this);
                } else {
                    pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            if (BaseUtils.DateCompare(BaseUtils.getTime(date), tv_start.getText().toString())) {
                                tv_end.setText(BaseUtils.getTime(date));
                            } else {
                                ToastUtil.showToast("结束时间必须大于开始时间", MyBusinessTravelActivity.this);
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
     * 提交公务出差申请
     */
    WaitDialog waitDialog;

    public void submint(String username, String content, String renwu, String didian, String danwei, String jiaotong, String money, String stime, String etime, String beizhu) {
        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(this, "login");
        OkHttpUtils.post()
                .url(InterfaceManager.siteURLIP + "/api/process/gongwuchuchai")
                .addParams("token", sharedPrefUtil.getString("token", ""))
                .addParams("username", username)
                .addParams("content", content)
                .addParams("renwu", renwu)
                .addParams("didian", didian)
                .addParams("danwei", danwei)
                .addParams("jiaotong", jiaotong)
                .addParams("money", money)
                .addParams("start_time", stime)
                .addParams("end_time", etime)
                .addParams("beizhu", beizhu)
                .addParams("process_id", entity.getId())
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
                        ToastUtil.showToast("请求网络失败", MyBusinessTravelActivity.this);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("onResponse", response);
                        if (waitDialog != null) {
                            waitDialog.dismiss();
                        }
                        Bean bean = JsonUtils.getObject(response, Bean.class);
                        if ("0".equals(bean.getCode())){
                            //处理数据
                            ToastUtil.showToast(MyBusinessTravelActivity.this, bean.getMsg());
                            finish();
                        }else{
                            ToastUtil.showToast(MyBusinessTravelActivity.this, bean.getMsg());
                        }


                    }
                });
    }


}
