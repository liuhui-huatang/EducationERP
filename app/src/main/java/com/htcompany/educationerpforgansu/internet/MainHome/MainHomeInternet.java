package com.htcompany.educationerpforgansu.internet.MainHome;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 个人信息网络请求类
 * Created by wrb on 2017/3/30.
 */
public class MainHomeInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public MainHomeInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 请求个人信息
     */
    public void getPersonMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONCENTER_BASEMSG),200,params,myHandler);
    }
    /**
     * 请求合同详情信息
     */
    public void getContractDetialsMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONCENTER_MYCONTRACTMSG),200,params,myHandler);
    }
    /**
     * 请求我的奖励列表数据
     */
    public void getMyRevardsListMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONCENTER_MYREWARDS_LIST),200,params,myHandler);
    }
    /**
     * 请求我的惩罚列表数据
     */
    public void getMyPunishmentsListMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONCENTER_MYPUNISHMENT_LIST),200,params,myHandler);
    }
    /**
     * 请求个人考核列表数据
     */
    public void getPersonalAsseMentListMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONCENTER_PERSONALASSESSMENT_LIST),200,params,myHandler);
    }
    /**
     * 请求绩效核定列表数据
     */
    public void getPerformanceForApprovalListMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONCENTER_PERSONALJXHD_LIST),200,params,myHandler);
    }
}
