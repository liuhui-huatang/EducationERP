package com.htcompany.educationerpforgansu.messagepart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.messagepart.entity.MainMessageEntity;

import java.util.List;

/**
 * 消息适配器
 * Created by wrb on 2017/4/25.
 */
public class MainMessageAdapter extends BaseAdapter{
   private Context context;
    private LayoutInflater inflater;
    private List<MainMessageEntity> datas;
    public MainMessageAdapter(Context context,List<MainMessageEntity> datas){
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
        ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView =  inflater.inflate(R.layout.mainmessageadapter_item,null);
            vh.mainmessagelv_cotent_tv=(TextView)convertView.findViewById(R.id.mainmessagelv_cotent_tv);
            vh.mainmessagelv_flag_tv=(TextView)convertView.findViewById(R.id.mainmessagelv_flag_tv);
            vh.mainmessagelv_title_tv=(TextView)convertView.findViewById(R.id.mainmessagelv_title_tv);
            vh.mainmessagelv_time_tv=(TextView)convertView.findViewById(R.id.mainmessagelv_time_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.mainmessagelv_cotent_tv.setText(datas.get(position).getContent());
        vh.mainmessagelv_title_tv.setText(datas.get(position).getTheme());
        vh.mainmessagelv_time_tv.setText(datas.get(position).getAddTime());
        if("未读".equals(datas.get(position).getIs_read())){
            vh.mainmessagelv_flag_tv.setVisibility(View.VISIBLE);
        }else{
            vh.mainmessagelv_flag_tv.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder{
        public TextView mainmessagelv_cotent_tv,mainmessagelv_flag_tv,mainmessagelv_title_tv,mainmessagelv_time_tv;
    }
}
