package com.htcompany.educationerpforgansu.workpart.studentpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.workpart.activitys.AllClassActivity;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;
import com.htcompany.educationerpforgansu.workpart.studentpart.adapters.StudentNoticesAddBJMDAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生公告添加界面
 * Created by wrb on 2016/11/23.
 */
public class StudentNoticesAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    //全校公告，班级公告，
    private RelativeLayout studentnotice_qxgg_rel,studentnotice_bjgg_rel,studentnotice_zzg_rel;
    private TextView studentnotice_qx_tv,studentnotice_bjgg_tv,studentnotice_zzgg_tv;
    private TextView studentnotice_qxline_tv,studentnotice_bjggline_tv,studentnotice_zzggline_tv;
    private int flag=0;
    private LinearLayout studentnoticeadd_jxb_ll,studentnotice_zzselect_ll;//部门公告时才显示
    //标题，正文
    private EditText studentnotice_ggtitle_edt,studentnotice_content_edt;
    //发布人，发布时间，组织
    private TextView studentnotice_fbr_tv,studentnotice_fbtime_tv,studentnotice_xzzz_tv;
    private LinearLayout studnetnoticeadd_selectjxb_ll;//教学班选择
    private GDGridView studnetnoticeadd_bmmd_grd;
    private StudentNoticesAddBJMDAdapter bjmdAdapter;
    private List<AllClassEntity> classEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentnoticesadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
   public void initDatas(){
       classEntities = new ArrayList<AllClassEntity>();
   }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);

        studentnotice_qxgg_rel= (RelativeLayout)this.findViewById(R.id.studentnotice_qxgg_rel);
        studentnotice_bjgg_rel= (RelativeLayout)this.findViewById(R.id.studentnotice_bjgg_rel);
        studentnotice_zzg_rel= (RelativeLayout)this.findViewById(R.id.studentnotice_zzg_rel);
        studentnotice_qx_tv = (TextView)this.findViewById(R.id.studentnotice_qx_tv);
        studentnotice_bjgg_tv = (TextView)this.findViewById(R.id.studentnotice_bjgg_tv);
        studentnotice_zzgg_tv = (TextView)this.findViewById(R.id.studentnotice_zzgg_tv);
        studentnotice_qxline_tv= (TextView)this.findViewById(R.id.studentnotice_qxline_tv);
        studentnotice_bjggline_tv= (TextView)this.findViewById(R.id.studentnotice_bjggline_tv);
        studentnotice_zzggline_tv= (TextView)this.findViewById(R.id.studentnotice_zzggline_tv);

        studentnoticeadd_jxb_ll = (LinearLayout)this.findViewById(R.id.studentnoticeadd_jxb_ll);
        studentnotice_zzselect_ll = (LinearLayout)this.findViewById(R.id.studentnotice_zzselect_ll);

        studentnotice_ggtitle_edt=(EditText)this.findViewById(R.id.studentnotice_ggtitle_edt);
        studentnotice_content_edt=(EditText)this.findViewById(R.id.studentnotice_content_edt);
        studentnotice_fbr_tv= (TextView)this.findViewById(R.id.studentnotice_fbr_tv);
        studentnotice_fbtime_tv= (TextView)this.findViewById(R.id.studentnotice_fbtime_tv);
        studentnotice_xzzz_tv= (TextView)this.findViewById(R.id.studentnotice_xzzz_tv);
        studnetnoticeadd_selectjxb_ll = (LinearLayout)this.findViewById(R.id.studnetnoticeadd_selectjxb_ll);
        studnetnoticeadd_bmmd_grd = (GDGridView)this.findViewById(R.id.studnetnoticeadd_bmmd_grd);
        bjmdAdapter = new StudentNoticesAddBJMDAdapter(this,classEntities);
        studnetnoticeadd_bmmd_grd.setAdapter(bjmdAdapter);
    }

    public void initViewValues() {
        title.setText("添加公告");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
        setViewSelect(flag);
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        studentnotice_qxgg_rel.setOnClickListener(this);
        studentnotice_bjgg_rel.setOnClickListener(this);
        studentnotice_zzg_rel.setOnClickListener(this);
        studnetnoticeadd_selectjxb_ll.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.studentnotice_qxgg_rel:
                flag=0;
                setViewSelect(flag);
                break;
            case R.id.studentnotice_bjgg_rel:
                flag=1;
                setViewSelect(flag);
                break;
            case R.id.studentnotice_zzg_rel:
                flag=2;
                setViewSelect(flag);
                break;
            case R.id.right_three_btn:
                //调到图书分类界面
                break;
            case R.id.studnetnoticeadd_selectjxb_ll:
                //选择班级
                Intent intent = new Intent(StudentNoticesAddActivity.this, AllClassActivity.class);
                startActivityForResult(intent,102);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 102:
                    List<AllClassEntity> datas =(List<AllClassEntity>) data.getSerializableExtra("classdatas");
                    if(datas!=null&&datas.size()>0) {
                        if (classEntities.size() > 0) {
                            classEntities.clear();
                        }
                            classEntities.addAll(datas);
                        bjmdAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    public void setViewNormal() {
        studentnotice_qx_tv.setTextColor(getResources().getColor(R.color.xsgl_details_lx2_color));
        studentnotice_bjgg_tv.setTextColor(getResources().getColor(R.color.xsgl_details_lx2_color));
        studentnotice_zzgg_tv.setTextColor(getResources().getColor(R.color.xsgl_details_lx2_color));

        studentnotice_qxline_tv.setVisibility(View.GONE);
        studentnotice_bjggline_tv.setVisibility(View.GONE);
        studentnotice_zzggline_tv.setVisibility(View.GONE);

        studentnoticeadd_jxb_ll.setVisibility(View.GONE);
        studentnotice_zzselect_ll.setVisibility(View.GONE);
    }

    public void setViewSelect(int index) {
        setViewNormal();
        switch (index) {
            case 0:
                studentnotice_qx_tv.setTextColor(getResources().getColor(R.color.xsgl_details_lx1_color));
                studentnotice_qxline_tv.setVisibility(View.VISIBLE);
                studentnoticeadd_jxb_ll.setVisibility(View.GONE);
                studentnotice_zzselect_ll.setVisibility(View.GONE);
                break;

            case 1:
                studentnotice_bjgg_tv.setTextColor(getResources().getColor(
                        R.color.xsgl_details_lx1_color));
                studentnotice_bjggline_tv.setVisibility(View.VISIBLE);
                studentnoticeadd_jxb_ll.setVisibility(View.VISIBLE);
                studentnotice_zzselect_ll.setVisibility(View.GONE);
                break;
            case 2:
                studentnotice_zzgg_tv.setTextColor(getResources().getColor(
                        R.color.xsgl_details_lx1_color));
                studentnotice_zzggline_tv.setVisibility(View.VISIBLE);
                studentnoticeadd_jxb_ll.setVisibility(View.GONE);
                studentnotice_zzselect_ll.setVisibility(View.VISIBLE);
                break;
        }

    }
}
