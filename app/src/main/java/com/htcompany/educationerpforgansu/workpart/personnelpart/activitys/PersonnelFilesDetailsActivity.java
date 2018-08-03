package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonnelFilesEntity;

/**
 * 档案详情
 * Created by wrb on 2016/11/18.
 */
public class PersonnelFilesDetailsActivity extends BaseActivity implements View.OnClickListener{
     private TextView title;
    private RelativeLayout reback_btn;
    private TextView filedetails_name_tv,filedetails_code_tv,filedetails_sfzh_tv,
            filedetails_zzzt_tv,filedetails_jg_tv,filedetails_gw_tv,
            filedetails_dabh_tv,filedetails_dalb_tv,filedetails_dazrrq_tv,
            filedetails_dalc_tv,filedetails_dazcrq_tv,filedetails_daqc_tv,
            filedetails_dagldw_tv,filedetails_crwz_tv;
    private PersonnelFilesEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personnelfilesdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity=(PersonnelFilesEntity)getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        filedetails_name_tv= (TextView)this.findViewById(R.id.filedetails_name_tv);
                filedetails_code_tv= (TextView)this.findViewById(R.id.filedetails_code_tv);
                filedetails_sfzh_tv= (TextView)this.findViewById(R.id.filedetails_sfzh_tv);
                filedetails_zzzt_tv= (TextView)this.findViewById(R.id.filedetails_zzzt_tv);
                filedetails_jg_tv= (TextView)this.findViewById(R.id.filedetails_jg_tv);
                filedetails_gw_tv= (TextView)this.findViewById(R.id.filedetails_gw_tv);
                filedetails_dabh_tv= (TextView)this.findViewById(R.id.filedetails_dabh_tv);
                filedetails_dalb_tv= (TextView)this.findViewById(R.id.filedetails_dalb_tv);
                filedetails_dazrrq_tv= (TextView)this.findViewById(R.id.filedetails_dazrrq_tv);
                filedetails_dalc_tv= (TextView)this.findViewById(R.id.filedetails_dalc_tv);
                filedetails_dazcrq_tv= (TextView)this.findViewById(R.id.filedetails_dazcrq_tv);
                filedetails_daqc_tv= (TextView)this.findViewById(R.id.filedetails_daqc_tv);
                filedetails_dagldw_tv= (TextView)this.findViewById(R.id.filedetails_dagldw_tv);
                filedetails_crwz_tv= (TextView)this.findViewById(R.id.filedetails_crwz_tv);
    }
    public void initViewValues(){
        title.setText("档案详情");
        if(entity!=null){
            filedetails_name_tv.setText(entity.getTeacher_name());
            filedetails_code_tv.setText(entity.getWorker_number());
            filedetails_sfzh_tv.setText(entity.getDocument_number());
            filedetails_zzzt_tv.setText(entity.getStatus());
            filedetails_jg_tv.setText(entity.getOrgname());
            filedetails_gw_tv.setText(entity.getSta_name());
            filedetails_dabh_tv.setText(entity.getPf_number());
            filedetails_dalb_tv.setText(entity.getPf_types());
            filedetails_dazrrq_tv.setText(entity.getPf_come_time());
            filedetails_dalc_tv.setText(entity.getPf_comefrom());
            filedetails_dazcrq_tv.setText(entity.getPf_gotime());
            filedetails_daqc_tv.setText(entity.getPf_goto());
            filedetails_dagldw_tv.setText(entity.getPf_go_com());
            filedetails_crwz_tv.setText(entity.getPf_position());
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
