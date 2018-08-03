package com.htcompany.educationerpforgansu.workpart.techarepart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 教师，班主任权限查看
 * Created by wrb on 2016/11/3.
 */
public class TecharePartView extends LinearLayout{
    private Context context;
    private View techareView;
    private LayoutInflater inflater;
    public TecharePartView(Context context) {
        super(context, null);
    }

    public TecharePartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        techareView =  inflater.inflate(R.layout.techarepart_view,null);
        addView(techareView);
    }

}
