package com.htcompany.educationerpforgansu.workpart.exampart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExamProjectEntity;

import java.util.List;

/**
 * 考试项目适配器
 * Created by wrb on 2017/5/8.
 */
public class ExamProjectAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ExamProjectEntity> datas;
    public ExamProjectAdapter(Context context,List<ExamProjectEntity> datas){
        this.context =context;
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
        ViewHodelr vh;
        if(convertView==null){
            vh = new ViewHodelr();
            convertView=inflater.inflate(R.layout.examproject_adapter_item,null);
            vh.examproject_adapter_tv = (TextView)convertView.findViewById(R.id.examproject_adapter_tv);
            vh.examproject_adapter_img=(ImageView)convertView.findViewById(R.id.examproject_adapter_img);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodelr) convertView.getTag();
        }
        vh.examproject_adapter_tv.setText(datas.get(position).getName());
        int viewIndex = position% FunctionDatas.getKEMUICON_Datas().size();
        vh.examproject_adapter_img.setImageResource(FunctionDatas.getKEMUICON_Datas().get(viewIndex));
        return convertView;
    }
    class ViewHodelr{
        public TextView examproject_adapter_tv;
        public ImageView examproject_adapter_img;
    }
}
