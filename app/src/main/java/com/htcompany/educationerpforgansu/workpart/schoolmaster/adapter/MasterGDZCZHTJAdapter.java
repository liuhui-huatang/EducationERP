package com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.MasterGDZCZHTJEntity;

import java.util.List;

/**
 * 固定资产统计适配器
 * Created by wrb on 2017/5/9.
 */
public class MasterGDZCZHTJAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MasterGDZCZHTJEntity> datas;
    public MasterGDZCZHTJAdapter(Context context,List<MasterGDZCZHTJEntity> datas){
        this.context =context;
        this.datas=datas;
        inflater =LayoutInflater.from(context);
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
            convertView=inflater.inflate(R.layout.master_gdzctj_adapteritem,null);
            vh.mastergdzctj_name_tv=(TextView)convertView.findViewById(R.id.mastergdzctj_name_tv);
            vh.mastergdzctj_number_tv=(TextView)convertView.findViewById(R.id.mastergdzctj_number_tv);
            convertView.setTag(vh);
        }else{
            vh=(ViewHodler)convertView.getTag();
        }
        vh.mastergdzctj_name_tv.setText(datas.get(position).getTypeName());
        vh.mastergdzctj_number_tv.setText(datas.get(position).getNum());
        return convertView;
    }
    class ViewHodler{
        public TextView mastergdzctj_name_tv,mastergdzctj_number_tv;
    }
}
