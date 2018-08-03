package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;

import java.util.List;

/**
 * 参会人员适配器
 * Created by wrb on 2016/12/13.
 */
public class MeetingApplyCHRYAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<String> datas;
    public MeetingApplyCHRYAdapter(Context context,List<String> datas){
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
        ViewHoder vh;
        if(convertView==null){
            vh = new ViewHoder();
            convertView =inflater.inflate(R.layout.meetingapplychryadapter_item,null);
            vh.meetingapllychry_name_tv = (TextView)convertView.findViewById(R.id.meetingapllychry_name_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHoder) convertView.getTag();
        }
        vh.meetingapllychry_name_tv.setText(datas.get(position));
        return convertView;
    }
    class ViewHoder{
        public TextView meetingapllychry_name_tv;

    }
}
