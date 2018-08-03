package com.htcompany.educationerpforgansu.workpart.techarepart.dailogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.views.TimeTableModel;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.StudentRollBookActivity;


/**
 * 课程详情
 * Created by wrb on 2017/1/16.
 */
public class ClassTableDetialsDailog extends AlertDialog implements View.OnClickListener{
    private Context context;
    LayoutInflater inflater;
    private TimeTableModel timeTableModel;
    private TextView classtabledailog_close_tv,classtabledailog_name_tv,classtabledailog_room_tv,classtabledailog_jc_tv,classtabledailog_time_tv;
    private TextView classtabledailog_roll_tv;//点名
    public ClassTableDetialsDailog(Context context, TimeTableModel timeTableModel) {
        super(context);
        this.context = context;
        this.timeTableModel = timeTableModel;
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.classtabledetials_dailog);
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
        initViews();
        initDatas();
    }
    public void initViews(){
        classtabledailog_close_tv=(TextView)this.findViewById(R.id.classtabledailog_close_tv);
        classtabledailog_name_tv=(TextView)this.findViewById(R.id.classtabledailog_name_tv);
        classtabledailog_room_tv=(TextView)this.findViewById(R.id.classtabledailog_room_tv);
        classtabledailog_jc_tv=(TextView)this.findViewById(R.id.classtabledailog_jc_tv);
        classtabledailog_time_tv=(TextView)this.findViewById(R.id.classtabledailog_time_tv);
        classtabledailog_roll_tv = (TextView)this.findViewById(R.id.classtabledailog_roll_tv);
        if("1".equals(timeTableModel.getIstodm())){
            classtabledailog_roll_tv.setVisibility(View.VISIBLE);
        }else{
            classtabledailog_roll_tv.setVisibility(View.GONE);
        }
        classtabledailog_roll_tv.setOnClickListener(this);
        classtabledailog_close_tv.setOnClickListener(this);
    }
    public void initDatas(){
        if(timeTableModel!=null){
            classtabledailog_name_tv.setText(timeTableModel.getName());
            classtabledailog_room_tv.setText(timeTableModel.getClassroom());
            classtabledailog_jc_tv.setText("第"+timeTableModel.getStartnum()+"节");
            classtabledailog_time_tv.setText("星期"+timeTableModel.getWeek()+"-"+timeTableModel.getStarttime());
        }
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()){
            case R.id.classtabledailog_close_tv:
                this.dismiss();
                break;
            case R.id.classtabledailog_roll_tv:
                //跳到学生点名界面
                Intent intent = new Intent(context, StudentRollBookActivity.class);
                intent.putExtra("id",timeTableModel.getId());
                context.startActivity(intent);
                this.dismiss();
                break;
        }
    }
}