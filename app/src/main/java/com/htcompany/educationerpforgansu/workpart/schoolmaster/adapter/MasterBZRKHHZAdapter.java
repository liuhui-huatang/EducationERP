package com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.BZRKHHZEntity;

import java.util.List;

/**
 * 班主任考核汇总适配器
 * Created by wrb on 2017/4/6.
 */
public class MasterBZRKHHZAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<BZRKHHZEntity> datas;
    public MasterBZRKHHZAdapter( Context context,List<BZRKHHZEntity> datas){
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
            convertView =inflater.inflate(R.layout.master_bzrkhhz_adapter_item,null);
            vh.khhz_name_tv=(TextView)convertView.findViewById(R.id.khhz_name_tv);
            vh.khhz_zgh_tv=(TextView)convertView.findViewById(R.id.khhz_zgh_tv);
            vh.khhz_xzb_tv=(TextView)convertView.findViewById(R.id.khhz_xzb_tv);
            vh.khhz_pm_tv=(TextView)convertView.findViewById(R.id.khhz_pm_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.khhz_name_tv.setText(datas.get(position).getTeacher_name());
        vh.khhz_zgh_tv.setText("职工号:"+datas.get(position).getNumber());
        vh.khhz_xzb_tv.setText("行政班:"+datas.get(position).getName());
        vh.khhz_pm_tv.setText(datas.get(position).getPaiming());
        return convertView;
    }
    class ViewHolder{
        public TextView khhz_name_tv,khhz_zgh_tv,khhz_xzb_tv,khhz_pm_tv;
    }
}
