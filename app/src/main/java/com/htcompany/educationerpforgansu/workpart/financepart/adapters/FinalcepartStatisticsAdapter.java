package com.htcompany.educationerpforgansu.workpart.financepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.StatisticsEntity;

import java.util.List;

/**
 * 费用统计适配器
 * Created by wrb on 2016/11/16.
 */
public class FinalcepartStatisticsAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<StatisticsEntity> datas;
    public FinalcepartStatisticsAdapter(Context context,List<StatisticsEntity> datas){
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
            convertView = inflater.inflate(R.layout.finalcepartstatisticsadapter_item,null);
            vh.statisticsadapter_zy_tv = (TextView)convertView.findViewById(R.id.statisticsadapter_zy_tv);
            vh.statisticsadapter_nj_tv = (TextView)convertView.findViewById(R.id.statisticsadapter_nj_tv);
            vh.statisticsadapter_xz_tv = (TextView)convertView.findViewById(R.id.statisticsadapter_xz_tv);
            vh.statisticsadapter_jxbgs_tv = (TextView)convertView.findViewById(R.id.statisticsadapter_jxbgs_tv);
            vh.statisticsadapter_ysmoney_tv = (TextView)convertView.findViewById(R.id.statisticsadapter_ysmoney_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.statisticsadapter_zy_tv.setText(datas.get(position).getMajor());
        vh.statisticsadapter_nj_tv.setText("年级:"+datas.get(position).getGrade());
        vh.statisticsadapter_xz_tv.setText("学制:"+datas.get(position).getLevels());
        vh.statisticsadapter_jxbgs_tv.setText("教学班个数:"+datas.get(position).getCount());
        vh.statisticsadapter_ysmoney_tv.setText("应收费:"+datas.get(position).getYingshou());
        return convertView;
    }
    class ViewHodler{
        public TextView statisticsadapter_zy_tv,statisticsadapter_nj_tv,statisticsadapter_xz_tv,
                statisticsadapter_jxbgs_tv,statisticsadapter_ysmoney_tv;
    }
}
