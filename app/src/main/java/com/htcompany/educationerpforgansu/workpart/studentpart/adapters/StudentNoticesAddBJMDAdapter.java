package com.htcompany.educationerpforgansu.workpart.studentpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;

import java.util.List;

/**
 * 班级名单适配器
 * Created by wrb on 2016/12/23.
 */
public class StudentNoticesAddBJMDAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<AllClassEntity> datas;
    public StudentNoticesAddBJMDAdapter(Context context,List<AllClassEntity> datas){
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
        vh.meetingdetails_name_tv.setText(datas.get(position).getCname());
        return convertView;
    }
    class ViewHodler{
        public TextView meetingdetails_name_tv;
    }
}
