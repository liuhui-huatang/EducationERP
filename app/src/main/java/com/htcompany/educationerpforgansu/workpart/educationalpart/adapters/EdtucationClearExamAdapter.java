package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.QKCJEntity;

import java.util.List;

/**
 * 清考成绩查询
 * Created by wrb on 2016/11/25.
 */
public class EdtucationClearExamAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<QKCJEntity> datas;
    public EdtucationClearExamAdapter(Context context,List<QKCJEntity> datas){
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
            convertView =inflater.inflate(R.layout.edtucationclearexamadapter_item,null);
            vh.jwglqkcj_xm_tv = (TextView)convertView.findViewById(R.id.jwglqkcj_xm_tv);
            vh.jwglqkcj_kc_tv = (TextView)convertView.findViewById(R.id.jwglqkcj_kc_tv);
            vh.jwglqkcj_cj_tv = (TextView)convertView.findViewById(R.id.jwglqkcj_cj_tv);
            vh.jwglqkcj_xh_tv = (TextView)convertView.findViewById(R.id.jwglqkcj_xh_tv);
            vh.jwglqkcj_jxb_tv = (TextView)convertView.findViewById(R.id.jwglqkcj_jxb_tv);
            vh.jwglqkcj_xq_tv = (TextView)convertView.findViewById(R.id.jwglqkcj_xq_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.jwglqkcj_xm_tv.setText(datas.get(position).getTruename());
        vh.jwglqkcj_kc_tv .setText("课程:"+datas.get(position).getCourceName());
        vh.jwglqkcj_cj_tv .setText("成绩:"+datas.get(position).getScore());
        vh.jwglqkcj_xh_tv.setText("学号:"+datas.get(position).getNumber());
        vh.jwglqkcj_jxb_tv .setText("班级:"+datas.get(position).getJxbname());
//        vh.jwglqkcj_xq_tv .setText(datas.get(position).getXq());
        return convertView;
    }
    class ViewHodler{
        public TextView jwglqkcj_xm_tv,jwglqkcj_kc_tv,jwglqkcj_cj_tv,jwglqkcj_xh_tv,jwglqkcj_jxb_tv,jwglqkcj_xq_tv;
    }
}
