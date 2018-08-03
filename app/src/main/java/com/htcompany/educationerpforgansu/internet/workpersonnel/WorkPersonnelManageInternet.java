package com.htcompany.educationerpforgansu.internet.workpersonnel;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 人事管理网络请求类
 * Created by wrb on 2017/4/12.
 */
public class WorkPersonnelManageInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public WorkPersonnelManageInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 请求人事档案列表
     */
    public void getPersonFilesList(String pagenum,String name){
        params = new RequestParams();
        params.addBodyParameter("page", pagenum);
        params.addBodyParameter("ser_username", name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WORKPERSON_PERSONFILE_LIST),200,params,myHandler);
    }
    /**
     * 请求社会保险记录列表
     */
    public void getPersonSocialInsuranceList(String pagenum,String name){
        params = new RequestParams();
        params.addBodyParameter("page", pagenum);
        params.addBodyParameter("username", name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WORKPERSON_PERSONSOCIALINSURANCE_LIST),200,params,myHandler);
    }
    /**
     * 请求劳动合同录列表
     */
    public void getPersonContractList(String pagenum,String name){
        params = new RequestParams();
        params.addBodyParameter("page", pagenum);
        params.addBodyParameter("username", name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WORKPERSON_PERSONCONTRACT_LIST),200,params,myHandler);
    }
    /**
     * 请求教职工管理列表
     */
    public void getPersoManageList(String pagenum,String name){
        params = new RequestParams();
        params.addBodyParameter("page", pagenum);
        params.addBodyParameter("username", name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WORKPERSON_PERSONMANAGE_LIST),200,params,myHandler);
    }
    /**
     * 请求教职工请假列表
     */
    public void getPersoLeaveApplyManageList(String pagenum,String name){
        params = new RequestParams();
        params.addBodyParameter("page", pagenum);
        params.addBodyParameter("ser_username", name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WORKPERSON_PERSONLEAVEAPPLY_LIST),200,params,myHandler);
    }
    /**
     * 请求教职工销假
     */
    public void uploadPersoLeaveApplyXJList(String id){
        params = new RequestParams();
        params.addBodyParameter("id", id);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WORKPERSON_PERSONLEAVEAPPLY_XJ_LIST),200,params,myHandler);
    }
    /**
     * 请求教职工销假
     */
    public void uploadPersoLeaveApplyTJList(String id){
        params = new RequestParams();
        params.addBodyParameter("id", id);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WORKPERSON_PERSONLEAVEAPPLY_TJ_LIST),200,params,myHandler);
    }
}
