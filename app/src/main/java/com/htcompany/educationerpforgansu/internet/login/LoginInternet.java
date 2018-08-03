package com.htcompany.educationerpforgansu.internet.login;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 登录网络请求类
 * Created by wrb on 2017/1/20.
 */
public class LoginInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    public LoginInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
    }
    /**
     * 登录
     */
    public void login(String username,String pwd){
        params = new RequestParams();
        params.addBodyParameter("username",username);
        params.addBodyParameter("password",pwd);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.lOGIN_LOGIN),200,params,myHandler);
    }
}
