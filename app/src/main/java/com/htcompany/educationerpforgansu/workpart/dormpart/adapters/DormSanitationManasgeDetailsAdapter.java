package com.htcompany.educationerpforgansu.workpart.dormpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageSourceEntity;

import java.util.List;

/**
 * 宿舍得分想界面
 * Created by wrb on 2017/4/26.
 */
public class DormSanitationManasgeDetailsAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<DormSanitationManageSourceEntity> datas;
    public DormSanitationManasgeDetailsAdapter(Context context,List<DormSanitationManageSourceEntity> datas){
        this.context =context;
        this.datas = datas;
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
        ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView =inflater.inflate(R.layout.dormsanitationmanasgedetailsadapter_item,null);
            vh.sanaitationdetailn_name_tv=(TextView)convertView.findViewById(R.id.sanaitationdetailn_name_tv);
            vh.sanaitationdetailn_count_tv=(TextView)convertView.findViewById(R.id.sanaitationdetailn_count_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.sanaitationdetailn_name_tv.setText(datas.get(position).getLabel());
        vh.sanaitationdetailn_count_tv.setText(datas.get(position).getValue());
        return convertView;
    }
    class ViewHolder{
        public TextView sanaitationdetailn_name_tv,sanaitationdetailn_count_tv;
    }
}
