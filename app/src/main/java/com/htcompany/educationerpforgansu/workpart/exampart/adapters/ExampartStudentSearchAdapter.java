package com.htcompany.educationerpforgansu.workpart.exampart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExampartStudentSearchEntity;

import java.util.List;

/**
 * 学生考场信息查询适配器
 * Created by wrb on 2016/11/24.
 */
public class ExampartStudentSearchAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ExampartStudentSearchEntity> datas;
    public ExampartStudentSearchAdapter(Context context,List<ExampartStudentSearchEntity> datas){
        this.context=context;
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
            convertView=inflater.inflate(R.layout.exampartstudentsearchadapter_item,null);
            vh.exampartstudentsearchadapter_name_tv=(TextView)convertView.findViewById(R.id.exampartstudentsearchadapter_name_tv);
            vh.exampartstudentsearchadapter_zwh_tv=(TextView)convertView.findViewById(R.id.exampartstudentsearchadapter_zwh_tv);
            vh.exampartstudentsearchadapter_kc_tv=(TextView)convertView.findViewById(R.id.exampartstudentsearchadapter_kc_tv);
            vh.exampartstudentsearchadapter_jkjs_tv=(TextView)convertView.findViewById(R.id.exampartstudentsearchadapter_jkjs_tv);
            vh.exampartstudentsearchadapter_jxb_tv=(TextView)convertView.findViewById(R.id.exampartstudentsearchadapter_jxb_tv);
            vh.exampartstudentsearchadapter_xh_tv=(TextView)convertView.findViewById(R.id.exampartstudentsearchadapter_xh_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.exampartstudentsearchadapter_name_tv.setText(datas.get(position).getTruename());
        vh.exampartstudentsearchadapter_zwh_tv.setText("座位号:"+datas.get(position).getZuoci());
        vh.exampartstudentsearchadapter_kc_tv.setText("考场:"+datas.get(position).getKaochang());
        vh.exampartstudentsearchadapter_jkjs_tv.setText("监考教师:"+datas.get(position).getTeachername());
        vh.exampartstudentsearchadapter_jxb_tv.setText("教学班:"+datas.get(position).getJxbname());
        vh.exampartstudentsearchadapter_xh_tv.setText("学号:"+datas.get(position).getNumber());
        return convertView;
    }
    class ViewHodler{
        public TextView exampartstudentsearchadapter_name_tv,exampartstudentsearchadapter_zwh_tv,
                exampartstudentsearchadapter_kc_tv,exampartstudentsearchadapter_jkjs_tv,
                exampartstudentsearchadapter_jxb_tv,exampartstudentsearchadapter_xh_tv;
    }
}
