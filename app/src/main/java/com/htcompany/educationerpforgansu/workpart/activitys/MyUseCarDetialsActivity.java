package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkCarEntity;

/**
 * 用车详情
 * Created by wrb on 2016/11/10.
 */
public class MyUseCarDetialsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView myusecardetials_carname_tv,myusecardetials_carcode_tv,myusecardetials_sqsj_tv,myusecardetials_starttime_tv,
            myusecardetials_endtime_tv,myusecardetials_mdd_tv,myusecardetials_lcs_tv,myusecardetials_diver_tv,myusecardetials_ccly_tv,
            myusecardetials_SHR_tv,myusecardetials_shtime_tv,myusecardetials_wtgyy_tv;
    private TextView myusecardetials_statuss_tv;
    private WorkCarEntity carEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myusecardetials_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        carEntity = (WorkCarEntity) getIntent().getSerializableExtra("carEntity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        myusecardetials_carname_tv= (TextView)this.findViewById(R.id.myusecardetials_carname_tv);
        myusecardetials_carcode_tv= (TextView)this.findViewById(R.id.myusecardetials_carcode_tv);
        myusecardetials_sqsj_tv= (TextView)this.findViewById(R.id.myusecardetials_sqsj_tv);
        myusecardetials_starttime_tv= (TextView)this.findViewById(R.id.myusecardetials_starttime_tv);
        myusecardetials_endtime_tv= (TextView)this.findViewById(R.id.myusecardetials_endtime_tv);
        myusecardetials_mdd_tv= (TextView)this.findViewById(R.id.myusecardetials_mdd_tv);
        myusecardetials_lcs_tv= (TextView)this.findViewById(R.id.myusecardetials_lcs_tv);
        myusecardetials_diver_tv= (TextView)this.findViewById(R.id.myusecardetials_diver_tv);
        myusecardetials_ccly_tv= (TextView)this.findViewById(R.id.myusecardetials_ccly_tv);
        myusecardetials_SHR_tv= (TextView)this.findViewById(R.id.myusecardetials_SHR_tv);
        myusecardetials_shtime_tv= (TextView)this.findViewById(R.id.myusecardetials_shtime_tv);
        myusecardetials_wtgyy_tv= (TextView)this.findViewById(R.id.myusecardetials_wtgyy_tv);
        myusecardetials_statuss_tv=(TextView)this.findViewById(R.id.myusecardetials_statuss_tv);
    }
    public void initViewValues(){
        title.setText("用车详情");
        if(carEntity!=null){
            myusecardetials_statuss_tv.setText(carEntity.getUc_status());
            myusecardetials_carname_tv.setText(carEntity.getName());
            myusecardetials_carcode_tv.setText(carEntity.getNumber());
//            myusecardetials_sqsj_tv.setText(carEntity.getCarsqtime());
            myusecardetials_starttime_tv.setText(carEntity.getUc_start_time());
            myusecardetials_endtime_tv.setText(carEntity.getUc_end_time());
            myusecardetials_mdd_tv.setText(carEntity.getUc_end_place());
            myusecardetials_lcs_tv.setText(carEntity.getUc_mileage());
            myusecardetials_diver_tv.setText(carEntity.getUsername());
            myusecardetials_ccly_tv.setText(carEntity.getUc_resion());
            myusecardetials_SHR_tv.setText(carEntity.getAuditer());
            myusecardetials_shtime_tv.setText(carEntity.getUc_audit_time());
            myusecardetials_wtgyy_tv.setText(carEntity.getUc_audit_resion());
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
