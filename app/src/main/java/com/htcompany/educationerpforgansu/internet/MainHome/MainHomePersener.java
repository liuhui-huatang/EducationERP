package com.htcompany.educationerpforgansu.internet.MainHome;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.homepart.entity.ContractDetailsEntity;
import com.htcompany.educationerpforgansu.homepart.entity.MyPunishmentEntity;
import com.htcompany.educationerpforgansu.homepart.entity.MyRewardsEntity;
import com.htcompany.educationerpforgansu.homepart.entity.PerformanceForApprovalEntity;
import com.htcompany.educationerpforgansu.homepart.entity.PersonMsgEntity;
import com.htcompany.educationerpforgansu.homepart.entity.PersonalAssessmentEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 个人信息解析类
 * Created by wrb on 2017/3/30.
 */
public class MainHomePersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public MainHomePersener(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析个人信息数据
     * @param rebackString
     * @return
     */
    public PersonMsgEntity parsePersonMsgData(String rebackString){
        PersonMsgEntity msgEntity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                msgEntity = JsonUtils.getObject(jsonObject.getJSONObject("data").toString(),PersonMsgEntity.class);
                return msgEntity;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return msgEntity;
    }
    /**
     * 解析合同信息数据
     * @param rebackString
     * @return
     */
    public ContractDetailsEntity parseContractDetailsData(String rebackString){
        ContractDetailsEntity msgEntity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                msgEntity = JsonUtils.getObject(jsonObject.getJSONObject("data").toString(),ContractDetailsEntity.class);
                return msgEntity;
            }else{
                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return msgEntity;
    }
    /**
     * 解析我的奖励列表数据
     * @param rebackString
     * @return
     */
    public List<MyRewardsEntity> parseMyRewards_ListData(String rebackString){
        List<MyRewardsEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),MyRewardsEntity.class);
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
     * 解析我的惩罚列表数据
     * @param rebackString
     * @return
     */
    public List<MyPunishmentEntity> parseMyPunishments_ListData(String rebackString){
        List<MyPunishmentEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),MyPunishmentEntity.class);
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
     * 解析个人考核列表数据
     * @param rebackString
     * @return
     */
    public List<PersonalAssessmentEntity> parsePersonalAssessment_ListData(String rebackString){
        List<PersonalAssessmentEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),PersonalAssessmentEntity.class);
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
     * 解析绩效核定列表数据
     * @param rebackString
     * @return
     */
    public List<PerformanceForApprovalEntity> parsePerformanceForApproval_ListData(String rebackString){
        List<PerformanceForApprovalEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),PerformanceForApprovalEntity.class);
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
