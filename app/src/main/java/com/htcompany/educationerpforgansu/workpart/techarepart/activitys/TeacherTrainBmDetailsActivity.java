package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;

/**
 * 计划详情
 * Created by wrb on 2016/11/30.
 */
public class TeacherTrainBmDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout edu_jspxxm_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationteachertrainingdtails_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        edu_jspxxm_ll = (LinearLayout)this.findViewById(R.id.edu_jspxxm_ll);
    }
    public void initViewValues(){
        title.setText("计划详情");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        edu_jspxxm_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.edu_jspxxm_ll:
                Intent intent = new Intent(TeacherTrainBmDetailsActivity.this,TeacherTrainBmProjectDetailsActivty.class);
                startActivity(intent);
                break;
        }
    }
}