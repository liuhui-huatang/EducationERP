package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.StudentRollBookAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassDMCEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生点名册
 * Created by wrb on 2017/1/19.
 */
public class StudentRollBookActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView studentrollbook_lv;
    private StudentRollBookAdapter rollBookAdapter;
    private String id ="";
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private List<ClassDMCEntity> dmcEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentrollbook_activity);
        initDatas();
        initView();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        dmcEntities = new ArrayList<ClassDMCEntity>();
        id = getIntent().getStringExtra("id");
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getClassDMCDatas(id);
    }
    public void initView(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        studentrollbook_lv = (ListView)this.findViewById(R.id.studentrollbook_lv);
        rollBookAdapter = new StudentRollBookAdapter(this,dmcEntities,teacherInternet,id);
        studentrollbook_lv.setAdapter(rollBookAdapter);
    }
    public void initViewVlaues(){
        title.setText("点名册");
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
                    List<ClassDMCEntity> datas = teacherPersener.parseClassDMCData((String)msg.obj);
                    if(datas!=null&&datas.size()>0) {
                        initlistDatas(datas);
                    }else {
                        ToastUtil.showToast("暂无数据",StudentRollBookActivity.this);
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
                case 201:
                    //点名返回数据
                    waitDialog.show();
                    teacherInternet.getClassDMCDatas(id);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentRollBookActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",StudentRollBookActivity.this);
                    break;
            }
        }
    };
    public void initlistDatas(List<ClassDMCEntity> datas){
        if(dmcEntities.size()>0){
            dmcEntities.clear();
        }
        for(ClassDMCEntity entity:datas){
            dmcEntities.add(entity);
        }
        rollBookAdapter.notifyDataSetChanged();
    }
}
