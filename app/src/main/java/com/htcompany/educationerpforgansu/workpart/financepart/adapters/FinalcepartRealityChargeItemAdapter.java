package com.htcompany.educationerpforgansu.workpart.financepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.RealityChargeItemEntity;

import java.util.List;

/**
 * 收费科目适配器
 * Created by wrb on 2017/4/10.
 */
public class FinalcepartRealityChargeItemAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<RealityChargeItemEntity> datas;
    public FinalcepartRealityChargeItemAdapter(Context context,List<RealityChargeItemEntity> datas){
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
            convertView = inflater.inflate(R.layout.finalcepartrealitychargeitemadapter_item,null);
            vh.finalcepartrealitychargeitemadapter_name_tv = (TextView)convertView.findViewById(R.id.finalcepartrealitychargeitemadapter_name_tv);
            vh.finalcepartrealitychargeitemadapter_money_tv = (TextView)convertView.findViewById(R.id.finalcepartrealitychargeitemadapter_money_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.finalcepartrealitychargeitemadapter_name_tv.setText(datas.get(position).getName());
        vh.finalcepartrealitychargeitemadapter_money_tv.setText(datas.get(position).getCount());
        return convertView;
    }
    class ViewHodler{
        public TextView finalcepartrealitychargeitemadapter_name_tv,finalcepartrealitychargeitemadapter_money_tv;
    }

}
