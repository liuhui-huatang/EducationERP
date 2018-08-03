package com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.VotingOptionsEntity;

import java.util.List;

/**
 * 投票选项列表适配器
 * Created by wrb on 2017/4/21.
 */
public class VotingPostionAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<VotingOptionsEntity> datas;
    public VotingPostionAdapter(Context context,List<VotingOptionsEntity> datas){
        this.context =context;
        this.datas =datas;
        inflater =LayoutInflater.from(context);
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
            vh =new ViewHodler();
            convertView = inflater.inflate(R.layout.votionpostionadapter_item,null);
            vh.votionposton_code_tv = (TextView)convertView.findViewById(R.id.votionposton_code_tv);
            vh.votionposton_cotent_tv = (TextView)convertView.findViewById(R.id.votionposton_cotent_tv);
            vh.votionposton_count_tv = (TextView)convertView.findViewById(R.id.votionposton_count_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.votionposton_code_tv.setText(position+1+".");
        vh.votionposton_cotent_tv.setText(datas.get(position).getName());
        vh.votionposton_count_tv.setText(datas.get(position).getCount()+"人");
        return convertView;
    }
    class ViewHodler{
        public TextView votionposton_code_tv,votionposton_cotent_tv,votionposton_count_tv;
    }
}
