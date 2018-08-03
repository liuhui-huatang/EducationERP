package com.htcompany.educationerpforgansu.workpart.dormpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.AppraisingManageEntity;

import java.util.List;

/**
 * 评优适配器
 * Created by wrb on 2016/11/23.
 */
public class DormAppraisingManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<AppraisingManageEntity> datas;
    public DormAppraisingManageAdapter(Context context,List<AppraisingManageEntity> datas){
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
        return position;
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
            convertView=inflater.inflate(R.layout.dormappraisingmanageadapter_item,null);
            vh.dormappraisingmanageadapter_count_tv=(TextView)convertView.findViewById(R.id.dormappraisingmanageadapter_count_tv);
            vh.dormappraisingmanageadapter_ss_tv=(TextView)convertView.findViewById(R.id.dormappraisingmanageadapter_ss_tv);
            vh.dormappraisingmanageadapter_jx_tv=(TextView)convertView.findViewById(R.id.dormappraisingmanageadapter_jx_tv);
            vh.dormappraisingmanageadapter_qy_tv=(TextView)convertView.findViewById(R.id.dormappraisingmanageadapter_qy_tv);
            vh.dormappraisingmanageadapter_date_tv=(TextView)convertView.findViewById(R.id.dormappraisingmanageadapter_date_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.dormappraisingmanageadapter_count_tv.setText(datas.get(position).getPoint());
        vh.dormappraisingmanageadapter_ss_tv.setText(datas.get(position).getRoom());
        vh.dormappraisingmanageadapter_jx_tv.setText(datas.get(position).getShow_prize_id());
        vh.dormappraisingmanageadapter_qy_tv.setText(datas.get(position).getShow_area_id());
        vh.dormappraisingmanageadapter_date_tv.setText(datas.get(position).getDate());
        return convertView;
    }
    class ViewHodler{
        public TextView dormappraisingmanageadapter_count_tv,dormappraisingmanageadapter_ss_tv,
                dormappraisingmanageadapter_jx_tv,dormappraisingmanageadapter_qy_tv,
                dormappraisingmanageadapter_date_tv;
    }
}
