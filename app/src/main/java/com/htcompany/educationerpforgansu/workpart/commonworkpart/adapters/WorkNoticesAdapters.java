package com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;

import java.util.List;

/**
 * 办公公告适配器
 * Created by wrb on 2016/11/22.
 */
public class WorkNoticesAdapters extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<WorkNoticeEntity> datas;
    public WorkNoticesAdapters(Context context,List<WorkNoticeEntity> datas){
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
            convertView = inflater.inflate(R.layout.worknoticesadapters_item,null);
            vh.worknoticeadppter_icon_img = (ImageView)convertView.findViewById(R.id.worknoticeadppter_icon_img);
            vh.worknoticeadppter_gglx_tv = (TextView)convertView.findViewById(R.id.worknoticeadppter_gglx_tv);
            vh.worknoticeadppter_ggbt_tv = (TextView)convertView.findViewById(R.id.worknoticeadppter_ggbt_tv);
            vh.worknoticeadppter_ggcontent_tv = (TextView)convertView.findViewById(R.id.worknoticeadppter_ggcontent_tv);
            vh.worknoticeadppter_ggtime_tv = (TextView)convertView.findViewById(R.id.worknoticeadppter_ggtime_tv);
            vh.worknoticeadppter_ggfbr_tv = (TextView)convertView.findViewById(R.id.worknoticeadppter_ggfbr_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        if("2".equals(datas.get(position).getType())){
            vh.worknoticeadppter_icon_img.setImageResource(R.mipmap.bggl_qxgg_icon);
            vh.worknoticeadppter_gglx_tv.setText("全校公告");
        }else if("1".equals(datas.get(position).getType())){
            vh.worknoticeadppter_icon_img.setImageResource(R.mipmap.bggl_bmgg_icon);
            vh.worknoticeadppter_gglx_tv.setText("部门公告");
        }
        vh.worknoticeadppter_ggbt_tv.setText(datas.get(position).getB_title());
        vh.worknoticeadppter_ggcontent_tv.setText(datas.get(position).getB_content());
        vh.worknoticeadppter_ggtime_tv.setText(datas.get(position).getB_send_date());
        if("null".equals(datas.get(position).getUsername())||datas.get(position).getUsername()==null){
            vh.worknoticeadppter_ggfbr_tv.setText("发布人：");
        }else{
            vh.worknoticeadppter_ggfbr_tv.setText("发布人："+datas.get(position).getUsername());
        }

        return convertView;
    }

    class ViewHodler{
        public ImageView worknoticeadppter_icon_img;
        public TextView worknoticeadppter_gglx_tv,worknoticeadppter_ggbt_tv,
                worknoticeadppter_ggcontent_tv,worknoticeadppter_ggtime_tv,worknoticeadppter_ggfbr_tv;
    }

}
