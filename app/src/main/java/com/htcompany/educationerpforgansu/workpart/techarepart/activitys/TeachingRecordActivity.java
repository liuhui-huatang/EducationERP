package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

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
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeachingRecordAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingRecordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 授课记录
 * Created by wrb on 2016/11/14.
 */
public class TeachingRecordActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView teachingrecord_wsj_img;
    private XListView teachingrecord_lv;
    private TeachingRecordAdapter recordAdapter;
    private List<TeachingRecordEntity> recordEntities;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachingrecord_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        recordEntities = new ArrayList<TeachingRecordEntity>();
        teacherInternet = new WorkTeacherInternet(this,myHandler);
        teacherPersener = new WorkTeacherPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getTeachingRecordDatas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        teachingrecord_wsj_img=(ImageView)this.findViewById(R.id.teachingrecord_wsj_img);
        teachingrecord_lv = (XListView)this.findViewById(R.id.teachingrecord_lv);
        teachingrecord_lv.setPullRefreshEnable(true);
        teachingrecord_lv.setPullLoadEnable(false);
        teachingrecord_lv.setXListViewListener(this);
        recordAdapter = new TeachingRecordAdapter(this,recordEntities);
        teachingrecord_lv.setAdapter(recordAdapter);
        teachingrecord_lv.setOnItemClickListener(recordClicer);
    }
    public void initViewValues(){
        title.setText("授课记录");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener recordClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TeachingRecordEntity entity = (TeachingRecordEntity) recordAdapter.getItem(position-1);
            Intent intent = new Intent(TeachingRecordActivity.this,TeachingRecordDetailsActivity.class);
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
                    if(recordEntities.size()==0){
                        teachingrecord_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        teachingrecord_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",TeachingRecordActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TeachingRecordEntity> datas = teacherPersener.parseTeachingRecordData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(recordEntities.size()==0){
                            teachingrecord_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            teachingrecord_wsj_img.setVisibility(View.GONE);
                        }
                        teachingrecord_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingRecordActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<TeachingRecordEntity> datas){
        if(pageNum==1){
            if(recordEntities.size()>0){
                recordEntities.clear();
            }
        }
        if(datas.size()>0){
            teachingrecord_lv.setPullLoadEnable(true);
        }
        for(TeachingRecordEntity entity:datas){
            recordEntities.add(entity);
        }
        if(recordEntities.size()==0){
            teachingrecord_wsj_img.setVisibility(View.VISIBLE);
        }else {
            teachingrecord_wsj_img.setVisibility(View.GONE);
        }
        recordAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        teacherInternet.getTeachingRecordDatas(String.valueOf(pageNum));
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        teacherInternet.getTeachingRecordDatas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        teachingrecord_lv.stopRefresh();
        teachingrecord_lv.stopLoadMore();
        teachingrecord_lv.setRefreshTime("刚刚");
    }
}
