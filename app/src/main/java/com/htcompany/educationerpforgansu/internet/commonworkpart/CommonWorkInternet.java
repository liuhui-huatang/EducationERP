package com.htcompany.educationerpforgansu.internet.commonworkpart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 办公公告部分网络请求类
 * Created by wrb on 2017/4/15.
 */
public class CommonWorkInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public CommonWorkInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取办公公告列表数据
     * @param type 0全部共公告，1部门公告，2全校公告
     */
    public void getWorkNoticeListDatas(String type){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("type", type);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_WORKNOTICE_LIST),200,params,myHandler);
    }
    /**
     * 删除办公公告列表数据
     */
    public void deleteWorkNoticeListItemDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_DELETEWORKNOTICE_LIST),201,params,myHandler);
    }
    /**
     * 添加办公公告数据
     */
    public void addWorkNoticeDatas(String title,String time,String content,String type,String org){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("title", title);
        params.addBodyParameter("time", time);
        params.addBodyParameter("content", content);
        params.addBodyParameter("type", type);
        params.addBodyParameter("org", org);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_WORKNOTICE_ADD),200,params,myHandler);
    }
    /**
     * 获取部门列表数据
     */
    public void getpartListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_PART_LIST),200,params,myHandler);
    }
    /**
     * 获取会议管理列表数据
     */
    public void getWorkMeetingsListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_MEETINGMANAGE_LIST),200,params,myHandler);
    }
    /**
     * 审核通过会议管理数据
     */
    public void assessWorkMeetingsDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_ASSESSMEETINGMANAGE),200,params,myHandler);
    }
    /**
     * 审核不通过会议管理数据
     */
    public void notAssessWorkMeetingsDatas(String id,String content){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        params.addBodyParameter("content", content);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_NTOASSESSMEETINGMANAGE),201,params,myHandler);
    }
    /**
     * 获取车辆使用列表数据
     */
    public void getUseCarsListDatas(String getAll){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("getAll", getAll);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_CARUSER_LIST),200,params,myHandler);
    }
    /**
     * 获取投票管理列表数据
     */
    public void getVotingManageListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_VOTINGMANGE_LIST),200,params,myHandler);
    }
    /**
     * 结束投票数据
     */
    public void overVotingManageDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_VOTINGOVER),200,params,myHandler);
    }
    /**
     *车辆审核数据
     */
    public void shCarsUseDatas(String id,String status,String uc_audit_resion){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        params.addBodyParameter("status", status);
        params.addBodyParameter("uc_audit_resion", uc_audit_resion);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_CARSH),200,params,myHandler);
    }
}
