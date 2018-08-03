package com.htcompany.educationerpforgansu.workpart.financepart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
import com.htcompany.educationerpforgansu.workpart.financepart.adapters.FinalcepartReturnMoneyAdapter;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.ReturnMoneyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 退费汇总记录
 * Created by wrb on 2016/11/16.
 */
public class FinalcepartReturnMoneyActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView finalcepartreturnmoney_wsj_img;
    private XListView finalcepartreturnmoney_lv;
    private FinalcepartReturnMoneyAdapter returnMoneyAdapter;
    private EditText returnmoney_search_edt;
    //网络数据请求类
    private List<ReturnMoneyEntity> returnMoneyEntities;
    private FinalcepartInternet finalcepartInternet;
    private FinalcepartPersener finalcepartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalcepartreturnmoney_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        returnMoneyEntities = new ArrayList<ReturnMoneyEntity>();
        finalcepartInternet = new FinalcepartInternet(this,myHandler);
        finalcepartPersener = new FinalcepartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        finalcepartInternet.getReturnMoneyDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        finalcepartreturnmoney_wsj_img=(ImageView)this.findViewById(R.id.finalcepartreturnmoney_wsj_img);
        finalcepartreturnmoney_lv = (XListView)this.findViewById(R.id.finalcepartreturnmoney_lv);
        returnmoney_search_edt = (EditText)this.findViewById(R.id.returnmoney_search_edt);
        finalcepartreturnmoney_lv.setPullRefreshEnable(true);
        finalcepartreturnmoney_lv.setPullLoadEnable(false);
        finalcepartreturnmoney_lv.setXListViewListener(this);
        returnMoneyAdapter = new FinalcepartReturnMoneyAdapter(this,returnMoneyEntities);
        finalcepartreturnmoney_lv.setAdapter(returnMoneyAdapter);
    }
    public void initViewVlaues(){
        title.setText("退费汇总记录");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        returnmoney_search_edt.addTextChangedListener(serachclier);
    }

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
            username = returnmoney_search_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(returnMoneyEntities.size()>0){
                    returnMoneyEntities.clear();
                }
                finalcepartInternet.getReturnMoneyDatas(String.valueOf(pageNum),username);
            }else{
                finalcepartInternet.getReturnMoneyDatas(String.valueOf(pageNum),username);
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
                    if(returnMoneyEntities.size()==0){
                        finalcepartreturnmoney_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        finalcepartreturnmoney_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",FinalcepartReturnMoneyActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ReturnMoneyEntity> datas = finalcepartPersener.parseReturnMoneyData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(returnMoneyEntities.size()==0){
                            finalcepartreturnmoney_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            finalcepartreturnmoney_wsj_img.setVisibility(View.GONE);
                        }
                        finalcepartreturnmoney_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",FinalcepartReturnMoneyActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<ReturnMoneyEntity> datas){
        if(pageNum==1){
            if(returnMoneyEntities.size()>0){
                returnMoneyEntities.clear();
            }
        }
        if(datas.size()>0){
            finalcepartreturnmoney_lv.setPullLoadEnable(true);
        }
        for(ReturnMoneyEntity entity:datas){
            returnMoneyEntities.add(entity);
        }
        if(returnMoneyEntities.size()==0){
            finalcepartreturnmoney_wsj_img.setVisibility(View.VISIBLE);
        }else {
            finalcepartreturnmoney_wsj_img.setVisibility(View.GONE);
        }
        returnMoneyAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        finalcepartInternet.getReturnMoneyDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        finalcepartInternet.getReturnMoneyDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        finalcepartreturnmoney_lv.stopRefresh();
        finalcepartreturnmoney_lv.stopLoadMore();
        finalcepartreturnmoney_lv.setRefreshTime("刚刚");
    }
}
