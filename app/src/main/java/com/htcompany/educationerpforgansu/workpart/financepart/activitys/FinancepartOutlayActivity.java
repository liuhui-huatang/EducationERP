package com.htcompany.educationerpforgansu.workpart.financepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.Finalcepart.FinalcepartInternet;
import com.htcompany.educationerpforgansu.internet.Finalcepart.FinalcepartPersener;
import com.htcompany.educationerpforgansu.workpart.financepart.adapters.FinancepartOutlayAdapter;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.OutlayEnity;

import java.util.ArrayList;
import java.util.List;

/**
 * 财务支出管理
 * Created by wrb on 2016/11/17.
 */
public class FinancepartOutlayActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private XListView financepartoutlay_lv;
    private EditText outlay_search_edt;
    private FinancepartOutlayAdapter outlayAdapter;
    private ImageView financepartoutlay_wsj_img;
    //网络数据请求类
    private List<OutlayEnity> outlayEnities;
    private FinalcepartInternet finalcepartInternet;
    private FinalcepartPersener finalcepartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financepartoutlay_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        outlayEnities = new ArrayList<OutlayEnity>();
        finalcepartInternet = new FinalcepartInternet(this,myHandler);
        finalcepartPersener = new FinalcepartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        finalcepartInternet.getOutlayDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        financepartoutlay_wsj_img=(ImageView)this.findViewById(R.id.financepartoutlay_wsj_img);
        outlay_search_edt = (EditText)this.findViewById(R.id.outlay_search_edt);
        financepartoutlay_lv = (XListView)this.findViewById(R.id.financepartoutlay_lv);
        financepartoutlay_lv.setPullRefreshEnable(true);
        financepartoutlay_lv.setPullLoadEnable(false);
        financepartoutlay_lv.setXListViewListener(this);
        outlayAdapter = new FinancepartOutlayAdapter(this,outlayEnities);
        financepartoutlay_lv.setAdapter(outlayAdapter);
        financepartoutlay_lv.setOnItemClickListener(outlayClicer);
    }
    public void initViewValues(){
        title.setText("支出管理");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        outlay_search_edt.addTextChangedListener(serachclier);
    }
   public AdapterView.OnItemClickListener outlayClicer  = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           OutlayEnity entity = (OutlayEnity) outlayAdapter.getItem(position-1);
           Intent intent = new Intent(FinancepartOutlayActivity.this,FinancepartOutlayDetailsActivity.class);
           intent.putExtra("entity",entity);
           startActivity(intent);
       }
   };
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.reback_btn:
              this.finish();
              break;
      }
    }

    public TextWatcher serachclier = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            username = outlay_search_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(outlayEnities.size()>0){
                    outlayEnities.clear();
                }
                finalcepartInternet.getOutlayDatas(String.valueOf(pageNum),username);
            }else{
                finalcepartInternet.getOutlayDatas(String.valueOf(pageNum),username);
            }
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(outlayEnities.size()==0){
                        financepartoutlay_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        financepartoutlay_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",FinancepartOutlayActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<OutlayEnity> datas = finalcepartPersener.parseOutlayData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(outlayEnities.size()==0){
                            financepartoutlay_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            financepartoutlay_wsj_img.setVisibility(View.GONE);
                        }
                        financepartoutlay_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",FinancepartOutlayActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<OutlayEnity> datas){
        if(pageNum==1){
            if(outlayEnities.size()>0){
                outlayEnities.clear();
            }
        }
        if(datas.size()>0){
            financepartoutlay_lv.setPullLoadEnable(true);
        }
        for(OutlayEnity entity:datas){
            outlayEnities.add(entity);
        }
        if(outlayEnities.size()==0){
            financepartoutlay_wsj_img.setVisibility(View.VISIBLE);
        }else {
            financepartoutlay_wsj_img.setVisibility(View.GONE);
        }
        outlayAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        finalcepartInternet.getOutlayDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        finalcepartInternet.getOutlayDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        financepartoutlay_lv.stopRefresh();
        financepartoutlay_lv.stopLoadMore();
        financepartoutlay_lv.setRefreshTime("刚刚");
    }
}
