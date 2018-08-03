package com.htcompany.educationerpforgansu.loginpart;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.MainActivity;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.chatpart.DemoHelper;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.login.LoginInternet;
import com.htcompany.educationerpforgansu.internet.login.LoginPersoner;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static com.htcompany.educationerpforgansu.commonpart.MyApp.context;

/**
 * 登录界面
 * Created by wrb on 2016/12/30.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView login_btn;
    private EditText login_username_edt, login_pwd_edt;
    //网络请求类
    private LoginInternet loginInternet;
    private LoginPersoner loginPersoner;
    private WaitDialog waitDialog = null;
    private SharedPrefUtil sharedPrefUtil = null;
    private String login_flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initDatas();
        initViews();
        initOnclicEvents();
    }

    public void initDatas() {
        sharedPrefUtil = new SharedPrefUtil(this, "login");
        login_flag = getIntent().getStringExtra("login_flag");
        if ("chat_flag".equals(login_flag)) {
            ToastUtil.showToast("您的账号在其他设备登录!", this);
            //设置激光推送别名为空
            sharedPrefUtil.putString("username", "");
            sharedPrefUtil.putString("userphoto", "");
            sharedPrefUtil.putString("hxuser", "");
            sharedPrefUtil.putString("hxpassword", "");
            sharedPrefUtil.putString("jpuid", "");
            sharedPrefUtil.putString("zhiwei", "");
            sharedPrefUtil.putString("token", "");
            sharedPrefUtil.putString("loginpwd", "");
            sharedPrefUtil.putString("islogin", "");
            sharedPrefUtil.commit();
            setAlias("");
            //退出环信
//            exit();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (tintManager != null) {
                tintManager.setTintDrawable(getResources().getDrawable(R.mipmap.logintitle));
//                tintManager.setTintColor(getResources().getColor(R.color.login_tv));
            }
        }
        loginInternet = new LoginInternet(this, myHandler);
        loginPersoner = new LoginPersoner(this);
        waitDialog = new WaitDialog(this, "");
    }

    public void initViews() {
        login_btn = (TextView) this.findViewById(R.id.login_btn);
        login_username_edt = (EditText) this.findViewById(R.id.login_username_edt);
        login_pwd_edt = (EditText) this.findViewById(R.id.login_pwd_edt);
        login_username_edt.setText(sharedPrefUtil.getString("loginname", ""));

        TextView tv_version = (TextView) this.findViewById(R.id.tv_version);
        tv_version.setText("V "+getVersionName(context));
    }

    public void initOnclicEvents() {
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if ("".equals(login_username_edt.getText().toString().trim())) {
                    ToastUtil.showToast("请输入账号", LoginActivity.this);
                } else if ("".equals(login_pwd_edt.getText().toString().trim())) {
                    ToastUtil.showToast("请输入密码", LoginActivity.this);
                } else {
                    if (BaseUtils.isNetworkAvailable(LoginActivity.this)) {
                        waitDialog.show();
                        loginInternet.login(login_username_edt.getText().toString().trim(), login_pwd_edt.getText().toString().trim());
                    } else {
                        ToastUtil.showToast("请连接网络", LoginActivity.this);
                    }
                }
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
                    ToastUtil.showToast("连接服务器失败", LoginActivity.this);
                    break;
                case 300:
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常", LoginActivity.this);
                    break;
                case 200:
                    Log.d("登录成功", (String) msg.obj);
                    //登录返回数据
                    if (loginPersoner.parserLogin((String) msg.obj)) {
//                        if ("".equals(sharedPrefUtil.getString("hxuser", "")) || "".equals(sharedPrefUtil.getString("hxpassword", ""))) {
//                            ToastUtil.showToast("缺少聊天账号请联系管理员", LoginActivity.this);
//                        } else {
                            Log.d("登录成功-->hxuser", sharedPrefUtil.getString("hxuser", ""));
                            Log.d("登录成功-->hxpassword", sharedPrefUtil.getString("hxpassword", ""));
//                            setLogin(sharedPrefUtil.getString("hxuser",""),sharedPrefUtil.getString("hxpassword",""));

                            sharedPrefUtil.putString("loginname", login_username_edt.getText().toString());
                            sharedPrefUtil.putString("loginpwd", login_pwd_edt.getText().toString());
                            sharedPrefUtil.putString("islogin", "1");
                            sharedPrefUtil.commit();
                            if (waitDialog != null) {
                                waitDialog.dismiss();
                            }
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();

//                        }
                    } else {
                        if (waitDialog != null) {
                            waitDialog.dismiss();
                        }
                        ToastUtil.showToast("请输入正确账号密码", LoginActivity.this);
                    }
                    break;
            }
        }
    };

    //登录环信
    public void setLogin(String username, String pwd) {
        EMClient.getInstance().login(username, pwd, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Log.d("登录环信成功", "");
                //更新换新昵称
                DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(sharedPrefUtil.getString("username", ""));
                DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(InterfaceManager.siteURLIP + sharedPrefUtil.getString("userphoto", ""));//设置换新头像
                sharedPrefUtil.putString("loginname", login_username_edt.getText().toString());
                sharedPrefUtil.putString("loginpwd", login_pwd_edt.getText().toString());
                sharedPrefUtil.putString("islogin", "1");
                sharedPrefUtil.commit();
                if (waitDialog != null) {
                    waitDialog.dismiss();
                }
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.d("登录环信", progress + "");
            }

            @Override
            public void onError(int code, String message) {
                Log.d("登录环信失败", message);
                System.out.print("登录失败" + code);
                if (waitDialog != null) {
                    waitDialog.dismiss();
                }
            }
        });
    }

    //=========================================注册激光推送别名=================================================
    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias(String alias) {
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    sharedPrefUtil.putString("jgbgsucess", "1");
                    sharedPrefUtil.commit();
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
            }
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(LoginActivity.this,
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };

    //退出环信
    public void exit() {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub

            }
        });
    }


    /**
     * 获取版本名称
     *
     * @param context 上下文
     * @return 版本名称
     */
    public static String getVersionName(Context context) {
//获取包管理器
        PackageManager pm = context.getPackageManager();
//获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
//返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
