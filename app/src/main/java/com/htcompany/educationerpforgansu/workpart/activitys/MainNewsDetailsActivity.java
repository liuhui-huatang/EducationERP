package com.htcompany.educationerpforgansu.workpart.activitys;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkNoticesDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.entities.DYNewsEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 新闻详情界面
 * Created by weiruibin on 2017/5/26.
 */

public class MainNewsDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView news_title_tv,news_fbr_tv,news_time_tv;
    private WebView news_content_wb;
    private ImageView news_photo_img;
    private DYNewsEntity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainnewsdetails_activity);
        initDatas();
        initViews();
        initvIEWvLAUES();
        initOnclicer();
    }
    public void initDatas(){
       entity=(DYNewsEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        news_title_tv=(TextView)this.findViewById(R.id.news_title_tv);
                news_fbr_tv=(TextView)this.findViewById(R.id.news_fbr_tv);
                news_time_tv=(TextView)this.findViewById(R.id.news_time_tv);
        news_content_wb=(WebView) this.findViewById(R.id.news_content_wb);
        news_photo_img=(ImageView)this.findViewById(R.id.news_photo_img);

    }
    public void initvIEWvLAUES(){
        title.setText("新闻详情");
       if(entity!=null){
           news_title_tv.setText(entity.getTitle());
           news_fbr_tv.setText("发布人："+entity.getAuthor());
           news_time_tv.setText("发布时间："+entity.getUpdate_time());
           if(!"".equals(entity.getImage())&&entity.getImage()!=null){
               ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+
               entity.getImage(),news_photo_img, MyApp.getOptions());
           }
           init();
       }
    }
    public void initOnclicer(){
reback_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.reback_btn:
        finish();
        break;
}
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = news_content_wb.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(false);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);//
        webSettings.setLoadWithOverviewMode(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
//        webSettings.setDefaultFontSize(60);
        webSettings.setTextSize(WebSettings.TextSize.LARGEST);
        news_content_wb.setWebViewClient(mWebViewClientBase);
        news_content_wb.setWebChromeClient(mWebChromeClientBase);
        news_content_wb.loadDataWithBaseURL("", entity.getContent(), "text/html", "utf-8", null);
        this.onResume();
    }
    private MainNewsDetailsActivity.WebViewClientBase mWebViewClientBase = new MainNewsDetailsActivity.WebViewClientBase();
    private class WebViewClientBase extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url,
                                           boolean isReload) {
            // TODO Auto-generated method stub
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }

    private MainNewsDetailsActivity.WebChromeClientBase mWebChromeClientBase = new MainNewsDetailsActivity.WebChromeClientBase();

    private class WebChromeClientBase extends WebChromeClient {
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

            Log.e("eee",consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            // TODO Auto-generated method stub
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url,
                                           boolean precomposed) {
            // TODO Auto-generated method stub
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog,
                                      boolean isUserGesture, Message resultMsg) {
            // TODO Auto-generated method stub
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }
    }

}
