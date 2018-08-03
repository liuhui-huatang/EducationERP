package com.htcompany.educationerpforgansu.internet.workteacher;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 教师请求类
 * Created by wrb on 2017/1/20.
 */
public class WorkTeacherInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public WorkTeacherInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取班主任手下教学班课表数据
     */
    public void getMyClassSelectDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHERCLASS_LIST),200,params,myHandler);
    }
    /**
     * 获取教学课表数据
     */
    public void getClassTableDatas(String week){
        params = new RequestParams();
        params.addBodyParameter("week",week);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHER_CLASSTABLE),200,params,myHandler);
    }
    /**
     * 获取班主任课表数据
     */
    public void getBZRTableDatas(String week,String classid){
        params = new RequestParams();
        params.addBodyParameter("week",week);
        params.addBodyParameter("eduid",classid);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_BZR_CLASSTABLE),200,params,myHandler);
    }
    /**
     * 获取点名册数据
     */
    public void getClassDMCDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("kebiao_id",id);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHER_DMC),200,params,myHandler);
    }
    /**
     * 获取班级考核数据
     */
    public void getClassCheackDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHER_CHECKED),200,params,myHandler);
    }
    /**
     * 提交点名数据
     */
    public void uploadClassDMZT(String kpi_id,String student_id,String kebiao_id){
        params = new RequestParams();
        params.addBodyParameter("kpi_id",kpi_id);
        params.addBodyParameter("student_id",student_id);
        params.addBodyParameter("kebiao_id",kebiao_id);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHER_DM_STATUS),201,params,myHandler);
    }
    /**
     * 获取教学互动列表数据
     */
    public void getJXHD_LISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHER_JXHD_LIST),200,params,myHandler);
    }
    /**
     * 提交教学互动问题回答
     */
    public void uploadJXHD_ANSWERS(String id,String question_da){
        params = new RequestParams();
        params.addBodyParameter("id",id);
        params.addBodyParameter("question_da",question_da);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHER_JXHD_ANSWER),200,params,myHandler);
    }
    /**
     * 获取教师培训计划列表数据
     */
    public void getTeacherTrainPlanLISTDatas(String page){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHER_TRAINPLAN_LIST),200,params,myHandler);
    }
    /**
     * 获取教师培训计划列表数据
     */
    public void uploadTeacherTrainPlanADDDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("ids", id);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHER_TRAINPLAN_ADD),200,params,myHandler);
    }
    /**
     * 获取代课调课列表数据
     */
    public void getTeachingDesignDatas(String sea_type){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("sea_type",sea_type);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHERREPALCECLASS_LIST),200,params,myHandler);
    }
    /**
     * 获取授课记录数据
     */
    public void getTeachingRecordDatas(String page){
        params = new RequestParams();
        params.addBodyParameter("page",page);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHERRECORD_LIST),200,params,myHandler);
    }
    /**
     * 获取周次列表数据
     */
    public void getWeeksListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_ALLWEEKS_LIST),200,params,myHandler);
    }
    /**
     * 获取学期列表数据
     */
    public void getAllTermsListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_ALLTERMS_LIST),200,params,myHandler);
    }
    /**
     * 获取班级列表数据
     */
    public void getAllClassListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_ALLCLASS_LIST),200,params,myHandler);
    }
    /**
     * 获取课程列表数据
     */
    public void getAllLessonListDatas(String term,String weeks){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("cur_term", term);
        params.addBodyParameter("week", weeks);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_ALLLESSON_LIST),200,params,myHandler);
    }
    /**
     * 获取代课调课列表数据
     */
    public void getReplaceTeacherClassDatas(String term,String weeks,String type,String paikeid){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("cur_term", term);
        params.addBodyParameter("week", weeks);
        params.addBodyParameter("type", type);
        params.addBodyParameter("paikeid", paikeid);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_REPALACELESSONORTEACHER_LIST),200,params,myHandler);
    }
    /**
     * 添加代课调课数据
     */
    public void addReplaceTeacherClassDatas(String term,String weeks,String type,String paikeid,String cur_dtea,String status){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("cur_term", term);
        params.addBodyParameter("week", weeks);
        params.addBodyParameter("type", type);
        params.addBodyParameter("cur_cource", paikeid);
        params.addBodyParameter("cur_dtea", cur_dtea);
        params.addBodyParameter("status", status);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_REPALACELESSONORTEACHER_ADD),200,params,myHandler);
    }
    /**
     * 获取班级名单列表数据
     */
    public void getAllClassNameListDatas(String page,String usernamge){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", page);
        params.addBodyParameter("sea_truename", usernamge);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_CLASSNAME_LIST),200,params,myHandler);
    }
    /**
     * 获取班级成绩汇总列表数据
     */
    public void getAllClassExamListDatas(String page,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", page);
        params.addBodyParameter("username", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_CLASSEXAM_LIST),200,params,myHandler);
    }
    /**
     * 获取班级公告列表数据
     */
    public void getAllClassNoticeListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_CLASSNOTICE_LIST),200,params,myHandler);
    }
    /**
     * 删除班级公告列表数据
     */
    public void deleteClassNoticeListDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id",id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_CLASSNOTICE_DELETE),201,params,myHandler);
    }
    /**
     * 添加班级公告列表数据
     */
    public void addClassNoticeListDatas(String title,String time,String content,String classkey){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("title", title);
        params.addBodyParameter("time", time);
        params.addBodyParameter("content", content);
        params.addBodyParameter("xzb", classkey);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_ADDCLASSNOTICE),200,params,myHandler);
    }
    /**
     * 获取学生请假列表数据
     */
    public void getStudentLeaveRecordsListDatas(String page,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", page);
        params.addBodyParameter("sea_truename", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_STUDENTLEAVERECORDS_LIST),200,params,myHandler);
    }
    /**
     * 提交学生请假数据
     */
    public void uploadStudentLeaveRecordsDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_STUDENTLEAVERECORDS_UPLOAD),200,params,myHandler);
    }
    /**
     * 销假学生请假数据
     */
    public void xiaojiaStudentLeaveRecordsDatas(String id){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("id", id);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_STUDENTLEAVERECORDS_XIAOJIA),200,params,myHandler);
    }
    /**
     * 获取宿舍管理列表数据
     */
    public void getTeacherDormManageListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHERDORMMANGE_LIST),200,params,myHandler);
    }
    /**
     * 获取日常事务等级列表数据
     */
    public void getTeacherRCSWDJListDatas(String pageNum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.WROK_TEACHERRCSWDJ_LIST),200,params,myHandler);
    }
}
