package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDListView;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.ClassCjEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationClassPerformanceDetailsAdapter;

/**
 * Created by 成绩详情 on 2016/11/24.
 */
public class EducationClassPerformanceDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView jwbjcjxq_jxb_tv,jwbjcjxq_xm_tv,jwbjcjxq_xh_tv,jwbjcjxq_xf_tv,jwbjcjxq_zcj_tv;
    private GDListView eductionexamxq_lv;
    private ClassCjEntity cjEntity;
    private EducationClassPerformanceDetailsAdapter educationClassPerformanceDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationclassperformancedetails_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        cjEntity = (ClassCjEntity) getIntent().getSerializableExtra("cjEntity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        jwbjcjxq_jxb_tv= (TextView)this.findViewById(R.id.jwbjcjxq_jxb_tv);
        jwbjcjxq_xm_tv= (TextView)this.findViewById(R.id.jwbjcjxq_xm_tv);
        jwbjcjxq_xh_tv= (TextView)this.findViewById(R.id.jwbjcjxq_xh_tv);
        jwbjcjxq_xf_tv= (TextView)this.findViewById(R.id.jwbjcjxq_xf_tv);
        jwbjcjxq_zcj_tv= (TextView)this.findViewById(R.id.jwbjcjxq_zcj_tv);
        eductionexamxq_lv=(GDListView)this.findViewById(R.id.eductionexamxq_lv);
    }
    public void initViewVlaues(){
        title.setText("成绩详情");
        if(cjEntity!=null){
            jwbjcjxq_jxb_tv.setText(cjEntity.getJxb());
            jwbjcjxq_xm_tv.setText(cjEntity.getUsername());
            jwbjcjxq_xh_tv.setText(cjEntity.getNumber());
            jwbjcjxq_xf_tv.setText(cjEntity.getXufen());
            jwbjcjxq_zcj_tv.setText(cjEntity.getTotal());
            if(cjEntity.getKc()!=null&&cjEntity.getKc().size()>0){
                    educationClassPerformanceDetailsAdapter = new EducationClassPerformanceDetailsAdapter(this,cjEntity.getKc());
                eductionexamxq_lv.setAdapter(educationClassPerformanceDetailsAdapter);
            }
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
