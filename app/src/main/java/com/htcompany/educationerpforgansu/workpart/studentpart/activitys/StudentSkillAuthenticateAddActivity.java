package com.htcompany.educationerpforgansu.workpart.studentpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;

/**
 * 技能鉴定添加界面
 * Created by wrb on 2016/11/23.
 */
public class StudentSkillAuthenticateAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentskillauthenticateadd_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);

    }

    public void initViewValues() {
        title.setText("技能鉴定添加");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("提交");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
}
