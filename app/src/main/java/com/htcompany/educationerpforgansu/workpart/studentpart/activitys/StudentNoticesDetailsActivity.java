package com.htcompany.educationerpforgansu.workpart.studentpart.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkNoticesDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNotcieEntity;

/**
 * 班级公告详情
 * Created by wrb on 2016/11/23.
 */
public class StudentNoticesDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private TextView studentnoticesdetails_title_tv,studentnoticesdetails_time_tv,
            studentnoticesdetails_person_tv,studentnoticesdetails_type_tv;
    private WebView studentnoticesdetails_content_tv;
    private ClassNotcieEntity entity=null;
    private SharedPrefUtil sharedPrefUtil=null;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentnoticesdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
   public void initDatas(){
       sharedPrefUtil = new SharedPrefUtil(this,"login");
       entity = (ClassNotcieEntity) getIntent().getSerializableExtra("entity");
       teacherInternet = new WorkTeacherInternet(this,tableHanler);
       teacherPersener = new WorkTeacherPersener(this,tableHanler);
       waitDialog = new WaitDialog(this,"");
   }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        studentnoticesdetails_title_tv= (TextView) this.findViewById(R.id.studentnoticesdetails_title_tv);
        studentnoticesdetails_time_tv= (TextView) this.findViewById(R.id.studentnoticesdetails_time_tv);
        studentnoticesdetails_person_tv= (TextView) this.findViewById(R.id.studentnoticesdetails_person_tv);
        studentnoticesdetails_type_tv= (TextView) this.findViewById(R.id.studentnoticesdetails_type_tv);
        studentnoticesdetails_content_tv= (WebView) this.findViewById(R.id.studentnoticesdetails_content_tv);
    }

    public void initViewValues() {
        title.setText("公告详情");
//        right_three_btn.setVisibility(View.VISIBLE);
//        rightthree_btn_tv.setText("删除");
        if(entity!=null){
            studentnoticesdetails_title_tv.setText(entity.getTitle());
            studentnoticesdetails_time_tv.setText("发布时间:"+entity.getTime());
            studentnoticesdetails_person_tv.setText("发布人:"+sharedPrefUtil.getString("username",""));
            studentnoticesdetails_type_tv.setVisibility(View.GONE);
            init();
        }
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                        Intent intent = new Intent();
                        setResult(101, intent);
                        finish();
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentNoticesDetailsActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",StudentNoticesDetailsActivity.this);
                    break;
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                waitDialog.show();
                    teacherInternet.deleteClassNoticeListDatas(entity.getId());
                break;
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = studentnoticesdetails_content_tv.getSettings();
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
        studentnoticesdetails_content_tv.setWebViewClient(mWebViewClientBase);
        studentnoticesdetails_content_tv.setWebChromeClient(mWebChromeClientBase);
        studentnoticesdetails_content_tv.loadDataWithBaseURL("", entity.getContent(), "text/html", "utf-8", null);
        this.onResume();
    }
    private StudentNoticesDetailsActivity.WebViewClientBase mWebViewClientBase = new StudentNoticesDetailsActivity.WebViewClientBase();
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

    private StudentNoticesDetailsActivity.WebChromeClientBase mWebChromeClientBase = new StudentNoticesDetailsActivity.WebChromeClientBase();

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
