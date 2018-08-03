package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.AllTermsEntity;

import java.util.List;

/**
 * 学期适配器
 * Created by wrb on 2017/4/17.
 */
public class AllTermsAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<AllTermsEntity> datas;
    public AllTermsAdapter(Context context,List<AllTermsEntity> datas){
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
        if("1".equals(datas.get(position).getIscur())){
            vh.importitlselectortitile_tv.setTextColor(context.getResources().getColor(R.color.red));
        }
        vh.importitlselectortitile_tv.setText(datas.get(position).getLabel());
        return convertView;
    }
    class ViewHodelr{
        public TextView importitlselectortitile_tv;
    }
}
