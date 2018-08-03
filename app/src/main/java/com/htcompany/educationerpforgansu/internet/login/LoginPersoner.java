package com.htcompany.educationerpforgansu.internet.login;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.dao.daoservice.WorkFunctionService;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 登录解析类
 * Created by wrb on 2017/1/20.
 */
public class LoginPersoner {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private WorkFunctionService workFunctionService;//所有数据管理类
    public LoginPersoner(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
        workFunctionService =  new WorkFunctionService(context);
    }

    /**
     * 解析登录数据
     * @param rebacstr
     * @return
     */
    public boolean parserLogin(String rebacstr){
        try {
            jsonObject = new JSONObject(rebacstr);
            status  =jsonObject.getString("code");
            if("0".equals(status)) {
                /**
                 * 获取用户权限组
                 * 首先判断是否同一个人,并且权限状态是否改变登录，如果是不执行此操作，如果不是执行此操作
                 */
                boolean quanxian=jsonObject.getJSONObject("data").getBoolean("quanxian");
                if(!jsonObject.getJSONObject("data").getString("unkey").equals(sharedPrefUtil.getString("unkey",""))||quanxian){
                    firstJsonarray = jsonObject.getJSONObject("data").getJSONArray("menu");
                if (FunctionDatas.jsQXdATAS.size() > 0) {
                    FunctionDatas.jsQXdATAS.clear();
                }
                if (firstJsonarray != null && firstJsonarray.length() >0) {
                    for (int i=0;i<firstJsonarray.length();i++) {
                        FunctionDatas.jsQXdATAS.add(firstJsonarray.getString(i));
                    }
                }
                    sharedPrefUtil.putString("ishave","");
                    workFunctionService.deleteAllEntity();
                 }
                String token = jsonObject.getJSONObject("data").getString("token");
                sharedPrefUtil.putString("username",jsonObject.getJSONObject("data").getString("true_name"));
                sharedPrefUtil.putString("userphoto",jsonObject.getJSONObject("data").getString("photo"));
                sharedPrefUtil.putString("hxuser",jsonObject.getJSONObject("data").getString("hxuser"));
                sharedPrefUtil.putString("hxpassword",jsonObject.getJSONObject("data").getString("hxpassword"));
                sharedPrefUtil.putString("unkey",jsonObject.getJSONObject("data").getString("unkey"));
                sharedPrefUtil.putString("jpuid",jsonObject.getJSONObject("data").getString("jpuid"));
                sharedPrefUtil.putString("zhiwei",jsonObject.getJSONObject("data").getString("station"));
                sharedPrefUtil.putString("token",token);
//                if(!"1".equals(sharedPrefUtil.getString("jgbgsucess",""))){
                    setAlias(jsonObject.getJSONObject("data").getString("jpuid"));
//                }
                sharedPrefUtil.commit();
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //=========================================注册激光推送别名=================================================
    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias(String alias) {
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    sharedPrefUtil.putString("jgbgsucess","1");
                    sharedPrefUtil.commit();
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
            }
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(context,
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };
}
