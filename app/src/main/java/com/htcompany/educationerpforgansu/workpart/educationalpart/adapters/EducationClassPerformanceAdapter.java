package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.ClassCjEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 班级成绩适配器
 * Created by wrb on 2016/11/24.
 */
public class EducationClassPerformanceAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassCjEntity> datas;
    public EducationClassPerformanceAdapter(Context context,List<ClassCjEntity> datas){
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
        ViewHoder vh;
        if(convertView==null){
            vh=new ViewHoder();
            convertView=inflater.inflate(R.layout.educationclassperformanceadapter_item,null);
            vh.jwbjcj_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.jwbjcj_photo_img);
            vh.jwbjcj_name_tv = (TextView)convertView.findViewById(R.id.jwbjcj_name_tv);
            vh.jwbjcj_xf_tv = (TextView)convertView.findViewById(R.id.jwbjcj_xf_tv);
            vh.jwbjcj_zcj_tv = (TextView)convertView.findViewById(R.id.jwbjcj_zcj_tv);
            vh.jwbjcj_xh_tv = (TextView)convertView.findViewById(R.id.jwbjcj_xh_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHoder) convertView.getTag();
        }
        Glide.with(context).load(InterfaceManager.siteURLIP+datas.get(position).getPhoto())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.jwbjcj_photo_img);
        vh.jwbjcj_name_tv.setText(datas.get(position).getUsername());
        vh.jwbjcj_xf_tv.setText("学分:"+datas.get(position).getXufen());
        vh.jwbjcj_zcj_tv.setText("总成绩:"+datas.get(position).getTotal());
        vh.jwbjcj_xh_tv.setText("学号:"+datas.get(position).getNumber());
        return convertView;
    }
    class ViewHoder{
        public XCRoundAndOvalImageView jwbjcj_photo_img;
        public TextView jwbjcj_name_tv,jwbjcj_xf_tv,jwbjcj_zcj_tv,jwbjcj_xh_tv;
    }
}
