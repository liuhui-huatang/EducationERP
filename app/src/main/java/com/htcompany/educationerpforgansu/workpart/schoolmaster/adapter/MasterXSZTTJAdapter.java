package com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.MasterXSZTEntity;

import java.util.List;

/**\
 * 学生状态统计适配器
 * Created by wrb on 2017/4/20.
 */
public class MasterXSZTTJAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MasterXSZTEntity> datas;
    public MasterXSZTTJAdapter(Context context,List<MasterXSZTEntity> datas){
        this.context = context;
        this.datas=datas;
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
            convertView=inflater.inflate(R.layout.master_xszttjadapter_item,null);
            vh.xszttjadapter_name_tv=(TextView)convertView.findViewById(R.id.xszttjadapter_name_tv);
            vh.xszttjadapter_count_tv=(TextView)convertView.findViewById(R.id.xszttjadapter_count_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.xszttjadapter_name_tv.setText(datas.get(position).getName());
        vh.xszttjadapter_count_tv.setText(datas.get(position).getValue()+"人");
        return convertView;
    }
    class ViewHodler{
        public TextView xszttjadapter_name_tv,xszttjadapter_count_tv;
    }
}
