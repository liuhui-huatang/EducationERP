package com.htcompany.educationerpforgansu.workpart.financepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.ReturnMoneyEntity;

import java.util.List;

/**
 * 退费记录汇总适配器
 * Created by wrb on 2016/11/16.
 */
public class FinalcepartReturnMoneyAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ReturnMoneyEntity> datas;
    public FinalcepartReturnMoneyAdapter(Context context,List<ReturnMoneyEntity> datas){
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
        ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.finalcepartreturnmoneyadapter_item,null);
            vh.returnmoneyadapter_money_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_money_tv);
            vh.returnmoneyadapter_time_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_time_tv);
            vh.returnmoneyadapter_jxb_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_jxb_tv);
            vh.returnmoneyadapter_bzr_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_bzr_tv);
            vh.returnmoneyadapter_xs_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_xs_tv);
            vh.returnmoneyadapter_xh_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_xh_tv);
            vh.returnmoneyadapter_fczt_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_fczt_tv);
            vh.returnmoneyadapter_fcr_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_fcr_tv);
            vh.returnmoneyadapter_fctime_tv = (TextView)convertView.findViewById(R.id.returnmoneyadapter_fctime_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.returnmoneyadapter_money_tv.setText("退费金额："+datas.get(position).getMoney());
        vh.returnmoneyadapter_time_tv.setText("退费时间："+datas.get(position).getTime());
        vh.returnmoneyadapter_jxb_tv.setText(datas.get(position).getJiaoxueban());
        vh.returnmoneyadapter_bzr_tv.setText(datas.get(position).getBanzhuren());
        vh.returnmoneyadapter_xs_tv.setText(datas.get(position).getXuesheng());
        vh.returnmoneyadapter_xh_tv.setText(datas.get(position).getXuehao());
        vh.returnmoneyadapter_fczt_tv.setText(datas.get(position).getFengcun());
        vh.returnmoneyadapter_fcr_tv.setText("封存人:"+datas.get(position).getFengcunUser());
        vh.returnmoneyadapter_fctime_tv.setText("封存时间:"+datas.get(position).getFengcunTime());
        return convertView;
    }
    class ViewHolder{
        public TextView returnmoneyadapter_money_tv,returnmoneyadapter_time_tv,
                returnmoneyadapter_jxb_tv,returnmoneyadapter_bzr_tv,returnmoneyadapter_xs_tv,
                returnmoneyadapter_xh_tv,returnmoneyadapter_fczt_tv,returnmoneyadapter_fcr_tv,
                returnmoneyadapter_fctime_tv;
    }
}
