package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;

/**
 * 教师培训计划添加
 * Created by wrb on 2016/11/25.
 */
public class EducationTeacherTrainingAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout educationjspxxx_add_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationteachertrainingadd_activity);
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        educationjspxxx_add_ll=(LinearLayout)this.findViewById(R.id.educationjspxxx_add_ll);
    }
    public void initViewVlaues(){
        title.setText("添加培训计划");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        educationjspxxx_add_ll.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.educationjspxxx_add_ll:
                Intent intent = new Intent(EducationTeacherTrainingAddActivity.this,EducationTeacherTrainingProjectAddActivity.class);
                startActivity(intent);
                break;
        }
    }
}
