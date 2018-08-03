package com.htcompany.educationerpforgansu.internet.bookmanage;

import android.content.Context;
import android.os.Handler;

import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.bookspart.entity.BookEntity;
import com.htcompany.educationerpforgansu.workpart.bookspart.entity.BookReserveEntity;
import com.htcompany.educationerpforgansu.workpart.bookspart.entity.BooksTypeEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 图书解析类
 * Created by wrb on 2017/2/17.
 */
public class ShoolBooksPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public ShoolBooksPersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析校园图书列表数据
     * @param rebackString
     * @return
     */
    public List<BookEntity> parseSchoolbooksData(String rebackString){
        List<BookEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),BookEntity.class);
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
     * 解析校园图书分类列表数据
     * @param rebackString
     * @return
     */
    public List<BooksTypeEntity> parseSchoolBookTypeData(String rebackString){
        List<BooksTypeEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),BooksTypeEntity.class);
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
     * 解析校园图书借阅记录列表数据
     * @param rebackString
     * @return
     */
    public List<BookReserveEntity> parseSchoolbooksReceverData(String rebackString){
        List<BookReserveEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),BookReserveEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
}
