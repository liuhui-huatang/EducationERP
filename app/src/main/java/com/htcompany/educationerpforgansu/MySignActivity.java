package com.htcompany.educationerpforgansu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.hyphenate.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 打卡界面
 * Created by wrb on 2016/12/30.
 */
public class MySignActivity extends BaseActivity implements View.OnClickListener {
    public static void startIntent(Activity activity) {
        Intent intent = new Intent(activity, MySignActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initViews();
        initDatas();
        initTime();
    }

    TextView tv_shang, tv_xia;
    Button bt_xia, bt_shang;
    ImageView iv_image;

    public void initViews() {
        TextView title = (TextView) this.findViewById(R.id.title);
        title.setText("签到页面");

        iv_image = (ImageView) findViewById(R.id.iv_image);
        this.findViewById(R.id.reback_btn).setOnClickListener(this);
        bt_shang = (Button) this.findViewById(R.id.bt_shang);
        bt_shang.setOnClickListener(this);
        bt_xia = (Button) this.findViewById(R.id.bt_xia);
        bt_xia.setOnClickListener(this);

        tv_shang = (TextView) this.findViewById(R.id.tv_shang);
        tv_xia = (TextView) this.findViewById(R.id.tv_xia);

    }

    public void initTime() {
        final TextView tv_time = (TextView) findViewById(R.id.tv_time);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss

        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                // TODO Auto-generated method stub
                if (msg.what == 0) {
                    //这里可以进行UI操作，如Toast，Dialog等
                    //获取当前时间
                    Date date = new Date(System.currentTimeMillis());
                    tv_time.setText("" + simpleDateFormat.format(date));
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        }, 1000, 1000);
    }


