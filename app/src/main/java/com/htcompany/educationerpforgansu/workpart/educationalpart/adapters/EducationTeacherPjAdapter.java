package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.TeacherPJEntity;

import java.util.List;

/**
 * 教师评教管理
 * Created by wrb on 2016/11/25.
 */
public class EducationTeacherPjAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeacherPJEntity> datas;
    public EducationTeacherPjAdapter(Context context,List<TeacherPJEntity> datas){
        this.context = context;
        this.datas = datas;
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
            convertView = inflater.inflate(R.layout.educationteacherpjadapter_item,null);
            vh.japjjl_xq_tv = (TextView)convertView.findViewById(R.id.japjjl_xq_tv);
            vh.japjjl_bpjjs_tv = (TextView)convertView.findViewById(R.id.japjjl_bpjjs_tv);
            vh.japjjl_pjjs_tv = (TextView)convertView.findViewById(R.id.japjjl_pjjs_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.japjjl_xq_tv .setText("学期:"+datas.get(position).getXq());
        vh.japjjl_bpjjs_tv.setText("受评教师:"+datas.get(position).getBpjs());
        vh.japjjl_pjjs_tv.setText("评教教师:"+datas.get(position).getPjjs());
        return convertView;
    }
    class ViewHodler{
        public TextView japjjl_xq_tv,japjjl_bpjjs_tv,japjjl_pjjs_tv;
    }
}
