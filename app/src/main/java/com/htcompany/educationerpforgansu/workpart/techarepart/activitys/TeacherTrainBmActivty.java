package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationTeacherTrainingAdapter;

/**
 * 教师培训
 * Created by wrb on 2016/11/30.
 */
public class TeacherTrainBmActivty extends BaseActivity implements View.OnClickListener{

    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private ListView teachertraining_lv;
    private EducationTeacherTrainingAdapter trainingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationteachertraining_activity);
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn= (RelativeLayout)this.findViewById(R.id.right_three_btn);
        rightthree_btn_tv= (TextView)this.findViewById(R.id.rightthree_btn_tv);
        teachertraining_lv = (ListView)this.findViewById(R.id.teachertraining_lv);
//        trainingAdapter = new EducationTeacherTrainingAdapter(this,);
//        teachertraining_lv.setAdapter(trainingAdapter);
    }
    public void initViewVlaues(){
        title.setText("教师培训");
//        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        teachertraining_lv.setOnItemClickListener(trainingClicer);
    }
    public AdapterView.OnItemClickListener trainingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //跳到培训计划详情界面
            Intent intent = new Intent(TeacherTrainBmActivty.this,TeacherTrainBmDetailsActivity.class);
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //跳到添加培训计划界面
//                Intent intent = new Intent(EducationTeacherTrainingActivity.this,EducationTeacherTrainingAddActivity.class);
//                startActivity(intent);
                break;
        }
    }
}
