package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.AllLessonEntity;

import java.util.List;

/**
 * 课程选择实体类
 * Created by wrb on 2017/4/20.
 */
public class AllLessonAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<AllLessonEntity> datas;
    public AllLessonAdapter(Context context,List<AllLessonEntity> datas){
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
