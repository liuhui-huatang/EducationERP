package com.htcompany.educationerpforgansu.workpart.studentpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;

/**
 * 技能鉴定报名详情
 * Created by wrb on 2016/11/23.
 */
public class StudentSkillAuthenticateDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    //全校公告，班级公告，
    private RelativeLayout studentjnjdxq_xq_rel,studentjnjdxq_bmqk_rel;
    private TextView studentjnjdxq_xq_tv,studentjnjdxq_bmqk_tv;
    private TextView studentjnjdxq_xqline_tv,studentjnjdxq_bmqkline_tv;
    private int flag=0;
    private LinearLayout skillauthenticate_xq_ll,skillauthenticate_bmqk_ll;//详情，报名情况
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentskillauthenticatedetails_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);

        studentjnjdxq_xq_rel= (RelativeLayout)this.findViewById(R.id.studentjnjdxq_xq_rel);
        studentjnjdxq_bmqk_rel= (RelativeLayout)this.findViewById(R.id.studentjnjdxq_bmqk_rel);

        studentjnjdxq_xq_tv = (TextView)this.findViewById(R.id.studentjnjdxq_xq_tv);
        studentjnjdxq_bmqk_tv = (TextView)this.findViewById(R.id.studentjnjdxq_bmqk_tv);

        studentjnjdxq_xqline_tv= (TextView)this.findViewById(R.id.studentjnjdxq_xqline_tv);
        studentjnjdxq_bmqkline_tv= (TextView)this.findViewById(R.id.studentjnjdxq_bmqkline_tv);

        skillauthenticate_xq_ll = (LinearLayout)this.findViewById(R.id.skillauthenticate_xq_ll);
        skillauthenticate_bmqk_ll = (LinearLayout)this.findViewById(R.id.skillauthenticate_bmqk_ll);
    }

    public void initViewValues() {
        title.setText("鉴定详情");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("删除");
        setViewSelect(flag);
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        studentjnjdxq_xq_rel.setOnClickListener(this);
        studentjnjdxq_bmqk_rel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.studentjnjdxq_xq_rel:
                flag=0;
                setViewSelect(flag);
                break;
            case R.id.studentjnjdxq_bmqk_rel:
                flag=1;
                setViewSelect(flag);
                break;
            case R.id.right_three_btn:
                //调到图书分类界面
                break;
        }
    }

    public void setViewNormal() {
        studentjnjdxq_xq_tv.setTextColor(getResources().getColor(R.color.xsgl_details_lx2_color));
        studentjnjdxq_bmqk_tv.setTextColor(getResources().getColor(R.color.xsgl_details_lx2_color));

        studentjnjdxq_xqline_tv.setVisibility(View.GONE);
        studentjnjdxq_bmqkline_tv.setVisibility(View.GONE);

        skillauthenticate_xq_ll.setVisibility(View.GONE);
        skillauthenticate_bmqk_ll.setVisibility(View.GONE);
    }

    public void setViewSelect(int index) {
        setViewNormal();
        switch (index) {
            case 0:
                studentjnjdxq_xq_tv.setTextColor(getResources().getColor(R.color.xsgl_details_lx1_color));
                studentjnjdxq_xqline_tv.setVisibility(View.VISIBLE);
                skillauthenticate_xq_ll.setVisibility(View.VISIBLE);
                break;

            case 1:
                studentjnjdxq_bmqk_tv.setTextColor(getResources().getColor(
                        R.color.xsgl_details_lx1_color));
                studentjnjdxq_bmqkline_tv.setVisibility(View.VISIBLE);
                skillauthenticate_bmqk_ll.setVisibility(View.VISIBLE);
                break;
        }

    }
}
