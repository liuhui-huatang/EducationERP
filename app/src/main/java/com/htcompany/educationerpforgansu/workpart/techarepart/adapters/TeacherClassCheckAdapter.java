package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassCheckEntity;

import java.util.List;

/**
 * 班级考核适配器
 * Created by wrb on 2016/11/30.
 */
public class TeacherClassCheckAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassCheckEntity> datas;
    public TeacherClassCheckAdapter(Context context,List<ClassCheckEntity> datas){
        this.context = context;
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
        ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=inflater.inflate(R.layout.teacherclasscheckadapter_item,null);
            vh.tcca_pm_tv=(TextView)convertView.findViewById(R.id.tcca_pm_tv);
            vh.tcca_title_tv=(TextView)convertView.findViewById(R.id.tcca_title_tv);
            vh.tcca_class_tv=(TextView)convertView.findViewById(R.id.tcca_class_tv);
            vh.tcca_count_tv=(TextView)convertView.findViewById(R.id.tcca_count_tv);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        vh.tcca_pm_tv.setText(datas.get(position).getSort());
        vh.tcca_title_tv.setText(datas.get(position).getJingsai());
        vh.tcca_class_tv.setText("班级:"+datas.get(position).getName());
        vh.tcca_count_tv.setText("总分:"+datas.get(position).getTotalScores());
        return convertView;
    }
    class ViewHolder{
        public TextView tcca_pm_tv,tcca_title_tv,tcca_class_tv,tcca_count_tv;
    }
}
