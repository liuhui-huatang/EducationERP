package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonManageEntity;

/**
 * 人事管理详情
 * Created by wrb on 2016/11/17.
 */
public class PersonManageDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView personmanaged_name_tv,personmanaged_code_tv,personmanaged_zjtype_tv,
            personmanaged_zjcode_tv,personmanaged_sex_tv,personmanaged_mz_tv,
            personmanaged_zzzt_tv,personmanaged_xzjgmc_tv,personmanaged_xzgwmc_tv,
            personmanaged_csrq_tv,personmanaged_xx_tv,personmanaged_jkzt_tv,
            personmanaged_hyzt_tv,personmanaged_zzmm_tv,personmanaged_jg_tv,
            personmanaged_csd_tv,personmanaged_hkszd_tv,personmanaged_hklb_tv,
            personmanaged_dqzz_tv,personmanaged_zzyb_tv,personmanaged_cjgzny_tv,
            personmanaged_cjny_tv,personmanaged_bzlb_tv,personmanaged_jzglb_tv,
            personmanaged_gwlb_tv,personmanaged_sfjzjs_tv,personmanaged_sfssxjs_tv;
    private PersonManageEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personmanagedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity=(PersonManageEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        personmanaged_name_tv= (TextView)this.findViewById(R.id.personmanaged_name_tv);
                personmanaged_code_tv= (TextView)this.findViewById(R.id.personmanaged_code_tv);
                personmanaged_zjtype_tv= (TextView)this.findViewById(R.id.personmanaged_zjtype_tv);
                personmanaged_zjcode_tv= (TextView)this.findViewById(R.id.personmanaged_zjcode_tv);
                personmanaged_sex_tv= (TextView)this.findViewById(R.id.personmanaged_sex_tv);
                personmanaged_mz_tv= (TextView)this.findViewById(R.id.personmanaged_mz_tv);
                personmanaged_zzzt_tv= (TextView)this.findViewById(R.id.personmanaged_zzzt_tv);
                personmanaged_xzjgmc_tv= (TextView)this.findViewById(R.id.personmanaged_xzjgmc_tv);
                personmanaged_xzgwmc_tv= (TextView)this.findViewById(R.id.personmanaged_xzgwmc_tv);
                personmanaged_csrq_tv= (TextView)this.findViewById(R.id.personmanaged_csrq_tv);
                personmanaged_xx_tv= (TextView)this.findViewById(R.id.personmanaged_xx_tv);
                personmanaged_jkzt_tv= (TextView)this.findViewById(R.id.personmanaged_jkzt_tv);
                personmanaged_hyzt_tv= (TextView)this.findViewById(R.id.personmanaged_hyzt_tv);
                personmanaged_zzmm_tv= (TextView)this.findViewById(R.id.personmanaged_zzmm_tv);
                personmanaged_jg_tv= (TextView)this.findViewById(R.id.personmanaged_jg_tv);
                personmanaged_csd_tv= (TextView)this.findViewById(R.id.personmanaged_csd_tv);
                personmanaged_hkszd_tv= (TextView)this.findViewById(R.id.personmanaged_hkszd_tv);
                personmanaged_hklb_tv= (TextView)this.findViewById(R.id.personmanaged_hklb_tv);
                personmanaged_dqzz_tv= (TextView)this.findViewById(R.id.personmanaged_dqzz_tv);
                personmanaged_zzyb_tv= (TextView)this.findViewById(R.id.personmanaged_zzyb_tv);
                personmanaged_cjgzny_tv= (TextView)this.findViewById(R.id.personmanaged_cjgzny_tv);
                personmanaged_cjny_tv= (TextView)this.findViewById(R.id.personmanaged_cjny_tv);
                personmanaged_bzlb_tv= (TextView)this.findViewById(R.id.personmanaged_bzlb_tv);
                personmanaged_jzglb_tv= (TextView)this.findViewById(R.id.personmanaged_jzglb_tv);
                personmanaged_gwlb_tv= (TextView)this.findViewById(R.id.personmanaged_gwlb_tv);
                personmanaged_sfjzjs_tv= (TextView)this.findViewById(R.id.personmanaged_sfjzjs_tv);
                personmanaged_sfssxjs_tv= (TextView)this.findViewById(R.id.personmanaged_sfssxjs_tv);
    }
    public void initViewValues(){
        title.setText("教职工详情");
        if(entity!=null){
            personmanaged_name_tv.setText(entity.getUsername());
            personmanaged_code_tv.setText(entity.getWorker_number());
            personmanaged_zjtype_tv.setText(entity.getDocument_type_show());
            personmanaged_zjcode_tv.setText(entity.getDocument_number());
            personmanaged_sex_tv.setText(entity.getSex());
            personmanaged_mz_tv.setText(entity.getNation_show());
            personmanaged_xzjgmc_tv.setText(entity.getOrganization());
            personmanaged_xzgwmc_tv.setText(entity.getOrganization_job());
            personmanaged_csrq_tv.setText(entity.getBirthday());
            personmanaged_xx_tv.setText(entity.getBlood_type_show());
            personmanaged_jkzt_tv.setText(entity.getHealth_condition_show());
            personmanaged_hyzt_tv.setText(entity.getMarital_status_show());
            personmanaged_zzmm_tv.setText(entity.getPolitics_status_show());
            personmanaged_jg_tv.setText(entity.getNative_place());
            personmanaged_csd_tv.setText(entity.getPlace_birth());
            personmanaged_hkszd_tv.setText(entity.getAccount_location());
            personmanaged_hklb_tv.setText(entity.getAccount_type_show());
            personmanaged_dqzz_tv.setText(entity.getCurrent_address());
            personmanaged_zzyb_tv.setText(entity.getZip_code());
            personmanaged_cjgzny_tv.setText(entity.getWorker_time());
            personmanaged_cjny_tv.setText(entity.getTeacher_time());
            personmanaged_bzlb_tv.setText(entity.getCompile_type_show());
            personmanaged_gwlb_tv.setText(entity.getPost_type_show());
            personmanaged_sfjzjs_tv.setText(entity.getIs_part_time_teacher());
            personmanaged_sfssxjs_tv.setText(entity.getIs_biform());
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
