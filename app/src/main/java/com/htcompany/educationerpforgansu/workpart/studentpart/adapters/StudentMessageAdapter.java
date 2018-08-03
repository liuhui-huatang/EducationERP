package com.htcompany.educationerpforgansu.workpart.studentpart.adapters;

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
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentMessageEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 学生信息适配器
 * Created by wrb on 2016/11/23.
 */
public class StudentMessageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<StudentMessageEntity> datas;
    public StudentMessageAdapter(Context context,List<StudentMessageEntity> datas){
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
            convertView =inflater.inflate(R.layout.studentmessageadapter_item,null);
            vh.student_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.student_photo_img);
            vh.studentmessageadapter_name_tv=(TextView)convertView.findViewById(R.id.studentmessageadapter_name_tv);
            vh.studentmessageadapter_bj_tv=(TextView)convertView.findViewById(R.id.studentmessageadapter_bj_tv);
            vh.studentmessageadapter_zy_tv=(TextView)convertView.findViewById(R.id.studentmessageadapter_zy_tv);
            vh.studentmessageadapter_code_tv=(TextView)convertView.findViewById(R.id.studentmessageadapter_code_tv);
            vh.studentmessageadapter_nj_tv=(TextView)convertView.findViewById(R.id.studentmessageadapter_nj_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.studentmessageadapter_name_tv.setText(datas.get(position).getTruename());
        vh.studentmessageadapter_bj_tv.setText("班级:"+datas.get(position).getTrbd_jxb());
        vh.studentmessageadapter_zy_tv.setText("专业:"+datas.get(position).getTrbd_major());
        vh.studentmessageadapter_code_tv.setText("学号:"+datas.get(position).getNumber());
        vh.studentmessageadapter_nj_tv.setText("年级:"+datas.get(position).getTrbd_grade());
        Glide.with(context).load(InterfaceManager.siteURLIP+datas.get(position).getPhoto())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.student_photo_img);
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView student_photo_img;
        public TextView studentmessageadapter_name_tv,studentmessageadapter_bj_tv,
                studentmessageadapter_zy_tv,studentmessageadapter_code_tv,
                studentmessageadapter_nj_tv;
    }
}
