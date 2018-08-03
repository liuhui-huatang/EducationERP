package com.htcompany.educationerpforgansu.homepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.homepart.entity.MyRewardsEntity;

import java.util.List;

/**
 * 我的奖励记录适`配器
 * Created by wrb on 2016/11/8.
 */
public class MyRewardsFragmentAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MyRewardsEntity> datas;
    public MyRewardsFragmentAdapter(Context context,List<MyRewardsEntity> datas){
        this.context= context;
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
            convertView = inflater.inflate(R.layout.myrewardsfragment_lv_item,null);
            vh.myrewardsitem_title_tv = (TextView)convertView.findViewById(R.id.myrewardsitem_title_tv);
            vh.myrewardsitem_name_tv = (TextView)convertView.findViewById(R.id.myrewardsitem_name_tv);
            vh.myrewardsitem_content_tv = (TextView)convertView.findViewById(R.id.myrewardsitem_content_tv);
            vh.myrewardsitem_time_tv = (TextView)convertView.findViewById(R.id.myrewardsitem_time_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.myrewardsitem_title_tv.setText("获奖项目:"+datas.get(position).getProject());
        vh.myrewardsitem_name_tv.setText(datas.get(position).getUsername());
        vh.myrewardsitem_content_tv.setText(datas.get(position).getGrade());
        vh.myrewardsitem_time_tv.setText("获奖时间:"+datas.get(position).getTime());
        return convertView;
    }
    class ViewHodler{
        public TextView myrewardsitem_title_tv,myrewardsitem_name_tv,myrewardsitem_content_tv,myrewardsitem_time_tv;
    }
}
