package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.MainNewsMoreAdapter;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;
import com.htcompany.educationerpforgansu.workpart.entities.DYNewsEntity;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.ReturnMoneyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多新闻列表
 * Created by weiruibin on 2017/7/10.
 */

public class MainNewsMoreActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private XListView mainnewsmore_lv;
    private MainNewsMoreAdapter newsMoreAdapter;
    private ArrayList<DYNewsEntity> allnewsList=new ArrayList<DYNewsEntity>();

    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainnewsmore_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclics();
    }
    public void initDatas(){
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        //请求新闻
        wokrpersonalInternet.getAllDyNews_Datas("",String.valueOf(pageNum));
    }
    public void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        mainnewsmore_lv=(XListView)this.findViewById(R.id.mainnewsmore_lv);
        mainnewsmore_lv.setPullRefreshEnable(true);
        mainnewsmore_lv.setPullLoadEnable(false);
        mainnewsmore_lv.setXListViewListener(this);
        newsMoreAdapter = new MainNewsMoreAdapter(this,allnewsList);
        mainnewsmore_lv.setAdapter(newsMoreAdapter);
        mainnewsmore_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DYNewsEntity entity = (DYNewsEntity) newsMoreAdapter.getItem(position-1);
                startActivity(new Intent(MainNewsMoreActivity.this, MainNewsDetailsActivity.class)
                        .putExtra("entity",entity));
            }
        });
    }
    public void initViewVlaues(){
        title.setText("校园新闻");
    }
    public void initOnclics(){
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
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                        ToastUtil.showToast("请求网络失败", MainNewsMoreActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("请求网络失败", MainNewsMoreActivity.this);
                    break;
                case 204:
                    //处理德育新闻数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DYNewsEntity> newsAlldatas = wokrpersonalPersener.parseDYnewsData((String)msg.obj);
                    if(newsAlldatas!=null&&newsAlldatas.size()>0) {
                        setLVDatas(newsAlldatas);
                    }else{
                        mainnewsmore_lv.setPullLoadEnable(true);
                        stopListView();
                    }
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<DYNewsEntity> datas){
        if(pageNum==1){
            if(allnewsList.size()>0){
                allnewsList.clear();
            }
        }
        if(datas.size()>0){
            mainnewsmore_lv.setPullLoadEnable(true);
        }
        for(DYNewsEntity entity:datas){
            allnewsList.add(entity);
        }
        newsMoreAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        wokrpersonalInternet.getAllDyNews_Datas("",String.valueOf(pageNum));
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        wokrpersonalInternet.getAllDyNews_Datas("",String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        mainnewsmore_lv.stopRefresh();
        mainnewsmore_lv.stopLoadMore();
        mainnewsmore_lv.setRefreshTime("刚刚");
    }
}
