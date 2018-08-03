package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherDormManageEntity;

/**
 * 宿舍管理详情
 * Created by wrb on 2016/11/30.
 */
public class TeacherDormManageDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView tdormmanage_code_tv,tdormmanage_name_tv,tdormmanage_sex_tv,
            tdormmanage_ssl_tv,tdormmanage_dqss_tv,tdormmanage_roomtype_tv,
            tdormmanage_cwh_tv,tdormmanage_sfssz_tv;
    private TeacherDormManageEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherdormmanagedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (TeacherDormManageEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        tdormmanage_code_tv= (TextView)this.findViewById(R.id.tdormmanage_code_tv);
        tdormmanage_name_tv= (TextView)this.findViewById(R.id.tdormmanage_name_tv);
        tdormmanage_sex_tv= (TextView)this.findViewById(R.id.tdormmanage_sex_tv);
        tdormmanage_ssl_tv= (TextView)this.findViewById(R.id.tdormmanage_ssl_tv);
        tdormmanage_dqss_tv= (TextView)this.findViewById(R.id.tdormmanage_dqss_tv);
        tdormmanage_roomtype_tv= (TextView)this.findViewById(R.id.tdormmanage_roomtype_tv);
        tdormmanage_cwh_tv= (TextView)this.findViewById(R.id.tdormmanage_cwh_tv);
        tdormmanage_sfssz_tv= (TextView)this.findViewById(R.id.tdormmanage_sfssz_tv);
    }
    public void initViewValues(){
        title.setText("住宿详情");
        if(entity!=null){
            tdormmanage_code_tv.setText(entity.getNumber());
            tdormmanage_name_tv.setText(entity.getTruename());
            tdormmanage_sex_tv.setText(entity.getGender());
            tdormmanage_ssl_tv.setText(entity.getRoom_category_id());
            tdormmanage_dqss_tv.setText(entity.getHouse_name());
            tdormmanage_roomtype_tv.setText(entity.getHouse_type());
            tdormmanage_cwh_tv.setText(entity.getBed());
            tdormmanage_sfssz_tv.setText(entity.getIs_housemaster());
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
