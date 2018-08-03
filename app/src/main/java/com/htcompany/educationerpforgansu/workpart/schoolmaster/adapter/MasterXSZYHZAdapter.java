package com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.htcompany.educationerpforgansu.R;

/**
 * 学生汇总适配器
 * Created by wrb on 2017/1/19.
 */
public class MasterXSZYHZAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    public MasterXSZYHZAdapter(Context context){
        this.context =context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;
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
        return inflater.inflate(R.layout.master_xshz_adapter_item,null);
    }
}
