package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.AllCarsTypeEntity;

import java.util.List;

/**
 * 车辆类型适配器
 * Created by wrb on 2016/12/16.
 */
public class AllCarsTypeAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<AllCarsTypeEntity> datas;
    private Handler handler;
    public AllCarsTypeAdapter(Context context,List<AllCarsTypeEntity> datas,Handler handler){
        this.context = context;
        this.datas =datas;
        this.handler = handler;
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
        if (convertView==null){
            vh = new ViewHodler();
            convertView = inflater.inflate(R.layout.importtitleselectadapter_item,null);
            vh.importitlselectortitile_tv = (TextView)convertView.findViewById(R.id.importitlselectortitile_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        final AllCarsTypeEntity entity = datas.get(position);
        vh.importitlselectortitile_tv.setText(datas.get(position).getName()+"("+datas.get(position).getNumber()+")");
        return convertView;
    }
    class ViewHodler{
        public TextView importitlselectortitile_tv;
    }
}
