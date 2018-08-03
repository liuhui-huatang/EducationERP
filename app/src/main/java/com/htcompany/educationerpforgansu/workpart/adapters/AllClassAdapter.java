package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;

import java.util.List;

/**
 * 班级适配器
 * Created by wrb on 2016/12/19.
 */
public class AllClassAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private Handler handler;
    private List<AllClassEntity> datas;
    public AllClassAdapter(Context context,List<AllClassEntity> datas,Handler handler){
        this.context = context;
        this.datas = datas;
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
            convertView = inflater.inflate(R.layout.alladdressadapter_item,null);
            vh.alladdress_hynam_cb = (CheckBox)convertView.findViewById(R.id.alladdress_hynam_cb);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        final AllClassEntity entity = datas.get(position);
        vh.alladdress_hynam_cb.setText(datas.get(position).getCname());
        vh.alladdress_hynam_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Message message=null;
                Bundle b =null;
                if(isChecked){
                    message = new Message();
                    b = new Bundle();
                    b.putSerializable("AllPartEntity",entity);
                    message.what=101;
                    message.setData(b);
                }else{
                    message = new Message();
                    b = new Bundle();
                    b.putSerializable("AllPartEntity",entity);
                    message.what=102;
                    message.setData(b);
                }
                handler.sendMessage(message);
            }
        });
        return convertView;
    }
    class ViewHodler{
        public CheckBox alladdress_hynam_cb;
    }
}