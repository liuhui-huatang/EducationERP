package com.htcompany.educationerpforgansu.workpart.exampart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 考务模块功能
 * Created by wrb on 2016/11/7.
 */
public class ExamPartView extends LinearLayout{
    private Context context;
    private View examView;
    private LayoutInflater inflater;
    public ExamPartView(Context context) {
        super(context, null);
    }

    public ExamPartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        examView =  inflater.inflate(R.layout.exampart_view,null);
        addView(examView);
    }
}
