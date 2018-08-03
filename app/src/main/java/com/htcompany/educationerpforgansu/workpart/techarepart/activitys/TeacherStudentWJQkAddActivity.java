package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;

/**
 * 添加学生违纪
 * Created by wrb on 2016/11/30.
 */
public class TeacherStudentWJQkAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;

    private TextView studentwjqkadd_term_tv,studentwjqkadd_name_tv,studentwjqkadd_time_tv;
    private EditText studentwjqkadd_jg_edt,studentwjqkadd_xg_tv;
    private DateViewDailog dateViewDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherstudentwjqkadd_activity);
        initViews();
        initViewValues();
        initOnclicerEvent();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        studentwjqkadd_term_tv= (TextView)this.findViewById(R.id.studentwjqkadd_term_tv);
        studentwjqkadd_name_tv= (TextView)this.findViewById(R.id.studentwjqkadd_name_tv);
        studentwjqkadd_time_tv= (TextView)this.findViewById(R.id.studentwjqkadd_time_tv);
        studentwjqkadd_jg_edt=(EditText)this.findViewById(R.id.studentwjqkadd_jg_edt);
        studentwjqkadd_xg_tv=(EditText)this.findViewById(R.id.studentwjqkadd_xg_tv);
    }
    public void initViewValues(){
        title.setText("违纪添加");
    }
    public void initOnclicerEvent(){
        reback_btn.setOnClickListener(this);
        studentwjqkadd_time_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.studentwjqkadd_time_tv:
                //违纪时间选择
                dateViewDailog = new DateViewDailog(TeacherStudentWJQkAddActivity.this,1000,myHandler,false);
                dateViewDailog.show();
                break;
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    studentwjqkadd_time_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
            }
        }
    };
}
