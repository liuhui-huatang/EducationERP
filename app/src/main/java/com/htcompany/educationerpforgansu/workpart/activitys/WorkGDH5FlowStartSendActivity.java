package com.htcompany.educationerpforgansu.workpart.activitys;

import android.annotation.SuppressLint;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFlowStartSendEntity;

/**
 * 固定工作流发起界面
 * Created by WRB on 2017/4/23.
 */
public class WorkGDH5FlowStartSendActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout reback_btn;
    private TextView title;
    private WebView h5gdgzl_wb;
    private String url= "";
    private SharedPrefUtil sharedPrefUtil=null;
    private ProgressBar gdgzlpb_progress;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private WorkFlowStartSendEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workgdh5flowstartsend_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity=(WorkFlowStartSendEntity) getIntent().getSerializableExtra("flowEntity");
        sharedPrefUtil = new SharedPrefUtil(this, "login");
        waitDialog = new WaitDialog(this,"");
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
//        waitDialog.show();
//        wokrpersonalInternet.getWorkGDH5StartSendTypeList(entity.getId());
        url = InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_STARTSENTGDH5)+"?token="+sharedPrefUtil.getString("token","")+"&id="+entity.getId();
       System.out.print(url);
    }
    public void initViews(){
        gdgzlpb_progress = (ProgressBar) findViewById(R.id.gdgzlpb_progress);
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        h5gdgzl_wb = (WebView)this.findViewById(R.id.h5gdgzl_wb);
    }
    public void initViewValues(){
        title.setText("流程发起");
        init();
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
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
                    ToastUtil.showToast("请求网络失败",WorkGDH5FlowStartSendActivity.this);
                    break;
                case 200:
                    //处理数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }

                    break;
            }
        }
    };
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = h5gdgzl_wb.getSettings();
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
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
//        webSettings.setDefaultFontSize(22);
        webSettings.setSupportZoom(true);
        h5gdgzl_wb.setWebViewClient(mWebViewClientBase);
        h5gdgzl_wb.setWebChromeClient(mWebChromeClientBase);
        h5gdgzl_wb.loadUrl(url);
        this.onResume();
    }
    private WebViewClientBase mWebViewClientBase = new WebViewClientBase();
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

    private WebChromeClientBase mWebChromeClientBase = new WebChromeClientBase();

    private class WebChromeClientBase extends WebChromeClient {
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

            Log.e("eee",consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                // 网页加载完成
                gdgzlpb_progress.setVisibility(View.GONE);
            } else {
                // 加载中
                gdgzlpb_progress.setProgress(newProgress);
            }
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
