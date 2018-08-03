package com.htcompany.educationerpforgansu.workpart.dailogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.workpart.adapters.MyLeaveTypeDialogAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.MyLeaveTypeEntity;

import java.util.List;

/**
 * 请假类型选择界面
 * Created by weiruibin on 2017/6/1.
 */

public class MyLeaveTypeDailog extends Dialog {
    private Context context;
    private SharedPrefUtil sharedPrefUtil=null;
    private ListView myleavetype_lv;
    private MyLeaveTypeDialogAdapter dialogAdapter;
    private List<MyLeaveTypeEntity> datas;
    private AdapterView.OnItemClickListener typeCliceer;
    public MyLeaveTypeDailog(Context context,List<MyLeaveTypeEntity> datas,AdapterView.OnItemClickListener typeCliceer) {
        super(context);
        this.context = context;
        this.typeCliceer=typeCliceer;
        this.datas=datas;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));//取消对话框黑底色
        this.setContentView(R.layout.myleavetype_dailog);
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
        initDatas();
        initViews();
        initOnlicEvent();
    }
    public void initDatas(){
    }
    public void initViews(){
        myleavetype_lv=(ListView)this.findViewById(R.id.myleavetype_lv);
        dialogAdapter=new MyLeaveTypeDialogAdapter(context,datas);
        myleavetype_lv.setAdapter(dialogAdapter);
        myleavetype_lv.setOnItemClickListener(typeCliceer);
    }
    public void initOnlicEvent(){
    }

}
