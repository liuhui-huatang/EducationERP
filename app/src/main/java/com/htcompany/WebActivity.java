package com.htcompany;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;

import java.util.HashMap;
import java.util.Map;

public class WebActivity extends BaseActivity implements View.OnClickListener {

    private TextView title;
    private WebView myweb;
    private String url;
    private SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initViews();
        sharedPrefUtil = new SharedPrefUtil(this, "login");
        initDatas();

    }


    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        title.setText("详情页面");
        this.findViewById(R.id.reback_btn).setOnClickListener(this);
        myweb= (WebView) findViewById(R.id.webview);
    }

    public void initDatas() {
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        if(from.equals(FunctionDatas.WORK_BGGL_HYLB)){
            title.setText("会议列表");
            url = InterfaceManager.getInstance().getURL(InterfaceManager.MEETING_LIST);

        }else if(from.equals(FunctionDatas.WORK_BGGL_KQTJ)){
            title.setText("考勤统计");
            url = InterfaceManager.getInstance().getURL(InterfaceManager.SIGN_TOTAL_DATA);
        }
        final Map extraHeaders = new HashMap();
        extraHeaders.put("token",sharedPrefUtil.getString("token",""));
        extraHeaders.put("unkey",sharedPrefUtil.getString("unkey",""));
        extraHeaders.put("wifi",getConnectWifiSsid());
        WebSettings webSettings = myweb.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //设置自适应屏幕，两者合用

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片


        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        myweb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        myweb.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }
        });
        myweb.loadUrl(url,extraHeaders);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }

    private String getConnectWifiSsid(){
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        Log.d("wifiInfo", wifiInfo.toString());
        Log.d("SSID",wifiInfo.getSSID());
        String currentWifi = wifiInfo.getSSID();
        currentWifi = currentWifi.replace("\"","").replace("\"","");
        return currentWifi;
    }
}
