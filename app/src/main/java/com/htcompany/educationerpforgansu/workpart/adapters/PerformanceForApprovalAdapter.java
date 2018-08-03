package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.htcompany.educationerpforgansu.R;

/**
 * 绩效核定适配器
 * Created by wrb on 2016/11/9.
 */
public class PerformanceForApprovalAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    public PerformanceForApprovalAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return inflater.inflate(R.layout.performanceforapproval_lv_item,null);
    }
}
