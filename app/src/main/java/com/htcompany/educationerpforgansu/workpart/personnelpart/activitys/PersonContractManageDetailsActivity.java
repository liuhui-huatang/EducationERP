package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonContractEntity;

/**
 * 合同详情
 * Created by wrb on 2016/11/17.
 */
public class PersonContractManageDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView personcontratct_name_tv,personcontratct_code_tv,personcontratct_jg_tv,
            personcontratct_gw_tv,personcontratct_htbh_tv,personcontratct_htstartrq_tv,
            personcontratct_htendrq_tv,personcontratct_htqdrq_tv,personcontratct_htstatus_tv,
            personcontratct_htxqcs_tv,personcontratct_zjycxqrq_tv,personcontratct_htzzreson_tv,
            personcontratct_bgcs_tv,personcontratct_bgtime_tv,personcontratct_bgresion_tv,
            personcontratct_cfwz_tv;
    private PersonContractEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personcontractmanagedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity =(PersonContractEntity)getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        personcontratct_name_tv = (TextView)this.findViewById(R.id.personcontratct_name_tv);
        personcontratct_code_tv= (TextView)this.findViewById(R.id.personcontratct_code_tv);
        personcontratct_jg_tv= (TextView)this.findViewById(R.id.personcontratct_jg_tv);
        personcontratct_gw_tv= (TextView)this.findViewById(R.id.personcontratct_gw_tv);
        personcontratct_htbh_tv= (TextView)this.findViewById(R.id.personcontratct_htbh_tv);
        personcontratct_htstartrq_tv= (TextView)this.findViewById(R.id.personcontratct_htstartrq_tv);
        personcontratct_htendrq_tv= (TextView)this.findViewById(R.id.personcontratct_htendrq_tv);
        personcontratct_htqdrq_tv= (TextView)this.findViewById(R.id.personcontratct_htqdrq_tv);
        personcontratct_htstatus_tv= (TextView)this.findViewById(R.id.personcontratct_htstatus_tv);
        personcontratct_htxqcs_tv= (TextView)this.findViewById(R.id.personcontratct_htxqcs_tv);
        personcontratct_zjycxqrq_tv= (TextView)this.findViewById(R.id.personcontratct_zjycxqrq_tv);
        personcontratct_htzzreson_tv= (TextView)this.findViewById(R.id.personcontratct_htzzreson_tv);
        personcontratct_bgcs_tv= (TextView)this.findViewById(R.id.personcontratct_bgcs_tv);
        personcontratct_bgtime_tv= (TextView)this.findViewById(R.id.personcontratct_bgtime_tv);
        personcontratct_bgresion_tv= (TextView)this.findViewById(R.id.personcontratct_bgresion_tv);
        personcontratct_cfwz_tv= (TextView)this.findViewById(R.id.personcontratct_cfwz_tv);
    }
    public void initViewValues(){
        title.setText("合同详情");
        if(entity!=null){
            setViewVlaues(entity);
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
    /**
     * 设置界面控件值
     * @param entity
     */
    public void setViewVlaues(PersonContractEntity entity){
        personcontratct_name_tv.setText(entity.getUsername());
        personcontratct_code_tv.setText(entity.getWorker_number());
        personcontratct_jg_tv.setText(entity.getOrgName());
        personcontratct_gw_tv.setText(entity.getSta_name());
        personcontratct_htbh_tv.setText(entity.getNumber());
        personcontratct_htstartrq_tv.setText(entity.getStart());
        personcontratct_htendrq_tv.setText(entity.getEnd());
        personcontratct_htqdrq_tv.setText(entity.getTime());
        if("1".equals(entity.getStatus())){
            personcontratct_htstatus_tv.setText("新签");
        }else if("2".equals(entity.getStatus())){
            personcontratct_htstatus_tv.setText("续签");
        }else if("3".equals(entity.getStatus())){
            personcontratct_htstatus_tv.setText("终止");
        }

        personcontratct_htxqcs_tv.setText(entity.getXuqian_cishu());
        personcontratct_zjycxqrq_tv.setText(entity.getXuqian_time());
        personcontratct_htzzreson_tv.setText(entity.getZhongzhi_reason());
        personcontratct_bgcs_tv.setText(entity.getBian_cishu());
        personcontratct_bgtime_tv.setText(entity.getBian_time());
        personcontratct_bgresion_tv.setText(entity.getBian_reason());
        personcontratct_cfwz_tv.setText(entity.getPosition());
    }
}
