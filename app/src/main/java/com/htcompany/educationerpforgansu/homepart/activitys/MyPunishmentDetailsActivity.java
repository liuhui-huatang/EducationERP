package com.htcompany.educationerpforgansu.homepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.homepart.entity.MyPunishmentEntity;

/**
 * 惩处记录详情
 * Created by wrb on 2016/11/8.
 */
public class MyPunishmentDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView mypunishmentdetails_ccname_tv,mypunishmentdetails_ccwh_tv,mypunishmentdetails_cctime_tv,
            mypunishmentdetails_ccyy_tv,mypunishmentdetails_cccontent_tv,mypunishmentdetails_cxcctime_tv,
            mypunishmentdetails_cccxwh_tv,mypunishmentdetails_cccxyy_tv;
    private MyPunishmentEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypunishmentdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (MyPunishmentEntity)getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        mypunishmentdetails_ccname_tv= (TextView)this.findViewById(R.id.mypunishmentdetails_ccname_tv);
        mypunishmentdetails_ccwh_tv= (TextView)this.findViewById(R.id.mypunishmentdetails_ccwh_tv);
        mypunishmentdetails_cctime_tv= (TextView)this.findViewById(R.id.mypunishmentdetails_cctime_tv);
        mypunishmentdetails_ccyy_tv= (TextView)this.findViewById(R.id.mypunishmentdetails_ccyy_tv);
        mypunishmentdetails_cccontent_tv= (TextView)this.findViewById(R.id.mypunishmentdetails_cccontent_tv);
        mypunishmentdetails_cxcctime_tv= (TextView)this.findViewById(R.id.mypunishmentdetails_cxcctime_tv);
        mypunishmentdetails_cccxwh_tv= (TextView)this.findViewById(R.id.mypunishmentdetails_cccxwh_tv);
        mypunishmentdetails_cccxyy_tv= (TextView)this.findViewById(R.id.mypunishmentdetails_cccxyy_tv);
    }
    public void initViewValues(){
        title.setText("惩处详情");
        if(entity!=null){
            mypunishmentdetails_ccname_tv.setText(entity.getName());
            mypunishmentdetails_ccwh_tv.setText(entity.getNumber());
            mypunishmentdetails_cctime_tv.setText(entity.getTime());
            mypunishmentdetails_ccyy_tv.setText(entity.getCause());
            mypunishmentdetails_cccontent_tv.setText(entity.getContent());
            mypunishmentdetails_cxcctime_tv.setText(entity.getUn_punish_time());
            mypunishmentdetails_cccxwh_tv.setText(entity.getUn_number());
            mypunishmentdetails_cccxyy_tv.setText(entity.getUn_cause());
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
