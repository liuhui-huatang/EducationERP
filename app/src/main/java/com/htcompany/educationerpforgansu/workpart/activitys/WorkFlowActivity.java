package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseFragmenetActivity;
import com.htcompany.educationerpforgansu.workpart.fragments.WorkFlowMySendFragment;
import com.htcompany.educationerpforgansu.workpart.fragments.WorkFlowOverFragment;
import com.htcompany.educationerpforgansu.workpart.fragments.WorkFlowWaitFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作流
 * Created by wrb on 2017/1/3.
 */
public class WorkFlowActivity extends BaseFragmenetActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    //======================导航=========================
    private RelativeLayout wokflow_wait_rel,wokflow_over_rel,wokflow_isend_rel;
    private TextView wokflow_wait_tv,wokflow_over_tv,wokflow_isend_tv;
    private TextView wokflow_waitline_tv,wokflow_overline_tv,wokflow_isendline_tv;
    /**
     * 导航下
     */
    private int titleBarFlag=0;
    private FragmentManager mFragmentManager;
    private List<Fragment> fragments;
    private ViewPager wokflow_viewpager;
    //代办事项，已办事项，我发起的流程
    private Fragment workflowWaitFragment,workflowOverFragment,workflowISendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workflow_activity);
        initDatas();
        initViews();
        initViewValues();
        InitViewPager();
        initOnclicEvents();
    }
    public void initDatas(){
        fragments = new ArrayList<Fragment>();
        workflowWaitFragment=new WorkFlowWaitFragment();
        workflowOverFragment=new WorkFlowOverFragment();
        workflowISendFragment=new WorkFlowMySendFragment();
        fragments.add(workflowWaitFragment);
        fragments.add(workflowOverFragment);
        fragments.add(workflowISendFragment);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        rightthree_btn_tv= (TextView)this.findViewById(R.id.rightthree_btn_tv);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        //======================导航=========================
        wokflow_wait_rel= (RelativeLayout)this.findViewById(R.id.wokflow_wait_rel);
        wokflow_over_rel= (RelativeLayout)this.findViewById(R.id.wokflow_over_rel);
        wokflow_isend_rel= (RelativeLayout)this.findViewById(R.id.wokflow_isend_rel);
        wokflow_wait_tv= (TextView)this.findViewById(R.id.wokflow_wait_tv);
        wokflow_over_tv= (TextView)this.findViewById(R.id.wokflow_over_tv);
        wokflow_isend_tv= (TextView)this.findViewById(R.id.wokflow_isend_tv);
        wokflow_waitline_tv= (TextView)this.findViewById(R.id.wokflow_waitline_tv);
        wokflow_overline_tv= (TextView)this.findViewById(R.id.wokflow_overline_tv);
        wokflow_isendline_tv= (TextView)this.findViewById(R.id.wokflow_isendline_tv);
        wokflow_viewpager = (ViewPager)this.findViewById(R.id.wokflow_viewpager);
    }
    public void initViewValues(){
        title.setText("工作流");
        rightthree_btn_tv.setText("发起");
        right_three_btn.setVisibility(View.VISIBLE);
        setViewNormal();
        setViewSelect(titleBarFlag);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        wokflow_wait_rel.setOnClickListener(this);
        wokflow_over_rel.setOnClickListener(this);
        wokflow_isend_rel.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    /**
     * 初始化Viewpager页
     */
    private void InitViewPager() {
        wokflow_viewpager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),fragments));
        wokflow_viewpager.setCurrentItem(0);
        wokflow_viewpager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //跳到发起工作流界面
                 startActivity(new Intent(this,WorkFlowStartSendActivity.class));
                break;
            case R.id.wokflow_wait_rel:
                //全部
                titleBarFlag=0;
                setViewSelect(titleBarFlag);
                wokflow_viewpager.setCurrentItem(titleBarFlag);
                break;
            case R.id.wokflow_over_rel:
                //进行中
                titleBarFlag=1;
                setViewSelect(titleBarFlag);
                wokflow_viewpager.setCurrentItem(titleBarFlag);
                break;
            case R.id.wokflow_isend_rel:
                //待评价
                titleBarFlag=2;
                setViewSelect(titleBarFlag);
                wokflow_viewpager.setCurrentItem(titleBarFlag);
                break;
        }
    }
    /**
     * 为选项卡绑定监听器
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int index) {
            titleBarFlag = index;
            switch (index) {
                case 0:
                    setViewSelect(titleBarFlag);
                    break;
                case 1:
                    setViewSelect(titleBarFlag);
                    break;
                case 2:
                    setViewSelect(titleBarFlag);
                    break;
            }
        }
    }

    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
    /**
     * 切换fragment
     *
     * @param index
     */
    public void onTabSelected(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (null == workflowWaitFragment) {
                    workflowWaitFragment = new WorkFlowWaitFragment();
                    transaction.add(R.id.center_layout, workflowWaitFragment);
                } else {
                    transaction.show(workflowWaitFragment);
                }
                break;
            case 1:
                if (null == workflowOverFragment) {
                    workflowOverFragment = new WorkFlowOverFragment();
                    transaction.add(R.id.center_layout, workflowOverFragment);
                } else {
                    transaction.show(workflowOverFragment);
                }
                break;
            case 2:
                if (null == workflowISendFragment) {
                    workflowISendFragment = new WorkFlowMySendFragment();
                    transaction.add(R.id.center_layout, workflowISendFragment);
                } else {
                    transaction.show(workflowISendFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }
    /**
     * 隐藏fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != workflowWaitFragment) {
            transaction.hide(workflowWaitFragment);
        }
        if (null != workflowOverFragment) {
            transaction.hide(workflowOverFragment);
        }
        if (null != workflowISendFragment) {
            transaction.hide(workflowISendFragment);
        }
    }

    public void setViewNormal() {
        wokflow_wait_tv.setTextColor(getResources().getColor(R.color.black));
        wokflow_over_tv.setTextColor(getResources().getColor(R.color.black));
        wokflow_isend_tv.setTextColor(getResources().getColor(R.color.black));

        wokflow_waitline_tv.setVisibility(View.GONE);
        wokflow_overline_tv.setVisibility(View.GONE);
        wokflow_isendline_tv.setVisibility(View.GONE);
    }

    public void setViewSelect(int index) {
        setViewNormal();
        switch (index) {
            case 0:
                wokflow_wait_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                wokflow_waitline_tv.setVisibility(View.VISIBLE);
                break;

            case 1:
                wokflow_over_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                wokflow_overline_tv.setVisibility(View.VISIBLE);
                break;
            case 2:
                wokflow_isend_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                wokflow_isendline_tv.setVisibility(View.VISIBLE);
                break;
        }

    }


}
