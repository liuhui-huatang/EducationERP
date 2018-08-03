package com.htcompany.educationerpforgansu.internet.exampart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 考务网络请求类
 * Created by wrb on 2017/5/8.
 */
public class ExamInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public ExamInternet(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取考试科目数据
     */
    public void getExamProjectlistDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EXAMPART_PROJECT_LIST),200,params,myHandler);
    }
    /**
     * 获取考试科目数据
     */
    public void getExampartStudentSearchlistDatas(String page,String name,String valuekey){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", page);
        params.addBodyParameter("sou_student", name);
        params.addBodyParameter("sou_project", valuekey);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EXAMPART_STUDENTSEARCH_LIST),200,params,myHandler);
    }
    /**
     * 获取监考教师数据
     */
    public void getExampartInvigilateSearchlistDatas(String page,String name,String valuekey){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("page", page);
        params.addBodyParameter("sou_name", name);
        params.addBodyParameter("sou_project", valuekey);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.EXAMPART_JKTEACHERSEARCH_LIST),200,params,myHandler);
    }
}
