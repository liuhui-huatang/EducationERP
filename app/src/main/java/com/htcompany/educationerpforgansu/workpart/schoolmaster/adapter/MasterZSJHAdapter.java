package com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.ZSJHEntity;

import java.util.List;

/**
 * 招生计划
 * Created by wrb on 2017/1/19.
 */
public class MasterZSJHAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ZSJHEntity> datas;
    public MasterZSJHAdapter(Context context, List<ZSJHEntity> datas){
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
            convertView = inflater.inflate(R.layout.master_zsjh_adapter_item,null);
            vh.masterzsjh_zy_tv=(TextView)convertView.findViewById(R.id.masterzsjh_zy_tv);
            vh.masterzsjh_jhzsrs_tv=(TextView)convertView.findViewById(R.id.masterzsjh_jhzsrs_tv);
            vh.masterzsjh_cc_tv=(TextView)convertView.findViewById(R.id.masterzsjh_cc_tv);
            vh.masterzsjh_sjzsrs_tv=(TextView)convertView.findViewById(R.id.masterzsjh_sjzsrs_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.masterzsjh_zy_tv.setText(datas.get(position).getTrbd_major());
        vh.masterzsjh_jhzsrs_tv.setText(datas.get(position).getPlan_num());
        vh.masterzsjh_cc_tv.setText(datas.get(position).getTrbd_unity());
        vh.masterzsjh_sjzsrs_tv.setText(datas.get(position).getShijinum());
        return convertView;
    }
    class ViewHodler{
        public TextView masterzsjh_zy_tv,masterzsjh_jhzsrs_tv,masterzsjh_cc_tv,masterzsjh_sjzsrs_tv;
    }
}
