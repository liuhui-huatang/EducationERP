package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingInteractionEntity;

import java.util.List;


/**
 * 教学互动
 * Created by wrb on 2016/11/22.
 */
public class TeachingInteractionAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeachingInteractionEntity> datas;
    public TeachingInteractionAdapter(Context context,List<TeachingInteractionEntity> datas){
        this.context  =context;
        this.datas = datas;
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
        ViewHoder vh;
        if(convertView==null){
            vh = new ViewHoder();
            convertView=inflater.inflate(R.layout.teachinginteractionadapter_item,null);
            vh.teachinginteractionadapter_kc_tv = (TextView)convertView.findViewById(R.id.teachinginteractionadapter_kc_tv);
            vh.teachinginteractionadapter_type_tv = (TextView)convertView.findViewById(R.id.teachinginteractionadapter_type_tv);
            vh.teachinginteractionadapter_content_tv = (TextView)convertView.findViewById(R.id.teachinginteractionadapter_content_tv);
            vh.teachinginteractionadapter_twr_tv = (TextView)convertView.findViewById(R.id.teachinginteractionadapter_twr_tv);
            vh.teachinginteractionadapter_twrq_tv = (TextView)convertView.findViewById(R.id.teachinginteractionadapter_twrq_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHoder) convertView.getTag();
        }
        if(datas.get(position).getKecheng()!=null&&!"null".equals(datas.get(position).getKecheng())){
            vh.teachinginteractionadapter_kc_tv.setText("课程:"+datas.get(position).getKecheng());
        }else{
            vh.teachinginteractionadapter_kc_tv.setText("课程:");
        }
        //0 已提交，3，已提交，1，未解决，2 已解决
        if("0".equals(datas.get(position).getIs_yes())){
            if("".equals(datas.get(position).getQuestion_da())){
                //未回答
                vh.teachinginteractionadapter_type_tv.setText("待解答");
                vh.teachinginteractionadapter_type_tv.setTextColor(context.getResources().getColor(R.color.ptjs_tv3_color));
            }else{
                //已回答
                vh.teachinginteractionadapter_type_tv.setText("已回答");
                vh.teachinginteractionadapter_type_tv.setTextColor(context.getResources().getColor(R.color.contacts_bs1_color));
            }
        }else if("2".equals(datas.get(position).getIs_yes())){
            vh.teachinginteractionadapter_type_tv.setText("未解决");
            vh.teachinginteractionadapter_type_tv.setTextColor(context.getResources().getColor(R.color.ptjs_tv3_color));
        }else if("1".equals(datas.get(position).getIs_yes())){
            vh.teachinginteractionadapter_type_tv.setText("已解决");
            vh.teachinginteractionadapter_type_tv.setTextColor(context.getResources().getColor(R.color.contacts_bs1_color));
        }else if("3".equals(datas.get(position).getIs_yes())){
            if("".equals(datas.get(position).getQuestion_da())){
                //未回答
                vh.teachinginteractionadapter_type_tv.setText("待解答");
                vh.teachinginteractionadapter_type_tv.setTextColor(context.getResources().getColor(R.color.ptjs_tv3_color));
            }else{
                //已回答
                vh.teachinginteractionadapter_type_tv.setText("已回答");
                vh.teachinginteractionadapter_type_tv.setTextColor(context.getResources().getColor(R.color.contacts_bs1_color));
            }

        }

        vh.teachinginteractionadapter_content_tv.setText(datas.get(position).getQuestion());
        vh.teachinginteractionadapter_twr_tv.setText("提问人:"+datas.get(position).getStu_username());
        vh.teachinginteractionadapter_twrq_tv.setText("提问日期:"+datas.get(position).getTwtime());
        return convertView;
    }
    class ViewHoder{
        public TextView teachinginteractionadapter_kc_tv,teachinginteractionadapter_type_tv,teachinginteractionadapter_content_tv,
                teachinginteractionadapter_twr_tv,teachinginteractionadapter_twrq_tv;
    }
}
