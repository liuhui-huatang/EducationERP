package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationTeacherTrainEnity;

import java.util.List;

/**
 * 培训计划管理
 * Created by wrb on 2016/11/25.
 */
public class EducationTeacherTrainingAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<EducationTeacherTrainEnity> datas;
    public EducationTeacherTrainingAdapter(Context context,List<EducationTeacherTrainEnity> datas){
        this.context = context;
        this.datas= datas;
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
            convertView=inflater.inflate(R.layout.educationteachertraining_adapter,null);
            vh.educationteachertraining_name_tv=(TextView)convertView.findViewById(R.id.educationteachertraining_name_tv);
            vh.educationteachertraining_plain_tv=(TextView)convertView.findViewById(R.id.educationteachertraining_plain_tv);
            vh.educationteachertraining_didian_tv=(TextView)convertView.findViewById(R.id.educationteachertraining_didian_tv);
            vh.educationteachertraining_xh_tv=(TextView)convertView.findViewById(R.id.educationteachertraining_xh_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.educationteachertraining_name_tv.setText(datas.get(position).getPei_name());
        vh.educationteachertraining_plain_tv.setText("所属计划:"+datas.get(position).getName());
        vh.educationteachertraining_didian_tv.setText("地点:"+datas.get(position).getPei_jidi());
        vh.educationteachertraining_xh_tv.setText("序号:"+datas.get(position).getPei_xuhao());
        return convertView;
    }
    class ViewHodler{
        public TextView educationteachertraining_name_tv,educationteachertraining_plain_tv,
                educationteachertraining_didian_tv,educationteachertraining_xh_tv;
    }
}
