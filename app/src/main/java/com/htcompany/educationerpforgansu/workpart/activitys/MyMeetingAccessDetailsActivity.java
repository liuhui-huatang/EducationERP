package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingDetailsRYMDAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

/**
 * 已通过会议详情
 * Created by wrb on 2016/11/10.
 */
public class MyMeetingAccessDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView mymeetingaccess_shr_tv,mymeetingaccess_shtime_tv;

    private TextView meetingdetails_status_tv,meetingdetails_hyname_tv,meetingdetails_hycode_tv,meetingdetails_hystarttiem_tv,
            meetingdetails_hyendtime_tv,meetingdetails_hyadres_tv,meetingdetails_hyzcr_tv,meetingdetials_content_tv;
    private GDGridView meetindetails_rymd_grd;
    private MeetingDetailsRYMDAdapter rymdAdapter;
    private MyMeetingEntity myMeetingEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetingaccessdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        myMeetingEntity = (MyMeetingEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);

//        mymeetingaccess_shr_tv= (TextView)this.findViewById(R.id.mymeetingaccess_shr_tv);
//        mymeetingaccess_shtime_tv= (TextView)this.findViewById(R.id.mymeetingaccess_shtime_tv);

        meetingdetails_status_tv = (TextView)this.findViewById(R.id.meetingdetails_status_tv);
        meetingdetails_hyname_tv= (TextView)this.findViewById(R.id.meetingdetails_hyname_tv);
        meetingdetails_hycode_tv= (TextView)this.findViewById(R.id.meetingdetails_hycode_tv);
        meetingdetails_hystarttiem_tv= (TextView)this.findViewById(R.id.meetingdetails_hystarttiem_tv);
        meetingdetails_hyendtime_tv= (TextView)this.findViewById(R.id.meetingdetails_hyendtime_tv);
        meetingdetails_hyadres_tv= (TextView)this.findViewById(R.id.meetingdetails_hyadres_tv);
        meetingdetails_hyzcr_tv= (TextView)this.findViewById(R.id.meetingdetails_hyzcr_tv);
        meetingdetials_content_tv= (TextView)this.findViewById(R.id.meetingdetials_content_tv);
        meetindetails_rymd_grd = (GDGridView)this.findViewById(R.id.meetindetails_rymd_grd);
    }
    public void initViewValues(){
        title.setText("会议详情");
        if(myMeetingEntity!=null){
            meetingdetails_status_tv.setText(myMeetingEntity.getStatuss());
            meetingdetails_hyname_tv.setText(myMeetingEntity.getName());
            meetingdetails_hycode_tv.setText(myMeetingEntity.getNumber());
            meetingdetails_hystarttiem_tv.setText(myMeetingEntity.getStart());
            meetingdetails_hyendtime_tv.setText(myMeetingEntity.getEnd());
            meetingdetails_hyadres_tv.setText(myMeetingEntity.getRoom());
            meetingdetails_hyzcr_tv.setText(myMeetingEntity.getHost());
            meetingdetials_content_tv.setText(myMeetingEntity.getContent());
            if(myMeetingEntity.getMm_people_json()!=null&&myMeetingEntity.getMm_people_json().size()>0){
                rymdAdapter = new MeetingDetailsRYMDAdapter(MyMeetingAccessDetailsActivity.this,myMeetingEntity.getMm_people_json());
                meetindetails_rymd_grd.setAdapter(rymdAdapter);
            }
        }

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
}
