package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.StudentPJEntity;

import java.util.List;

/**
 * 学生评教管理适配器
 * Created by wrb on 2016/11/25.
 */
public class EducationStudentPJManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<StudentPJEntity> datas;
    public EducationStudentPJManageAdapter(Context context,List<StudentPJEntity> datas){
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
            convertView=inflater.inflate(R.layout.educationstudentpjmanageadapter_item,null);
            vh.xspjitem_title_tv = (TextView)convertView.findViewById(R.id.xspjitem_title_tv);
            vh.xspjitem_starttime_tv = (TextView)convertView.findViewById(R.id.xspjitem_starttime_tv);
            vh.xspjitem_endtime_tv = (TextView)convertView.findViewById(R.id.xspjitem_endtime_tv);
            vh.xspjitem_zt_tv = (TextView)convertView.findViewById(R.id.xspjitem_zt_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.xspjitem_title_tv.setText(datas.get(position).getWjbt());
        vh.xspjitem_starttime_tv.setText(datas.get(position).getStarttime());
        vh.xspjitem_endtime_tv .setText(datas.get(position).getEndtime());
        vh.xspjitem_zt_tv.setText("已发布");
        return convertView;
    }
    class ViewHodler{
        public TextView xspjitem_title_tv,xspjitem_starttime_tv,xspjitem_endtime_tv,xspjitem_zt_tv;
    }
}
