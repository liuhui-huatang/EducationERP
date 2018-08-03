package com.htcompany.educationerpforgansu.homepart.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.SalaryEntity;

import java.util.List;

/**
 * 我的工资适配器
 * Created by wrb on 2016/11/8.
 */
public class MySalariesAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<SalaryEntity> datas;
    public MySalariesAdapter(Context context,List<SalaryEntity> datas){
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler vh;
        if(convertView==null){
            vh= new ViewHodler();
            convertView = inflater.inflate(R.layout.home_salariesadapter_item,null);
            vh.home_salariesadapterbg_ll = (LinearLayout)convertView.findViewById(R.id.home_salariesadapterbg_ll);
            vh.home_salariesadapterbg_rel = (RelativeLayout) convertView.findViewById(R.id.home_salariesadapterbg_rel);
            vh.home_salariesmonth_tv = (TextView) convertView.findViewById(R.id.home_salariesmonth_tv);
            vh.home_salariesmoney_tv = (TextView) convertView.findViewById(R.id.home_salariesmoney_tv);
            vh.home_salarietime_tv = (TextView) convertView.findViewById(R.id.home_salarietime_tv);
            vh.home_salariescode_tv = (TextView) convertView.findViewById(R.id.home_salariescode_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        if(position==0){
            vh.home_salariesadapterbg_ll.setBackground(context.getResources().getDrawable(R.mipmap.home_sarays_bg1));
            vh.home_salariesadapterbg_rel.setBackground(context.getResources().getDrawable(R.mipmap.home_sarays_yuan1));
        }else{
            vh.home_salariesadapterbg_ll.setBackground(context.getResources().getDrawable(R.mipmap.home_sarays_bg2));
            vh.home_salariesadapterbg_rel.setBackground(context.getResources().getDrawable(R.mipmap.home_sarays_yuan2));
        }
        vh.home_salariesmonth_tv.setText(datas.get(position).getMonth());
        vh.home_salariesmoney_tv.setText(datas.get(position).getMoney());


        vh.home_salarietime_tv.setText("发放时间:"+datas.get(position).getTime());
        vh.home_salariescode_tv.setText("工资卡号:"+datas.get(position).getKahao());
        return convertView;
    }
    class ViewHodler{
        public LinearLayout home_salariesadapterbg_ll;
        public RelativeLayout home_salariesadapterbg_rel;
        public TextView home_salariesmonth_tv,home_salariesmoney_tv,home_salarietime_tv,home_salariescode_tv;
    }
}
