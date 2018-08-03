package com.htcompany.educationerpforgansu.workpart.exampart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExampartInvigilateEntity;

import java.util.List;

/**
 * 监考信息查询适配器
 * Created by wrb on 2016/11/24.
 */
public class ExampartInvigilateAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ExampartInvigilateEntity> datas;
    public ExampartInvigilateAdapter(Context context,List<ExampartInvigilateEntity> datas){
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
        ViewHoder vh;
        if(convertView==null){
            vh =new ViewHoder();
            convertView=inflater.inflate(R.layout.exampartinvigilateadapter_item,null);
            vh.exampartinvigilateadapter_kc_tv=(TextView)convertView.findViewById(R.id.exampartinvigilateadapter_kc_tv);
            vh.exampartinvigilateadapter_jkjs_tv=(TextView)convertView.findViewById(R.id.exampartinvigilateadapter_jkjs_tv);
            vh.exampartinvigilateadapter_date_tv=(TextView)convertView.findViewById(R.id.exampartinvigilateadapter_date_tv);
            vh.exampartinvigilateadapter_kscc_tv=(TextView)convertView.findViewById(R.id.exampartinvigilateadapter_kscc_tv);
            vh.exampartinvigilateadapter_gh_tv=(TextView)convertView.findViewById(R.id.exampartinvigilateadapter_gh_tv);
            vh.exampartinvigilateadapter_jiaoshi_tv=(TextView)convertView.findViewById(R.id.exampartinvigilateadapter_jiaoshi_tv);
            vh.exampartinvigilateadapter_time_tv=(TextView)convertView.findViewById(R.id.exampartinvigilateadapter_time_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHoder) convertView.getTag();
        }
        vh.exampartinvigilateadapter_kc_tv.setText("考场:"+datas.get(position).getKaochang());
        vh.exampartinvigilateadapter_jkjs_tv.setText("监考教师:"+datas.get(position).getUsername());
        vh.exampartinvigilateadapter_date_tv.setText("考试日期:"+datas.get(position).getCctime());
        vh.exampartinvigilateadapter_kscc_tv.setText("场次:"+datas.get(position).getChangci());
        vh.exampartinvigilateadapter_gh_tv.setText("工号:"+datas.get(position).getWorker_number());
        vh.exampartinvigilateadapter_jiaoshi_tv.setText("教室:"+datas.get(position).getJiaoshi());
        vh.exampartinvigilateadapter_time_tv.setText("考试时间:"+datas.get(position).getCcstart()+"-"+datas.get(position).getCcend());
        return convertView;
    }
    class ViewHoder{
        public TextView exampartinvigilateadapter_kc_tv,exampartinvigilateadapter_jkjs_tv,
                exampartinvigilateadapter_date_tv,exampartinvigilateadapter_kscc_tv,
                exampartinvigilateadapter_gh_tv,exampartinvigilateadapter_jiaoshi_tv,
                exampartinvigilateadapter_time_tv;
    }
}
