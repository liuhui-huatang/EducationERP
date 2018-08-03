package com.htcompany.educationerpforgansu.homepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.homepart.entity.MyPunishmentEntity;

import java.util.List;

/**
 * 惩罚记录适配器
 * Created by wrb on 2016/11/8.
 */
public class MyPunishmentFragmentAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MyPunishmentEntity> datas;
    public MyPunishmentFragmentAdapter(Context context,List<MyPunishmentEntity> datas){
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
            convertView=inflater.inflate(R.layout.mypunishmentfragment_lv_item,null);
            vh.mypunishmentitem_title_tv = (TextView)convertView.findViewById(R.id.mypunishmentitem_title_tv);
            vh.mypunishmentitem_name_tv = (TextView)convertView.findViewById(R.id.mypunishmentitem_name_tv);
            vh.mypunishmentitem_content_tv = (TextView)convertView.findViewById(R.id.mypunishmentitem_content_tv);
            vh.mypunishmentitem_time_tv = (TextView)convertView.findViewById(R.id.mypunishmentitem_time_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.mypunishmentitem_title_tv.setText("惩处事件:"+datas.get(position).getName());
        vh.mypunishmentitem_name_tv.setText("惩处人:"+datas.get(position).getUsername());
        vh.mypunishmentitem_content_tv.setText("内容:"+datas.get(position).getContent());
        vh.mypunishmentitem_time_tv.setText("惩处时间:"+datas.get(position).getTime());
        return convertView;
    }
    class ViewHodler{
        public TextView mypunishmentitem_title_tv,mypunishmentitem_name_tv,mypunishmentitem_content_tv,mypunishmentitem_time_tv;
    }
}
