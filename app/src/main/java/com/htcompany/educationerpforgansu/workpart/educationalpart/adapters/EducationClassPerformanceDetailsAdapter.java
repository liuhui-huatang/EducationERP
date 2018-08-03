package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.ClassCJkcEntity;

import java.util.List;

/**
 * 课程适配器
 * Created by wrb on 2017/4/24.
 */
public class EducationClassPerformanceDetailsAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassCJkcEntity> datas;
    public EducationClassPerformanceDetailsAdapter(Context context,List<ClassCJkcEntity> datas){
        this.context =context;
        this.datas=datas;
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
            convertView=inflater.inflate(R.layout.educationclassperformancedetailsadapter_item,null);
            vh.educationclassperformancedetailsadapter_name_tv=(TextView)convertView.findViewById(R.id.educationclassperformancedetailsadapter_name_tv);
            vh.educationclassperformancedetailsadapter_source_tv=(TextView)convertView.findViewById(R.id.educationclassperformancedetailsadapter_source_tv);
           convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.educationclassperformancedetailsadapter_name_tv.setText(datas.get(position).getName());
        vh.educationclassperformancedetailsadapter_source_tv.setText(datas.get(position).getFenshu());
        return convertView;
    }
    class ViewHodler{
        public TextView educationclassperformancedetailsadapter_name_tv,educationclassperformancedetailsadapter_source_tv;
    }
}
