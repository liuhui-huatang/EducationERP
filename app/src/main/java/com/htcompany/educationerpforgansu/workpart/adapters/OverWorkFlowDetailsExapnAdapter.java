package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.OverFlowItemEntity;
import com.htcompany.educationerpforgansu.workpart.entities.OverFlowStepEntity;

import java.util.List;

/**
 * 待办工作流适配器
 * Created by wrb on 2017/4/23.
 */
public class OverWorkFlowDetailsExapnAdapter extends BaseExpandableListAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<OverFlowStepEntity> parentDatas;
    private List<List<OverFlowItemEntity>> childDatas;
    public OverWorkFlowDetailsExapnAdapter(Context context,List<OverFlowStepEntity> parentDatas,List<List<OverFlowItemEntity>> childDatas){
        this.context = context;
        this.parentDatas = parentDatas;
        this.childDatas =childDatas;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return parentDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childDatas.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childDatas.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder pvh;
        if(convertView==null){
            pvh = new ParentViewHolder();
            convertView=inflater.inflate(R.layout.residmenu_left_child_item,null);
            pvh.childitem_name_tv = (TextView)convertView.findViewById(R.id.childitem_name_tv);
            pvh.childitem_statius_tv = (TextView)convertView.findViewById(R.id.childitem_statius_tv);
            convertView.setTag(pvh);
        }else{
            pvh = (ParentViewHolder) convertView.getTag();
        }
        pvh.childitem_name_tv.setText(parentDatas.get(groupPosition).getName());
        pvh.childitem_statius_tv.setText(parentDatas.get(groupPosition).getStatus());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder cvh;
        if(convertView==null){
            cvh=new ChildViewHolder();
            convertView=inflater.inflate(R.layout.residmenu_left_parent_item,null);
            cvh.overworkdetialschilditem_name_tv = (TextView)convertView.findViewById(R.id.overworkdetialschilditem_name_tv);
            cvh.overworkdetialschilditem_status_tv = (TextView)convertView.findViewById(R.id.overworkdetialschilditem_status_tv);
            cvh.overworkdetialschilditem_jg_tv = (TextView)convertView.findViewById(R.id.overworkdetialschilditem_jg_tv);
            cvh.overworkdetialschilditem_time_tv = (TextView)convertView.findViewById(R.id.overworkdetialschilditem_time_tv);
            cvh.overworkdetialschilditem_postion_tv = (TextView)convertView.findViewById(R.id.overworkdetialschilditem_postion_tv);
            convertView.setTag(cvh);
        }else{
            cvh = (ChildViewHolder) convertView.getTag();
        }
        if(childDatas.get(groupPosition)!=null&&childDatas.get(groupPosition).size()>0) {
            cvh.overworkdetialschilditem_name_tv.setText("姓名:" + childDatas.get(groupPosition).get(childPosition).getUsername());
            cvh.overworkdetialschilditem_status_tv.setText("状态:" + childDatas.get(groupPosition).get(childPosition).getStatus());
            cvh.overworkdetialschilditem_jg_tv.setText("机构:" + childDatas.get(groupPosition).get(childPosition).getOrg());
            cvh.overworkdetialschilditem_time_tv.setText("办理时间:" + childDatas.get(groupPosition).get(childPosition).getBltime());
            cvh.overworkdetialschilditem_postion_tv.setText(childDatas.get(groupPosition).get(childPosition).getBlyj());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public class ParentViewHolder{
        public TextView childitem_name_tv,childitem_statius_tv;
    }
    public class ChildViewHolder{
        public TextView overworkdetialschilditem_name_tv,overworkdetialschilditem_status_tv,
                overworkdetialschilditem_jg_tv,overworkdetialschilditem_time_tv,overworkdetialschilditem_postion_tv;
    }
}
