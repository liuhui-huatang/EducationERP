package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.XXClassEntity;

/**
 * 选修课学生查询详情界面
 * Created by wrb on 2016/11/21.
 */
public class EducationSearchStudent0ptionalClassDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;

    private TextView xxkxscx_jxbmc_tv,xxkxscx_bzrmc_tv,xxkxscx_xsmc_tv,xxkxscx_xsxh_tv,xxkxscx_xxkc_tv,
            xxkxscx_xxkbj_tv,xxkxscx_zyone_tv,xxkxscx_zytwo_tv,xxkxscx_zythree_tv,
            xxkxscx_zyfour_tv,xxkxscx_zyfive_tv,xxkxscx_bmfs_tv,xxkxscx_bmsj_tv;
    private XXClassEntity classEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationsearchstudentoptionalclassdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        classEntity = (XXClassEntity) getIntent().getSerializableExtra("classEntity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        xxkxscx_jxbmc_tv = (TextView)this.findViewById(R.id.xxkxscx_jxbmc_tv);
        xxkxscx_bzrmc_tv = (TextView)this.findViewById(R.id.xxkxscx_bzrmc_tv);
        xxkxscx_xsmc_tv = (TextView)this.findViewById(R.id.xxkxscx_xsmc_tv);
        xxkxscx_xsxh_tv = (TextView)this.findViewById(R.id.xxkxscx_xsxh_tv);
        xxkxscx_xxkc_tv = (TextView)this.findViewById(R.id.xxkxscx_xxkc_tv);
        xxkxscx_xxkbj_tv = (TextView)this.findViewById(R.id.xxkxscx_xxkbj_tv);
        xxkxscx_zyone_tv = (TextView)this.findViewById(R.id.xxkxscx_zyone_tv);
        xxkxscx_zytwo_tv= (TextView)this.findViewById(R.id.xxkxscx_zytwo_tv);
        xxkxscx_zythree_tv= (TextView)this.findViewById(R.id.xxkxscx_zythree_tv);
        xxkxscx_zyfour_tv= (TextView)this.findViewById(R.id.xxkxscx_zyfour_tv);
        xxkxscx_zyfive_tv= (TextView)this.findViewById(R.id.xxkxscx_zyfive_tv);
        xxkxscx_bmfs_tv = (TextView)this.findViewById(R.id.xxkxscx_bmfs_tv);
        xxkxscx_bmsj_tv = (TextView)this.findViewById(R.id.xxkxscx_bmsj_tv);
    }
    public void initViewValues(){
        title.setText("详情");
        if(classEntity!=null){
            xxkxscx_jxbmc_tv.setText(classEntity.getJxbname());
            xxkxscx_bzrmc_tv.setText(classEntity.getTrbd_teacherid());
            xxkxscx_xsmc_tv.setText(classEntity.getTruename());
            xxkxscx_xsxh_tv.setText(classEntity.getNumber());
            xxkxscx_xxkc_tv.setText(classEntity.getTrbd_cource());
            xxkxscx_xxkbj_tv.setText(classEntity.getTrbd_xxbj());
            xxkxscx_zyone_tv.setText(classEntity.getTrbd_zhiyuany());
            xxkxscx_zytwo_tv.setText(classEntity.getTrbd_zhiyuane());
            xxkxscx_zythree_tv.setText(classEntity.getTrbd_zhiyuans());
            xxkxscx_zyfour_tv.setText(classEntity.getTrbd_zhiyuant());
            xxkxscx_zyfive_tv.setText(classEntity.getTrbd_zhiyuanw());
            xxkxscx_bmfs_tv.setText(classEntity.getTrbd_bmfs());
            xxkxscx_bmsj_tv.setText(classEntity.getTrbd_bmsj());
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
