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
 * 会议详情名单适配器
 * Created by wrb on 2016/12/13.
 */
public class MeetingDetailsRYMDAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<String> datas;
    public MeetingDetailsRYMDAdapter(Context context,List<String> datas){
        this.context = context;
        this.datas =datas;
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
            vh =new ViewHodler();
            convertView = inflater.inflate(R.layout.meetingdetailsrymdadapter_item,null);
            vh.meetingdetails_name_tv = (TextView)convertView.findViewById(R.id.meetingdetails_name_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        vh.meetingdetails_name_tv.setText(datas.get(position));
        return convertView;
    }
    class ViewHodler{
        public TextView meetingdetails_name_tv;
    }
}
