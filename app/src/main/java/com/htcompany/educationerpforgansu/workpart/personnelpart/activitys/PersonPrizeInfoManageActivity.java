package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseFragmenetActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.fragments.PersonManagePunishmentFragment;
import com.htcompany.educationerpforgansu.workpart.personnelpart.fragments.PersonManageRewardsFragment;

/**
 * 教职工奖惩记录管理
 * Created by wrb on 2016/11/21.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class PersonPrizeInfoManageActivity extends BaseFragmenetActivity implements View.OnClickListener{
    private RelativeLayout rebackselector_btn,rightselector_three_btn;
    private TextView title_leftitem_tv,title_rightitem_tv;
    private int titleItemShowFlag=1;
    private PersonManageRewardsFragment rewardsFragment;
    private PersonManagePunishmentFragment punishmentFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction beginTransaction = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprizeinfo_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        rewardsFragment = new PersonManageRewardsFragment();
        punishmentFragment = new PersonManagePunishmentFragment();
    }
    public void initViews(){
        rebackselector_btn=(RelativeLayout)this.findViewById(R.id.rebackselector_btn);
        rightselector_three_btn=(RelativeLayout)this.findViewById(R.id.rightselector_three_btn);
        title_leftitem_tv=(TextView)this.findViewById(R.id.title_leftitem_tv);
        title_rightitem_tv=(TextView)this.findViewById(R.id.title_rightitem_tv);
    }

    public void initViewValues(){
        rightselector_three_btn.setVisibility(View.GONE);
        title_leftitem_tv.setText("奖励记录");
        title_rightitem_tv.setText("惩罚记录");
        initSelectViews(titleItemShowFlag);
    }

    public void initOnclicEvents(){
        title_leftitem_tv.setOnClickListener(this);
        title_rightitem_tv.setOnClickListener(this);
        rebackselector_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rebackselector_btn:
                this.finish();
                break;
            case R.id.title_leftitem_tv:
                titleItemShowFlag=1;
                initSelectViews(titleItemShowFlag);
                break;
            case R.id.title_rightitem_tv:
                titleItemShowFlag=2;
                initSelectViews(titleItemShowFlag);
                break;
        }
    }

    public void initNormalViews(){
        title_leftitem_tv.setTextColor(getResources().getColor(R.color.white));
        title_leftitem_tv.setBackground(getResources().getDrawable(R.drawable.title_tcolor_left_shape));
        title_rightitem_tv.setTextColor(getResources().getColor(R.color.white));
        title_rightitem_tv.setBackground(getResources().getDrawable(R.drawable.title_tcolor_right_shape));
    }

    public void initSelectViews(int viewFlag){
        initNormalViews();
        switch (viewFlag){
            case 1:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.myprizeinfocontent_framgent, rewardsFragment);
                beginTransaction.commit();
                title_leftitem_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                title_leftitem_tv.setBackground(getResources().getDrawable(R.drawable.title_whit_left_shape));
                break;
            case 2:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.myprizeinfocontent_framgent, punishmentFragment);
                beginTransaction.commit();
                title_rightitem_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                title_rightitem_tv.setBackground(getResources().getDrawable(R.drawable.title_whit_right_shape));
                break;
        }
    }
}
