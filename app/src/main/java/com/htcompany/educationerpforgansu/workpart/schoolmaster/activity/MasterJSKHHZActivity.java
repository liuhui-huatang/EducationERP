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
import com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter.MasterJSKHHZAdapter;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.ImportTitleEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.JSKHHZEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师考核汇总
 * Created by wrb on 2017/4/6.
 */
public class MasterJSKHHZActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView bzrkhhz_wsj_img;
    private ListView bzrkhhz_lv;
    private MasterJSKHHZAdapter jskhhzAdapter;
    private TextView bzrkhhz_title_tv;
    //网络请求类
    private SchoolMaterInternet materInternet;
    private SchoolMaterPersener materPersener;
    private WaitDialog waitDialog=null;
//    private int pageNum=1;//页数
    private List<JSKHHZEntity> jskhhzEntities;
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
        jskhhzEntities = new ArrayList<JSKHHZEntity>();
        materInternet = new SchoolMaterInternet(this,myHandler);
        materPersener=new SchoolMaterPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        materInternet.getJSKHHZ_ListDatas(themeId);
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
        jskhhzAdapter = new MasterJSKHHZAdapter(this,jskhhzEntities);
        bzrkhhz_lv.setAdapter(jskhhzAdapter);
    }
    public void initViewValues(){
        title.setText("教师考核汇总");
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
                Intent intent = new Intent(MasterJSKHHZActivity.this, ImportTitleSelectActivity.class);
                intent.putExtra("flag","PTJS");
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
                        if(jskhhzEntities.size()>0){
                            jskhhzEntities.clear();
                        }
                        jskhhzAdapter.notifyDataSetChanged();
                        bzrkhhz_title_tv.setText(entity.getName());
                        themeId = entity.getId();
                        waitDialog.show();
                        materInternet.getJSKHHZ_ListDatas(themeId);
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
                    ToastUtil.showToast("连接超时",MasterJSKHHZActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<JSKHHZEntity> datas = materPersener.parseJSKHHZ_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        bzrkhhz_wsj_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        bzrkhhz_wsj_img.setVisibility(View.VISIBLE);
//                        bzrkhhz_lv.setPullLoadEnable(false);
//                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MasterJSKHHZActivity.this);
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
    public void setLVDatas(List<JSKHHZEntity> datas){
//        if(pageNum==1){
            if(jskhhzEntities.size()>0){
                jskhhzEntities.clear();
            }
//        }
//        if(datas.size()>10){
//            bzrkhhz_lv.setPullLoadEnable(true);
//        }
        for(JSKHHZEntity entity:datas){
            jskhhzEntities.add(entity);
        }
        jskhhzAdapter.notifyDataSetChanged();
//        stopListView();
    }
//    /**
//     * 下拉刷新
//     */
//    @Override
//    public void onRefresh() {
//        pageNum=1;
//        materInternet.getJSKHHZ_ListDatas(String.valueOf(pageNum));
//    }
//
//    /**
//     * 上拉加载更多
//     */
//    @Override
//    public void onLoadMore() {
//        pageNum++;
//        materInternet.getJSKHHZ_ListDatas(String.valueOf(pageNum));
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
