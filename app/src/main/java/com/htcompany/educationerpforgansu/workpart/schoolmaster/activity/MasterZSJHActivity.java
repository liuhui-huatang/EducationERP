package com.htcompany.educationerpforgansu.workpart.schoolmaster.activity;

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
import com.htcompany.educationerpforgansu.internet.SchoolMaster.SchoolMaterInternet;
import com.htcompany.educationerpforgansu.internet.SchoolMaster.SchoolMaterPersener;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter.MasterZSJHAdapter;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.ZSJHEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 校长空间-招生计划
 * Created by wrb on 2017/1/3.
 */
public class MasterZSJHActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView bmaster_zsjh_img;
    private XListView zsjh_lv;
    private MasterZSJHAdapter zsjhAdapter;
    //网络请求类
    private SchoolMaterInternet materInternet;
    private SchoolMaterPersener materPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private List<ZSJHEntity> zsjhEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_zsjh_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        zsjhEntities = new ArrayList<ZSJHEntity>();
        materInternet = new SchoolMaterInternet(this,myHandler);
        materPersener=new SchoolMaterPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        materInternet.getZSJH_ListDatas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        bmaster_zsjh_img=(ImageView)this.findViewById(R.id.bmaster_zsjh_img);
        zsjh_lv = (XListView)this.findViewById(R.id.zsjh_lv);
        zsjh_lv.setPullRefreshEnable(true);
        zsjh_lv.setPullLoadEnable(false);
        zsjh_lv.setXListViewListener(this);
        zsjhAdapter = new MasterZSJHAdapter(this,zsjhEntities);
        zsjh_lv.setAdapter(zsjhAdapter);
    }
    public void initViewValues(){
        title.setText("招生计划");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        zsjh_lv.setOnItemClickListener(itemClickListener);
    }
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ZSJHEntity entity = (ZSJHEntity) zsjhAdapter.getItem(position-1);
            Intent intent = new Intent(MasterZSJHActivity.this,MasterZSJHDetailsActivity.class);
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
                    ToastUtil.showToast("连接超时",MasterZSJHActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ZSJHEntity> datas = materPersener.parseZSJH_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        bmaster_zsjh_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        zsjh_lv.setPullLoadEnable(false);
                        stopListView();
                        if(zsjhEntities.size()==0) {
                            bmaster_zsjh_img.setVisibility(View.VISIBLE);
                        }else {
                            bmaster_zsjh_img.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MasterZSJHActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<ZSJHEntity> datas){
        if(pageNum==1){
            if(zsjhEntities.size()>0){
                zsjhEntities.clear();
            }
        }
        if(datas.size()>0){
            zsjh_lv.setPullLoadEnable(true);
        }
        for(ZSJHEntity entity:datas){
            zsjhEntities.add(entity);
        }
        zsjhAdapter.notifyDataSetChanged();
        stopListView();

    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        materInternet.getZSJH_ListDatas(String.valueOf(pageNum));
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        materInternet.getZSJH_ListDatas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        zsjh_lv.stopRefresh();
        zsjh_lv.stopLoadMore();
        zsjh_lv.setRefreshTime("刚刚");
    }
}
