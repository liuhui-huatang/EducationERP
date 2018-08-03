package com.htcompany.educationerpforgansu.internet.workgrzx;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.views.photoview.Bimp;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

import java.io.File;

/**
 *个人中心
 * Created by wrb on 2017/1/6.
 */
public class WokrpersonalInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public WokrpersonalInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 请求待办事项
     */
    public void getWaitClGZL(String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_WAIT),200,params,myHandler);
    }
    /**
     * 提交固定预定义成功待办事项
     */
    public void uploadSucessmsg(String content,String id,String status){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("runId",id);
        params.addBodyParameter("opinion",content);
        params.addBodyParameter("status",status);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_SHCG),200,params,myHandler);
    }
    /**
     * 自由工作流通过下一步接口
     */
    public void freeFlowGzliu_TGNEXT(String content,String id,String next_name,String tuid){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("runId",id);
        params.addBodyParameter("opinion",content);
        params.addBodyParameter("next_name",next_name);
        params.addBodyParameter("tuid",tuid);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_ZYGZL_TGNEXT),200,params,myHandler);
    }
    /**
     * 自由工作流通过结束接口
     */
    public void freeFlowGzliu_TGOVER(String content,String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("runId",id);
        params.addBodyParameter("opinion",content);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_ZYGZL_TGOVER),200,params,myHandler);
    }
    /**
     * 自由工作流B不通过结束接口
     */
    public void freeFlowGzliu_BTGOVER(String content,String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("runId",id);
        params.addBodyParameter("opinion",content);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_ZYGZL_BTGOVER),200,params,myHandler);
    }
    /**
     * 请求已办办事项
     * sorttype 1发起时间，2办理时间
     */
    public void getOverClGZL(String pageNum,String sorttype){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("sorttype", sorttype);

        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_OVER),200,params,myHandler);
    }
    /**
     * 请求我发起的工作流
     */
    public void getMySendClGZL(String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_MYSEND),200,params,myHandler);
    }
    /**
     * 主页请求我发起的工作流
     */
    public void getMainMySendClGZL(String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_MYSEND),205,params,myHandler);
    }
    /**
     * 请求已办办事项详情
     */
    public void getOverDetialsList(String flow_id,String run_id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("flow_id", flow_id);
        params.addBodyParameter("run_id", run_id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_OVERFLOWDETIALS),200,params,myHandler);
    }
    /**
     * 请求发起工作流类型列表数据
     */
    public void getWorkFlowStartSendTypeList(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_STARTSENTTYPE_LIST),200,params,myHandler);
    }
    /**
     * 发起工作流类型数据
     */
    public void uploadWorkFlowStartSendTypeList(String flow_id,String name,String next_name,String content,String teacheruid){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("name", name);
        params.addBodyParameter("flow_id", flow_id);
        params.addBodyParameter("next_name", next_name);
        params.addBodyParameter("content", content);
        params.addBodyParameter("teacheruid", teacheruid);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_STARTSENFREEFLOW),200,params,myHandler);
    }
    /**
     * 请求发起固定工作流数据
     */
    public void getWorkGDH5StartSendTypeList(String flow_id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", flow_id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_STARTSENTGDH5),200,params,myHandler);
    }
    /**
     * 获取教师数据
     */
    public void getTeachers(String name){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("name", name);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_GET_TEACHERS),200,params,myHandler);
    }
    /**
     * 获取我的工资列表数据
     */
    public void getMySarlarysDatas(String pageNum,String my){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("my", my);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.FINALPART_GZLS_LIST),200,params,myHandler);
    }
    /**
     * 获取资产包修列表数据
     */
    public void getAsset_RepairListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_ZCWH_LIST),200,params,myHandler);
    }
    /**
     * 添加资产包修列表数据
     */
    public void addAsset_RepairListDatas(String title,String place,String description){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("title",title);
        params.addBodyParameter("place",place);
        params.addBodyParameter("description",description);
        if(Bimp.tempSelectBitmap.size()>0){
           File file =new File(Bimp.tempSelectBitmap.get(0).imagePath);
            if(file.exists()){
                params.addBodyParameter("imgurl",new File(Bimp.tempSelectBitmap.get(0).imagePath));
            }
        }
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_ADDZCWH),200,params,myHandler);
    }
