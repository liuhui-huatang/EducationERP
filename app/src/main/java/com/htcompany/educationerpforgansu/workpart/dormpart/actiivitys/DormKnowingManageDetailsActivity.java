package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormKnowingManageEntity;

/**
 * 查寝详情
 * Created by wrb on 2016/11/22.
 */
public class DormKnowingManageDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView kmd_time_tv,kmd_student_tv,kmd_ssl_tv,kmd_ss_tv,
            kmd_cw_tv,kmd_roomtype_tv,kmd_xzb_tv,kmd_bzr_tv,kmd_qqlb_tv,
            kmd_fhsj_tv,kmd_cqr_tv;
    private DormKnowingManageEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormknowingmanageetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity= (DormKnowingManageEntity)getIntent().getSerializableExtra("entity");
    }

    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        kmd_time_tv= (TextView)this.findViewById(R.id.kmd_time_tv);
        kmd_student_tv= (TextView)this.findViewById(R.id.kmd_student_tv);
        kmd_ssl_tv= (TextView)this.findViewById(R.id.kmd_ssl_tv);
        kmd_ss_tv= (TextView)this.findViewById(R.id.kmd_ss_tv);
        kmd_cw_tv= (TextView)this.findViewById(R.id.kmd_cw_tv);
        kmd_roomtype_tv= (TextView)this.findViewById(R.id.kmd_roomtype_tv);
        kmd_xzb_tv= (TextView)this.findViewById(R.id.kmd_xzb_tv);
        kmd_bzr_tv= (TextView)this.findViewById(R.id.kmd_bzr_tv);
        kmd_qqlb_tv= (TextView)this.findViewById(R.id.kmd_qqlb_tv);
        kmd_fhsj_tv= (TextView)this.findViewById(R.id.kmd_fhsj_tv);
        kmd_cqr_tv= (TextView)this.findViewById(R.id.kmd_cqr_tv);
    }
    public void initViewValues(){
        title.setText("查寝详情");
        if(entity!=null){
            kmd_time_tv.setText(entity.getDate());
            kmd_student_tv.setText(entity.getStuid());
            kmd_ssl_tv.setText(entity.getBuild());
            kmd_ss_tv.setText(entity.getRoomname());
            kmd_cw_tv.setText(entity.getBed());
            kmd_roomtype_tv.setText(entity.getHouse_type());
            kmd_xzb_tv.setText(entity.getXzb());
            kmd_bzr_tv.setText(entity.getTeacher());
            kmd_qqlb_tv.setText(entity.getShow_absenteeism_category_id());
            kmd_fhsj_tv.setText(entity.getReturn_time());
            kmd_cqr_tv.setText(entity.getCheck_chamber_user());
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
