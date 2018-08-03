package com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters;

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
 * 车辆使用管理适配器
 * Created by wrb on 2016/11/22.
 */
public class WorkCarsUseAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<WorkCarEntity> datas;
    public WorkCarsUseAdapter(Context context,List<WorkCarEntity> datas){
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
            convertView =inflater.inflate(R.layout.workcarsuseadapter_item,null);
            vh.workcaruseadapter_sqr_tv = (TextView)convertView.findViewById(R.id.workcaruseadapter_sqr_tv);
            vh.workcaruseadapter_name_tv = (TextView)convertView.findViewById(R.id.workcaruseadapter_name_tv);
            vh.workcaruseadapter_cph_tv = (TextView)convertView.findViewById(R.id.workcaruseadapter_cph_tv);
            vh.workcaruseadapter_zt_tv = (TextView)convertView.findViewById(R.id.workcaruseadapter_zt_tv);
            vh.workcaruseadapter_mdd_tv = (TextView)convertView.findViewById(R.id.workcaruseadapter_mdd_tv);
            vh.workcaruseadapter_sqsj_tv = (TextView)convertView.findViewById(R.id.workcaruseadapter_sqsj_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.workcaruseadapter_sqr_tv.setText("申请人:"+datas.get(position).getApplyer());
        vh.workcaruseadapter_name_tv.setText(datas.get(position).getBrand());
        vh.workcaruseadapter_cph_tv.setText("资产编号:"+datas.get(position).getNumber());
        if("已申请".equals(datas.get(position).getUc_status())){
            vh.workcaruseadapter_zt_tv.setTextColor(Color.parseColor("#ff8e43"));
        }else if("通过审核".equals(datas.get(position).getUc_status())){
            vh.workcaruseadapter_zt_tv.setTextColor(Color.parseColor("#3eb755"));
        }else if("未通过申请".equals(datas.get(position).getUc_status())){
            vh.workcaruseadapter_zt_tv.setTextColor(Color.parseColor("#517aad"));
        }
        vh.workcaruseadapter_zt_tv.setText(datas.get(position).getUc_status());
        vh.workcaruseadapter_mdd_tv.setText("目的地:"+datas.get(position).getUc_end_place());
        vh.workcaruseadapter_sqsj_tv.setText("出发时间:"+datas.get(position).getUc_start_time());
        return convertView;
    }
    class ViewHodler{
        public TextView workcaruseadapter_name_tv,workcaruseadapter_sqr_tv,workcaruseadapter_cph_tv,
                workcaruseadapter_zt_tv,workcaruseadapter_mdd_tv,workcaruseadapter_sqsj_tv;
    }
}
