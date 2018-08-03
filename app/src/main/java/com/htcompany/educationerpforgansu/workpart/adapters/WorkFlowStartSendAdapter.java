package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFlowStartSendEntity;

import java.util.List;

/**
 * 发起工作流适配器
 * Created by wrb on 2017/4/23.
 */
public class WorkFlowStartSendAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<WorkFlowStartSendEntity> datas;
    public WorkFlowStartSendAdapter(Context context,List<WorkFlowStartSendEntity> datas){
        this.context =context;
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
            convertView = inflater.inflate(R.layout.workflowstartsendadapter_item,null);
            vh.workflowsstartsendadapter_name_tv = (TextView)convertView.findViewById(R.id.workflowsstartsendadapter_name_tv);
            vh.workflowsstartsendadapter_type_tv = (TextView)convertView.findViewById(R.id.workflowsstartsendadapter_type_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.workflowsstartsendadapter_name_tv.setText(datas.get(position).getName());
        vh.workflowsstartsendadapter_type_tv.setText("流程名称:"+datas.get(position).getProcess_name());
        return convertView;
    }
    class ViewHodler{
        public TextView workflowsstartsendadapter_name_tv,workflowsstartsendadapter_type_tv;
    }
}
