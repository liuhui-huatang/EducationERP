package com.htcompany.educationerpforgansu.homepart;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.homepart.activitys.MyContractDetialsActivity;
import com.htcompany.educationerpforgansu.homepart.activitys.MyPrizeInfoActivity;
import com.htcompany.educationerpforgansu.homepart.activitys.MySalariesActivity;
import com.htcompany.educationerpforgansu.homepart.activitys.PersonMsgActivity;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.activitys.PerformanceForApprovalActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.PersonalAssessmentActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 我的
 * Created by wrb on 2016/11/2.
 */
public class MainHomeFragment extends Fragment implements View.OnClickListener{
    private View view;
    Intent intent=null;
    private XCRoundAndOvalImageView mainwork_photo_img;
    private TextView mainhome_zhiwei_tv;
    //基本信息，劳动合同，我的工资,奖惩记录
    private RelativeLayout home_personmsg_rel,home_personldhtg_rel,home_mysalaries_rel,home_myprizeinfo_rel,home_gerenkaohe_rel,home_jxhd_rel;
    private TextView mainhome_name_tv,exit_app_tv;
    private SharedPrefUtil sharedPrefUtil=null;
    private TextView mainhome_title_tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mainhome_fragment,container,false);
        initDatas();
        initViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            initValues();
        }
        initViewValues();
        initOnclicEvents();




        return view;
    }
    public void initDatas(){
        sharedPrefUtil = new SharedPrefUtil(getActivity(), "login");
    }
    public void initViews(){
        mainhome_title_tv=(TextView)view.findViewById(R.id.mainhome_title_tv);
        mainwork_photo_img=(XCRoundAndOvalImageView)view.findViewById(R.id.mainwork_photo_img);
        home_personmsg_rel = (RelativeLayout)view.findViewById(R.id.home_personmsg_rel);
        home_personldhtg_rel = (RelativeLayout)view.findViewById(R.id.home_personldhtg_rel);
        home_mysalaries_rel = (RelativeLayout)view.findViewById(R.id.home_mysalaries_rel);
        home_myprizeinfo_rel = (RelativeLayout)view.findViewById(R.id.home_myprizeinfo_rel);

        home_gerenkaohe_rel= (RelativeLayout)view.findViewById(R.id.home_gerenkaohe_rel);
        home_jxhd_rel= (RelativeLayout)view.findViewById(R.id.home_jxhd_rel);

        mainhome_name_tv=(TextView)view.findViewById(R.id.mainhome_name_tv);
        mainhome_zhiwei_tv=(TextView)view.findViewById(R.id.mainhome_zhiwei_tv);
                exit_app_tv=(TextView)view.findViewById(R.id.exit_app_tv);
    }
    public void initViewValues(){
        mainhome_name_tv.setText(sharedPrefUtil.getString("username",""));
        mainhome_zhiwei_tv.setText("岗位:"+sharedPrefUtil.getString("zhiwei",""));
//            ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+sharedPrefUtil.getString("userphoto",""),
//                    mainwork_photo_img,MyApp.getOptions());
        Glide.with(getActivity())
                .load(InterfaceManager.siteURLIP+sharedPrefUtil.getString("userphoto",""))
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(mainwork_photo_img);

    }
    public void initValues(){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mainhome_title_tv.getLayoutParams();
        layoutParams.setMargins(0, BaseUtils.getStatusBarHeight(getActivity()),0,0);//4个参数按顺序分别是左上右下
        mainhome_title_tv.setLayoutParams(layoutParams); //mView是控件
    }
    public void initOnclicEvents(){
        home_personmsg_rel.setOnClickListener(this);
        home_personldhtg_rel.setOnClickListener(this);
        home_mysalaries_rel.setOnClickListener(this);
        home_myprizeinfo_rel.setOnClickListener(this);
        home_gerenkaohe_rel.setOnClickListener(this);
        home_jxhd_rel.setOnClickListener(this);
        exit_app_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_app_tv:
                 exit();
                break;
            case R.id.home_personmsg_rel:
                //基本信息
                intent = new Intent(getActivity(), PersonMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.home_personldhtg_rel:
                //劳动合同
                intent = new Intent(getActivity(), MyContractDetialsActivity.class);
                startActivity(intent);
                break;
            case R.id.home_mysalaries_rel:
                //我的工资
                intent = new Intent(getActivity(),MySalariesActivity.class);
                startActivity(intent);
                break;
            case R.id.home_myprizeinfo_rel:
                //奖惩记录
                intent = new Intent(getActivity(),MyPrizeInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.home_gerenkaohe_rel:
                //个人考核
                intent = new Intent(getActivity(),PersonalAssessmentActivity.class);
                startActivity(intent);
                break;
            case R.id.home_jxhd_rel:
                //绩效核定
                intent = new Intent(getActivity(),PerformanceForApprovalActivity.class);
                startActivity(intent);
                break;
        }
    }

    //退出环信
    public void exit(){
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                sharedPrefUtil.putString("username","");
                sharedPrefUtil.putString("userphoto","");
                sharedPrefUtil.putString("hxuser","");
                sharedPrefUtil.putString("hxpassword","");
                sharedPrefUtil.putString("jpuid","");
                sharedPrefUtil.putString("zhiwei","");
                sharedPrefUtil.putString("token","");
                sharedPrefUtil.putString("loginpwd","");
                sharedPrefUtil.putString("islogin","");
                sharedPrefUtil.commit();
                setAlias("");
                MyApp.getInstance().exit();
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub

            }
        });
    }

    //=========================================注册激光推送别名=================================================
    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias(String alias) {
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    sharedPrefUtil.putString("jgbgsucess","1");
                    sharedPrefUtil.commit();
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
            }
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getActivity(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };
}
