package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;

/**
 * 绩效核定详情
 * Created by wrb on 2016/11/9.
 */
public class PerformanceForApprovalDetialsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView pfadetial_zt_tv,pfadetial_starttime_tv,pfadetial_endtime_tv,
            pfadetial_jzg_tv,pfadetial_gw_tv,pfadetial_zgcode_tv,pfadetial_hdr_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performanceforapprovaldetials_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
    }
    public void initViewValues(){
        title.setText("绩效核定详情");
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
