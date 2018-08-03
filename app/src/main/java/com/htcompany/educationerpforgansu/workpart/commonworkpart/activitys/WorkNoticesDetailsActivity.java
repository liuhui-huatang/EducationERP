package com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
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
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.activitys.TeacherPJActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;

/**
 * 办公公告详情
 * Created by wrb on 2016/11/22.
 */
public class WorkNoticesDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private WorkNoticeEntity entity=null;
    private TextView worknoticesdetails_title_tv,worknoticesdetails_time_tv,worknoticesdetails_person_tv,
            worknoticesdetails_type_tv,worknoticesdetails_content_tv;
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    private String ismain="";
    private WebView worknoticesdetails_wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worknoticesdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        ismain=getIntent().getStringExtra("ismain");
        entity = (WorkNoticeEntity) getIntent().getSerializableExtra("entity");
        workInternet = new CommonWorkInternet(this,myHandler);
        workPersener = new CommonWorkPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        worknoticesdetails_title_tv= (TextView) this.findViewById(R.id.worknoticesdetails_title_tv);
        worknoticesdetails_time_tv= (TextView) this.findViewById(R.id.worknoticesdetails_time_tv);
        worknoticesdetails_person_tv= (TextView) this.findViewById(R.id.worknoticesdetails_person_tv);
        worknoticesdetails_type_tv= (TextView) this.findViewById(R.id.worknoticesdetails_type_tv);
        worknoticesdetails_content_tv= (TextView) this.findViewById(R.id.worknoticesdetails_content_tv);
        worknoticesdetails_wb=(WebView)this.findViewById(R.id.worknoticesdetails_wb);
    }

    public void initViewValues() {
        title.setText("公告详情");
//        if("1".equals(ismain)){
//            right_three_btn.setVisibility(View.GONE);
//        }else {
//            right_three_btn.setVisibility(View.VISIBLE);
//            rightthree_btn_tv.setText("删除");
//        }
        if(entity!=null){
            worknoticesdetails_title_tv.setText(entity.getB_title());
            worknoticesdetails_time_tv.setText(entity.getB_send_date());
            worknoticesdetails_person_tv.setText(entity.getUsername());
            worknoticesdetails_type_tv.setText(entity.getB_send_type());
            worknoticesdetails_content_tv.setText(Html.fromHtml(entity.getB_content()));
        init();
        }

    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                if(entity!=null) {
                    waitDialog.show();
                    workInternet.deleteWorkNoticeListItemDatas(entity.getId());
                }
                break;
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",WorkNoticesDetailsActivity.this);
                    break;
                case 201:
                    //删除办公公告
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(workPersener.parserDeleteWorkNotice((String)msg.obj)){
                       finish();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",WorkNoticesDetailsActivity.this);
                    break;
            }
        }
    };
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = worknoticesdetails_wb.getSettings();
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
        worknoticesdetails_wb.setWebViewClient(mWebViewClientBase);
        worknoticesdetails_wb.setWebChromeClient(mWebChromeClientBase);
        worknoticesdetails_wb.loadDataWithBaseURL("", entity.getB_content(), "text/html", "utf-8", null);
        this.onResume();
    }
    private WorkNoticesDetailsActivity.WebViewClientBase mWebViewClientBase = new WorkNoticesDetailsActivity.WebViewClientBase();
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

    private WorkNoticesDetailsActivity.WebChromeClientBase mWebChromeClientBase = new WorkNoticesDetailsActivity.WebChromeClientBase();

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
