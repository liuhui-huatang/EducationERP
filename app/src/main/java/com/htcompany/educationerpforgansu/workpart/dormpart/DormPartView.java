package com.htcompany.educationerpforgansu.workpart.dormpart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 宿舍功能
 * Created by wrb on 2016/11/7.
 */
public class DormPartView extends LinearLayout{
    private Context context;
    private View dormView;
    private LayoutInflater inflater;
    public DormPartView(Context context) {
        super(context, null);
    }

    public DormPartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        dormView =  inflater.inflate(R.layout.dormpart_view,null);
        addView(dormView);
    }
}
