package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingRecordEntity;

import java.util.List;

/**
 * 授课记录适配器
 * Created by wrb on 2016/11/14.
 */
public class TeachingRecordAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeachingRecordEntity> datas;
    public TeachingRecordAdapter(Context context,List<TeachingRecordEntity> datas){
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
            convertView=inflater.inflate(R.layout.teachingrecordadapter_item,null);
            vh.recordadapter_kc_tv = (TextView)convertView.findViewById(R.id.recordadapter_kc_tv);
            vh.recordadapter_jxb_tv= (TextView)convertView.findViewById(R.id.recordadapter_jxb_tv);
            vh.recordadapter_time_tv= (TextView)convertView.findViewById(R.id.recordadapter_time_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.recordadapter_kc_tv.setText("课程:"+datas.get(position).getKecheng());
        vh.recordadapter_jxb_tv.setText("教学班:"+datas.get(position).getBanji());
        vh.recordadapter_time_tv.setText(datas.get(position).getSk_date()+"  "+datas.get(position).getSk_time());
        return convertView;
    }
    class ViewHodler{
        public TextView recordadapter_kc_tv,recordadapter_time_tv,recordadapter_jxb_tv;
    }
}
