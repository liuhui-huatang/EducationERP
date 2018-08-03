package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonSocialInsuranceEntity;

/**
 * 社会记录详情
 * Created by wrb on 2016/11/18.
 */
public class PersonSocialInsuranceDetailsActivity extends BaseActivity implements View.OnClickListener{

    private TextView title;
    private RelativeLayout reback_btn;
    private TextView ssdetail_name_tv,ssdetail_sex_tv,ssdetail_code_tv,
            ssdetail_sfzcode_tv,ssdetail_hklb_tv,ssdetail_zt_tv,
            ssdetail_jg_tv,ssdetail_gw_tv,ssdetail_shbzh_tv,
            ssdetail_jnstarttime_tv,ssdetail_jnendtime_tv,ssdetail_jfjs_tv,
            ssdetail_dwjf_tv,ssdetail_grjf_tv,ssdetail_hjje_tv;
    private PersonSocialInsuranceEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personsocialinsurancedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity=(PersonSocialInsuranceEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        ssdetail_name_tv= (TextView)this.findViewById(R.id.ssdetail_name_tv);
        ssdetail_sex_tv= (TextView)this.findViewById(R.id.ssdetail_sex_tv);
        ssdetail_code_tv= (TextView)this.findViewById(R.id.ssdetail_code_tv);
        ssdetail_sfzcode_tv= (TextView)this.findViewById(R.id.ssdetail_sfzcode_tv);
        ssdetail_hklb_tv= (TextView)this.findViewById(R.id.ssdetail_hklb_tv);
        ssdetail_zt_tv= (TextView)this.findViewById(R.id.ssdetail_zt_tv);
        ssdetail_jg_tv= (TextView)this.findViewById(R.id.ssdetail_jg_tv);
        ssdetail_gw_tv= (TextView)this.findViewById(R.id.ssdetail_gw_tv);
        ssdetail_shbzh_tv= (TextView)this.findViewById(R.id.ssdetail_shbzh_tv);
        ssdetail_jnstarttime_tv= (TextView)this.findViewById(R.id.ssdetail_jnstarttime_tv);
        ssdetail_jnendtime_tv= (TextView)this.findViewById(R.id.ssdetail_jnendtime_tv);
        ssdetail_jfjs_tv= (TextView)this.findViewById(R.id.ssdetail_jfjs_tv);
        ssdetail_dwjf_tv= (TextView)this.findViewById(R.id.ssdetail_dwjf_tv);
        ssdetail_grjf_tv= (TextView)this.findViewById(R.id.ssdetail_grjf_tv);
        ssdetail_hjje_tv= (TextView)this.findViewById(R.id.ssdetail_hjje_tv);
    }
    public void initViewValues(){
        title.setText("社会保险详情");
        if(entity!=null){
            ssdetail_name_tv.setText(entity.getUsername());
            ssdetail_sex_tv.setText(entity.getSex());
            ssdetail_code_tv.setText(entity.getWorker_number());
            ssdetail_sfzcode_tv.setText(entity.getDocument_number());
            ssdetail_hklb_tv.setText(entity.getDictName());
            ssdetail_zt_tv.setText(entity.getOfficeName());
            ssdetail_jg_tv.setText(entity.getOrgname());
            ssdetail_gw_tv.setText(entity.getSta_name());
            ssdetail_shbzh_tv.setText(entity.getSi_number());
            ssdetail_jnstarttime_tv.setText(entity.getSi_start_time());
            ssdetail_jnendtime_tv.setText(entity.getSi_end_time());
            ssdetail_jfjs_tv.setText(entity.getSi_base());
            ssdetail_dwjf_tv.setText(entity.getSi_per_pay());
            ssdetail_grjf_tv.setText(entity.getSi_com_pay());
            ssdetail_hjje_tv.setText(entity.getSi_total());
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
