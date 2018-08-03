package com.htcompany.educationerpforgansu.workpart.dailogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;

/**
 * 筛选框
 * Created by weiruibin on 2017/5/26.
 */

public class ShaixuanDailog extends Dialog{
    private Handler handler;
    private int event;
    private Context context;
    private Button phonedailog_cancle_btn,phonedailog_sure_btn;
    private SharedPrefUtil sharedPrefUtil=null;
    public View.OnClickListener clickListener;
    public ShaixuanDailog(Context context, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        this.clickListener=clickListener;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));//取消对话框黑底色
        this.setContentView(R.layout.shaixuan_dailog);
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
        initDatas();
        initViews();
        initOnlicEvent();
    }
    public void initDatas(){
    }
    public void initViews(){
        phonedailog_cancle_btn=(Button)this.findViewById(R.id.phonedailog_cancle_btn);
        phonedailog_sure_btn=(Button)this.findViewById(R.id.phonedailog_sure_btn);
    }
    public void initOnlicEvent(){
        phonedailog_sure_btn.setOnClickListener(clickListener);
        phonedailog_cancle_btn.setOnClickListener(clickListener);
    }

}
