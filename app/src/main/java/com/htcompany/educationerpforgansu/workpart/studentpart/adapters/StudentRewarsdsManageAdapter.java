package com.htcompany.educationerpforgansu.workpart.studentpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentRewarsdsEntity;

import java.util.List;

/**
 * 学生奖励适配器
 * Created by wrb on 2016/11/23.
 */
public class StudentRewarsdsManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<StudentRewarsdsEntity> datas;
    public StudentRewarsdsManageAdapter(Context context,List<StudentRewarsdsEntity> datas){
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
            convertView = inflater.inflate(R.layout.studentrewarsdsmanageadapter_item,null);
            vh.studentrewarsdsmanageadapter_photo_img = (ImageView)convertView.findViewById(R.id.studentrewarsdsmanageadapter_photo_img);
            vh.classexamadapter_name_tv = (TextView) convertView.findViewById(R.id.classexamadapter_name_tv);
            vh.classexamadapter_xf_tv = (TextView) convertView.findViewById(R.id.classexamadapter_xf_tv);
            vh.classexamadapter_xh_tv = (TextView) convertView.findViewById(R.id.classexamadapter_xh_tv);
            vh.classexamadapter_cj_tv = (TextView) convertView.findViewById(R.id.classexamadapter_cj_tv);
        }
        return inflater.inflate(R.layout.studentrewarsdsmanageadapter_item,null);
    }
    class ViewHodler{
        public ImageView studentrewarsdsmanageadapter_photo_img;
        public TextView classexamadapter_name_tv,classexamadapter_xf_tv,classexamadapter_xh_tv,classexamadapter_cj_tv;
    }
}
