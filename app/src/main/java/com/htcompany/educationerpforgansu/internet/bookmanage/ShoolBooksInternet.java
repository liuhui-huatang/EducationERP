package com.htcompany.educationerpforgansu.internet.bookmanage;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 图书网络请求类
 * Created by wrb on 2017/2/17.
 */
public class ShoolBooksInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public ShoolBooksInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 获取图书列表数据
     * @param page 页数
     * @param sea_name 图书名称
     * @param sea_bookcategory 分类id
     */
    public void getBooks_LISTDatas(String page,String sea_name,String sea_bookcategory){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", page);
        params.addBodyParameter("sea_name", sea_name);
        params.addBodyParameter("sea_bookcategory", sea_bookcategory);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLLBOOKS_LIST),200,params,myHandler);
    }
    /**
     * 获取图书分类数据
     */
    public void getBooks_TypeDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLLBOOKS_TYPES),200,params,myHandler);
    }
    /**
     * 获取借阅记录数据
     */
    public void getBooks_MyReadsDatas(String page,String sea_name,String sea_bookcategory){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLLBOOKS_MYREADJL),200,params,myHandler);
    }
}
