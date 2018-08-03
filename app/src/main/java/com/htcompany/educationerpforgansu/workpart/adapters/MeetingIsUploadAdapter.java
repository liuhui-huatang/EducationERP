package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

import java.util.List;

/**
 * 已提交会议适配器
 * Created by wrb on 2016/11/10.
 */
public class MeetingIsUploadAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MyMeetingEntity> datas;
    public MeetingIsUploadAdapter(Context context,List<MyMeetingEntity> datas){
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
            convertView = inflater.inflate(R.layout.meetingwaitupload_lv_item,null);
            vh.meetingwaitupload_name_tv = (TextView)convertView.findViewById(R.id.meetingwaitupload_name_tv);
            vh.meetingwaitupload_zt_tv = (TextView)convertView.findViewById(R.id.meetingwaitupload_zt_tv);
            vh.meetingwaitupload_starttime_tv = (TextView)convertView.findViewById(R.id.meetingwaitupload_starttime_tv);
            vh.meetingwaitupload_endtime_tv = (TextView)convertView.findViewById(R.id.meetingwaitupload_endtime_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
//        vh.meetingwaitupload_name_tv.setText(datas.get(position).getMname());
//        vh.meetingwaitupload_zt_tv.setText("待提交");
//        vh.meetingwaitupload_starttime_tv.setText("开始时间："+datas.get(position).getMstarttime());
//        vh.meetingwaitupload_endtime_tv.setText("结束时间："+datas.get(position).getMendtime());
        return convertView;
    }
    class ViewHodler{
        public TextView meetingwaitupload_name_tv,meetingwaitupload_zt_tv,meetingwaitupload_starttime_tv,meetingwaitupload_endtime_tv;
    }
}
