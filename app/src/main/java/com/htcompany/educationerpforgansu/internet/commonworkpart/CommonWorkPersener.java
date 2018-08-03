package com.htcompany.educationerpforgansu.internet.commonworkpart;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.VotingManageEntity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkCarEntity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllPartEntity;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 办公公告解析类
 * Created by wrb on 2017/4/15.
 */
public class CommonWorkPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public CommonWorkPersener(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析办公公告列表数据
     * @param rebackString
     * @return
     */
    public List<WorkNoticeEntity> parseWorknotcie_ListData(String rebackString){
        List<WorkNoticeEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),WorkNoticeEntity.class);
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
     * 解析删除公告返回数据
     * @param rebacstr
     * @return
     */
    public boolean parserDeleteWorkNotice(String rebacstr){
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
     * 解析部门列表数据
     * @param rebackString
     * @return
     */
    public List<AllPartEntity> parseAllParts_ListData(String rebackString){
        List<AllPartEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),AllPartEntity.class);
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
     * 解析会议管理列表数据
     * @param rebackString
     * @return
     */
    public List<MyMeetingEntity> parseMeetings_ListData(String rebackString){
        List<MyMeetingEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),MyMeetingEntity.class);
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
     * 解析车辆使用列表数据
     * @param rebackString
     * @return
     */
    public List<WorkCarEntity> parseUserCars_ListData(String rebackString){
        List<WorkCarEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),WorkCarEntity.class);
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
     * 解析投票管理列表数据
     * @param rebackString
     * @return
     */
    public List<VotingManageEntity> parseVotingManage_ListData(String rebackString){
        List<VotingManageEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),VotingManageEntity.class);
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
