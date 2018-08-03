package com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.JYZTitleEntity;

import java.util.List;

/**
 * 教研组考核主题适配器
 * Created by wrb on 2017/5/13.
 */
public class JYZTitleSelectAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<JYZTitleEntity> datas;
    public JYZTitleSelectAdapter(Context context,List<JYZTitleEntity> datas){
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
        vh.importitlselectortitile_tv.setText(datas.get(position).getLabel());
        return convertView;
    }
    class ViewHodelr{
        public TextView importitlselectortitile_tv;
    }
}
