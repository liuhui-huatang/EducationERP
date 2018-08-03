package com.htcompany.educationerpforgansu.workpart.studentpart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 学生功能模块
 * Created by wrb on 2016/11/7.
 */
public class StudentPartView extends LinearLayout{
    private Context context;
    private View studentView;
    private LayoutInflater inflater;
    public StudentPartView(Context context) {
        super(context, null);
    }

    public StudentPartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        studentView =  inflater.inflate(R.layout.studentpart_view,null);
        addView(studentView);
    }
}
