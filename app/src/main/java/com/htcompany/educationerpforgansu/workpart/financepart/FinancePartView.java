package com.htcompany.educationerpforgansu.workpart.financepart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 财务管理功能
 * Created by wrb on 2016/11/7.
 */
public class FinancePartView extends LinearLayout{
    private Context context;
    private View financeView;
    private LayoutInflater inflater;
    public FinancePartView(Context context) {
        super(context, null);
    }

    public FinancePartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        financeView =  inflater.inflate(R.layout.financepart_view,null);
        addView(financeView);
    }
}
