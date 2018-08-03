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
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentMessageEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeacherTrainAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherPXEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师培训
 * Created by wrb on 2016/11/22.
 */
public class TeacherTrainActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private XListView teachertrain_lv;
    private TeacherTrainAdapter trainAdapter;
    private List<TeacherPXEntity> pxEntities;
    private ImageView teachertrain_wsj_img;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachertrain_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        pxEntities = new ArrayList<TeacherPXEntity>();
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getTeacherTrainPlanLISTDatas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        teachertrain_wsj_img=(ImageView)this.findViewById(R.id.teachertrain_wsj_img);
        teachertrain_lv = (XListView)this.findViewById(R.id.teachertrain_lv);
        teachertrain_lv.setPullRefreshEnable(true);
        teachertrain_lv.setPullLoadEnable(false);
        teachertrain_lv.setXListViewListener(this);
        trainAdapter = new TeacherTrainAdapter(this,pxEntities);
        teachertrain_lv.setAdapter(trainAdapter);
    }
    public void initViewValues(){
        title.setText("教师培训");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        teachertrain_lv.setOnItemClickListener(trainclicer);
    }
    public AdapterView.OnItemClickListener trainclicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TeacherPXEntity entity = (TeacherPXEntity) trainAdapter.getItem(position-1);
            Intent intent = new Intent(TeacherTrainActivity.this,TeacherTrainDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivityForResult(intent,100);
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
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TeacherPXEntity> datas = teacherPersener.parseTeacherTrainPlanLISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else{
                        if(pxEntities.size()==0){
                            teachertrain_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            teachertrain_wsj_img.setVisibility(View.GONE);
                        }
                        teachertrain_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeacherTrainActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(pxEntities.size()==0){
                        teachertrain_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        teachertrain_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接服务器失败",TeacherTrainActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas(List<TeacherPXEntity> datas ){
        if(pageNum==1) {
            if (pxEntities.size() > 0) {
                pxEntities.clear();
            }
        }
        if(datas.size()>0){
            teachertrain_lv.setPullLoadEnable(true);
        }
        for(TeacherPXEntity entity:datas){
            pxEntities.add(entity);
        }
        if(pxEntities.size()==0){
            teachertrain_wsj_img.setVisibility(View.VISIBLE);
        }else {
            teachertrain_wsj_img.setVisibility(View.GONE);
        }
        trainAdapter.notifyDataSetChanged();
        stopListView();
    }

    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<StudentMessageEntity> datas){

    }

    @Override
    public void onRefresh() {
        pageNum=1;
        teacherInternet.getTeacherTrainPlanLISTDatas(String.valueOf(pageNum));
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        teacherInternet.getTeacherTrainPlanLISTDatas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        teachertrain_lv.stopRefresh();
        teachertrain_lv.stopLoadMore();
        teachertrain_lv.setRefreshTime("刚刚");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pageNum=1;
        teacherInternet.getTeacherTrainPlanLISTDatas(String.valueOf(pageNum));
    }
}
