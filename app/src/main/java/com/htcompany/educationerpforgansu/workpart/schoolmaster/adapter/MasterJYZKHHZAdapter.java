package com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.JYZKHHZEntity;

import java.util.List;

/**
 * 教研组考核适配器
 * Created by wrb on 2017/4/6.
 */
public class MasterJYZKHHZAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<JYZKHHZEntity> datas;
    public MasterJYZKHHZAdapter( Context context,List<JYZKHHZEntity> datas){
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
        ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView =inflater.inflate(R.layout.master_jyzkhhz_adapter_item,null);
            vh.jyzkhhz_name_tv=(TextView)convertView.findViewById(R.id.jyzkhhz_name_tv);
            vh.jyzkhhz_zf_tv=(TextView)convertView.findViewById(R.id.jyzkhhz_zf_tv);
            vh.jyzkhhz_pm_tv=(TextView)convertView.findViewById(R.id.jyzkhhz_pm_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.jyzkhhz_name_tv.setText(datas.get(position).getName());
        vh.jyzkhhz_zf_tv.setText("总分:"+datas.get(position).getTotalScores()+"分");
        vh.jyzkhhz_pm_tv.setText("排名:第"+datas.get(position).getSort()+"名");
        return convertView;
    }
    class ViewHolder{
        public TextView jyzkhhz_name_tv,jyzkhhz_zf_tv,jyzkhhz_pm_tv;
    }
}
