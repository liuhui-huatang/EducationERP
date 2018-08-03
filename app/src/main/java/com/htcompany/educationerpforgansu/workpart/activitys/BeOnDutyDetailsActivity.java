package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.entities.BeOnDutyEntity;

/**
 * 值班详情界面
 * Created by wrb on 2017/4/24.
 */
public class BeOnDutyDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView beondutydetial_term_tv,beondutydetial_jg_tv,beondutydetial_status_tv,
            beondutydetial_starttiem_tv, beondutydetial_endtime_tv,beondutydetial_content_tv,
            beondutydetial_zdr_tv;
    private BeOnDutyEntity carEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beondutydetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        carEntity = (BeOnDutyEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        beondutydetial_term_tv= (TextView)this.findViewById(R.id.beondutydetial_term_tv);
        beondutydetial_jg_tv= (TextView)this.findViewById(R.id.beondutydetial_jg_tv);
        beondutydetial_status_tv= (TextView)this.findViewById(R.id.beondutydetial_status_tv);
        beondutydetial_starttiem_tv= (TextView)this.findViewById(R.id.beondutydetial_starttiem_tv);
        beondutydetial_endtime_tv= (TextView)this.findViewById(R.id.beondutydetial_endtime_tv);
        beondutydetial_content_tv= (TextView)this.findViewById(R.id.beondutydetial_content_tv);
        beondutydetial_zdr_tv= (TextView)this.findViewById(R.id.beondutydetial_zdr_tv);
    }
    public void initViewValues(){
        title.setText("值班详情");
        if(carEntity!=null){
            beondutydetial_term_tv.setText(carEntity.getTerm());
            beondutydetial_jg_tv.setText(carEntity.getJgname());
            beondutydetial_status_tv.setText(carEntity.getTrbd_status());
            beondutydetial_starttiem_tv.setText(carEntity.getStart_time());
            beondutydetial_endtime_tv.setText(carEntity.getEnd_time());
            beondutydetial_content_tv.setText(carEntity.getContent());
            beondutydetial_zdr_tv.setText(carEntity.getUsername());
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
