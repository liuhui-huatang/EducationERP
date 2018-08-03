package com.htcompany.educationerpforgansu.internet;

import android.os.Handler;
import android.os.Message;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

/**
 * xutils请求类
 * Created by wrb on 2016/12/28.
 */
public class MyXutilsRequest {
    private HttpUtils httpUtils;
    private String rebackString="";
    private Message message=null;
    public MyXutilsRequest(){
        httpUtils = new HttpUtils();
        httpUtils.configHttpCacheSize(0);
    }
    public static MyXutilsRequest myXutilsRequest=null;
    public  static MyXutilsRequest getMyXutlsRequest(){
        if(myXutilsRequest==null){
            myXutilsRequest= new MyXutilsRequest();
            return myXutilsRequest;
        }else{
            return  myXutilsRequest;
        }
    }

    public synchronized void sendRequestMethod(String url,final int event,RequestParams params, final Handler handler){
        LogUtils.d("sendRequestMethod=>"+url);
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                rebackString=responseInfo.result;
                if(rebackString!=null&&!"".equals(rebackString)){
                    message = new Message();
                    message.what =event;
                    message.obj = rebackString;
                    handler.sendMessage(message);
                }
                System.out.print(rebackString);
            }
            @Override
            public void onFailure(HttpException e, String s) {
                handler.sendEmptyMessage(400);
            }
        });
    }
    public synchronized void sendNoRequestMethod(String url,final int event,final Handler handler){
        httpUtils.send(HttpRequest.HttpMethod.GET, url,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                rebackString=responseInfo.result;
                if(rebackString!=null&&!"".equals(rebackString)){
                    message = new Message();
                    message.what =event;
                    message.obj = rebackString;
                    handler.sendMessage(message);
                }
                System.out.print(rebackString);
            }
            @Override
            public void onFailure(HttpException e, String s) {
                handler.sendEmptyMessage(400);
            }
        });
    }
}
