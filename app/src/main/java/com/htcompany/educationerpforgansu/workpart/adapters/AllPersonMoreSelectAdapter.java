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
import com.htcompany.educationerpforgansu.workpart.entities.AllJZGEntity;

import java.util.List;

/**
 * 人员多选
 * Created by wrb on 2016/12/13.
 */
public class AllPersonMoreSelectAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<AllJZGEntity> datas;
    private Handler handler;
    public AllPersonMoreSelectAdapter(Context context,List<AllJZGEntity> datas,Handler handler){
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
        if(convertView==null){
            vh = new ViewHodler();
            convertView=inflater.inflate(R.layout.allpersonmoreselectadapter_item,null);
            vh.allpersonmoreselect_name_cb = (CheckBox)convertView.findViewById(R.id.allpersonmoreselect_name_cb);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        final AllJZGEntity entity = datas.get(position);
        vh.allpersonmoreselect_name_cb.setText(datas.get(position).getUsername());
        vh.allpersonmoreselect_name_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Message message=null;
                Bundle b =null;
                if(isChecked){
                    message = new Message();
                    b = new Bundle();
                    b.putSerializable("personEntity",entity);
                    message.what=101;
                    message.setData(b);
                }else{
                    message = new Message();
                    b = new Bundle();
                    b.putSerializable("personEntity",entity);
                    message.what=102;
                    message.setData(b);
                }
                handler.sendMessage(message);
            }
        });
        return convertView;
    }
    class ViewHodler{
        public CheckBox allpersonmoreselect_name_cb;
    }
}
