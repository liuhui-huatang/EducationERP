package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkCarEntity;

import java.util.List;

/**
 * 我使用的车辆适配器
 * Created by wrb on 2016/11/10.
 */
public class MyUseCarsAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<WorkCarEntity> datas;
    public MyUseCarsAdapter(Context context,List<WorkCarEntity> datas){
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
            convertView = inflater.inflate(R.layout.myusecars_lv_item,null);
            vh.myusercarsadpter_name_tv = (TextView)convertView.findViewById(R.id.myusercarsadpter_name_tv);
            vh.myusercarsadpter_code_tv = (TextView)convertView.findViewById(R.id.myusercarsadpter_code_tv);
            vh.myusercarsadpter_zt_tv = (TextView)convertView.findViewById(R.id.myusercarsadpter_zt_tv);
            vh.myusercarsadpter_time_tv = (TextView)convertView.findViewById(R.id.myusercarsadpter_time_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        vh.myusercarsadpter_name_tv.setText(datas.get(position).getBrand());
        vh.myusercarsadpter_code_tv.setText("资产编号:"+datas.get(position).getNumber());
        if("已申请".equals(datas.get(position).getUc_status())){
            vh.myusercarsadpter_zt_tv.setTextColor(Color.parseColor("#ff8e43"));
        }else if("通过审核".equals(datas.get(position).getUc_status())){
            vh.myusercarsadpter_zt_tv.setTextColor(Color.parseColor("#3eb755"));
        }else if("未通过申请".equals(datas.get(position).getUc_status())){
            vh.myusercarsadpter_zt_tv.setTextColor(Color.parseColor("#517aad"));
        }
        vh.myusercarsadpter_zt_tv.setText(datas.get(position).getUc_status());
        vh.myusercarsadpter_time_tv.setText(datas.get(position).getUc_start_time());
        return convertView;
    }
    class ViewHodler{
        public TextView myusercarsadpter_name_tv,myusercarsadpter_code_tv,myusercarsadpter_zt_tv,myusercarsadpter_time_tv;
    }
}
