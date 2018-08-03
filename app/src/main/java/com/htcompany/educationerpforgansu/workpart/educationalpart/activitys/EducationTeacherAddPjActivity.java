package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.workpart.activitys.AllPersonMoreSelectActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingApplyCHRYAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllPersonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加教师评教界面
 * Created by wrb on 2016/11/25.
 */
public class EducationTeacherAddPjActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView teacherpjadd_xq_tv,teacherpjadd_spjs_tv;
    private LinearLayout teacherpjadd_pjjsxz_ll;
    private GDGridView teacherpjadd_pjjs_grid;
    private List<AllPersonEntity> chrydatas;
    private MeetingApplyCHRYAdapter chryAdapter;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationteacheraddpj_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        chrydatas = new ArrayList<AllPersonEntity>();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        teacherpjadd_xq_tv= (TextView)this.findViewById(R.id.teacherpjadd_xq_tv);
        teacherpjadd_spjs_tv= (TextView)this.findViewById(R.id.teacherpjadd_spjs_tv);
        teacherpjadd_pjjsxz_ll=(LinearLayout)this.findViewById(R.id.teacherpjadd_pjjsxz_ll);
        teacherpjadd_pjjs_grid=(GDGridView)this.findViewById(R.id.teacherpjadd_pjjs_grid);
//        chryAdapter = new MeetingApplyCHRYAdapter(this,chrydatas);
//        teacherpjadd_pjjs_grid.setAdapter(chryAdapter);

    }
    public void initViewVlaues(){
        title.setText("添加评教");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        teacherpjadd_pjjsxz_ll.setOnClickListener(this);
        teacherpjadd_spjs_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.teacherpjadd_pjjsxz_ll:
                //参会人员
                intent = new Intent(EducationTeacherAddPjActivity.this,AllPersonMoreSelectActivity.class);
                intent.putExtra("personFlag","more");
                startActivityForResult(intent,102);
                break;
            case R.id.teacherpjadd_spjs_tv:
                //会议主持人
                intent = new Intent(EducationTeacherAddPjActivity.this,AllPersonMoreSelectActivity.class);
                intent.putExtra("personFlag","one");
                startActivityForResult(intent,103);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (requestCode){
                case 102:
                    //会议参会人员
                    List<AllPersonEntity> datas = (List<AllPersonEntity>) data.getSerializableExtra("persondatas");
                    if(datas!=null&&datas.size()>0){
                        if(chrydatas.size()>0){
                            chrydatas.clear();
                        }
                        for(AllPersonEntity personEntity:datas){
                            chrydatas.add(personEntity);
                        }
                        chryAdapter.notifyDataSetChanged();
                    }
                    break;
                case 103:
                    //会议主持人
                    List<AllPersonEntity> datas2 = (List<AllPersonEntity>) data.getSerializableExtra("persondatas");
                    if(datas2!=null&&datas2.size()>0){
                        teacherpjadd_spjs_tv.setText(datas2.get(0).getPname());
                    }
                    break;
            }
        }
    }
}
