package com.htcompany.educationerpforgansu.internet.workteacher;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllLessonEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllTermsEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllWeeksEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassCheckEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassDMCEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassExamEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNameEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNotcieEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassTableEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.MyClassSelectEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.RepalceClassOrTeacherEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.StudentLeaveApplyEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherDormManageEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherPXEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherRcshjcdjEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingDesignEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingInteractionEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingRecordEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.WeeksEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 教师解析类
 * Created by wrb on 2017/1/20.
 */
public class WorkTeacherPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public WorkTeacherPersener(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析我的班级数据
     * @param rebackString
     * @return
     */
    public  List<MyClassSelectEntity> parseMyClassSelectData(String rebackString){
        List<MyClassSelectEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("edulist").toString(),MyClassSelectEntity.class);
                return weekdatas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return weekdatas;
    }
    //课表数据
    public void parseClassTableData(String rebackString){
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                String cur_weeks = jsonObject2.getString("cur_weeks");//当前周
                //所有周次
                List<WeeksEntity> weekdatas = JsonUtils.getListObject(jsonObject2.getJSONArray("weeks").toString(),WeeksEntity.class);
                //本周所有课程
                    List<ClassTableEntity> datas =JsonUtils.getListObject(jsonObject2.getJSONArray("cources").toString(),ClassTableEntity.class);
                Message message = new Message();
                message.what=201;
                message.obj = cur_weeks;
                Bundle bundle = new Bundle();
                bundle.putSerializable("weekdatas",(Serializable) weekdatas);
                bundle.putSerializable("tableEntities",(Serializable) datas);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
    }

    /**
     * 解析点名册数据
     * @param rebackString
     * @return
     */
    public  List<ClassDMCEntity> parseClassDMCData(String rebackString){
        List<ClassDMCEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ClassDMCEntity.class);
                return weekdatas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return weekdatas;
    }
    /**
     * 解析点名册数据
     * @param rebackString
     * @return
     */
    public  List<ClassCheckEntity> parseClassCheckData(String rebackString){
        List<ClassCheckEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ClassCheckEntity.class);
                return weekdatas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return weekdatas;
    }
    /**
     * 解析教学互动列表数据
     * @param rebackString
     * @return
     */
    public  List<TeachingInteractionEntity> parseJXHDLISTData(String rebackString){
        List<TeachingInteractionEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),TeachingInteractionEntity.class);
                return weekdatas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return weekdatas;
    }
    /**
     * 解析问题回答数据
     * @param rebacstr
     * @return
     */
    public boolean parserJXHD_ANSWERDATAS(String rebacstr){
        try {
            jsonObject = new JSONObject(rebacstr);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 解析教师培训列表数据
     * @param rebackString
     * @return
     */
    public  List<TeacherPXEntity> parseTeacherTrainPlanLISTData(String rebackString){
        List<TeacherPXEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("list").toString(),TeacherPXEntity.class);
                return weekdatas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return weekdatas;
    }
    /**
     * 解析教师培训报名数据
     * @param rebacstr
     * @return
     */
    public boolean parserTeacherTrainPlanBMDATAS(String rebacstr){
        try {
            jsonObject = new JSONObject(rebacstr);
            status  =jsonObject.getString("code");
            MyApp.notice=jsonObject.getString("msg");
            if("0".equals(status)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return false;
    }
    /**
     * 解析教师代课调课数据
     * @param rebackString
     * @return
     */
    public  List<TeachingDesignEntity> parseTeachingDesignData(String rebackString){
        List<TeachingDesignEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("list").toString(),TeachingDesignEntity.class);
                return weekdatas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return weekdatas;
    }
    /**
     * 解析教师授课记录数据
     * @param rebackString
     * @return
     */
    public  List<TeachingRecordEntity> parseTeachingRecordData(String rebackString){
        List<TeachingRecordEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),TeachingRecordEntity.class);
                return weekdatas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return weekdatas;
    }
    /**
     * 解析学期列表数据
     * @param rebackString
     * @return
     */
    public  List<AllTermsEntity> parseTermsLSITData(String rebackString){
        List<AllTermsEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),AllTermsEntity.class);
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
     * 解析班级列表数据
     * @param rebackString
     * @return
     */
    public  List<AllClassEntity> parseClassLSITData(String rebackString){
        List<AllClassEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),AllClassEntity.class);
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
     * 解析周次列表数据
     * @param rebackString
     * @return
     */
    public  List<AllWeeksEntity> parseWeeksLSITData(String rebackString){
        List<AllWeeksEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),AllWeeksEntity.class);
                return weekdatas;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return weekdatas;
    }
    /**
     * 解析学期列表数据
     * @param rebackString
     * @return
     */
    public  List<AllLessonEntity> parseLessonLSITData(String rebackString){
        List<AllLessonEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),AllLessonEntity.class);
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
     * 解析班级名单列表数据
     * @param rebackString
     * @return
     */
    public  List<ClassNameEntity> parseCLassNameData(String rebackString){
        List<ClassNameEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),ClassNameEntity.class);
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
     * 解析班级成绩列表数据
     * @param rebackString
     * @return
     */
    public  List<ClassExamEntity> parseCLassExamData(String rebackString){
        List<ClassExamEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ClassExamEntity.class);
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
     * 解析班级公告列表数据
     * @param rebackString
     * @return
     */
    public  List<ClassNotcieEntity> parseCLassNoticeListData(String rebackString){
        List<ClassNotcieEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ClassNotcieEntity.class);
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
     * 解析学生请假列表数据
     * @param rebackString
     * @return
     */
    public  List<StudentLeaveApplyEntity> parseStudentLeaveRecordsListData(String rebackString){
        List<StudentLeaveApplyEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),StudentLeaveApplyEntity.class);
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
     * 解析宿舍管理列表数据
     * @param rebackString
     * @return
     */
    public  List<TeacherDormManageEntity> parseTeacherDormManageListData(String rebackString){
        List<TeacherDormManageEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),TeacherDormManageEntity.class);
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
     * 解析代课教师，调课课程列表数据
     * @param rebackString
     * @return
     */
    public  List<RepalceClassOrTeacherEntity> parseRepalceClassOrTeacherLSITData(String rebackString){
        List<RepalceClassOrTeacherEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),RepalceClassOrTeacherEntity.class);
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
     * 解析日常事务登记列表数据
     * @param rebackString
     * @return
     */
    public  List<TeacherRcshjcdjEntity> parseRCSWDJLSITData(String rebackString){
        List<TeacherRcshjcdjEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),TeacherRcshjcdjEntity.class);
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