//    /**
//     * 请全校公告
//     */
//    public void getSchoolNotice(){
//        params = new RequestParams();
//        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
//        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_WAIT),201,params,myHandler);
//    }
//    /**
//     * 请办公公告
//     */
//    public void getWorkNotice(){
//        params = new RequestParams();
//        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
//        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFLOW_WAIT),202,params,myHandler);
//    }
    /**
     * 获取办公公告列表数据
     *  0全部共公告，1部门公告，2全校公告
     */
    public void getWorkNoticeSchoolListDatas(String type){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("type", type);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_WORKNOTICE_LIST),201,params,myHandler);
    }
    /**
     * 获取办公公告列表数据
     *  0全部共公告，1部门公告，2全校公告
     */
    public void getWorkNoticePartListDatas(String type){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("type", type);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMMONWORKPART_WORKNOTICE_LIST),202,params,myHandler);
    }
    /**
     * 获取办公公告列表数据
     *  0全部共公告，1部门公告，2全校公告
     */
    public void getWorkNoticeMyPartListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROKFNOTICE_MYPART),202,params,myHandler);
    }
    /**
     * 获取我的会议列表数据
     */
    public void getMyMeetinsList_Datas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_MYMEETINGS_LIST),200,params,myHandler);
    }
    /**
     * 获取会议地址列表数据
     */
    public void getMeetinsAddressList_Datas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_MYMEETINGADDRESS_LIST),200,params,myHandler);
    }
    /**
     * 添加我的会议表数据
     */
    public void addMyMeetins_Datas(String title,String place,String zcr,String mm_people_json,
                                   String mm_content,String mm_num,String start,String end){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("mm_name",title);
        params.addBodyParameter("mm_room_id",place);
        params.addBodyParameter("mm_host",zcr);
        params.addBodyParameter("mm_people_json",mm_people_json);
        params.addBodyParameter("mm_content",mm_content);
        params.addBodyParameter("mm_num",mm_num);
        params.addBodyParameter("start",start);
        params.addBodyParameter("end",end);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_ADDMEETINGS),200,params,myHandler);
    }
    /**
     * 获取车辆类型列表数据
     */
    public void getCarTypeList_Datas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_CARTYPES_LIST),200,params,myHandler);
    }
    /**
     * 获取我的车辆使用列表数据
     */
    public void getMyUserCarList_Datas(String getAll){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("getAll", getAll);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_MYUSERCAR_LIST),200,params,myHandler);
    }
    /**
     * 添加我的车辆使用数据
     */
    public void addMyUserCarList_Datas(String uc_car_id,String uc_end_place,String uc_mileage,String uc_start_time,String uc_end_time,String uc_driver,String uc_resion){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("uc_car_id", uc_car_id);
        params.addBodyParameter("uc_end_place", uc_end_place);
        params.addBodyParameter("uc_mileage", uc_mileage);
        params.addBodyParameter("uc_start_time", uc_start_time);
        params.addBodyParameter("uc_end_time", uc_end_time);
        params.addBodyParameter("uc_driver", uc_driver);
        params.addBodyParameter("uc_resion", uc_resion);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_ADDMYUSERCAR),200,params,myHandler);
    }
    /**
     * 获取我的请假列表数据
     */
    public void getMyLeaveApplyList_Datas(String page){
        params = new RequestParams();
        params.addBodyParameter("page", page);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_MYLEAVEAPPLY_LIST),200,params,myHandler);
    }
    /**
     * 获取我的请假类型列表数据
     */
    public void getMyLeaveApplyTypesList_Datas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_MYLEAVEAPPLYTYPES_LIST),201,params,myHandler);
    }
    /**
     * 添加我的请假列表数据
     */
    public void addMyLeaveApplyList_Datas(String start,String end,String reason,String type,String sftype){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("start", start);
        params.addBodyParameter("end", end);
        params.addBodyParameter("reason", reason);
        params.addBodyParameter("type", type);
        params.addBodyParameter("jzgtype", sftype);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_MYLEAVEAPPLY_ADD),200,params,myHandler);
    }
    /**
     * 获取我的值班列表数据
     */
    public void getMyBeonDutyList_Datas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_BEONDUTE_LIST),200,params,myHandler);
    }
    /**
     * 获取我的日程列表数据
     */
    public void getMySchedulesList_Datas(String date){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("date", date);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_SCHEDULES_LIST),200,params,myHandler);
    }
    /**
     * 添加我的日程列表数据
     */
    public void addMySchedulesList_Datas(String sdate,String edate,String Content){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("s_time", sdate);
        params.addBodyParameter("edate", edate);
        params.addBodyParameter("message", Content);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.PERSONWORK_SCHEDULES_ADD),200,params,myHandler);
    }
    /**
     * 获取德育新闻列表数据
     */
    public void getMyDyNews_Datas(String issy,String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("issy", issy);
        params.addBodyParameter("page", pageNum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_DYNEWS),203,params,myHandler);
    }
    /**
     * 获取德育新闻列表数据
     */
    public void getAllDyNews_Datas(String issy,String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("issy", issy);
        params.addBodyParameter("page", pageNum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_DYNEWS),204,params,myHandler);
    }
}
