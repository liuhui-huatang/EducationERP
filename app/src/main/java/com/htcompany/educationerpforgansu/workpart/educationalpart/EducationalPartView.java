package com.htcompany.educationerpforgansu.workpart.educationalpart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 教务功能
 * Created by wrb on 2016/11/3.
 */
public class EducationalPartView extends LinearLayout{
    private View eductiaonalView;
    private Context context;
    private LayoutInflater inflater;
    public EducationalPartView(Context context) {
        super(context, null);
    }

    public EducationalPartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        eductiaonalView = inflater.inflate(R.layout.educationalpartview,null);
        addView(eductiaonalView);
    }
}
