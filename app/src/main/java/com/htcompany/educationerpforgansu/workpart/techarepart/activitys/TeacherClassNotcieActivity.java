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
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.studentpart.activitys.StudentNoticesDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeacherClassNotcieAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNotcieEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级公告
 * Created by wrb on 2016/11/29.
 */
public class TeacherClassNotcieActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private SwipeMenuListView studnetnotices_lv;
    private TeacherClassNotcieAdapter noticesAdapters;
    private List<ClassNotcieEntity> notcieEntities;
    Intent intent = null;
    private ImageView studnetnotices_wsj_img;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private ClassNotcieEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentnotices_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        notcieEntities = new ArrayList<ClassNotcieEntity>();
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getAllClassNoticeListDatas();
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        studnetnotices_wsj_img=(ImageView)this.findViewById(R.id.studnetnotices_wsj_img);
        studnetnotices_lv = (SwipeMenuListView) this.findViewById(R.id.studnetnotices_lv);
        noticesAdapters = new TeacherClassNotcieAdapter(this,notcieEntities);
        studnetnotices_lv.setAdapter(noticesAdapters);
    }

    public void initViewValues() {
        title.setText("班级公告");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        studnetnotices_lv.setOnItemClickListener(bookManageClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到公告详情界面
            entity = (ClassNotcieEntity) noticesAdapters.getItem(position);
            intent = new Intent(TeacherClassNotcieActivity.this, StudentNoticesDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivityForResult(intent,101);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //调到添加公告界面
                intent = new Intent(TeacherClassNotcieActivity.this, TeacherClassNotcieAddActivity.class);
                startActivityForResult(intent,101);
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
                    List<ClassNotcieEntity> datas = teacherPersener.parseCLassNoticeListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else {
                        if(notcieEntities.size()==0){
                            studnetnotices_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            studnetnotices_wsj_img.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeacherClassNotcieActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(notcieEntities.size()==0){
                        studnetnotices_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        studnetnotices_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接服务器失败",TeacherClassNotcieActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas(List<ClassNotcieEntity> datas ){
        if (notcieEntities.size()>0){
            notcieEntities.clear();
        }
        for(ClassNotcieEntity entity:datas){
            notcieEntities.add(entity);
        }
        if(notcieEntities.size()==0){
            studnetnotices_wsj_img.setVisibility(View.VISIBLE);
        }else {
            studnetnotices_wsj_img.setVisibility(View.GONE);
        }
        noticesAdapters.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    teacherInternet.getAllClassNoticeListDatas();
                    break;
            }
        }
    }


}
