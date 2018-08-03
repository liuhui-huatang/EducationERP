package com.htcompany.educationerpforgansu.internet.workpersonnel;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonContractEntity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonLeaveApplyEntity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonManageEntity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonSocialInsuranceEntity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonnelFilesEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * 人事管理数据解析类
 * Created by wrb on 2017/4/12.
 */
public class WorkPersonnelManagePersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public WorkPersonnelManagePersener(Context context, Handler myHandler){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析数据
     * @param jsonString
     * @return
     */
    public boolean parseUploadSucess(String jsonString){
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
     * 解析人事档案数据
     * @param jsonString
     * @return
     */
    public List<PersonnelFilesEntity> paresePersonFile(String jsonString){
        List<PersonnelFilesEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),PersonnelFilesEntity.class);
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
     * 解析社会保险记录数据
     * @param jsonString
     * @return
     */
    public List<PersonSocialInsuranceEntity> paresePersonSocialInsurance(String jsonString){
        List<PersonSocialInsuranceEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),PersonSocialInsuranceEntity.class);
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
     * 解析合同数据
     * @param jsonString
     * @return
     */
    public List<PersonContractEntity> paresePersonContract(String jsonString){
        List<PersonContractEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),PersonContractEntity.class);
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
    public List<PersonManageEntity> paresePersonManageDatas(String jsonString){
        List<PersonManageEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),PersonManageEntity.class);
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
     * 解析教职工请假数据
     * @param jsonString
     * @return
     */
    public List<PersonLeaveApplyEntity> paresePersonLeavewApplyManageDatas(String jsonString){
        List<PersonLeaveApplyEntity> datas = null;
        try {
            jsonObject = new JSONObject(jsonString);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                if(BaseUtils.isString(jsonObject.getString("data").toString())) {
                    datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),PersonLeaveApplyEntity.class);
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
}
