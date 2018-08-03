package com.htcompany.educationerpforgansu.workpart.dormpart.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageSourceEntity;

import java.util.List;

/**
 * 评分项输入
 * Created by WRB on 2017/4/26.
 */
public class DormSanitationAddAddAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<DormSanitationManageSourceEntity> datas;
    public DormSanitationAddAddAdapter(Context context, List<DormSanitationManageSourceEntity> datas){
        this.context =context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView =inflater.inflate(R.layout.dormsanitatioaddaddadapter_item,null);
            vh.dormsanitatioaddaddadapter_name_tv=(TextView)convertView.findViewById(R.id.dormsanitatioaddaddadapter_name_tv);
            vh.dormsanitatioaddaddadapter_count_tv=(EditText)convertView.findViewById(R.id.dormsanitatioaddaddadapter_count_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.dormsanitatioaddaddadapter_name_tv.setText(datas.get(position).getLabel());
        vh.dormsanitatioaddaddadapter_count_tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               String source = vh.dormsanitatioaddaddadapter_count_tv.getText().toString();
                if (!"".equals(source)) {
                    datas.get(position).setSource(source);
                }
            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView dormsanitatioaddaddadapter_name_tv;
        public EditText dormsanitatioaddaddadapter_count_tv;
    }
}