    public void initDatas() {
        getSign();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.bt_shang:
                shangSign();
                break;
            case R.id.bt_xia:
                xiaSign();
                break;
        }
    }

    WaitDialog waitDialog;

    public void shangSign() {
        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(this, "login");
        OkHttpUtils.post()
                .url(InterfaceManager.siteURLIP + "/api/teacher/work")
                .addParams("token", sharedPrefUtil.getString("token", ""))
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
                        ToastUtil.showToast("请求网络失败", MySignActivity.this);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("onResponse", response);
                        if (waitDialog != null) {
                            waitDialog.dismiss();
                        }
//                        if ("0".equals(bean.getCode())) {
//                            //处理数据
//                            ToastUtil.showToast(MySignActivity.this, "成功："+bean.getMsg());
//                            getSign();
//                        } else {
//                            ToastUtil.showToast(MySignActivity.this, bean.getMsg());
//                        }
                        Bean bean = JsonUtils.getObject(response, Bean.class);
                        if ("0".equals(bean.getCode())) {
                            tv_shang.setText("上班打卡：" + JSON.parseObject(bean.getData()).getString("time") + " 正常");
                            bt_shang.setText("已打卡");
                            bt_shang.setClickable(false);
                            bt_shang.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gray));
                        } else if ("1".equals(bean.getCode())) {
                            tv_shang.setText("上班打卡：" + JSON.parseObject(bean.getData()).getString("time") + " 迟到");
                            bt_shang.setText("已打卡");
                            bt_shang.setClickable(false);
                            bt_shang.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gray));
                        } else {

                        }
                        ToastUtil.showToast(MySignActivity.this, bean.getMsg());

                    }
                });
    }

    public void xiaSign() {

        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(this, "login");
        OkHttpUtils.post()
                .url(InterfaceManager.siteURLIP + "/api/teacher/afterwork")
                .addParams("token", sharedPrefUtil.getString("token", ""))
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
                        ToastUtil.showToast("请求网络失败", MySignActivity.this);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("onResponse", response);
                        if (waitDialog != null) {
                            waitDialog.dismiss();
                        }
//                        Bean bean = JsonUtils.getObject(response, Bean.class);
//                        if ("0".equals(bean.getCode())) {
//                            //处理数据
//                            ToastUtil.showToast(MySignActivity.this, "成功："+bean.getMsg());
//                            getSign();
//                        } else {
//                            ToastUtil.showToast(MySignActivity.this, bean.getMsg());
//                        }
                        Bean bean = JsonUtils.getObject(response, Bean.class);
                        if ("0".equals(bean.getCode())) {
                            tv_xia.setText("下班打卡：" + JSON.parseObject(bean.getData()).getString("time") + " 正常");
                            bt_xia.setText("已打卡");
//                            bt_xia.setClickable(false);
//                            bt_xia.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gray));
                        } else if ("1".equals(bean.getCode())) {
                            tv_xia.setText("下班打卡：" + JSON.parseObject(bean.getData()).getString("time") + " 早退");
                            bt_xia.setText("已打卡");
//                            bt_xia.setClickable(false);
//                            bt_xia.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gray));
                        } else {

                        }
                        ToastUtil.showToast(MySignActivity.this, bean.getMsg());


                    }
                });
    }


    public void getSign() {

        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(this, "login");
        OkHttpUtils.post()
                .url(InterfaceManager.siteURLIP + "/api/teacher/workinfo")
                .addParams("token", sharedPrefUtil.getString("token", ""))
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
                        ToastUtil.showToast("请求网络失败", MySignActivity.this);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("onResponse", response);
                        if (waitDialog != null) {
                            waitDialog.dismiss();
                        }
                        Bean bean = JsonUtils.getObject(response, Bean.class);
                        if ("0".equals(bean.getCode())) {
                            String dataJson = JsonUtils.getJsonStr(bean.getData(), "data");
                            String infoJson = JsonUtils.getJsonStr(bean.getData(), "info");

                            Glide.with(MySignActivity.this)
                                    .load(InterfaceManager.siteURLIP + JsonUtils.getJsonStr(infoJson, "image"))
                                    .placeholder(R.mipmap.defult_photo_icon)
                                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                    .into(iv_image);

                            //处理数据
                            final List<SignBean> list = JsonUtils.getListObject(dataJson, SignBean.class);
                            final SignBean shangBean = list.get(0);
                            final SignBean xiaBean = list.get(1);

                            MySignActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setView(shangBean, tv_shang, bt_shang);
                                    setView(xiaBean, tv_xia, bt_xia);
                                }
                            });
                        } else {
                            ToastUtil.showToast(MySignActivity.this, bean.getMsg());
                        }
                    }
                });
    }

    public void setView(SignBean bean, TextView tv, Button btn) {
        //  type 1为上班打卡 2为下班打卡
        if (bean.getType() == 1) {
            //   status 1为正常考勤 2为迟到:早退 0: 未打卡
            if (bean.getStatus() == 0) {
                tv.setText("上班打卡：" + "未打卡");
                btn.setText("上班");
                btn.setClickable(true);
                btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.blure));
            } else if (bean.getStatus() == 1) {
                tv.setText("上班打卡：" + bean.getTime() + " 正常");
                btn.setText("已打卡");
                btn.setClickable(false);
                btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gray));
            } else {
                tv.setText("上班打卡：" + bean.getTime() + " 迟到");
                btn.setText("已打卡");
                btn.setClickable(false);
                btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gray));
            }
        } else {
            //   status 1为正常考勤 2为迟到:早退 0: 未打卡
            if (bean.getStatus() == 0) {
                tv.setText("下班打卡：" + "未打卡");
                btn.setText("下班");
//                btn.setClickable(true);
//                btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.blure));
            } else if (bean.getStatus() == 1) {
                tv.setText("下班打卡：" + bean.getTime() + " 正常");
                btn.setText("已打卡");
//                btn.setClickable(false);
//                btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gray));
            } else {
                tv.setText("下班打卡：" + bean.getTime() + " 早退");
                btn.setText("已打卡");
//                btn.setClickable(false);
//                btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gray));
            }


        }


    }

}
