package com.htcompany.educationerpforgansu.internet.SchoolMaster;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 校长空间网络请求列
 * Created by wrb on 2017/4/10.
 */
public class SchoolMaterInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public SchoolMaterInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取招生计划数据
     */
    public void getZSJH_ListDatas(String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("sea_major", "");
        params.addBodyParameter("sea_educational", "");
        params.addBodyParameter("sea_unity", "");
        params.addBodyParameter("sea_grade", "");
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_ZSJF_LIST),200,params,myHandler);
    }
    /**
     * 获取教师考核汇总数据
     */
    public void getJSKHHZ_ListDatas(String themeId){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("themeId", themeId);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_JSKHZ_LIST),200,params,myHandler);
    }
    /**
     * 获取普通教师考核主题数据
     */
    public void getPTJSKH_title_ListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_PTJSKH_TITLE_LIST),200,params,myHandler);
    }
    /**
     * 获取班主任考核主题数据
     */
    public void getBZRKH_title_ListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_BZRKH_TITLE_LIST),200,params,myHandler);
    }
    /**
     * 获取班主任考核汇总数据
     */
    public void getBZRKHHZ_ListDatas(String themeId){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("themeId", themeId);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_BZRKH_LIST),200,params,myHandler);
    }
    /**
     * 获取普通教师考核主题数据
     */
    public void getJYZKH_title_ListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_JYZKH_TITLE_LIST),200,params,myHandler);
    }
    /**
     * 获取教研组考核汇总数据
     */
    public void getJYZKHHZ_ListDatas(String themeId){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("themeId", themeId);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_JYZKHZ_LIST),200,params,myHandler);
    }
    /**
     * 获取学生状态数据
     */
    public void getMasterXSZT_ListDatas(String grad){
        params = new RequestParams();
        params.addBodyParameter("sea_grade", grad);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_XSZT_LIST),200,params,myHandler);
    }
    /**
     * 获取学生专业分布列表数据
     */
    public void getMasterXSZYFB_ListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_XSZYFB_LIST),200,params,myHandler);
    }
    /**
     * 获取固定资产统计列表数据
     */
    public void getMasterGDZCTJ_ListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOLMASTER_GDZCTJ_LIST),200,params,myHandler);
    }
}
