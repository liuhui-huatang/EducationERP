package com.htcompany.educationerpforgansu.internet.exampart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExamProjectEntity;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExampartInvigilateEntity;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExampartStudentSearchEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 考务网络解析类
 * Created by wrb on 2017/5/8.
 */
public class ExamPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public ExamPersener(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析成功数据
     * @param rebacstr
     * @return
     */
    public boolean parserSuccessDatas(String rebacstr){
        try {
            jsonObject = new JSONObject(rebacstr);
            status  =jsonObject.getString("code");
            if("0".equals(status)) {
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
     * 解析考试科目数据
     * @param rebackString
     * @return
     */
    public List<ExamProjectEntity> parseExamProjectlistData(String rebackString){
        List<ExamProjectEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ExamProjectEntity.class);
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
     * 解析学生考试科目数据
     * @param rebackString
     * @return
     */
    public List<ExampartStudentSearchEntity> parseExampartStudentSearchlistData(String rebackString){
        List<ExampartStudentSearchEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),ExampartStudentSearchEntity.class);
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
     * 解析监考教师查询数据
     * @param rebackString
     * @return
     */
    public List<ExampartInvigilateEntity> parseExampartInvigilateSearchlistData(String rebackString){
        List<ExampartInvigilateEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),ExampartInvigilateEntity.class);
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
