package com.htcompany.educationerpforgansu.internet.studentpart;

import android.content.Context;

import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentMessageEntity;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentNoticesEntity;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentRewarsdsEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */
public class StudentPartPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    public StudentPartPersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析学生信息列表数据
     * @param jsonString
     * @return
     */
    public List<StudentMessageEntity> parseStudentMessageListDatas(String jsonString){
        List<StudentMessageEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    String srt = jsonObject.getJSONObject("data").getJSONArray("data").toString();
                    datas = JsonUtils.getListObject(srt,StudentMessageEntity.class);
                    return datas;
                }
            }else{
                return datas;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析学生公告列表数据
     * @param jsonString
     * @return
     */
    public List<StudentNoticesEntity> parseStudentNoticeListDatas(String jsonString){
        List<StudentNoticesEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    String srt = jsonObject.getJSONObject("data").getJSONArray("list").toString();
                    datas = JsonUtils.getListObject(srt,StudentNoticesEntity.class);
                    return datas;
                }
            }else{
                return datas;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析学生奖励列表数据
     * @param jsonString
     * @return
     */
    public List<StudentRewarsdsEntity> parseStudentRewarsdsListDatas(String jsonString){
        List<StudentRewarsdsEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    String srt = jsonObject.getJSONObject("data").getJSONArray("list").toString();
                    datas = JsonUtils.getListObject(srt,StudentRewarsdsEntity.class);
                    return datas;
                }
            }else{
                return datas;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析成功数据
     * @return
     */
    public boolean parserSucessDatas(String rebacstr){
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
}
