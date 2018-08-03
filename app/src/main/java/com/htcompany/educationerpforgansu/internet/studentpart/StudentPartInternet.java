package com.htcompany.educationerpforgansu.internet.studentpart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**]
 * 学生部分网络请求类
 * Created by wrb on 2017/4/22.
 */
public class StudentPartInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public StudentPartInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 请求学生列表
     */
    public void getStudentMessageList(String page,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", page);
        params.addBodyParameter("sea_truename", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.STUDENTPART_STUDENTMESSAGE_LIST),200,params,myHandler);
    }
    /**
     * 请求学生公告列表
     */
    public void getStudentNoticeList(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.STUDENTPART_STUDENTNOTICE_LIST),200,params,myHandler);
    }
    /**
     * 删除学生公告数据
     */
    public void deleteStudentNoticeList(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.STUDENTPART_STUDENTNOTICE_DELETE),201,params,myHandler);
    }
    /**
     * 请求学生奖励列表
     */
    public void getStudentRewarsdList(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.STUDENTPART_STUDENTRWARD_LIST),200,params,myHandler);
    }
    /**
     * 删除学生公告数据
     */
    public void deleteStudentRewardsList(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.STUDENTPART_STUDENTRWARD_DELETE),201,params,myHandler);
    }
}
