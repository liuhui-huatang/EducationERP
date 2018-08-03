package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;

/**
 * 投票详情
 * Created by wrb on 2016/11/11.
 */
public class MyVotingDetailsActivity extends BaseActivity implements OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView myvotingd_title_tv,myvotingd_starttime_tv,
            myvotingd_endtime_tv,myvotingd_status_tv,myvotingd_content_tv,
            personalassessment_kpzfcount_tv,myvotingdetails_showfdrs_tv,
            myvotingd_fd_tv,myvotingd_zc_tv;
    private ProgressBar myvotingd_zcrs_pro,myvotingd_fbrs_pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.myvotingdetails_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
    }
    public void initViewValues(){
        title.setText("投票详情");
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
