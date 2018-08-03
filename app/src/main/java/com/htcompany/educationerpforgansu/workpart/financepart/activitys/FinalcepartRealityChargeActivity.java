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
import com.htcompany.educationerpforgansu.workpart.financepart.adapters.FinalcepartRealityChargeAdapter;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.RealityChargeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 实际收费记录
 * Created by wrb on 2016/11/16.
 */
public class FinalcepartRealityChargeActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reabck_btn;
    private ImageView finalcepartrealitycharge_wsj_img;
    private XListView finalcepartrealitycharge_lv;
    private FinalcepartRealityChargeAdapter realityChargeAdapter;
    private EditText sfjl_seracher_edt;
    //网络数据请求类
    private List<RealityChargeEntity> realityChargeEntities;
    private FinalcepartInternet finalcepartInternet;
    private FinalcepartPersener finalcepartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalcepartrealitycharge_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        realityChargeEntities = new ArrayList<RealityChargeEntity>();
        finalcepartInternet = new FinalcepartInternet(this,myHandler);
        finalcepartPersener = new FinalcepartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        finalcepartInternet.getRealityChargeDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reabck_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        sfjl_seracher_edt = (EditText)this.findViewById(R.id.sfjl_seracher_edt);
        finalcepartrealitycharge_wsj_img=(ImageView)this.findViewById(R.id.finalcepartrealitycharge_wsj_img);
        finalcepartrealitycharge_lv = (XListView)this.findViewById(R.id.finalcepartrealitycharge_lv);
        finalcepartrealitycharge_lv.setPullRefreshEnable(true);
        finalcepartrealitycharge_lv.setPullLoadEnable(false);
        finalcepartrealitycharge_lv.setXListViewListener(this);
        realityChargeAdapter = new FinalcepartRealityChargeAdapter(this,realityChargeEntities);
        finalcepartrealitycharge_lv.setAdapter(realityChargeAdapter);
    }
    public void initViewVlaues(){
        title.setText("实际收费记录");
    }
    public void initOnclicEvents(){
        reabck_btn.setOnClickListener(this);
        finalcepartrealitycharge_lv.setOnItemClickListener(realitychargeClicer);
        sfjl_seracher_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener realitychargeClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            RealityChargeEntity entity = (RealityChargeEntity) realityChargeAdapter.getItem(position-1);
            Intent intent = new Intent(FinalcepartRealityChargeActivity.this,FinalcepartRealityChargeDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
    public TextWatcher serachclier = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
             username = sfjl_seracher_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(realityChargeEntities.size()>0){
                    realityChargeEntities.clear();
                }
                finalcepartInternet.getRealityChargeDatas(String.valueOf(pageNum),username);
            }else{
                finalcepartInternet.getRealityChargeDatas(String.valueOf(pageNum),username);
            }
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
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(realityChargeEntities.size()==0){
                        finalcepartrealitycharge_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        finalcepartrealitycharge_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",FinalcepartRealityChargeActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<RealityChargeEntity> datas = finalcepartPersener.parseRealityChargeData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(realityChargeEntities.size()==0){
                            finalcepartrealitycharge_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            finalcepartrealitycharge_wsj_img.setVisibility(View.GONE);
                        }
                        finalcepartrealitycharge_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",FinalcepartRealityChargeActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<RealityChargeEntity> datas){
        if(pageNum==1){
            if(realityChargeEntities.size()>0){
                realityChargeEntities.clear();
            }
        }
        if(datas.size()>0){
            finalcepartrealitycharge_lv.setPullLoadEnable(true);
        }else{
            finalcepartrealitycharge_lv.setPullLoadEnable(false);
        }
        for(RealityChargeEntity entity:datas){
            realityChargeEntities.add(entity);
        }
        if(realityChargeEntities.size()==0){
            finalcepartrealitycharge_wsj_img.setVisibility(View.VISIBLE);
        }else {
            finalcepartrealitycharge_wsj_img.setVisibility(View.GONE);
        }
        realityChargeAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        finalcepartInternet.getRealityChargeDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        finalcepartInternet.getRealityChargeDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        finalcepartrealitycharge_lv.stopRefresh();
        finalcepartrealitycharge_lv.stopLoadMore();
        finalcepartrealitycharge_lv.setRefreshTime("刚刚");
    }
}
