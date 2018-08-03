package com.htcompany.educationerpforgansu.workpart.dormpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageEntity;

import java.util.List;

/**
 * 卫生管理适配器
 * Created by wrb on 2016/11/23.
 */
public class DormSanitationManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<DormSanitationManageEntity> datas;
    public DormSanitationManageAdapter(Context context,List<DormSanitationManageEntity> datas){
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
            convertView = inflater.inflate(R.layout.dormsanitationmanageadapter_item,null);
            vh.dormsanitationmanageadapter_ssl_tv=(TextView)convertView.findViewById(R.id.dormsanitationmanageadapter_ssl_tv);
            vh.dormsanitationmanageadapter_time_tv=(TextView)convertView.findViewById(R.id.dormsanitationmanageadapter_time_tv);
            vh.dormsanitationmanageadapter_sroom_tv=(TextView)convertView.findViewById(R.id.dormsanitationmanageadapter_sroom_tv);
            vh.dormsanitationmanageadapter_source_tv=(TextView)convertView.findViewById(R.id.dormsanitationmanageadapter_source_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.dormsanitationmanageadapter_ssl_tv.setText("宿舍楼:"+datas.get(position).getSushe_lou());
        vh.dormsanitationmanageadapter_time_tv.setText(datas.get(position).getDa_data());
        vh.dormsanitationmanageadapter_sroom_tv.setText("宿舍:"+datas.get(position).getSushe_num());
        vh.dormsanitationmanageadapter_source_tv.setText(datas.get(position).getDa_allpoint());
        return convertView;
    }
    class ViewHodler{
        public TextView dormsanitationmanageadapter_ssl_tv,dormsanitationmanageadapter_time_tv,
                dormsanitationmanageadapter_sroom_tv,dormsanitationmanageadapter_source_tv;
    }
}
