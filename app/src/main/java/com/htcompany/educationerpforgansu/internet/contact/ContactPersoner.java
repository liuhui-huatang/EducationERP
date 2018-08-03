package com.htcompany.educationerpforgansu.internet.contact;

import android.content.Context;

import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.contactpart.entity.ClassTxlEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 通讯录
 * Created by wrb on 2017/1/21.
 */
public class ContactPersoner {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    public ContactPersoner(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析课表数据
     * @param rebackString
     * @return
     */
    public List<ClassTxlEntity> parseClassTxlData(String rebackString){
        List<ClassTxlEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                //本周所有课程
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ClassTxlEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
}
