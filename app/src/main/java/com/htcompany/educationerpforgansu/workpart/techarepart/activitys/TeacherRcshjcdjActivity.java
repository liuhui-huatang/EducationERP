package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenu;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuCreator;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuItem;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuListView;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentMessageEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeacherRcshjcdjAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNameEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherRcshjcdjEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 日常事务检查登记
 * Created by wrb on 2016/11/29.
 */
public class TeacherRcshjcdjActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private XListView jsrcswjcdj_lv;
    private TeacherRcshjcdjAdapter rcshjcdjAdapter;
    private List<TeacherRcshjcdjEntity> rcshjcdjEntities;
    private ImageView jsrcswjcdj_dbsx_wsj_img;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherrcshjcdj_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        rcshjcdjEntities= new ArrayList<TeacherRcshjcdjEntity>();
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getTeacherRCSWDJListDatas(String.valueOf(pageNum));
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        jsrcswjcdj_dbsx_wsj_img=(ImageView)this.findViewById(R.id.jsrcswjcdj_dbsx_wsj_img);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        jsrcswjcdj_lv = (XListView) this.findViewById(R.id.jsrcswjcdj_lv);
        jsrcswjcdj_lv.setPullRefreshEnable(true);
        jsrcswjcdj_lv.setPullLoadEnable(false);
        jsrcswjcdj_lv.setXListViewListener(this);
        rcshjcdjAdapter = new TeacherRcshjcdjAdapter(this,rcshjcdjEntities);
        jsrcswjcdj_lv.setAdapter(rcshjcdjAdapter);
    }

    public void initViewValues() {
        title.setText("日常事务检查登记");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //调到添加公告界面
                intent = new Intent(TeacherRcshjcdjActivity.this, TeacherRcshjcdjAddActivity.class);
                startActivity(intent);
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
                    List<TeacherRcshjcdjEntity> datas = teacherPersener.parseRCSWDJLSITData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else{
                        if(rcshjcdjEntities.size()==0){
                            jsrcswjcdj_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            jsrcswjcdj_dbsx_wsj_img.setVisibility(View.GONE);
                        }
                        jsrcswjcdj_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeacherRcshjcdjActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(rcshjcdjEntities.size()==0){
                        jsrcswjcdj_dbsx_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        jsrcswjcdj_dbsx_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接服务器失败",TeacherRcshjcdjActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas(List<TeacherRcshjcdjEntity> datas ){
        if(pageNum==1){
            if (rcshjcdjEntities.size()>0){
                rcshjcdjEntities.clear();
            }
        }
        if(datas.size()>0){
            jsrcswjcdj_lv.setPullLoadEnable(true);
        }

        for(TeacherRcshjcdjEntity entity:datas){
            rcshjcdjEntities.add(entity);
        }
        if(rcshjcdjEntities.size()==0){
            jsrcswjcdj_dbsx_wsj_img.setVisibility(View.VISIBLE);
        }else {
            jsrcswjcdj_dbsx_wsj_img.setVisibility(View.GONE);
        }
        rcshjcdjAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        teacherInternet.getTeacherRCSWDJListDatas(String.valueOf(pageNum));
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        teacherInternet.getTeacherRCSWDJListDatas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        jsrcswjcdj_lv.stopRefresh();
        jsrcswjcdj_lv.stopLoadMore();
        jsrcswjcdj_lv.setRefreshTime("刚刚");
    }

}
