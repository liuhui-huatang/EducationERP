package com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

import java.util.List;

/**
 * 会议适配器
 * Created by wrb on 2016/11/22.
 */
public class WorkMeetingAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MyMeetingEntity> datas;
    public WorkMeetingAdapter(Context context,List<MyMeetingEntity> datas){
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
            convertView=inflater.inflate(R.layout.workmeetingadapter_item,null);
            vh.workmeetingadapter_title_tv = (TextView)convertView.findViewById(R.id.workmeetingadapter_title_tv);
            vh.workmeetingadapter_starttiem_tv = (TextView)convertView.findViewById(R.id.workmeetingadapter_starttiem_tv);
            vh.workmeetingadapter_endtime_tv = (TextView)convertView.findViewById(R.id.workmeetingadapter_endtime_tv);
            vh.workmeetingadapter_sqr_tv = (TextView)convertView.findViewById(R.id.workmeetingadapter_sqr_tv);
            vh.workmeetingadapter_zt_tv = (TextView)convertView.findViewById(R.id.workmeetingadapter_zt_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.workmeetingadapter_title_tv.setText(datas.get(position).getName());
        vh.workmeetingadapter_starttiem_tv.setText("开始时间:"+datas.get(position).getStart());
        vh.workmeetingadapter_endtime_tv.setText("结束时间:"+datas.get(position).getEnd());
        if(!"".equals(datas.get(position).getApply())&&datas.get(position).getApply()!=null){
            vh.workmeetingadapter_sqr_tv.setText("申请人:"+datas.get(position).getApply());

        }else{
            vh.workmeetingadapter_sqr_tv.setText("申请人:");

        }
        if("通过审核".equals(datas.get(position).getStatuss())){
            vh.workmeetingadapter_zt_tv.setTextColor(Color.parseColor("#3eb755"));
        }else if("已提交".equals(datas.get(position).getStatuss())){
            vh.workmeetingadapter_zt_tv.setTextColor(Color.parseColor("#ff8e43"));
        }else if("结束".equals(datas.get(position).getStatuss())){
            vh.workmeetingadapter_zt_tv.setTextColor(Color.parseColor("#517aad"));
        }
        vh.workmeetingadapter_zt_tv.setText(datas.get(position).getStatuss());
        return convertView;
    }
    class ViewHodler{
        public TextView workmeetingadapter_title_tv,workmeetingadapter_starttiem_tv,
                workmeetingadapter_endtime_tv,workmeetingadapter_sqr_tv,workmeetingadapter_zt_tv;
    }
}
