package com.htcompany.educationerpforgansu.workpart.financepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.OutlayEnity;

import java.util.List;

/**
 * 支出管理适配器
 * Created by wrb on 2016/11/17.
 */
public class FinancepartOutlayAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<OutlayEnity> datas;
    public FinancepartOutlayAdapter(Context context,List<OutlayEnity> datas){
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
            convertView = inflater.inflate(R.layout.financepartoutlayadapter_item,null);
            vh.outlayadapter_photo_img = (ImageView)convertView.findViewById(R.id.outlayadapter_photo_img);
            vh.outlayadapter_name_tv = (TextView) convertView.findViewById(R.id.outlayadapter_name_tv);
            vh.outlayadapter_gw_tv = (TextView) convertView.findViewById(R.id.outlayadapter_gw_tv);
            vh.outlayadapter_type_tv = (TextView) convertView.findViewById(R.id.outlayadapter_type_tv);
            vh.outlayadapter_money_tv = (TextView) convertView.findViewById(R.id.outlayadapter_money_tv);
            vh.outlayadapter_time_tv = (TextView) convertView.findViewById(R.id.outlayadapter_time_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.outlayadapter_name_tv.setText(datas.get(position).getTeacherName());
        vh.outlayadapter_gw_tv.setText("岗位:"+datas.get(position).getGangwei());
        vh.outlayadapter_type_tv.setText("支出类型:"+datas.get(position).getType());
        vh.outlayadapter_money_tv.setText(datas.get(position).getMoney());
        vh.outlayadapter_time_tv.setText(datas.get(position).getTime());
        return convertView;
    }
    class ViewHodler{
        public ImageView outlayadapter_photo_img;
        public TextView outlayadapter_name_tv,outlayadapter_gw_tv,outlayadapter_type_tv,outlayadapter_money_tv,outlayadapter_time_tv;
    }
}
