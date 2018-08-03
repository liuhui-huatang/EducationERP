package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherPXEntity;

import java.util.List;

/**
 * 教师部分——教师培训适配器
 * Created by wrb on 2016/12/19.
 */
public class TeacherTrainAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeacherPXEntity> datas;
    public TeacherTrainAdapter(Context context,List<TeacherPXEntity> datas){
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
            convertView =inflater.inflate(R.layout.ieachertrainadapter_item,null);
            vh.teacherpx_xmmc_tv = (TextView)convertView.findViewById(R.id.teacherpx_xmmc_tv);
            vh.teacherpx_ssjh_tv = (TextView)convertView.findViewById(R.id.teacherpx_ssjh_tv);
            vh.teacherpx_kssj_tv = (TextView)convertView.findViewById(R.id.teacherpx_kssj_tv);
            vh.teacherpx_jssj_tv = (TextView)convertView.findViewById(R.id.teacherpx_jssj_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.teacherpx_xmmc_tv.setText(datas.get(position).getPei_name());
        vh.teacherpx_ssjh_tv.setText("所属计划:"+datas.get(position).getName());
        vh.teacherpx_kssj_tv.setText("开始时间:"+datas.get(position).getPei_atime());
        vh.teacherpx_jssj_tv.setText("结束时间:"+datas.get(position).getPei_btime());
        return convertView;
    }
    class ViewHodler{
        public TextView teacherpx_xmmc_tv,teacherpx_ssjh_tv,teacherpx_kssj_tv,teacherpx_jssj_tv;
    }
}
