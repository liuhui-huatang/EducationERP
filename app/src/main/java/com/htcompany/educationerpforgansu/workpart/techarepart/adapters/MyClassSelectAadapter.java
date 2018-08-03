package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.MyClassSelectEntity;

import java.util.List;

/**
 * 我的班级选择界面
 * Created by wrb on 2017/5/9.
 */
public class MyClassSelectAadapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MyClassSelectEntity> datas;
    public MyClassSelectAadapter(Context context,List<MyClassSelectEntity> datas){
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
            convertView=inflater.inflate(R.layout.importtitleselectadapter_item,null);
            vh.importitlselectortitile_tv = (TextView)convertView.findViewById(R.id.importitlselectortitile_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodelr) convertView.getTag();
        }
        vh.importitlselectortitile_tv.setText(datas.get(position).getName());
        return convertView;
    }
    class ViewHodelr{
        public TextView importitlselectortitile_tv;
    }
}
