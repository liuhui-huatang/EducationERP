package com.htcompany.educationerpforgansu.homepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.homepart.adapters.MySalariesAdapter;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.SalaryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的工资
 * Created by wrb on 2016/11/8.
 */
public class MySalariesActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView mysalaries_wsj_img;
    private XListView mysalaries_lv;
    private MySalariesAdapter salariesAdapter;
    //======================网络请求类========================
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private List<SalaryEntity> salaryEntities;
    private WaitDialog waitDialog;
    private int pageNum=1;//页数
    private String unkey="";
    private SharedPrefUtil sharedPrefUtil=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysalaries_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        sharedPrefUtil = new SharedPrefUtil(this, "login");
        unkey =sharedPrefUtil.getString("unkey","");
        salaryEntities = new ArrayList<SalaryEntity>();
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalPersener = new WokrpersonalPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        wokrpersonalInternet.getMySarlarysDatas(String.valueOf(pageNum),unkey);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        mysalaries_wsj_img=(ImageView)this.findViewById(R.id.mysalaries_wsj_img);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        mysalaries_lv = (XListView) this.findViewById(R.id.mysalaries_lv);
        mysalaries_lv.setPullRefreshEnable(true);
        mysalaries_lv.setPullLoadEnable(false);
        mysalaries_lv.setXListViewListener(this);
        salariesAdapter = new MySalariesAdapter(this,salaryEntities);
        mysalaries_lv.setAdapter(salariesAdapter);
        mysalaries_lv.setOnItemClickListener(salariesClicker);
    }
    public void initViewValues(){
        title.setText("我的工资");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener salariesClicker = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SalaryEntity entity = (SalaryEntity) salariesAdapter.getItem(position-1);
            Intent intent = new Intent(MySalariesActivity.this,MySalariesDetailsActivity.class);
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
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",MySalariesActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<SalaryEntity> datas = wokrpersonalPersener.parseSarilayData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(salaryEntities.size()==0){
                            mysalaries_wsj_img.setVisibility(View.VISIBLE);
                        }else{
                            mysalaries_wsj_img.setVisibility(View.GONE);
                        }
                        mysalaries_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MySalariesActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<SalaryEntity> datas){
        if(pageNum==1){
            if(salaryEntities.size()>0){
                salaryEntities.clear();
            }
        }
        if(datas.size()>20){
            mysalaries_lv.setPullLoadEnable(true);
        }
        for(SalaryEntity entity:datas){
            salaryEntities.add(entity);
        }
        salariesAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        wokrpersonalInternet.getMySarlarysDatas(String.valueOf(pageNum),unkey);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        wokrpersonalInternet.getMySarlarysDatas(String.valueOf(pageNum),unkey);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        mysalaries_lv.stopRefresh();
        mysalaries_lv.stopLoadMore();
        mysalaries_lv.setRefreshTime("刚刚");
    }
}
