package com.htcompany.educationerpforgansu;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

/**
 * 登录界面
 * Created by wrb on 2016/12/30.
 */
public class MyWebActivity extends BaseActivity implements View.OnClickListener {
    WebView myweb;

    public static void  startIntent(Activity activity,String url){
        Intent intent=new Intent( activity,MyWebActivity.class);
        intent.putExtra("url",url);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initViews();
        initDatas();
    }


    public void initViews() {
        TextView title = (TextView) this.findViewById(R.id.title);
        title.setText("详情页面");
        this.findViewById(R.id.reback_btn).setOnClickListener(this);
        myweb= (WebView) findViewById(R.id.webview);
    }

    public void initDatas() {
        myweb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        myweb.loadUrl(getIntent().getStringExtra("url"));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }


}
