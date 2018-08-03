package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherRcshjcdjEntity;

import java.util.List;

/**
 * 日常事务检查登记适配器
 * Created by wrb on 2016/11/29.
 */
public class TeacherRcshjcdjAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeacherRcshjcdjEntity> datas;
    public TeacherRcshjcdjAdapter(Context context,List<TeacherRcshjcdjEntity> datas){
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
            convertView=inflater.inflate(R.layout.teacherrcshjcdjadapter_item,null);
            vh.teacherrcshjcdjadapter_term_tv=(TextView)convertView.findViewById(R.id.teacherrcshjcdjadapter_term_tv);
            vh.teacherrcshjcdjadapter_time_tv=(TextView)convertView.findViewById(R.id.teacherrcshjcdjadapter_time_tv);
            vh.teacherrcshjcdjadapter_cotent_tv=(TextView)convertView.findViewById(R.id.teacherrcshjcdjadapter_cotent_tv);
            vh.teacherrcshjcdjadapter_result_tv=(TextView)convertView.findViewById(R.id.teacherrcshjcdjadapter_result_tv);
            convertView.setTag(vh);
        }else {
            vh=(ViewHodler) convertView.getTag();
        }
        vh.teacherrcshjcdjadapter_term_tv.setText(datas.get(position).getTerm_name());
        vh.teacherrcshjcdjadapter_time_tv.setText("时间:"+datas.get(position).getTime());
        vh.teacherrcshjcdjadapter_cotent_tv.setText("检查内容:"+datas.get(position).getContent());
        vh.teacherrcshjcdjadapter_result_tv.setText("处理记录:"+datas.get(position).getQuestion());
        return convertView;
    }
    class ViewHodler{
        public TextView teacherrcshjcdjadapter_term_tv,teacherrcshjcdjadapter_time_tv,
                teacherrcshjcdjadapter_cotent_tv,teacherrcshjcdjadapter_result_tv;
     }
}
