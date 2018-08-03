package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.WaitFlowEntity;

import java.util.List;

/**
 * 待办事项列表适配器
 * Created by wrb on 2017/1/6.
 */
public class WorkFlowAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<WaitFlowEntity> datas;
    public WorkFlowAdapter(Context context,List<WaitFlowEntity> datas){
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
            convertView = inflater.inflate(R.layout.workflowadapter_item,null);
            vh.workflowadapter_title_tv = (TextView)convertView.findViewById(R.id.workflowadapter_title_tv);
            vh.workflowadapter_status_tv = (TextView)convertView.findViewById(R.id.workflowadapter_status_tv);
            vh.workflowadapter_fqr_tv = (TextView)convertView.findViewById(R.id.workflowadapter_fqr_tv);
            vh.workflowadapter_time_tv = (TextView)convertView.findViewById(R.id.workflowadapter_time_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler)convertView.getTag();
        }
        vh.workflowadapter_title_tv.setText(datas.get(position).getTitle());
        if("办理中".equals(datas.get(position).getRunstatus_n())){
            vh.workflowadapter_status_tv.setTextColor(Color.parseColor("#ff8e43"));
        }else if("办理不通过".equals(datas.get(position).getRunstatus_n())){
            vh.workflowadapter_status_tv.setTextColor(Color.parseColor("#ff6d6d"));
        }else if("办理结束".equals(datas.get(position).getRunstatus_n())){
            vh.workflowadapter_status_tv.setTextColor(Color.parseColor("#44c15c"));
        }
        vh.workflowadapter_status_tv.setText(datas.get(position).getRunstatus_n());
        vh.workflowadapter_fqr_tv.setText("发起人:"+datas.get(position).getStart_uname());
        vh.workflowadapter_time_tv.setText(datas.get(position).getRunendtime());
        return convertView;
    }
    class ViewHodler{
        public TextView workflowadapter_title_tv,workflowadapter_status_tv,workflowadapter_fqr_tv,workflowadapter_time_tv;
    }
}
