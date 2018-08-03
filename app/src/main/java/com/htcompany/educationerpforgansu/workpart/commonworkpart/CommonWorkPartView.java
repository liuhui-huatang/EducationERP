package com.htcompany.educationerpforgansu.workpart.commonworkpart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 办公模块
 * Created by wrb on 2016/11/22.
 */
public class CommonWorkPartView extends LinearLayout{
    private Context context;
    private View commonworkpartView;
    private LayoutInflater inflater;
    public CommonWorkPartView(Context context) {
        super(context, null);
    }

    public CommonWorkPartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        commonworkpartView =  inflater.inflate(R.layout.commonworkpart_view,null);
        addView(commonworkpartView);
    }
}
