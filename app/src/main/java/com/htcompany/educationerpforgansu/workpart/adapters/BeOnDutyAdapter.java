package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.BeOnDutyEntity;

import java.util.List;

/**
 * 我的值班
 * Created by wrb on 2016/11/11.
 */
public class BeOnDutyAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<BeOnDutyEntity> datas;
    public BeOnDutyAdapter(Context context,List<BeOnDutyEntity> datas){
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
            vh =new ViewHodler();
            convertView=inflater.inflate(R.layout.beonduty_lv_item,null);
            vh.beonduty_term_tv=(TextView)convertView.findViewById(R.id.beonduty_term_tv);
            vh.beonduty_stauts_tv=(TextView)convertView.findViewById(R.id.beonduty_stauts_tv);
            vh.beonduty_sttime_tv=(TextView)convertView.findViewById(R.id.beonduty_sttime_tv);
            vh.beonduty_endtime_tv=(TextView)convertView.findViewById(R.id.beonduty_endtime_tv);
            vh.beonduty_zdperson_tv=(TextView)convertView.findViewById(R.id.beonduty_zdperson_tv);
            convertView.setTag(vh);
        }else{
            vh= (ViewHodler) convertView.getTag();
        }
        vh.beonduty_term_tv.setText(datas.get(position).getTerm());
        vh.beonduty_stauts_tv.setText(datas.get(position).getTrbd_status());
        vh.beonduty_sttime_tv.setText(datas.get(position).getStart_time());
        vh.beonduty_endtime_tv.setText(datas.get(position).getEnd_time());
        vh.beonduty_zdperson_tv.setText("制定人:"+datas.get(position).getUsername());
        return convertView;
    }
    class ViewHodler{
        public TextView beonduty_term_tv,beonduty_stauts_tv,beonduty_sttime_tv,beonduty_endtime_tv,beonduty_zdperson_tv;
    }
}
