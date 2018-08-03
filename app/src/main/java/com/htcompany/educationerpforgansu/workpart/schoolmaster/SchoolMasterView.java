package com.htcompany.educationerpforgansu.workpart.schoolmaster;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 校长空间
 * Created by wrb on 2017/1/3.
 */
public class SchoolMasterView extends LinearLayout {
    private Context context;
    private View techareView;
    private LayoutInflater inflater;

    public SchoolMasterView(Context context) {
        super(context, null);
    }

    public SchoolMasterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        techareView = inflater.inflate(R.layout.schoolmaster_view, null);
        addView(techareView);
    }
}
