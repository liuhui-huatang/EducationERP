package com.htcompany.educationerpforgansu.workpart.financepart.activitys;

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
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.homepart.activitys.MySalariesDetailsActivity;
import com.htcompany.educationerpforgansu.internet.Finalcepart.FinalcepartInternet;
import com.htcompany.educationerpforgansu.internet.Finalcepart.FinalcepartPersener;
import com.htcompany.educationerpforgansu.workpart.financepart.adapters.FinalcepartSalaryAdapter;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.SalaryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 工资表-财务 （与我的工资共用同一界面
 * Created by wrb on 2016/11/16.
 */
public class FinalcepartSalaryActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reabck_btn;
    private ImageView finalsalaries_wsj_img;
    private XListView finalsalaries_lv;
    private FinalcepartSalaryAdapter salariesAdapter;
    //网络请求类
    private FinalcepartInternet finalcepartInternet;
    private FinalcepartPersener finalcepartPersener;
    private WaitDialog waitDialog=null;
    private List<SalaryEntity> salaryEntities;
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalcepartsalary_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        salaryEntities = new ArrayList<SalaryEntity>();
        finalcepartInternet = new FinalcepartInternet(this,myHandler);
        finalcepartPersener = new FinalcepartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        finalcepartInternet.getSarlarysDatas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reabck_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        finalsalaries_wsj_img=(ImageView)this.findViewById(R.id.finalsalaries_wsj_img);
        finalsalaries_lv = (XListView) this.findViewById(R.id.finalsalaries_lv);
        finalsalaries_lv.setPullRefreshEnable(true);
        finalsalaries_lv.setPullLoadEnable(false);
        finalsalaries_lv.setXListViewListener(this);
        salariesAdapter = new FinalcepartSalaryAdapter(this,salaryEntities);
        finalsalaries_lv.setAdapter(salariesAdapter);
        finalsalaries_lv.setOnItemClickListener(salariesClicker);
    }
    public void initViewValues(){
        title.setText("工资表");
    }
    public void initOnclicEvents(){
        reabck_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public AdapterView.OnItemClickListener salariesClicker = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SalaryEntity entity = (SalaryEntity) salariesAdapter.getItem(position-1);
            Intent intent = new Intent(FinalcepartSalaryActivity.this,MySalariesDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
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
                    if(salaryEntities.size()==0){
                        finalsalaries_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        finalsalaries_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",FinalcepartSalaryActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<SalaryEntity> datas = finalcepartPersener.parseSarilayData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(salaryEntities.size()==0){
                            finalsalaries_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            finalsalaries_wsj_img.setVisibility(View.GONE);
                        }
                        finalsalaries_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",FinalcepartSalaryActivity.this);
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
        if(datas.size()>0){
            finalsalaries_lv.setPullLoadEnable(true);
        }
        for(SalaryEntity entity:datas){
            salaryEntities.add(entity);
        }
        if(salaryEntities.size()==0){
            finalsalaries_wsj_img.setVisibility(View.VISIBLE);
        }else {
            finalsalaries_wsj_img.setVisibility(View.GONE);
        }
        salariesAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        finalcepartInternet.getSarlarysDatas(String.valueOf(pageNum));
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        finalcepartInternet.getSarlarysDatas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        finalsalaries_lv.stopRefresh();
        finalsalaries_lv.stopLoadMore();
        finalsalaries_lv.setRefreshTime("刚刚");
    }
}
