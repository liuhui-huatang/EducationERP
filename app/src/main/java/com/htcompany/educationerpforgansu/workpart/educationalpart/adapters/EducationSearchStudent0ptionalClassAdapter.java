package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.XXClassEntity;

import java.util.List;

/**
 * 选修课学生查询适配器
 * Created by wrb on 2016/11/21.
 */
public class EducationSearchStudent0ptionalClassAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<XXClassEntity> datas;
    public EducationSearchStudent0ptionalClassAdapter(Context context,List<XXClassEntity> datas){
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
        if(convertView == null){
            vh = new ViewHodler();
            convertView = inflater.inflate(R.layout.educationsearchstudentoptionalclassadapter_item,null);
            vh.soptionclassadpater_name_tv = (TextView)convertView.findViewById(R.id.soptionclassadpater_name_tv);
            vh.soptionclassadpater_bmr_tv = (TextView)convertView.findViewById(R.id.soptionclassadpater_bmr_tv);
            vh.soptionclassadpater_bmrxh_tv = (TextView)convertView.findViewById(R.id.soptionclassadpater_bmrxh_tv);
            vh.soptionclassadpater_bmrbj_tv = (TextView)convertView.findViewById(R.id.soptionclassadpater_bmrbj_tv);
            vh.soptionclassadpater_bmtime_tv = (TextView)convertView.findViewById(R.id.soptionclassadpater_bmtime_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.soptionclassadpater_name_tv.setText(datas.get(position).getTrbd_cource());
        vh.soptionclassadpater_bmr_tv.setText("姓名:"+datas.get(position).getTruename());
        vh.soptionclassadpater_bmrxh_tv.setText("学号:"+datas.get(position).getNumber());
        vh.soptionclassadpater_bmrbj_tv.setText("教学班:"+datas.get(position).getJxbname());
        vh.soptionclassadpater_bmtime_tv.setText("报名时间:"+datas.get(position).getTrbd_bmsj());
        return convertView;
    }
    class ViewHodler{
        public TextView soptionclassadpater_name_tv,soptionclassadpater_bmr_tv,
                soptionclassadpater_bmrxh_tv,soptionclassadpater_bmrbj_tv,soptionclassadpater_bmtime_tv;
    }
}
