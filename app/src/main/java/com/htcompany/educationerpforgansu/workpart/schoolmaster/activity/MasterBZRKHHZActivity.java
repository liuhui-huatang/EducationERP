package com.htcompany.educationerpforgansu.workpart.schoolmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.SchoolMaster.SchoolMaterInternet;
import com.htcompany.educationerpforgansu.internet.SchoolMaster.SchoolMaterPersener;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter.MasterBZRKHHZAdapter;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.BZRKHHZEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.ImportTitleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 班主任考核汇总界面
 * Created by wrb on 2017/4/6.
 */
public class MasterBZRKHHZActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView bzrkhhz_lv;
    private TextView bzrkhhz_title_tv;
    private ImageView bzrkhhz_wsj_img;
    private MasterBZRKHHZAdapter bzrkhhzAdapter;
    //网络请求类
    private SchoolMaterInternet materInternet;
    private SchoolMaterPersener materPersener;
    private WaitDialog waitDialog=null;
    private List<BZRKHHZEntity> bzrkhhzEntities;
    private String themeId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_bzrkhhz_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        bzrkhhzEntities = new ArrayList<BZRKHHZEntity>();
        materInternet = new SchoolMaterInternet(this,myHandler);
        materPersener=new SchoolMaterPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        materInternet.getBZRKHHZ_ListDatas(themeId);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        bzrkhhz_wsj_img=(ImageView)this.findViewById(R.id.bzrkhhz_wsj_img);
        bzrkhhz_title_tv = (TextView)this.findViewById(R.id.bzrkhhz_title_tv);
        bzrkhhz_lv = (ListView)this.findViewById(R.id.bzrkhhz_lv);
//        bzrkhhz_lv.setPullRefreshEnable(true);
//        bzrkhhz_lv.setPullLoadEnable(false);
//        bzrkhhz_lv.setXListViewListener(this);
        bzrkhhzAdapter = new MasterBZRKHHZAdapter(this,bzrkhhzEntities);
        bzrkhhz_lv.setAdapter(bzrkhhzAdapter);
    }
    public void initViewValues(){
        title.setText("班主任考核汇总");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        bzrkhhz_title_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.bzrkhhz_title_tv:
                Intent intent = new Intent(MasterBZRKHHZActivity.this, ImportTitleSelectActivity.class);
                intent.putExtra("flag","BZR");
                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    ImportTitleEntity entity = (ImportTitleEntity) data.getSerializableExtra("entity");
                    if(entity!=null){
                        bzrkhhz_title_tv.setText(entity.getName());
                        themeId = entity.getId();
                        if(bzrkhhzEntities.size()>0){
                            bzrkhhzEntities.clear();
                        }
                        bzrkhhzAdapter.notifyDataSetChanged();
                        waitDialog.show();
                        materInternet.getBZRKHHZ_ListDatas(themeId);
                    }
                    break;
            }
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
                    ToastUtil.showToast("连接超时",MasterBZRKHHZActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<BZRKHHZEntity> datas = materPersener.parseBZRKHHZ_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        bzrkhhz_wsj_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
//                        bzrkhhz_lv.setPullLoadEnable(false);
//                        stopListView();
                        bzrkhhz_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MasterBZRKHHZActivity.this);
                    break;
                case 2000:
                    bzrkhhz_title_tv.setText((String)msg.obj);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<BZRKHHZEntity> datas){
//        if(pageNum==1){
            if(bzrkhhzEntities.size()>0){
                bzrkhhzEntities.clear();
            }
//        }
//        if(datas.size()>10){
//            bzrkhhz_lv.setPullLoadEnable(true);
//        }
        for(BZRKHHZEntity entity:datas){
            bzrkhhzEntities.add(entity);
        }
        bzrkhhzAdapter.notifyDataSetChanged();
//        stopListView();
    }
//    /**
//     * 下拉刷新
//     */
//    @Override
//    public void onRefresh() {
//        pageNum=1;
//        materInternet.getBZRKHHZ_ListDatas(String.valueOf(pageNum));
//    }
//
//    /**
//     * 上拉加载更多
//     */
//    @Override
//    public void onLoadMore() {
//        pageNum++;
//        materInternet.getBZRKHHZ_ListDatas(String.valueOf(pageNum));
//    }
//
//    /**
//     * 停止列表刷新
//     */
//    public void stopListView() {
//        bzrkhhz_lv.stopRefresh();
//        bzrkhhz_lv.stopLoadMore();
//        bzrkhhz_lv.setRefreshTime("刚刚");
//    }
}
