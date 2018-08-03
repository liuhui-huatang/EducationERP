package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.workpart.activitys.AllClassActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationStudentPJAddBJMDAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生评教添加界面
 * Created by wrb on 2016/11/25.
 */
public class EducationStudentPJAddctivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView studentpjadd_pjxq_tv,studentpjadd_starttime_tv,studentpjadd_endtime_tv;
    private EditText studentpjadd_wjbt_edt,studentpjadd_fzdw_edt,studentpjadd_zsjbjmc_edt;
    private RelativeLayout studentpjadd_bjmd_rel;
    private GDGridView studentpjadd_bjmd_grd;
    private EducationStudentPJAddBJMDAdapter bjmdAdapter;
    private List<AllClassEntity> classEntities;
    private DateViewDailog dateViewDailog;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationstudentpjadd_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        classEntities  = new ArrayList<AllClassEntity>();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);

        studentpjadd_pjxq_tv = (TextView)this.findViewById(R.id.studentpjadd_pjxq_tv);
        studentpjadd_starttime_tv = (TextView)this.findViewById(R.id.studentpjadd_starttime_tv);
        studentpjadd_endtime_tv = (TextView)this.findViewById(R.id.studentpjadd_endtime_tv);

        studentpjadd_wjbt_edt=(EditText)this.findViewById(R.id.studentpjadd_wjbt_edt);
        studentpjadd_fzdw_edt=(EditText)this.findViewById(R.id.studentpjadd_fzdw_edt);
        studentpjadd_zsjbjmc_edt=(EditText)this.findViewById(R.id.studentpjadd_zsjbjmc_edt);

        studentpjadd_bjmd_rel = (RelativeLayout)this.findViewById(R.id.studentpjadd_bjmd_rel);
        studentpjadd_bjmd_grd = (GDGridView)this.findViewById(R.id.studentpjadd_bjmd_grd);
        bjmdAdapter = new EducationStudentPJAddBJMDAdapter(this,classEntities);
        studentpjadd_bjmd_grd.setAdapter(bjmdAdapter);
    }
    public void initViewVlaues(){
        title.setText("添加评教");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        studentpjadd_starttime_tv.setOnClickListener(this);
        studentpjadd_endtime_tv.setOnClickListener(this);
        studentpjadd_bjmd_rel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.studentpjadd_starttime_tv:
                //开始时间
                dateViewDailog = new DateViewDailog(EducationStudentPJAddctivity.this,1000,myHandler,false);
                dateViewDailog.show();
                break;
            case R.id.studentpjadd_endtime_tv:
                //结束时间
                dateViewDailog = new DateViewDailog(EducationStudentPJAddctivity.this,2000,myHandler,false);
                dateViewDailog.show();
                break;
            case R.id.studentpjadd_bjmd_rel:
                //班级名单
                intent = new Intent(EducationStudentPJAddctivity.this,AllClassActivity.class);
                startActivityForResult(intent,102);
                break;
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    //开始时间
                    studentpjadd_starttime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 2000:
                    //结束时间
                    studentpjadd_endtime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (requestCode){
                case 102:
                    //班级名单
                    List<AllClassEntity> datas = (List<AllClassEntity>) data.getSerializableExtra("classdatas");
                    if(datas!=null&&datas.size()>0){
                        if(classEntities.size()>0){
                            classEntities.clear();
                        }
                        for(AllClassEntity personEntity:datas){
                            classEntities.add(personEntity);
                        }
                        bjmdAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
}
