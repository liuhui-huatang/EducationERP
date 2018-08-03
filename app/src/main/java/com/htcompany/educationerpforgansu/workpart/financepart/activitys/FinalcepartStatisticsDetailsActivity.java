package com.htcompany.educationerpforgansu.workpart.financepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.StatisticsEntity;

/**
 * 费用统计详情
 * Created by wrb on 2016/11/17.
 */
public class FinalcepartStatisticsDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView statisticsdetails_zy_tv,statisticsdetails_cc_tv,statisticsdetails_xz_tv,
            statisticsdetails_nj_tv,statisticsdetails_jxbgs_tv,statisticsdetails_zrs_tv,
            statisticsdetails_ysfy_tv,statisticsdetails_tzje_tv,statisticsdetails_ysf_tv,
            statisticsdetails_qf_tv;
    private StatisticsEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalcepartstatisticsdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (StatisticsEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        statisticsdetails_zy_tv= (TextView)this.findViewById(R.id.statisticsdetails_zy_tv);
        statisticsdetails_cc_tv= (TextView)this.findViewById(R.id.statisticsdetails_cc_tv);
        statisticsdetails_xz_tv= (TextView)this.findViewById(R.id.statisticsdetails_xz_tv);
        statisticsdetails_nj_tv= (TextView)this.findViewById(R.id.statisticsdetails_nj_tv);
        statisticsdetails_jxbgs_tv= (TextView)this.findViewById(R.id.statisticsdetails_jxbgs_tv);
        statisticsdetails_zrs_tv= (TextView)this.findViewById(R.id.statisticsdetails_zrs_tv);
        statisticsdetails_ysfy_tv= (TextView)this.findViewById(R.id.statisticsdetails_ysfy_tv);
        statisticsdetails_tzje_tv= (TextView)this.findViewById(R.id.statisticsdetails_tzje_tv);
        statisticsdetails_ysf_tv= (TextView)this.findViewById(R.id.statisticsdetails_ysf_tv);
        statisticsdetails_qf_tv= (TextView)this.findViewById(R.id.statisticsdetails_qf_tv);
    }
    public void initViewValues(){
        title.setText("费用统计详情");
        if(entity!=null){
            statisticsdetails_zy_tv.setText(entity.getMajor());
            statisticsdetails_cc_tv.setText(entity.getLevels());
            statisticsdetails_xz_tv.setText(entity.getLength());
            statisticsdetails_nj_tv.setText(entity.getGrade());
            statisticsdetails_jxbgs_tv.setText(entity.getCount());
            statisticsdetails_zrs_tv.setText(entity.getRenshu());
            statisticsdetails_ysfy_tv.setText(entity.getYishou());
            statisticsdetails_tzje_tv.setText(entity.getTiaozheng());
            statisticsdetails_ysf_tv.setText(entity.getYingshou());
            statisticsdetails_qf_tv.setText(entity.getQianfei());
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
