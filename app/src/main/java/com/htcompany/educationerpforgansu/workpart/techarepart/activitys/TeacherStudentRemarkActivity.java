package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.studentpart.adapters.EducationStudentPJJLAdapter;

/**
 * 学生评教
 * Created by wrb on 2016/11/29.
 */
public class TeacherStudentRemarkActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView teacherstudnetpjjl_lv;
    private EducationStudentPJJLAdapter pjjlAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherstudentremark_activity);
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        teacherstudnetpjjl_lv = (ListView)this.findViewById(R.id.teacherstudnetpjjl_lv);
        pjjlAdapter = new EducationStudentPJJLAdapter(this);
        teacherstudnetpjjl_lv.setAdapter(pjjlAdapter);
    }
    public void initViewVlaues(){
        title.setText("学生评教");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        teacherstudnetpjjl_lv.setOnItemClickListener(trainingClicer);
    }
    public AdapterView.OnItemClickListener trainingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //跳到培训计划详情界面
//            Intent intent = new Intent(EducationTeacherTrainingActivity.this,EducationTeacherTrainingDtailsActivity.class);
//            startActivity(intent);
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
}
