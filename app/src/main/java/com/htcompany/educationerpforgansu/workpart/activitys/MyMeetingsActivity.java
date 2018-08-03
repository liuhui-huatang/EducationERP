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
import com.htcompany.educationerpforgansu.workpart.fragments.MeetingAccessFragment;
import com.htcompany.educationerpforgansu.workpart.fragments.MeetingFailedFragment;
import com.htcompany.educationerpforgansu.workpart.fragments.MeetingIsUploadFragment;
import com.htcompany.educationerpforgansu.workpart.fragments.MeetingOverFragment;
import com.htcompany.educationerpforgansu.workpart.fragments.MeetingWaitUploadFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的会议
 * Created by wrb on 2016/11/9.
 */
public class MyMeetingsActivity extends BaseFragmenetActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
   /*
    *  * 全部，进行中,待评价,待支付,完成：副布局
    */
    private RelativeLayout order_all_rel, order_continu_rel,
            order_evaluate_rel, order_pay_rel, order_complete_rel;
    /**
     * 待提交，待审核，已通过，未通过，已取消
     */
    private TextView order_all_tv, order_continu_tv, order_evaluate_tv,
            order_pay_tv, order_complete_tv;
    /**
     * 全部下划线,进行中下划线，待评价下划线,待支付下划线，已完成下划线
     */
    private TextView order_allline_tv, order_continuline_tv,
            order_evaluateline_tv, order_payline_tv, order_completeline_tv;
    /**
     * 导航下
     */
    private int titleBarFlag=0;
    private FragmentManager mFragmentManager;
    private List<Fragment> fragments;
    private ViewPager order_viewpager;
    //待提交会议，已提交会议，已通过会议，未通过会议，已结束会议
    private Fragment meetingWaitUploadFragment,meetingIsUploadFragment,meetingAccessFragment,meetingFailedFragment,meetingOverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymeetings_activity);
        initDatas();
        initViews();
        initViewValue();
        InitViewPager();
        initOnclicEvents();
    }
    public void initDatas() {
        // TODO Auto-generated method stub
        fragments = new ArrayList<Fragment>();
        meetingWaitUploadFragment = new MeetingWaitUploadFragment();
        meetingIsUploadFragment = new MeetingIsUploadFragment();
        meetingAccessFragment = new MeetingAccessFragment();
        meetingFailedFragment = new MeetingFailedFragment();
        meetingOverFragment = new MeetingOverFragment();
        fragments.add(meetingWaitUploadFragment);
        fragments.add(meetingIsUploadFragment);
        fragments.add(meetingAccessFragment);
        fragments.add(meetingFailedFragment);
        fragments.add(meetingOverFragment);
    }
    public void initViews() {
        // TODO Auto-generated method stub
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        rightthree_btn_tv = (TextView)this.findViewById(R.id.rightthree_btn_tv);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        order_all_rel = (RelativeLayout) this.findViewById(R.id.order_all_rel);
        order_continu_rel = (RelativeLayout) this
                .findViewById(R.id.order_continu_rel);
        order_evaluate_rel = (RelativeLayout) this
                .findViewById(R.id.order_evaluate_rel);
        order_pay_rel = (RelativeLayout) this.findViewById(R.id.order_pay_rel);
        order_complete_rel = (RelativeLayout) this
                .findViewById(R.id.order_complete_rel);
        order_all_tv = (TextView) this.findViewById(R.id.order_all_tv);
        order_continu_tv = (TextView) this.findViewById(R.id.order_continu_tv);
        order_evaluate_tv = (TextView) this
                .findViewById(R.id.order_evaluate_tv);
        order_pay_tv = (TextView) this.findViewById(R.id.order_pay_tv);
        order_complete_tv = (TextView) this
                .findViewById(R.id.order_complete_tv);
        order_allline_tv = (TextView) this.findViewById(R.id.order_allline_tv);
        order_continuline_tv = (TextView) this
                .findViewById(R.id.order_continuline_tv);
        order_evaluateline_tv = (TextView) this
                .findViewById(R.id.order_evaluateline_tv);
        order_payline_tv = (TextView) this.findViewById(R.id.order_payline_tv);
        order_completeline_tv = (TextView) this
                .findViewById(R.id.order_completeline_tv);
        order_viewpager = (ViewPager)this.findViewById(R.id.order_viewpager);
    }
    public void initViewValue() {
        // TODO Auto-generated method stub
        title.setText("我的会议");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("申请");
        setViewNormal();
        setViewSelect(titleBarFlag);
    }

    public void initOnclicEvents() {
        // TODO Auto-generated method stub
        reback_btn.setOnClickListener(this);
        order_all_rel.setOnClickListener(titleBarClier);
        order_continu_rel.setOnClickListener(titleBarClier);
        order_evaluate_rel.setOnClickListener(titleBarClier);
        order_pay_rel.setOnClickListener(titleBarClier);
        order_complete_rel.setOnClickListener(titleBarClier);
        right_three_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //调到会议申请界面
                Intent intent = new Intent(MyMeetingsActivity.this,MeetingApplyActivity.class);
                        startActivity(intent);
                break;
        }
    }
    /**
     * 初始化Viewpager页
     */
    private void InitViewPager() {
        order_viewpager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
                fragments));
        order_viewpager.setCurrentItem(0);
        order_viewpager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    /**
     * 导航点击监听事件
     */
    public View.OnClickListener titleBarClier = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.order_all_rel:
                    //全部
                    titleBarFlag=0;
                    setViewSelect(titleBarFlag);
                    order_viewpager.setCurrentItem(titleBarFlag);
                    break;
                case R.id.order_continu_rel:
                    //进行中
                    titleBarFlag=1;
                    setViewSelect(titleBarFlag);
                    order_viewpager.setCurrentItem(titleBarFlag);
                    break;
                case R.id.order_evaluate_rel:
                    //待评价
                    titleBarFlag=2;
                    setViewSelect(titleBarFlag);
                    order_viewpager.setCurrentItem(titleBarFlag);
                    break;
                case R.id.order_pay_rel:
                    //待支付
                    titleBarFlag=3;
                    setViewSelect(titleBarFlag);
                    order_viewpager.setCurrentItem(titleBarFlag);
                    break;
                case R.id.order_complete_rel:
                    //完成
                    titleBarFlag=4;
                    setViewSelect(titleBarFlag);
                    order_viewpager.setCurrentItem(titleBarFlag);
                    break;

                default:
                    break;
            }
        }
    };
    public void setViewNormal() {
        order_all_tv.setTextColor(getResources().getColor(R.color.black));
        order_continu_tv.setTextColor(getResources().getColor(R.color.black));
        order_evaluate_tv.setTextColor(getResources().getColor(R.color.black));
        order_pay_tv.setTextColor(getResources().getColor(R.color.black));
        order_complete_tv.setTextColor(getResources().getColor(R.color.black));

        order_allline_tv.setVisibility(View.GONE);
        order_continuline_tv.setVisibility(View.GONE);
        order_evaluateline_tv.setVisibility(View.GONE);
        order_payline_tv.setVisibility(View.GONE);
        order_completeline_tv.setVisibility(View.GONE);
    }

    public void setViewSelect(int index) {
        setViewNormal();
        switch (index) {
            case 0:
                order_all_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                order_allline_tv.setVisibility(View.VISIBLE);
                break;

            case 1:
                order_continu_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                order_continuline_tv.setVisibility(View.VISIBLE);
                break;
            case 2:
                order_evaluate_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                order_evaluateline_tv.setVisibility(View.VISIBLE);
                break;
            case 3:
                order_pay_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                order_payline_tv.setVisibility(View.VISIBLE);
                break;
            case 4:
                order_complete_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                order_completeline_tv.setVisibility(View.VISIBLE);
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
                case 3:
                    setViewSelect(titleBarFlag);
                    break;
                case 4:
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
                if (null == meetingWaitUploadFragment) {
                    meetingWaitUploadFragment = new MeetingWaitUploadFragment();
                    transaction.add(R.id.center_layout, meetingWaitUploadFragment);
                } else {
                    transaction.show(meetingWaitUploadFragment);
                }
                break;
            case 1:
                if (null == meetingIsUploadFragment) {
                    meetingIsUploadFragment = new MeetingIsUploadFragment();
                    transaction.add(R.id.center_layout, meetingIsUploadFragment);
                } else {
                    transaction.show(meetingIsUploadFragment);
                }
                break;
            case 2:
                if (null == meetingAccessFragment) {
                    meetingAccessFragment = new MeetingAccessFragment();
                    transaction.add(R.id.center_layout, meetingAccessFragment);
                } else {
                    transaction.show(meetingAccessFragment);
                }
                break;
            case 3:
                if (null == meetingFailedFragment) {
                    meetingFailedFragment = new MeetingFailedFragment();
                    transaction.add(R.id.center_layout, meetingFailedFragment);
                } else {
                    transaction.show(meetingFailedFragment);
                }
                break;
            case 4:
                if (null == meetingOverFragment) {
                    meetingOverFragment = new MeetingOverFragment();
                    transaction.add(R.id.center_layout, meetingOverFragment);
                } else {
                    transaction.show(meetingOverFragment);
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
        if (null != meetingWaitUploadFragment) {
            transaction.hide(meetingWaitUploadFragment);
        }
        if (null != meetingIsUploadFragment) {
            transaction.hide(meetingIsUploadFragment);
        }
        if (null != meetingAccessFragment) {
            transaction.hide(meetingAccessFragment);
        }
        if (null != meetingFailedFragment) {
            transaction.hide(meetingFailedFragment);
        }
        if (null != meetingOverFragment) {
            transaction.hide(meetingOverFragment);
        }
    }
}
