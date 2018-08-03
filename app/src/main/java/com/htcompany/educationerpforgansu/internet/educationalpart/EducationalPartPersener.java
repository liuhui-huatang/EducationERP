package com.htcompany.educationerpforgansu.internet.educationalpart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.ClassCjEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationJXTEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationTeacherTrainEnity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationTeacherTrainingPersonEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.MakeupCjEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.QKCJEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.XXClassEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 教务模块数据解析类
 * Created by wrb on 2017/4/14.
 */
public class EducationalPartPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public EducationalPartPersener(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析学生选修课列表数据
     * @param rebackString
     * @return
     */
    public List<XXClassEntity> parseStudentSelecrClassListData(String rebackString){
        List<XXClassEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("list").toString(),XXClassEntity.class);
                return datas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析教学互动统计列表数据
     * @param rebackString
     * @return
     */
    public List<EducationJXTEntity> parseTeacherJXHDTJListData(String rebackString){
        List<EducationJXTEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("list").toString(),EducationJXTEntity.class);
                return datas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析班级成绩汇总列表数据
     * @param rebackString
     * @return
     */
    public List<ClassCjEntity> parseClassExamHZListData(String rebackString){
        List<ClassCjEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ClassCjEntity.class);
                return datas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析清考成绩汇总列表数据
     * @param rebackString
     * @return
     */
    public List<QKCJEntity> parseClearExamListData(String rebackString){
        List<QKCJEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),QKCJEntity.class);
                return datas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析补考成绩汇总列表数据
     * @param rebackString
     * @return
     */
    public List<MakeupCjEntity> parseMakeUpExamListData(String rebackString){
        List<MakeupCjEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),MakeupCjEntity.class);
                return datas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }

    /**
     * 解析教师培训列表数据
     * @param rebackString
     * @return
     */
    public List<EducationTeacherTrainEnity> parseTeacherTrainListData(String rebackString){
        List<EducationTeacherTrainEnity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("list").toString(),EducationTeacherTrainEnity.class);
                return datas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析教师培训报名人列表数据
     * @param rebackString
     * @return
     */
    public List<EducationTeacherTrainingPersonEntity> parseTeacherTrainPersonListData(String rebackString){
        List<EducationTeacherTrainingPersonEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("list").toString(),EducationTeacherTrainingPersonEntity.class);
                return datas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
}
