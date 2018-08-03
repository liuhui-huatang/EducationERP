package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.StudentWJQKEntity;

import java.util.List;

/**
 * 学生违纪适配器
 * Created by wrb on 2016/11/30.
 */
public class TeacherStudentWJQKAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<StudentWJQKEntity> datas;
    public TeacherStudentWJQKAdapter(Context context,List<StudentWJQKEntity> datas){
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
            vh = new ViewHodler();
            convertView = inflater.inflate(R.layout.teacherstudentwjqkadapter_item,null);
            vh.studentwjqkadapter_term_tv = (TextView)convertView.findViewById(R.id.studentwjqkadapter_term_tv);
            vh.studentwjqkadapter_time_tv = (TextView)convertView.findViewById(R.id.studentwjqkadapter_time_tv);
            vh.studentwjqkadapter_name_tv = (TextView)convertView.findViewById(R.id.studentwjqkadapter_name_tv);
            vh.studentwjqkadapter_xh_tv = (TextView)convertView.findViewById(R.id.studentwjqkadapter_xh_tv);
            vh.studentwjqkadapter_jxb_tv = (TextView)convertView.findViewById(R.id.studentwjqkadapter_jxb_tv);
            vh.studentwjqkadapter_jg_tv = (TextView)convertView.findViewById(R.id.studentwjqkadapter_jg_tv);
            vh.studentwjqkadapter_yx_tv = (TextView)convertView.findViewById(R.id.studentwjqkadapter_yx_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.studentwjqkadapter_term_tv.setText(datas.get(position).getXq());
        vh.studentwjqkadapter_time_tv.setText(datas.get(position).getTime());
        vh.studentwjqkadapter_name_tv.setText("姓名:"+datas.get(position).getXsname());
        vh.studentwjqkadapter_jg_tv.setText("经过:"+datas.get(position).getWjjg());
        vh.studentwjqkadapter_yx_tv.setText("影响:"+datas.get(position).getWjyx());
        return convertView;
    }
    class ViewHodler{
        public TextView studentwjqkadapter_term_tv,studentwjqkadapter_time_tv,studentwjqkadapter_name_tv,
                studentwjqkadapter_xh_tv,studentwjqkadapter_jxb_tv,studentwjqkadapter_jg_tv,studentwjqkadapter_yx_tv;
    }
}
