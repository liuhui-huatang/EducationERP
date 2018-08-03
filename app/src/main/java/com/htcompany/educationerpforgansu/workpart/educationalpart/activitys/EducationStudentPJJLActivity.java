package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

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
 * 学生评教记录
 * Created by wrb on 2016/11/25.
 */
public class EducationStudentPJJLActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView studnetpjjl_lv;
    private EducationStudentPJJLAdapter pjjlAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationstudentpjjl_activity);
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        studnetpjjl_lv = (ListView)this.findViewById(R.id.studnetpjjl_lv);
        pjjlAdapter = new EducationStudentPJJLAdapter(this);
        studnetpjjl_lv.setAdapter(pjjlAdapter);
    }
    public void initViewVlaues(){
        title.setText("学生评教记录");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        studnetpjjl_lv.setOnItemClickListener(trainingClicer);
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
