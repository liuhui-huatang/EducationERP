package com.htcompany.educationerpforgansu.internet.educationalpart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 教务部分网络请求类
 * Created by wrb on 2017/4/14.
 */
public class EducationalPartInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public EducationalPartInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取学生选修课列表数据
     */
    public void getStudentSelectClass_ListDatas(String pageNum,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("sea_username", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EDUCATIONPART_STUDENTXXCLASS_LIST),200,params,myHandler);
    }
    /**
     * 获取教学互动统计列表数据
     */
    public void getTeacherJXHDTJ_ListDatas(String pageNum,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("sou_teacher_id", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EDUCATIONPART_JSHDTJ_LIST),200,params,myHandler);
    }
    /**
     * 获取班级成绩列表数据
     */
    public void getClassExamHz_ListDatas(String pageNum,String classid){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("username", classid);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EDUCATIONPART_CLASSEXAM_LIST),200,params,myHandler);
    }
    /**
     * 获取清考成绩列表数据
     */
    public void getClearExam_ListDatas(String pageNum,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("username", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EDUCATIONPART_CLEAREXAM_LIST),200,params,myHandler);
    }
    /**
     * 获取补考考成绩列表数据
     */
    public void getMakeUpExam_ListDatas(String pageNum,String username){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("username", username);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EDUCATIONPART_MAKEEXAM_LIST),200,params,myHandler);
    }
    /**
     * 获取教师培训项目管理列表数据
     */
    public void getTeacherTrainProject_ListDatas(String pageNum,String username){
        params = new RequestParams();
        params.addBodyParameter("page", pageNum);
        params.addBodyParameter("ser_pei_name", username);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EDUCATIONPART_TEACHERTRAINPROJECT_LIST),200,params,myHandler);
    }
    /**
     * 获取教师培训项目报名人列表数据
     */
    public void getTeacherTrainProjectPerson_ListDatas(String pid){
        params = new RequestParams();
        params.addBodyParameter("xiangmu_id", pid);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EDUCATIONPART_TEACHERTRAINPROJECTBMPERSON_LIST),200,params,myHandler);
    }
    /**
     * 提交教师培训项目报名人数据
     */
    public void uploadTeacherTrainProjectPersonDatas(String pid,String status){
        params = new RequestParams();
        params.addBodyParameter("kpiid", pid);
        params.addBodyParameter("status", pid);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EDUCATIONPART_TEACHERTRAINPROJECTBMPERSON_UPLOAD),1200,params,myHandler);
    }
}
