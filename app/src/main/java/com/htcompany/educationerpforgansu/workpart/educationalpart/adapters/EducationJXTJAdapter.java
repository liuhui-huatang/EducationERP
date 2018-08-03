package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationJXTEntity;

import java.util.List;

/**
 * 教学互动统计
 * Created by wrb on 2016/11/25.
 */
public class EducationJXTJAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<EducationJXTEntity> datas;
    public EducationJXTJAdapter(Context context,List<EducationJXTEntity> datas){
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
            convertView =inflater.inflate(R.layout.educationjxtjadapter_item,null);
            vh.educationjxtjadapter_bm_tv=(TextView)convertView.findViewById(R.id.educationjxtjadapter_bm_tv);
            vh.educationjxtjadapter_btwjs_tv=(TextView)convertView.findViewById(R.id.educationjxtjadapter_btwjs_tv);
            vh.educationjxtjadapter_wts_tv=(TextView)convertView.findViewById(R.id.educationjxtjadapter_wts_tv);
            vh.educationjxtjadapter_yhds_tv=(TextView)convertView.findViewById(R.id.educationjxtjadapter_yhds_tv);
            vh.educationjxtjadapter_whds_tv=(TextView)convertView.findViewById(R.id.educationjxtjadapter_whds_tv);
            vh.educationjxtjadapter_yjjs_tv=(TextView)convertView.findViewById(R.id.educationjxtjadapter_yjjs_tv);
            vh.educationjxtjadapter_wjjs_tv=(TextView)convertView.findViewById(R.id.educationjxtjadapter_wjjs_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        vh.educationjxtjadapter_bm_tv.setText(datas.get(position).getJigou_name());
        vh.educationjxtjadapter_btwjs_tv.setText(datas.get(position).getTeacher_name());
        vh.educationjxtjadapter_wts_tv.setText(datas.get(position).getWenti_num());
        vh.educationjxtjadapter_yhds_tv.setText(datas.get(position).getHuida_num());
        vh.educationjxtjadapter_whds_tv.setText(datas.get(position).getWeida_num());
        vh.educationjxtjadapter_yjjs_tv.setText(datas.get(position).getYijiejue_num());
        vh.educationjxtjadapter_wjjs_tv.setText(datas.get(position).getWeijiejue_num());
        return convertView;
    }
    class  ViewHodler{
        private TextView educationjxtjadapter_bm_tv,educationjxtjadapter_btwjs_tv,
                educationjxtjadapter_wts_tv,educationjxtjadapter_yhds_tv,educationjxtjadapter_whds_tv,
                educationjxtjadapter_yjjs_tv,educationjxtjadapter_wjjs_tv;
    }
}
