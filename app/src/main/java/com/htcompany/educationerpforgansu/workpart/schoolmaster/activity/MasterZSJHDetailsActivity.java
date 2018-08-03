package com.htcompany.educationerpforgansu.workpart.schoolmaster.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.ZSJHEntity;

/**
 * 招生计划详情
 * Created by wrb on 2017/1/19.
 */
public class MasterZSJHDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView masterzsjh_zy_tv,masterzsjh_xz_tv,masterzsjh_cc_tv,
            masterzsjh_nj_tv,masterzsjh_jhrs_tv,masterzsjh_sjrs_tv,masterzsjh_wcbfb_tv,
            masterzsjh_zsfzr_tv;
    private ZSJHEntity zsjhEntity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_zsjh_detailsactivity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        zsjhEntity = (ZSJHEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        masterzsjh_zy_tv= (TextView)this.findViewById(R.id.masterzsjh_zy_tv);
        masterzsjh_xz_tv= (TextView)this.findViewById(R.id.masterzsjh_xz_tv);
        masterzsjh_cc_tv= (TextView)this.findViewById(R.id.masterzsjh_cc_tv);
        masterzsjh_nj_tv= (TextView)this.findViewById(R.id.masterzsjh_nj_tv);
        masterzsjh_jhrs_tv= (TextView)this.findViewById(R.id.masterzsjh_jhrs_tv);
        masterzsjh_sjrs_tv= (TextView)this.findViewById(R.id.masterzsjh_sjrs_tv);
        masterzsjh_wcbfb_tv= (TextView)this.findViewById(R.id.masterzsjh_wcbfb_tv);
        masterzsjh_zsfzr_tv= (TextView)this.findViewById(R.id.masterzsjh_zsfzr_tv);
    }
    public void initViewValues(){
        title.setText("招生计划详情");
        if(zsjhEntity!=null){
            masterzsjh_zy_tv.setText(zsjhEntity.getTrbd_major());
            masterzsjh_xz_tv.setText(zsjhEntity.getTrbd_educational());
            masterzsjh_cc_tv.setText(zsjhEntity.getTrbd_unity());
            masterzsjh_nj_tv.setText(zsjhEntity.getTrbd_grade());
            masterzsjh_jhrs_tv.setText(zsjhEntity.getPlan_num());
            masterzsjh_sjrs_tv.setText(zsjhEntity.getShijinum());
            masterzsjh_wcbfb_tv.setText(zsjhEntity.getWanchengbfb());
            masterzsjh_zsfzr_tv.setText(zsjhEntity.getFuzeren());
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
