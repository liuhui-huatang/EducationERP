package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationStudentPJAddBJMDAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生评教详情
 * Created by WRB on 2016/11/25.
 */
public class EducationStudentPJDetailsActivity extends BaseActivity implements View.OnClickListener{

    private TextView title;
    private RelativeLayout pjdetials_pjjl_rel;
    private RelativeLayout reback_btn;
    private GDGridView studentpjdetails_bjmd_grd;
    private EducationStudentPJAddBJMDAdapter bjmdAdapter;
    private List<AllClassEntity> classEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationstudentpjdetails_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        classEntities  = new ArrayList<AllClassEntity>();
        AllClassEntity entity = new AllClassEntity();
        entity.setCname("电子0902");
        AllClassEntity entity1 = new AllClassEntity();
        entity1.setCname("软件0902");
        classEntities.add(entity);
        classEntities.add(entity1);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        pjdetials_pjjl_rel= (RelativeLayout) this.findViewById(R.id.pjdetials_pjjl_rel);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        studentpjdetails_bjmd_grd = (GDGridView)this.findViewById(R.id.studentpjdetails_bjmd_grd);
        bjmdAdapter = new EducationStudentPJAddBJMDAdapter(this,classEntities);
        studentpjdetails_bjmd_grd.setAdapter(bjmdAdapter);
    }
    public void initViewVlaues(){
        title.setText("评教详情");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        pjdetials_pjjl_rel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.pjdetials_pjjl_rel:
                Intent intent = new Intent(EducationStudentPJDetailsActivity.this,EducationStudentPJJLActivity.class);
                startActivity(intent);
                break;
        }
    }
}
