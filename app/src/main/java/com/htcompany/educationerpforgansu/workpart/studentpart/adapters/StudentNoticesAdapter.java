package com.htcompany.educationerpforgansu.workpart.studentpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentNoticesEntity;

import java.util.List;

/**
 *学生公告适配器
 * Created by wrb on 2016/11/23.
 */
public class StudentNoticesAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<StudentNoticesEntity> datas;
    public StudentNoticesAdapter(Context context,List<StudentNoticesEntity> datas){
        this.context = context;
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
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView=inflater.inflate(R.layout.studentnoticesadapter_item,null);
            vh.studentnoticeadppter_icon_img = (ImageView)convertView.findViewById(R.id.studentnoticeadppter_icon_img);
            vh.studentnoticeadppter_gglx_tv = (TextView)convertView.findViewById(R.id.studentnoticeadppter_gglx_tv);
            vh.studentnoticeadppter_ggbt_tv = (TextView)convertView.findViewById(R.id.studentnoticeadppter_ggbt_tv);
            vh.studentnoticeadppter_ggcontent_tv = (TextView)convertView.findViewById(R.id.studentnoticeadppter_ggcontent_tv);
            vh.studentnoticeadppter_ggtime_tv = (TextView)convertView.findViewById(R.id.studentnoticeadppter_ggtime_tv);
            vh.studentnoticeadppter_ggfbr_tv = (TextView)convertView.findViewById(R.id.studentnoticeadppter_ggfbr_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        if("1".equals(datas.get(position).getWtype())){
            vh.studentnoticeadppter_icon_img.setImageResource(R.mipmap.xsgl_gg_icon1);
            vh.studentnoticeadppter_gglx_tv.setText("全校公告");
        }else if("2".equals(datas.get(position).getWtype())){
            vh.studentnoticeadppter_icon_img.setImageResource(R.mipmap.xsgl_gg_icon2);
            vh.studentnoticeadppter_gglx_tv.setText("班级公告");
        }else if("3".equals(datas.get(position).getWtype())){
            vh.studentnoticeadppter_icon_img.setImageResource(R.mipmap.xsgl_gg_icon3);
            vh.studentnoticeadppter_gglx_tv.setText("组织公告");
        }
        vh.studentnoticeadppter_ggbt_tv.setText(datas.get(position).getWtitle());
        vh.studentnoticeadppter_ggcontent_tv.setText(datas.get(position).getWcontent());
        vh.studentnoticeadppter_ggtime_tv.setText("发布时间:"+datas.get(position).getWtime());
        vh.studentnoticeadppter_ggfbr_tv.setText("发布人:"+datas.get(position).getWfbr());
        return convertView;
    }
    class ViewHodler{
        public ImageView studentnoticeadppter_icon_img;
        public TextView studentnoticeadppter_gglx_tv,studentnoticeadppter_ggbt_tv,studentnoticeadppter_ggcontent_tv,
                studentnoticeadppter_ggtime_tv,studentnoticeadppter_ggfbr_tv;
    }
}
