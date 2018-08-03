package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.homepart.entity.PersonalAssessmentEntity;

import java.util.List;

/**
 * 个人考核适配器
 * Created by wrb on 2016/11/8.
 */
public class PersonalAssessmentAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<PersonalAssessmentEntity> datas;
    public PersonalAssessmentAdapter(Context context,List<PersonalAssessmentEntity> datas){
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
            convertView=inflater.inflate(R.layout.personalassessment_lv_item,null);
            vh.personalassessment_thme_tv=(TextView)convertView.findViewById(R.id.personalassessment_thme_tv);
            vh.personalassessment_zpzfcount_tv=(TextView)convertView.findViewById(R.id.personalassessment_zpzfcount_tv);
            vh.personalassessment_kpzfcount_tv=(TextView)convertView.findViewById(R.id.personalassessment_kpzfcount_tv);
            vh.personalassessment_myf_pb=(ProgressBar) convertView.findViewById(R.id.personalassessment_myf_pb);
            vh.personalassessment_kpf_pb=(ProgressBar) convertView.findViewById(R.id.personalassessment_kpf_pb);
            convertView.setTag(vh);
        }else{
            vh=(ViewHodler) convertView.getTag();
        }
        vh.personalassessment_thme_tv.setText(datas.get(position).getTheme());
        vh.personalassessment_zpzfcount_tv.setText(datas.get(position).getSelf_score()+"分");
        vh.personalassessment_kpzfcount_tv.setText(datas.get(position).getTotal_score()+"分");
        vh.personalassessment_myf_pb.setProgress(Integer.valueOf(datas.get(position).getSelf_score()));
        vh.personalassessment_kpf_pb.setProgress(Integer.valueOf(datas.get(position).getTotal_score()));
        return convertView;
    }
    class ViewHodler{
        public TextView personalassessment_thme_tv,personalassessment_zpzfcount_tv,personalassessment_kpzfcount_tv;
        public ProgressBar personalassessment_myf_pb,personalassessment_kpf_pb;
    }
}
