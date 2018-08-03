package com.htcompany.educationerpforgansu.workpart.dormpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormDisciplineEntity;

import java.util.List;

/**
 * 违纪情况适配器
 * Created by wrb on 2016/11/23.
 */
public class DormDisciplineSearchAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<DormDisciplineEntity> datas;
    public DormDisciplineSearchAdapter(Context context,List<DormDisciplineEntity> datas){
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
            convertView=inflater.inflate(R.layout.dormdisciplinesearchadapter_item,null);
            vh.dormdisciplinesearchadapter_name_tv=(TextView)convertView.findViewById(R.id.dormdisciplinesearchadapter_name_tv);
            vh.dormdisciplinesearchadapter_time_tv=(TextView)convertView.findViewById(R.id.dormdisciplinesearchadapter_time_tv);
            vh.dormdisciplinesearchadapter_adress_tv=(TextView)convertView.findViewById(R.id.dormdisciplinesearchadapter_adress_tv);
            vh.dormdisciplinesearchadapter_bed_tv=(TextView)convertView.findViewById(R.id.dormdisciplinesearchadapter_bed_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.dormdisciplinesearchadapter_name_tv.setText(datas.get(position).getTruename());
        vh.dormdisciplinesearchadapter_time_tv.setText(datas.get(position).getDate());
        String pweizhi="";
        if(!"".equals(datas.get(position).getRoom_category_id())&&datas.get(position).getRoom_category_id()!=null&&!"null".equals(datas.get(position).getRoom_category_id())){
            pweizhi=datas.get(position).getRoom_category_id();
        }
        if(!"".equals(datas.get(position).getRoomNum())&&datas.get(position).getRoomNum()!=null&&!"null".equals(datas.get(position).getRoomNum())){
            pweizhi=pweizhi+datas.get(position).getRoomNum();
        }
        vh.dormdisciplinesearchadapter_adress_tv.setText(pweizhi);
        if(!"".equals(datas.get(position).getBed())&&datas.get(position).getBed()!=null&&!"null".equals(datas.get(position).getBed())){
            vh.dormdisciplinesearchadapter_bed_tv.setText(datas.get(position).getBed()+"号床位");

        }else {
            vh.dormdisciplinesearchadapter_bed_tv.setText("");

        }
        return convertView;
    }
    class ViewHodler{
        public TextView dormdisciplinesearchadapter_name_tv,dormdisciplinesearchadapter_time_tv,
                dormdisciplinesearchadapter_adress_tv,dormdisciplinesearchadapter_bed_tv;
    }
}
