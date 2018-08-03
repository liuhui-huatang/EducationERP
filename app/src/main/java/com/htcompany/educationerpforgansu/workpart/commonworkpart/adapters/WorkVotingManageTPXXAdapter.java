package com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.htcompany.educationerpforgansu.R;

/**
 * 投票选项适配器
 * Created by wrb on 2016/12/15.
 */
public class WorkVotingManageTPXXAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    public WorkVotingManageTPXXAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 3;
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
        return inflater.inflate(R.layout.workvotingmanagetpxxadapter_item,null);
    }
}
