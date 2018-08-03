package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNotcieEntity;
import java.util.List;
/**
 * 班级公告适配器
 * Created by wrb on 2016/12/21.
 */
public class TeacherClassNotcieAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassNotcieEntity> datas;
    public TeacherClassNotcieAdapter(Context context,List<ClassNotcieEntity> datas){
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
        ViewHoder vh;
        if(convertView==null){
            vh = new ViewHoder();
            convertView = inflater.inflate(R.layout.teacherclassnotcieadapter_item,null);
            vh.teacherclassnotcieadapter_lx_tv = (TextView)convertView.findViewById(R.id.teacherclassnotcieadapter_lx_tv);
            vh.teacherclassnotcieadapter_content_tv = (TextView)convertView.findViewById(R.id.teacherclassnotcieadapter_content_tv);
            vh.teacherclassnotcieadapter_fbtime_tv = (TextView)convertView.findViewById(R.id.teacherclassnotcieadapter_fbtime_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHoder) convertView.getTag();
        }
        vh.teacherclassnotcieadapter_lx_tv.setText("公告标题:"+datas.get(position).getTitle());
        vh.teacherclassnotcieadapter_content_tv.setText(Html.fromHtml(datas.get(position).getContent()));
        vh.teacherclassnotcieadapter_fbtime_tv.setText(datas.get(position).getTime());
        return convertView;
    }
    class ViewHoder{
        public TextView teacherclassnotcieadapter_lx_tv,
                teacherclassnotcieadapter_content_tv,teacherclassnotcieadapter_fbtime_tv;
    }
}
