package com.htcompany.educationerpforgansu.homepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.homepart.entity.MyRewardsEntity;

/**
 * 奖励详情
 * Created by wrb on 2016/11/8.
 */
public class MyRewardsDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView rewardsdetails_hjxm_tv,rewardsdetails_hjjb_tv,rewardsdetails_hjdj_tv,
            rewardsdetails_hjlb_tv,rewardsdetails_jlfs_tv,rewardsdetails_jlmc_tv,
            rewardsdetails_bjdw_tv,rewardsdetails_hjtime_tv,rewardsdetails_rych_tv,
            rewardsdetails_hjjs_tv,rewardsdetails_jlresion_tv,rewardsdetails_jlnr_tv;
    private MyRewardsEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrewardsdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (MyRewardsEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        rewardsdetails_hjxm_tv = (TextView)this.findViewById(R.id.rewardsdetails_hjxm_tv);
        rewardsdetails_hjjb_tv = (TextView)this.findViewById(R.id.rewardsdetails_hjjb_tv);
        rewardsdetails_hjdj_tv = (TextView)this.findViewById(R.id.rewardsdetails_hjdj_tv);
        rewardsdetails_hjlb_tv = (TextView)this.findViewById(R.id.rewardsdetails_hjlb_tv);
        rewardsdetails_jlfs_tv = (TextView)this.findViewById(R.id.rewardsdetails_jlfs_tv);
        rewardsdetails_jlmc_tv = (TextView)this.findViewById(R.id.rewardsdetails_jlmc_tv);
        rewardsdetails_bjdw_tv = (TextView)this.findViewById(R.id.rewardsdetails_bjdw_tv);
        rewardsdetails_hjtime_tv = (TextView)this.findViewById(R.id.rewardsdetails_hjtime_tv);
        rewardsdetails_rych_tv = (TextView)this.findViewById(R.id.rewardsdetails_rych_tv);
        rewardsdetails_hjjs_tv = (TextView)this.findViewById(R.id.rewardsdetails_hjjs_tv);
        rewardsdetails_jlresion_tv = (TextView)this.findViewById(R.id.rewardsdetails_jlresion_tv);
        rewardsdetails_jlnr_tv = (TextView)this.findViewById(R.id.rewardsdetails_jlnr_tv);
    }
    public void initViewValues(){
        title.setText("奖励详情");
        if(entity!=null){
            rewardsdetails_hjxm_tv.setText(entity.getProject());
            rewardsdetails_hjjb_tv.setText(entity.getLevel());
            rewardsdetails_hjdj_tv.setText(entity.getGrade());
            rewardsdetails_hjlb_tv.setText(entity.getCategory());
            rewardsdetails_jlfs_tv.setText(entity.getManner());
            rewardsdetails_jlmc_tv.setText(entity.getName());
            rewardsdetails_bjdw_tv.setText(entity.getUnit());
            rewardsdetails_hjtime_tv.setText(entity.getTime());
            rewardsdetails_rych_tv.setText(entity.getHonor());
            rewardsdetails_hjjs_tv.setText(entity.getRole());
            rewardsdetails_jlresion_tv.setText(entity.getCause());
            rewardsdetails_jlnr_tv.setText(entity.getContent());
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
