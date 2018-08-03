package com.htcompany.educationerpforgansu.internet.dormpart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageSourceEntity;
import com.lidroid.xutils.http.RequestParams;

import java.util.List;

/**
 * 宿舍管理请求类
 * Created by wrb on 2017/4/13.
 */
public class DormInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public DormInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 获查寝管理列表数据
     */
    public void getDormKnowingManageDatas(String page,String stuid){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("stuid",stuid);
        params.addBodyParameter("changeSearch","1");
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_CQGL_LIST),200,params,myHandler);
    }
    /**
     * 获查寝管理添加字典数据
     */
    public void getDormKnowingManageAddSelecDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_GETSELECTDATAS_LIST),200,params,myHandler);
    }
    /**
     * 添加查寝管理列表数据
     */
    public void addDormKnowingManageDatas(String seachertime,String domgroom,String student,String qqlb,String bactime,String remark,String person){
        params = new RequestParams();
        params.addBodyParameter("date",seachertime);
        params.addBodyParameter("roomid",domgroom);
        params.addBodyParameter("stuid",student);
        params.addBodyParameter("absenteeism_category_id",qqlb);
        params.addBodyParameter("return_time",bactime);
        params.addBodyParameter("content",remark);
        params.addBodyParameter("check_chamber_user",person);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_ADDCQMANGE_LIST),201,params,myHandler);
    }

    /**
     * 获卫生检查列表数据
     */
    public void getDormSanitationManageDatas(String page){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("changeSearch","1");
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_DORMWSGL_LIST),200,params,myHandler);
    }
    /**
     * 获取卫生检查字典数据
     */
    public void gettDormSanitationManageAddItemSelecDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_WSJCITME_LIST),800,params,myHandler);
    }
    /**
     * 添加卫生检查列表数据
     */
    public void addDormWSJCDatas(String da_data, String roomid, List<DormSanitationManageSourceEntity> mlist){
        params = new RequestParams();
        params.addBodyParameter("da_data",da_data);
        params.addBodyParameter("roomid",roomid);
        for(DormSanitationManageSourceEntity entity:mlist){
            params.addBodyParameter(entity.getValue(),entity.getSource());
        }
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_ADDWSJCITME),201,params,myHandler);
    }
    /**
     * 获卫生得分汇总列表数据
     */
    public void getDormHealthScoreSummaryDatas(String page){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_DORMWSDFHZ_LIST),200,params,myHandler);
    }
    /**
     * 获取宿舍评优列表数据
     */
    public void getDormAppraisingManageDatas(String page,String name){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("changeSearch","1");
        params.addBodyParameter("name",name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_DORMPY_LIST),200,params,myHandler);
    }
    /**
     * 获取宿舍违纪列表数据
     */
    public void getDormDisciplineDatas(String page,String name){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("truename",name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_WJCX_LIST),200,params,myHandler);
    }
    /**
     * 添加违纪数据
     */
    public void addDormDisciplineDatas(String student,String bactime,String wjqk,String clyj){
        params = new RequestParams();
        params.addBodyParameter("resident_staff_id",student);
        params.addBodyParameter("date",bactime);
        params.addBodyParameter("case",wjqk);
        params.addBodyParameter("opinion",clyj);
        params.addBodyParameter("notes","");
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_WJCX_ADD),201,params,myHandler);
    }
    /**
     * 获取入住退宿查询列表数据
     */
    public void getDormRzTsSearchDatas(String page,String name){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("changeSearch","1");
        params.addBodyParameter("stuid",name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_RZTUCX_LIST),200,params,myHandler);
    }
    /**
     * 获取宿舍调换查询列表数据
     */
    public void getDormExchageSeachDatas(String page,String name){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("changeSearch","1");
        params.addBodyParameter("stuid",name);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.DORMPART_SSDH_LIST),200,params,myHandler);
    }
}
