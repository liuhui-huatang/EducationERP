package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassExamEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 班级成绩适配器
 * Created by wrb on 2016/11/25.
 */
public class TeachPartClassExamAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassExamEntity> datas;
    public TeachPartClassExamAdapter(Context context,List<ClassExamEntity> datas ){
        this.context = context;
        this.datas=datas;
        inflater=LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.teachpartclassexamadapter_item,null);
            vh.classexamadapter_photo_tv=(XCRoundAndOvalImageView)convertView.findViewById(R.id.classexamadapter_photo_tv);
            vh.classexamadapter_count_tv = (TextView)convertView.findViewById(R.id.classexamadapter_counth_tv);
            vh.classexamadapter_name_tv = (TextView)convertView.findViewById(R.id.classexamadapter_name_tv);
            vh.classexamadapter_xf_tv = (TextView)convertView.findViewById(R.id.classexamadapter_xf_tv);
            vh.classexamadapter_xh_tv = (TextView)convertView.findViewById(R.id.classexamadapter_xh_tv);
            vh.classexamadapter_cj_tv = (TextView)convertView.findViewById(R.id.classexamadapter_cj_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        Glide.with(context).load(InterfaceManager.siteURLIP+datas.get(position).getPhoto())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.classexamadapter_photo_tv);
        vh.classexamadapter_count_tv.setText(position+1+"");
        vh.classexamadapter_name_tv.setText(datas.get(position).getUsername());
        vh.classexamadapter_xf_tv.setText("学分:"+datas.get(position).getXufen());
        vh.classexamadapter_xh_tv.setText("学号:"+datas.get(position).getNumber());
        vh.classexamadapter_cj_tv.setText(datas.get(position).getTotal());
        return convertView;
    }
    class ViewHodler{
        public TextView classexamadapter_count_tv,classexamadapter_name_tv,classexamadapter_xf_tv,
                classexamadapter_xh_tv,classexamadapter_cj_tv;
        public XCRoundAndOvalImageView classexamadapter_photo_tv;
    }
}
