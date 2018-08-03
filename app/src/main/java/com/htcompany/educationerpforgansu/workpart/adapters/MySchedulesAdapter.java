package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.ScheduleEntity;

import java.util.List;

/**
 * 我的日程适配器
 * Created by wrb on 2016/11/7.
 */
public class MySchedulesAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ScheduleEntity> datas;
    public MySchedulesAdapter(Context context,List<ScheduleEntity> datas){
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
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
            convertView =inflater.inflate(R.layout.main_schedules_item,null);
            vh.myschedules_tiem_tv = (TextView) convertView.findViewById(R.id.myschedules_tiem_tv);
            vh.myschedules_content_tv = (TextView) convertView.findViewById(R.id.myschedules_content_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.myschedules_tiem_tv.setText(datas.get(position).getS_time()+"至"+datas.get(position).getE_time());
        vh.myschedules_content_tv.setText(datas.get(position).getMessage());
        return convertView;
    }
    class ViewHodler{
        public TextView myschedules_tiem_tv,myschedules_content_tv;
    }
}
