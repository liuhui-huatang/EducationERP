package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingDesignEntity;

import java.util.List;

/**
 * 教学设计适配器
 * Created by wrb on 2016/11/14.
 */
public class TeachingDesignAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeachingDesignEntity> datas;
    public TeachingDesignAdapter(Context context,List<TeachingDesignEntity> datas){
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView  = inflater.inflate(R.layout.teachingdesignadapter_item,null);
            vh.teachingdesignadapter_term_tv = (TextView)convertView.findViewById(R.id.teachingdesignadapter_term_tv);
            vh.teachingdesignadapter_zt_tv = (TextView)convertView.findViewById(R.id.teachingdesignadapter_zt_tv);
            vh.teachingdesignadapter_kc_tv = (TextView)convertView.findViewById(R.id.teachingdesignadapter_kc_tv);
            vh.teachingdesignadapter_dkjs_tv = (TextView)convertView.findViewById(R.id.teachingdesignadapter_dkjs_tv);
            vh.teachingdesignadapter_sj_tv = (TextView)convertView.findViewById(R.id.teachingdesignadapter_sj_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.teachingdesignadapter_term_tv.setText(datas.get(position).getXqid_name());
        vh.teachingdesignadapter_zt_tv.setText(datas.get(position).getStatus_name());
        vh.teachingdesignadapter_kc_tv.setText(datas.get(position).getCourceid_name());
        if("2".equals(datas.get(position).getType())){
            vh.teachingdesignadapter_dkjs_tv.setText("调课课程:"+datas.get(position).getCourceid_name());
        }else if("1".equals(datas.get(position).getType())){
            vh.teachingdesignadapter_dkjs_tv.setText("代课教师:"+datas.get(position).getTeacherid_name());
        }

        vh.teachingdesignadapter_sj_tv = (TextView)convertView.findViewById(R.id.teachingdesignadapter_sj_tv);
        return convertView;
    }
    class ViewHodler{
        public TextView teachingdesignadapter_term_tv,teachingdesignadapter_zt_tv,
                teachingdesignadapter_kc_tv,teachingdesignadapter_dkjs_tv,teachingdesignadapter_sj_tv;
    }
}
