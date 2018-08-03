package com.htcompany.educationerpforgansu.workpart.dormpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.ScoreSummaryEntitiy;

import java.util.List;

/**
 * 卫生得分汇总适配器
 * Created by wrb on 2016/11/23.
 */
public class DormHealthScoreSummaryAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ScoreSummaryEntitiy> datas;
    public DormHealthScoreSummaryAdapter(Context context,List<ScoreSummaryEntitiy> datas){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView =inflater.inflate(R.layout.dormhealthscoresummaryadapter_item,null);
            vh.dormhealthscoresummaryadapter_bj_tv=(TextView)convertView.findViewById(R.id.dormhealthscoresummaryadapter_bj_tv);
            vh.dormhealthscoresummaryadapter_name_tv=(TextView)convertView.findViewById(R.id.dormhealthscoresummaryadapter_name_tv);
            vh.dormhealthscoresummaryadapter_fs_tv=(TextView)convertView.findViewById(R.id.dormhealthscoresummaryadapter_fs_tv);
            convertView.setTag(vh);
        }else{
            vh= (ViewHodler) convertView.getTag();
        }
        vh.dormhealthscoresummaryadapter_bj_tv.setText(datas.get(position).getName());
        vh.dormhealthscoresummaryadapter_name_tv.setText(datas.get(position).getUsername());
        vh.dormhealthscoresummaryadapter_fs_tv.setText(datas.get(position).getScore()+"分");
        return convertView;
    }
    class ViewHodler{
        public TextView dormhealthscoresummaryadapter_bj_tv,dormhealthscoresummaryadapter_name_tv,dormhealthscoresummaryadapter_fs_tv;
    }
}
