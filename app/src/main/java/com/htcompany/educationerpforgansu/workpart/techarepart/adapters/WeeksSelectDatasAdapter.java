package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.htcompany.educationerpforgansu.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 周次选择适配器
 * Created by wrb on 2017/4/22.
 */
public class WeeksSelectDatasAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<String> datas;
    public  Map<Integer,Boolean> map=new HashMap<Integer,Boolean>();// 存放已被选中的CheckBox
    public WeeksSelectDatasAdapter(Context context,List<String> datas){
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHodler vh;
        if (convertView==null){
            vh = new ViewHodler();
            convertView = inflater.inflate(R.layout.alladdressadapter_item,null);
            vh.alladdress_hynam_cb = (CheckBox)convertView.findViewById(R.id.alladdress_hynam_cb);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        final String str = datas.get(position);
        vh.alladdress_hynam_cb.setText(datas.get(position));

        vh.alladdress_hynam_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    map.put(position,true);
                }else {
                    map.remove(position);
                }
            }
        });

        if(map!=null&&map.containsKey(position)){
            vh.alladdress_hynam_cb.setChecked(true);
        }else {
            vh.alladdress_hynam_cb.setChecked(false);
        }

        return convertView;
    }
    class ViewHodler{
        public CheckBox alladdress_hynam_cb;
    }
}
