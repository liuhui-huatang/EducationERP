package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormDisciplineEntity;

/**
 * 违纪详情
 * Created by wrb on 2016/11/23.
 */
public class DormDisciplineDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView dormdisciplinedetails_time_tv,dormdisciplinedetails_name_tv,
            dormdisciplinedetails_ssl_tv,dormdisciplinedetails_ss_tv,dormdisciplinedetails_cw_tv,
            dormdisciplinedetails_wjqk_tv,dormdisciplinedetails_clyj_tv,dormdisciplinedetails_bzr_tv,
            dormdisciplinedetails_grade_tv;
    private DormDisciplineEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormdisciplinedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (DormDisciplineEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        dormdisciplinedetails_time_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_time_tv);
        dormdisciplinedetails_name_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_name_tv);
        dormdisciplinedetails_ssl_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_ssl_tv);
        dormdisciplinedetails_ss_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_ss_tv);
        dormdisciplinedetails_cw_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_cw_tv);
        dormdisciplinedetails_wjqk_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_wjqk_tv);
        dormdisciplinedetails_clyj_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_clyj_tv);
        dormdisciplinedetails_bzr_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_bzr_tv);
                dormdisciplinedetails_grade_tv= (TextView)this.findViewById(R.id.dormdisciplinedetails_grade_tv);
    }
    public void initViewValues(){
        title.setText("违纪详情");
        if(entity!=null){
            dormdisciplinedetails_time_tv.setText(entity.getDate());
            dormdisciplinedetails_name_tv.setText(entity.getTruename());
            dormdisciplinedetails_ssl_tv.setText(entity.getRoom_category_id());
            dormdisciplinedetails_ss_tv.setText(entity.getName());
            dormdisciplinedetails_cw_tv.setText(entity.getBed());
            dormdisciplinedetails_wjqk_tv.setText(entity.getCases());
            dormdisciplinedetails_clyj_tv.setText(entity.getOpinion());
            dormdisciplinedetails_bzr_tv.setText(entity.getTeacherName());
            dormdisciplinedetails_grade_tv.setText(entity.getClassName());
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
