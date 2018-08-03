package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.homepart.entity.PerformanceForApprovalEntity;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomeInternet;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomePersener;
import com.htcompany.educationerpforgansu.workpart.adapters.PerformanceForApprovalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 绩效核定
 * Created by wrb on 2016/11/9.
 */
public class PerformanceForApprovalActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView performanceforapproval_lv;
    private PerformanceForApprovalAdapter performanceForApprovalAdapter;
    private List<PerformanceForApprovalEntity> forApprovalEntities;
    //=========================网络请求类==========================
    private MainHomeInternet homeInternet;
    private MainHomePersener homePersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performanceforapproval_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas() {
        forApprovalEntities = new ArrayList<PerformanceForApprovalEntity>();
        homeInternet = new MainHomeInternet(this, myHandler);
        homePersener = new MainHomePersener(this, myHandler);
        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        homeInternet.getPerformanceForApprovalListMsg();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        performanceforapproval_lv = (ListView)this.findViewById(R.id.performanceforapproval_lv);
        performanceForApprovalAdapter = new PerformanceForApprovalAdapter(this);
        performanceforapproval_lv.setAdapter(performanceForApprovalAdapter);
        performanceforapproval_lv.setOnItemClickListener(perforOnclcer);
    }
    public void initViewValues(){
        title.setText("绩效核定");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener perforOnclcer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(PerformanceForApprovalActivity.this,PerformanceForApprovalDetialsActivity.class);
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
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",PerformanceForApprovalActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<PerformanceForApprovalEntity> datas = homePersener.parsePerformanceForApproval_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",PerformanceForApprovalActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<PerformanceForApprovalEntity> datas){
        if(forApprovalEntities.size()>0){
            forApprovalEntities.clear();
        }
        for(PerformanceForApprovalEntity entity:datas){
            forApprovalEntities.add(entity);
        }
        performanceForApprovalAdapter.notifyDataSetChanged();
    }
}
