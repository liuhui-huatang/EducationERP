package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingDetailsRYMDAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllPersonEntity;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 已提交详情
 * Created by wrb on 2016/11/10.
 */
public class MyMeetingIsUploadDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView meetingdetails_status_tv,meetingdetails_hyname_tv,meetingdetails_hycode_tv,meetingdetails_hystarttiem_tv,
            meetingdetails_hyendtime_tv,meetingdetails_hyadres_tv,meetingdetails_hyzcr_tv,meetingdetials_content_tv;
    private GDGridView meetindetails_rymd_grd;
    private MeetingDetailsRYMDAdapter rymdAdapter;
    private MyMeetingEntity myMeetingEntity;
    private List<AllPersonEntity> personEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetingisuploaddetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        myMeetingEntity = (MyMeetingEntity) getIntent().getSerializableExtra("entity");
        personEntities = new ArrayList<AllPersonEntity>();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);

        meetingdetails_status_tv = (TextView)this.findViewById(R.id.meetingdetails_status_tv);
        meetingdetails_hyname_tv= (TextView)this.findViewById(R.id.meetingdetails_hyname_tv);
        meetingdetails_hycode_tv= (TextView)this.findViewById(R.id.meetingdetails_hycode_tv);
        meetingdetails_hystarttiem_tv= (TextView)this.findViewById(R.id.meetingdetails_hystarttiem_tv);
        meetingdetails_hyendtime_tv= (TextView)this.findViewById(R.id.meetingdetails_hyendtime_tv);
        meetingdetails_hyadres_tv= (TextView)this.findViewById(R.id.meetingdetails_hyadres_tv);
        meetingdetails_hyzcr_tv= (TextView)this.findViewById(R.id.meetingdetails_hyzcr_tv);
        meetingdetials_content_tv= (TextView)this.findViewById(R.id.meetingdetials_content_tv);
        meetindetails_rymd_grd = (GDGridView)this.findViewById(R.id.meetindetails_rymd_grd);
//        rymdAdapter = new MeetingDetailsRYMDAdapter(MyMeetingIsUploadDetailsActivity.this,personEntities);
//        meetindetails_rymd_grd.setAdapter(rymdAdapter);
    }
    public void initViewValues(){
        title.setText("会议详情");
//        if(myMeetingEntity!=null){
//            meetingdetails_status_tv.setText("待审核");
//            meetingdetails_hyname_tv.setText(myMeetingEntity.getMname());
//            meetingdetails_hycode_tv.setText(myMeetingEntity.getMcode());
//            meetingdetails_hystarttiem_tv.setText(myMeetingEntity.getMstarttime());
//            meetingdetails_hyendtime_tv.setText(myMeetingEntity.getMendtime());
//            meetingdetails_hyadres_tv.setText(myMeetingEntity.getMaddress());
//            meetingdetails_hyzcr_tv.setText(myMeetingEntity.getMzcr());
//            meetingdetials_content_tv.setText(myMeetingEntity.getMcontent());
//            if(myMeetingEntity.getMchrys()!=null){
//                for(AllPersonEntity entity:myMeetingEntity.getMchrys()){
//                    personEntities.add(entity);
//                    rymdAdapter.notifyDataSetChanged();
//                }
//            }
//        }

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
