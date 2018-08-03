package com.htcompany.educationerpforgansu.internet.workgrzx;

import android.content.Context;

import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkCarEntity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllAddressEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllCarsTypeEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllJZGEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AssetMaintenanceEntity;
import com.htcompany.educationerpforgansu.workpart.entities.BeOnDutyEntity;
import com.htcompany.educationerpforgansu.workpart.entities.DYNewsEntity;
import com.htcompany.educationerpforgansu.workpart.entities.MyLeaveApplyEntity;
import com.htcompany.educationerpforgansu.workpart.entities.MyLeaveTypeEntity;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;
import com.htcompany.educationerpforgansu.workpart.entities.OverFlowStepEntity;
import com.htcompany.educationerpforgansu.workpart.entities.ScheduleEntity;
import com.htcompany.educationerpforgansu.workpart.entities.WaitFlowEntity;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFlowStartSendEntity;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.SalaryEntity;
import com.lidroid.xutils.util.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 处理数据
 * Created by wrb on 2017/1/6.
 */
public class WokrpersonalPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    public WokrpersonalPersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析待办事项数据
     * @param jsonString
     * @return
     */
    public List<WaitFlowEntity> parseWrokFlow(String jsonString){
        List<WaitFlowEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){

                LogUtils.d("parseWrokFlow=>"+jsonObject.getString("data").toString());

                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    String srt = jsonObject.getJSONObject("data").getJSONArray("list").toString();
                    System.out.print(srt);
                    datas = JsonUtils.getListObject(srt,WaitFlowEntity.class);
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
     * 解析待办事项数据总数
     * @param jsonString
     * @return
     */
    public String parseWaitWrokFlowCount(String jsonString){
        String count="0";
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    count=jsonObject.getJSONObject("data").getString("total");
                    return count;
                }
            }else{
                return count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    /**
     * 解析已办办事项详情数据
     * @param jsonString
     * @return
     */
    public List<OverFlowStepEntity> parseOverWrokFlowDetials(String jsonString){
        List<OverFlowStepEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    String srt = jsonObject.getJSONArray("data").toString();
                    System.out.print(srt);
                    datas = JsonUtils.getListObject(srt,OverFlowStepEntity.class);
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
     * 解析待办事项审核通过数据
     * @param jsonString
     * @return
     */
    public boolean parseWaitWrokFlowUploadSucess(String jsonString){
        try {
            jsonObject = new JSONObject(jsonString);
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
     * 解析发起工作流类型列表数据
     * @param jsonString
     * @return
     */
    public List<WorkFlowStartSendEntity> parseWrokFlowStartSendListDatas(String jsonString){
        List<WorkFlowStartSendEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    String srt = jsonObject.getJSONObject("data").getJSONArray("data").toString();
                    System.out.print(srt);
                    datas = JsonUtils.getListObject(srt,WorkFlowStartSendEntity.class);
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
     * 解析教职工数据
     * @param jsonString
     * @return
     */
    public List<AllJZGEntity> parseJZGFlow(String jsonString){
        List<AllJZGEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    String srt = jsonObject.getJSONArray("data").toString();
                    System.out.print(srt);
                    datas = JsonUtils.getListObject(srt,AllJZGEntity.class);
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
     * 解析工资数据
     * @param rebackString
     * @return
     */
    public List<SalaryEntity> parseSarilayData(String rebackString){
        List<SalaryEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),SalaryEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }

    /**
     * 解析资产报修数据列表数据
     * @param rebackString
     * @return
     */
    public List<AssetMaintenanceEntity> parseAssetRepairListData(String rebackString){
        List<AssetMaintenanceEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),AssetMaintenanceEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析添加资产报修
     * @param rebacstr
     * @return
     */
    public boolean parserAddAssetReapir(String rebacstr){
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
//                myHandler.sendEmptyMessage(300);
            }
        } catch (JSONException e) {
            e.printStackTrace();
//            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析我的会议列表数据
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析会议地址列表数据
     * @param rebackString
     * @return
     */
    public List<AllAddressEntity> parseMeetingAddress_ListData(String rebackString){
        List<AllAddressEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),AllAddressEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析我的使用车辆列表数据
     * @param rebackString
     * @return
     */
    public List<WorkCarEntity> parseMyUsercars_ListData(String rebackString){
        List<WorkCarEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),WorkCarEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析车辆类型列表数据
     * @param rebackString
     * @return
     */
    public List<AllCarsTypeEntity> parseCarsType_ListData(String rebackString){
        List<AllCarsTypeEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),AllCarsTypeEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析我请假列表数据
     * @param rebackString
     * @return
     */
    public List<MyLeaveApplyEntity> parseMyLeaveApply_ListData(String rebackString){
        List<MyLeaveApplyEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),MyLeaveApplyEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析我请假类型列表数据
     * @param rebackString
     * @return
     */
    public List<MyLeaveTypeEntity> parseMyLeaveTypes_ListData(String rebackString){
        List<MyLeaveTypeEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),MyLeaveTypeEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析我的值班列表数据
     * @param rebackString
     * @return
     */
    public List<BeOnDutyEntity> parseMyBeOnDuty_ListData(String rebackString){
        List<BeOnDutyEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),BeOnDutyEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析我的日程列表数据
     * @param rebackString
     * @return
     */
    public List<ScheduleEntity> parseMySchedules_ListData(String rebackString){
        List<ScheduleEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ScheduleEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析德育新闻列表数据
     * @param rebackString
     * @return
     */
    public List<DYNewsEntity> parseDYnewsData(String rebackString){
        List<DYNewsEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),DYNewsEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
}
