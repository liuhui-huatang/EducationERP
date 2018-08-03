package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.MyLeaveTypeEntity;

import java.util.List;

/**
 * 请假类型适配器
 * Created by weiruibin on 2017/6/1.
 */

public class MyLeaveTypeDialogAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MyLeaveTypeEntity> datas;
    public MyLeaveTypeDialogAdapter(Context context,List<MyLeaveTypeEntity> datas){
        this.context=context;
        this.datas=datas;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView=inflater.inflate(R.layout.myleavetype_dailog_item,null);
            vh.myleavetypedialog_tv=(TextView)convertView.findViewById(R.id.myleavetypedialog_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.myleavetypedialog_tv.setText(datas.get(position).getName());
        return convertView;
    }
    class ViewHodler{
        public TextView myleavetypedialog_tv;
    }
}
