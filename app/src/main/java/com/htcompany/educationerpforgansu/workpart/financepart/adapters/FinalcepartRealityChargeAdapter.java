package com.htcompany.educationerpforgansu.workpart.financepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.RealityChargeEntity;

import java.util.List;

/**
 * 实际收费记录
 * Created by wrb on 2016/11/16.
 */
public class FinalcepartRealityChargeAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<RealityChargeEntity> datas;
    public FinalcepartRealityChargeAdapter(Context context,List<RealityChargeEntity> datas){
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
        if(convertView ==null){
            vh =new ViewHodler();
            convertView = inflater.inflate(R.layout.finalcepartrealitychargeadapter_item,null);
            vh.rcadapter_name_tv = (TextView)convertView.findViewById(R.id.rcadapter_name_tv);
            vh.rcadapter_time_tv = (TextView)convertView.findViewById(R.id.rcadapter_time_tv);
            vh.rcadapter_biaozhun_tv = (TextView)convertView.findViewById(R.id.rcadapter_biaozhun_tv);
            vh.rcadapter_money_tv = (TextView)convertView.findViewById(R.id.rcadapter_money_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.rcadapter_name_tv.setText(datas.get(position).getProject_name());
        vh.rcadapter_time_tv.setText("收费日期："+datas.get(position).getTime());
        vh.rcadapter_biaozhun_tv.setText("收费标准："+datas.get(position).getBiaozhun());
        vh.rcadapter_money_tv.setText(datas.get(position).getMoney());
        return convertView;
    }
    class ViewHodler{
        public TextView rcadapter_name_tv,rcadapter_time_tv,rcadapter_biaozhun_tv,rcadapter_money_tv;
    }

}
