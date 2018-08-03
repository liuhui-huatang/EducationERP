package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.MakeupCjEntity;

import java.util.List;

/**
 * 补考成绩查询
 * Created by wrb on 2016/11/24.
 */
public class EducationMakeupCjAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MakeupCjEntity> datas;
    public EducationMakeupCjAdapter(Context context, List<MakeupCjEntity> datas ){
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
            convertView=inflater.inflate(R.layout.educationmakeupcjadapter_item,null);
            vh.educationmakeupcjadapter_name_tv=(TextView)convertView.findViewById(R.id.educationmakeupcjadapter_name_tv);
            vh.educationmakeupcjadapter_class_tv=(TextView)convertView.findViewById(R.id.educationmakeupcjadapter_class_tv);
            vh.educationmakeupcjadapter_jxb_tv=(TextView)convertView.findViewById(R.id.educationmakeupcjadapter_jxb_tv);
            vh.educationmakeupcjadapter_exam_tv=(TextView)convertView.findViewById(R.id.educationmakeupcjadapter_exam_tv);
            vh.educationmakeupcjadapter_number_tv=(TextView)convertView.findViewById(R.id.educationmakeupcjadapter_number_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.educationmakeupcjadapter_name_tv.setText(datas.get(position).getTruename());
        vh.educationmakeupcjadapter_class_tv.setText("课程:"+datas.get(position).getCourceName());
        vh.educationmakeupcjadapter_jxb_tv.setText("班级:"+datas.get(position).getJxbname());
        vh.educationmakeupcjadapter_exam_tv.setText("成绩:"+datas.get(position).getCourceName());
        vh.educationmakeupcjadapter_number_tv.setText("学号:"+datas.get(position).getNumber());
        return convertView;
    }
    class ViewHodler{
        public TextView educationmakeupcjadapter_name_tv,educationmakeupcjadapter_class_tv,
                educationmakeupcjadapter_jxb_tv,educationmakeupcjadapter_exam_tv,educationmakeupcjadapter_number_tv;
    }
}
