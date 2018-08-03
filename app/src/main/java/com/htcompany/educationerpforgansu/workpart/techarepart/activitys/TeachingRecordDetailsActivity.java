package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingRecordEntity;

/**
 * 授课记录详情
 * Created by wrb on 2016/11/14.
 */
public class TeachingRecordDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView recorddetails_term_tv,recorddetails_kc_tv,recorddetails_jxb_tv,
            recorddetails_skrq_tv,recorddetails_sksj_tv,recorddetails_zc_tv,recorddetails_xq_tv,recorddetails_jc_tv,
            recorddetails_skdd_tv;
    private TeachingRecordEntity recordEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachingrecorddetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        recordEntity = (TeachingRecordEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        recorddetails_term_tv= (TextView)this.findViewById(R.id.recorddetails_term_tv);
        recorddetails_kc_tv= (TextView)this.findViewById(R.id.recorddetails_kc_tv);
        recorddetails_jxb_tv= (TextView)this.findViewById(R.id.recorddetails_jxb_tv);
        recorddetails_skrq_tv= (TextView)this.findViewById(R.id.recorddetails_skrq_tv);
        recorddetails_sksj_tv= (TextView)this.findViewById(R.id.recorddetails_sksj_tv);
        recorddetails_zc_tv= (TextView)this.findViewById(R.id.recorddetails_zc_tv);
        recorddetails_xq_tv= (TextView)this.findViewById(R.id.recorddetails_xq_tv);
        recorddetails_jc_tv= (TextView)this.findViewById(R.id.recorddetails_jc_tv);
        recorddetails_skdd_tv= (TextView)this.findViewById(R.id.recorddetails_skdd_tv);
    }
    public void initViewValues(){
        title.setText("记录详情");
        if(recordEntity!=null){
            recorddetails_term_tv.setText(recordEntity.getXueqi());
            recorddetails_kc_tv.setText(recordEntity.getKecheng());
            recorddetails_jxb_tv.setText(recordEntity.getBanji());
            recorddetails_skrq_tv.setText(recordEntity.getSk_date());
            recorddetails_sksj_tv.setText(recordEntity.getSk_time());
            recorddetails_zc_tv.setText("第"+recordEntity.getZc()+"周");
            recorddetails_xq_tv.setText(recordEntity.getXq());
            recorddetails_jc_tv.setText(recordEntity.getJc());
            recorddetails_skdd_tv.setText(recordEntity.getRoom());
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
