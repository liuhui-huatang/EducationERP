package com.htcompany.educationerpforgansu.workpart.personnelpart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 人事功能模块
 * Created by wrb on 2016/11/7.
 */
public class PersonnelPartView extends LinearLayout{
    private Context context;
    private View personnelView;
    private LayoutInflater inflater;
    public PersonnelPartView(Context context) {
        super(context, null);
    }

    public PersonnelPartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        personnelView =  inflater.inflate(R.layout.personnelpart_view,null);
        addView(personnelView);
    }
}
