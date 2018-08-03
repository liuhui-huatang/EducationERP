package com.htcompany.educationerpforgansu.internet.SchoolMaster;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.BZRKHHZEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.GradEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.ImportTitleEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.JSKHHZEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.JYZKHHZEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.JYZTitleEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.MasterGDZCZHTJEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.MasterXSZTEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.MasterXSZYFBEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.ZSJHEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 校长空间网络节系列
 * Created by wrb on 2017/4/10.
 */
public class SchoolMaterPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Message message=null;
    private Handler myHandler;
    public SchoolMaterPersener(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析招生计划列表数据
     * @param rebackString
     * @return
     */
    public List<ZSJHEntity> parseZSJH_LISTData(String rebackString){
        List<ZSJHEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("list").toString(),ZSJHEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析教师考核汇总列表数据
     * @param rebackString
     * @return
     */
    public List<JSKHHZEntity> parseJSKHHZ_LISTData(String rebackString){
        List<JSKHHZEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),JSKHHZEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析班主任考核汇总列表数据
     * @param rebackString
     * @return
     */
    public List<BZRKHHZEntity> parseBZRKHHZ_LISTData(String rebackString){
        List<BZRKHHZEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),BZRKHHZEntity.class);
                message=new Message();
                message.what=2000;
                message.obj = jsonObject.getJSONObject("data").getString("theme");
                myHandler.sendMessage(message);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }

    /**
     * 解析班主任考核主题列表数据
     * @param rebackString
     * @return
     */
    public List<ImportTitleEntity> parseBZRKH_TITLE_LISTData(String rebackString){
        List<ImportTitleEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ImportTitleEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析教研组考核主题列表数据
     * @param rebackString
     * @return
     */
    public List<JYZTitleEntity> parseJYZ_TITLE_LISTData(String rebackString){
        List<JYZTitleEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),JYZTitleEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析教研组考核汇总列表数据
     * @param rebackString
     * @return
     */
    public List<JYZKHHZEntity> parseJYZKHHZ_LISTData(String rebackString){
        List<JYZKHHZEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),JYZKHHZEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析学生专业分布列表数据
     * @param rebackString
     * @return
     */
    public List<MasterXSZYFBEntity> parseXSZYFBB_LISTData(String rebackString){
        List<MasterXSZYFBEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),MasterXSZYFBEntity.class);
                return datas;
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析固定资产统计列表数据
     * @param rebackString
     * @return
     */
    public List<MasterGDZCZHTJEntity> parseGDZCTJ_LISTData(String rebackString){
        List<MasterGDZCZHTJEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),MasterGDZCZHTJEntity.class);
                return datas;
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }

    /**
     * 解析学生状态列表数据
     * @param rebackString
     * @return
     */
    public void parseXSZT_LISTData(String rebackString){
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                //所有周次
                List<GradEntity> gradedatas = JsonUtils.getListObject(jsonObject2.getJSONArray("grade").toString(),GradEntity.class);
                //本周所有课程
                List<MasterXSZTEntity> datas =JsonUtils.getListObject(jsonObject2.getJSONArray("list").toString(),MasterXSZTEntity.class);
                Message message = new Message();
                message.what=201;
                Bundle bundle = new Bundle();
                bundle.putSerializable("gradedatas",(Serializable) gradedatas);
                bundle.putSerializable("xsztEntities",(Serializable) datas);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
    }
}
