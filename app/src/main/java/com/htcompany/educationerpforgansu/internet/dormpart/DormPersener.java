package com.htcompany.educationerpforgansu.internet.dormpart;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.AppraisingManageEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormBulingEntitiy;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormDisciplineEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormExchageSeachEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormKnowingManageEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormQQLBEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormRzTsSearchEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageSourceEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.ScoreSummaryEntitiy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 宿舍网络数据解析类
 * Created by wrb on 2017/4/13.
 */
public class DormPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    private Message message=null;
    public DormPersener(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析宿舍卫生管理列表数据
     * @param rebackString
     * @return
     */
    public List<DormSanitationManageEntity> parseDormSanitationManageData(String rebackString){
         List<DormSanitationManageEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),DormSanitationManageEntity.class);
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
     * 解析宿舍卫生管理评分项列表数据
     * @param rebackString
     * @return
     */
    public List<DormSanitationManageSourceEntity> parseDormSanitationManageItemData(String rebackString){
        List<DormSanitationManageSourceEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),DormSanitationManageSourceEntity.class);
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
     * 解析成功数据
     * @param rebacstr
     * @return
     */
    public boolean parserSuccessDATAS(String rebacstr){
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
     * 解析宿舍楼（含宿舍及人员）列表数据
     * @param rebackString
     * @return
     */
    public void parseDormBuldiongSelectManageData(String rebackString){
        List<DormBulingEntitiy> bulingEntitiys=null;
        List<DormQQLBEntity> qqlbEntities=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                bulingEntitiys = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("buildings").toString(),DormBulingEntitiy.class);
                qqlbEntities = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("absenteeism_category").toString(),DormQQLBEntity.class);
                message =new Message();
                message.what=202;
                Bundle b =new Bundle();
                b.putSerializable("bulingEntitiys",(Serializable) bulingEntitiys);
                b.putSerializable("qqlbEntities",(Serializable) qqlbEntities);
                message.setData(b);
                myHandler.sendMessage(message);
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
    }

    /**
     * 解析差勤管理列表数据
     * @param rebackString
     * @return
     */
    public List<DormKnowingManageEntity> parseDormKnowingManageData(String rebackString){
        List<DormKnowingManageEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),DormKnowingManageEntity.class);
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
     * 解析卫生得分汇总列表数据
     * @param rebackString
     * @return
     */
    public List<ScoreSummaryEntitiy> parseDormHealthScoreSummaryData(String rebackString){
        List<ScoreSummaryEntitiy> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ScoreSummaryEntitiy.class);
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
     * 解析宿舍评优列表数据
     * @param rebackString
     * @return
     */
    public List<AppraisingManageEntity> parseDormAppraisingManageData(String rebackString){
        List<AppraisingManageEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),AppraisingManageEntity.class);
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
     * 解析宿舍违纪列表数据
     * @param rebackString
     * @return
     */
    public List<DormDisciplineEntity> parseDormDisciplineManageData(String rebackString){
        List<DormDisciplineEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),DormDisciplineEntity.class);
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
     * 解析宿舍入住退宿列表数据
     * @param rebackString
     * @return
     */
    public List<DormRzTsSearchEntity> parseDormRzTsSearchData(String rebackString){
        List<DormRzTsSearchEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),DormRzTsSearchEntity.class);
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
     * 解析宿舍调换列表数据
     * @param rebackString
     * @return
     */
    public List<DormExchageSeachEntity> parseDormExchageSeachData(String rebackString){
        List<DormExchageSeachEntity> weekdatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                weekdatas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),DormExchageSeachEntity.class);
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
}
