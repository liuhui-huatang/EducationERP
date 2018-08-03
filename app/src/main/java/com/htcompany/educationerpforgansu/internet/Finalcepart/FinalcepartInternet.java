package com.htcompany.educationerpforgansu.internet.Finalcepart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 财务网络请求类
 * Created by wrb on 2017/4/5.
 */
public class FinalcepartInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public FinalcepartInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取工资列表数据
     */
    public void getSarlarysDatas(String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.FINALPART_GZLS_LIST),200,params,myHandler);
    }
    /**
     * 获取收费记录列表数据
     */
    public void getRealityChargeDatas(String pageNum,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("userName", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.FINALPART_SFJL_LIST),200,params,myHandler);
    }
    /**
     * 获取退费列表数据
     */
    public void getReturnMoneyDatas(String pageNum,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("userName", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.FINALPART_TFJL_LIST),200,params,myHandler);
    }
    /**
     * 获取支出管理列表数据
     */
    public void getOutlayDatas(String pageNum,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("userName", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.FINALPART_OUTLAY_LIST),200,params,myHandler);
    }

    /**
     * 获取支出管理同意数据
     */
    public void getOutlayAgreeDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.FINALPART_OUTLAY_AGREE),200,params,myHandler);
    }
    /**
     * 获取支出管理同意数据
     */
    public void getOutlayNotAgreeDatas(String id,String reson){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        params.addBodyParameter("reason", reson);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.FINALPART_OUTLAY_NOT),201,params,myHandler);
    }
    /**
     * 获取f费用统计列表数据
     */
    public void getStatisticsDatas(String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.FINALPART_FYTJ_LIST),200,params,myHandler);
    }
}
