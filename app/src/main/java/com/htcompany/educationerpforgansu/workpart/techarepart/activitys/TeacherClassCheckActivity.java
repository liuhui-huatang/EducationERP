package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeacherClassCheckAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassCheckEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassDMCEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级考核
 * Created by wrb on 2016/11/30.
 */
public class TeacherClassCheckActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView eacherclasscheck_wsj_img;
    private ListView teacherclasscheck_lv;
    private TeacherClassCheckAdapter classCheckAdapter;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;

    private List<ClassCheckEntity> classCheckEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherclasscheck_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        classCheckEntities = new ArrayList<ClassCheckEntity>();
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getClassCheackDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        eacherclasscheck_wsj_img=(ImageView)this.findViewById(R.id.eacherclasscheck_wsj_img);
        teacherclasscheck_lv = (ListView)this.findViewById(R.id.teacherclasscheck_lv);
        classCheckAdapter = new TeacherClassCheckAdapter(this,classCheckEntities);
        teacherclasscheck_lv.setAdapter(classCheckAdapter);
    }
    public void initViewValues(){
        title.setText("班级考核");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }

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
                    List<ClassCheckEntity> datas = teacherPersener.parseClassCheckData((String)msg.obj);
                    if(datas!=null&&datas.size()>0) {
                        eacherclasscheck_wsj_img.setVisibility(View.GONE);
                        initlistDatas(datas);
                    }else {
                        eacherclasscheck_wsj_img.setVisibility(View.VISIBLE);
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",TeacherClassCheckActivity.this);
                    break;
            }
        }
    };

    public void initlistDatas(List<ClassCheckEntity> datas){
        if(classCheckEntities.size()>0){
            classCheckEntities.clear();
        }
        for(ClassCheckEntity entity:datas){
            classCheckEntities.add(entity);
        }
        classCheckAdapter.notifyDataSetChanged();
    }
}
