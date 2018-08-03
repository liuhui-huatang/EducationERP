package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.RepalceClassOrTeacherEntity;

import java.util.List;

/**
 * 适配器代课教师 调课教师
 * Created by wrb on 2017/4/22.
 */
public class RepalceClassOrTeacherAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<RepalceClassOrTeacherEntity> datas;
    public RepalceClassOrTeacherAdapter(Context context,List<RepalceClassOrTeacherEntity> datas){
        this.context =context;
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
        ViewHodelr vh;
        if(convertView==null){
            vh = new ViewHodelr();
            convertView=inflater.inflate(R.layout.allessonadapter_item,null);
            vh.lesson_namge_tv = (TextView)convertView.findViewById(R.id.lesson_namge_tv);
            vh.lesson_cotent_tv = (TextView)convertView.findViewById(R.id.lesson_cotent_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodelr) convertView.getTag();
        }
        vh.lesson_namge_tv.setText(datas.get(position).getLabel());
        vh.lesson_cotent_tv.setText(Html.fromHtml(datas.get(position).getHtml()));
        return convertView;
    }
    class ViewHodelr{
        public TextView lesson_namge_tv,lesson_cotent_tv;
    }
}
