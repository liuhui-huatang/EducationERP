package com.htcompany.educationerpforgansu.loginpart;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.htcompany.educationerpforgansu.MainActivity;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.login.LoginInternet;
import com.htcompany.educationerpforgansu.internet.login.LoginPersoner;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import cn.jpush.android.api.JPushInterface;

/**
 * 闪屏欢迎页
 * Created by wrb on 2017/3/11.
 */
public class WelcomActivity extends Activity {
    private SharedPrefUtil sharedPrefUtil=null;
    //网络请求类
    private LoginInternet loginInternet;
    private LoginPersoner loginPersoner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnClicEvents();
    }

    public void initDatas() {
        loginInternet = new LoginInternet(this,myHandler);
        loginPersoner = new LoginPersoner(this);
        sharedPrefUtil = new SharedPrefUtil(this,"login");
//        myHandler.sendEmptyMessageDelayed(100,3000);
        if(!"".equals(sharedPrefUtil.getString("loginname",""))&&!"".equals(sharedPrefUtil.getString("loginpwd",""))){
            loginInternet.login(sharedPrefUtil.getString("loginname",""),sharedPrefUtil.getString("loginpwd",""));
        }else{
            myHandler.sendEmptyMessageDelayed(100,3000);
        }
    }

    public void initViews() {

    }

    public void initViewValues() {

    }

    public void initOnClicEvents() {

    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                        if("1".equals(sharedPrefUtil.getString("islogin", ""))){
                            startActivity(new Intent(WelcomActivity.this,MainActivity.class));
                            finish();
                        }else{
                            startActivity(new Intent(WelcomActivity.this,LoginActivity.class));
                            finish();
                        }
                    break;
                case 200:
                    //登录返回数据
                    loginPersoner.parserLogin((String)msg.obj);
                    if("1".equals(sharedPrefUtil.getString("islogin", ""))){
                        startActivity(new Intent(WelcomActivity.this,MainActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(WelcomActivity.this,LoginActivity.class));
                        finish();
                    }
//                    if(!EMClient.getInstance().isLoggedInBefore()){
//                        setLogin(sharedPrefUtil.getString("hxuser",""),sharedPrefUtil.getString("hxpassword",""));
//                    }
                    break;
                case 400:
                    startActivity(new Intent(WelcomActivity.this,LoginActivity.class));
                    finish();
                    break;
            }
        }
    };
    //登录环信
    public void setLogin(String username,String pwd){
        EMClient.getInstance().login(username,pwd,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
            }
            @Override
            public void onProgress(int progress, String status) {

            }
            @Override
            public void onError(int code, String message) {
                System.out.print("登录失败"+code);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    };
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
