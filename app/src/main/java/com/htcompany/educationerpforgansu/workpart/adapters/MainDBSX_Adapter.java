package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.WaitFlowEntity;

import java.util.List;

/**
 * 主页待办事项列表
 * Created by wrb on 2017/1/9.
 */
public class MainDBSX_Adapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<WaitFlowEntity> datas;
    public MainDBSX_Adapter(Context context,List<WaitFlowEntity> datas){
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
            convertView = inflater.inflate(R.layout.maingd_lv_item,null);
            vh.mainlv_title_tv = (TextView)convertView.findViewById(R.id.mainlv_title_tv);
            vh.mainlv_time_tv = (TextView)convertView.findViewById(R.id.mainlv_time_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler)convertView.getTag();
        }
        vh.mainlv_title_tv.setText(datas.get(position).getTitle());
        vh.mainlv_time_tv.setText(datas.get(position).getStart_time());
        return convertView;
    }
    class ViewHodler{
        public TextView mainlv_title_tv,mainlv_time_tv;
    }
}
