package com.htcompany.educationerpforgansu.contactpart;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.contactpart.activitys.ContactsActivity;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;

/**
 * 通讯录
 * Created by wrb on 2016/11/2.
 */
public class MainContactFragment extends Fragment implements View.OnClickListener{
    private View view;
    private RelativeLayout contact_school_rel,contact_part_rel,contact_class_rel;
    private Intent intent=null;
    private TextView contact_title_tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maincontact_fragment,container,false);
        initViews();
        initOnclicEvents();
        initViewValues();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            initValues();
        }
        return view;
    }
    public void initViews(){
        contact_title_tv=(TextView)view.findViewById(R.id.contact_title_tv);
        contact_school_rel = (RelativeLayout)view.findViewById(R.id.contact_school_rel);
        contact_part_rel= (RelativeLayout)view.findViewById(R.id.contact_part_rel);
                contact_class_rel= (RelativeLayout)view.findViewById(R.id.contact_class_rel);
    }
    public void initValues(){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)contact_title_tv.getLayoutParams();
        layoutParams.setMargins(0, BaseUtils.getStatusBarHeight(getActivity()),0,0);//4个参数按顺序分别是左上右下
        contact_title_tv.setLayoutParams(layoutParams); //mView是控件
    }
    public void initViewValues(){
        if(FunctionDatas.jsQXdATAS.size()>0){
            if(FunctionDatas.jsQXdATAS.contains(FunctionDatas.FUCIOTN_BZR)){
                contact_class_rel.setVisibility(View.VISIBLE);
            }else{
                contact_class_rel.setVisibility(View.GONE);
            }
        }else{
            contact_class_rel.setVisibility(View.GONE);
        }
    }
    public void initOnclicEvents(){
        contact_school_rel.setOnClickListener(this);
        contact_part_rel.setOnClickListener(this);
        contact_class_rel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact_school_rel:
                intent = new Intent(getActivity(), ContactsActivity.class);
                intent.putExtra("contetnFlag","SCHOOLALL");
                startActivity(intent);
                break;
            case R.id.contact_part_rel:
                intent = new Intent(getActivity(), ContactsActivity.class);
                intent.putExtra("contetnFlag","PARTALL");
                startActivity(intent);
                break;
            case R.id.contact_class_rel:
                intent = new Intent(getActivity(), ContactsActivity.class);
                intent.putExtra("contetnFlag","CLASSALL");
                startActivity(intent);
                break;
        }
    }
}
