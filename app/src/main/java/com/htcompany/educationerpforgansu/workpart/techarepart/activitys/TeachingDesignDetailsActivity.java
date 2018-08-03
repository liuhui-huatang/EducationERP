package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingDesignEntity;

/**
 * //代课调课详情
 * Created by wrb on 2016/12/20.
 */
public class TeachingDesignDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TeachingDesignEntity designEntity;
    private TextView teachingdesigndetails_term_tv,teachingdesigndetails_kc_tv,teachingdesigndetails_zc_tv,
            teachingdesigndetails_xq_tv,teachingdesigndetails_jc_tv,teachingdesigndetails_type_tv,teachingdesigndetails_dkjs_tv,
            teachingdesigndetails_dkkc_tv,teachingdesigndetails_dkxq_tv,teachingdesigndetails_dkjc_tv;
    //代课，调课
    private LinearLayout teachingdesigndetails_dkcontent_ll,teachingdesigndetails_tkcontent_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachingdesigndetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        designEntity = (TeachingDesignEntity)getIntent().getSerializableExtra("designEntity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        teachingdesigndetails_term_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_term_tv);
        teachingdesigndetails_kc_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_kc_tv);
        teachingdesigndetails_zc_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_zc_tv);
        teachingdesigndetails_xq_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_xq_tv);
        teachingdesigndetails_jc_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_jc_tv);
        teachingdesigndetails_type_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_type_tv);
        teachingdesigndetails_dkjs_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_dkjs_tv);
        teachingdesigndetails_dkkc_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_dkkc_tv);
        teachingdesigndetails_dkxq_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_dkxq_tv);
        teachingdesigndetails_dkjc_tv= (TextView)this.findViewById(R.id.teachingdesigndetails_dkjc_tv);

        teachingdesigndetails_dkcontent_ll=(LinearLayout)this.findViewById(R.id.teachingdesigndetails_dkcontent_ll);
        teachingdesigndetails_tkcontent_ll=(LinearLayout)this.findViewById(R.id.teachingdesigndetails_tkcontent_ll);
    }
    public void initViewValues(){
        if(designEntity!=null){
            if("1".equals(designEntity.getType())){
                title.setText("代课详情");
                teachingdesigndetails_type_tv.setText("代课");
                teachingdesigndetails_dkcontent_ll.setVisibility(View.VISIBLE);
                teachingdesigndetails_tkcontent_ll.setVisibility(View.GONE);
            }else if("2".equals(designEntity.getType())){
                title.setText("调课详情");
                teachingdesigndetails_type_tv.setText("调课");
                teachingdesigndetails_dkcontent_ll.setVisibility(View.GONE);
                teachingdesigndetails_tkcontent_ll.setVisibility(View.VISIBLE);
            }
            teachingdesigndetails_term_tv.setText(designEntity.getXqid_name());
            teachingdesigndetails_kc_tv.setText(designEntity.getCourceid_name());
            teachingdesigndetails_zc_tv.setText(designEntity.getWeeks());
            teachingdesigndetails_xq_tv.setText(designEntity.getYweek());
            teachingdesigndetails_jc_tv.setText(designEntity.getYsecid());
            teachingdesigndetails_dkjs_tv.setText(designEntity.getDktea_name());
            if("1".equals(designEntity.getType())){
                teachingdesigndetails_dkkc_tv.setText(designEntity.getTcource_name());
            }else if("2".equals(designEntity.getType())){
                teachingdesigndetails_dkkc_tv.setText(designEntity.getDktea_name());
            }
            teachingdesigndetails_dkxq_tv.setText(designEntity.getTweek());
            teachingdesigndetails_dkjc_tv.setText(designEntity.getTsecid());
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
