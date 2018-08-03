package com.htcompany.educationerpforgansu.internet.messageinternet;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 消息网络请求类
 * Created by weiruibin on 2017/5/24.
 */

public class MessageInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public MessageInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 请求消息列表
     */
    public void getPersonMsg(String page){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", page);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MESSAGE_MESSAGELSIT),200,params,myHandler);
    }
    /**
     * 读取消息
     *
     */
    public void readMsg(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MESSAGE_READMESS),200,params,myHandler);
    }
}
