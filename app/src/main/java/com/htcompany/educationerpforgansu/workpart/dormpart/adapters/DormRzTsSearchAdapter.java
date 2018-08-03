package com.htcompany.educationerpforgansu.workpart.dormpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormRzTsSearchEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 入住退宿查询适配器
 * Created by wrb on 2016/11/22.
 */
public class DormRzTsSearchAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<DormRzTsSearchEntity> datas;
    public DormRzTsSearchAdapter(Context context,List<DormRzTsSearchEntity> datas){
        this.context = context;
        this.datas=datas;
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
            vh =new ViewHolder();
            convertView=inflater.inflate(R.layout.dormrztssearchadapter_item,null);
            vh.dormrztssearchadapter_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.dormrztssearchadapter_photo_img);
            vh.dormrztssearchadapter_flag_img=(ImageView)convertView.findViewById(R.id.dormrztssearchadapter_flag_img);
            vh.dormrztssearchadapter_sex_img=(ImageView)convertView.findViewById(R.id.dormrztssearchadapter_sex_img);

            vh.dormrztssearchadapter_name_tv=(TextView) convertView.findViewById(R.id.dormrztssearchadapter_name_tv);
            vh.dormrztssearchadapter_time_tv=(TextView) convertView.findViewById(R.id.dormrztssearchadapter_time_tv);
            vh.dormrztssearchadapter_class_tv=(TextView) convertView.findViewById(R.id.dormrztssearchadapter_class_tv);
            vh.dormrztssearchadapter_type_tv=(TextView) convertView.findViewById(R.id.dormrztssearchadapter_type_tv);
            vh.dormrztssearchadapter_bzr_tv=(TextView) convertView.findViewById(R.id.dormrztssearchadapter_bzr_tv);
            vh.dormrztssearchadapter_room_tv=(TextView) convertView.findViewById(R.id.dormrztssearchadapter_room_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHolder)convertView.getTag();
        }
        if(!"".equals(datas.get(position).getImgurl())&&datas.get(position).getImgurl()!=null){
            ImageLoader.getInstance().displayImage(datas.get(position).getImgurl(),vh.dormrztssearchadapter_photo_img, MyApp.getOptions());
        }
        if("男".equals(datas.get(position).getSex())){
            vh.dormrztssearchadapter_sex_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ssmk_rztsjl_icon3));
        }else{
            vh.dormrztssearchadapter_sex_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ssmk_rztsjl_icon4));
        }
        if("入住".equals(datas.get(position).getType())){
            vh.dormrztssearchadapter_flag_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ssmk_rztsjl_icon2));
        }else{
            vh.dormrztssearchadapter_flag_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ssmk_rztsjl_icon1));
        }

        vh.dormrztssearchadapter_name_tv.setText(datas.get(position).getStu_name());
        vh.dormrztssearchadapter_time_tv.setText(datas.get(position).getDate());
        vh.dormrztssearchadapter_class_tv.setText("行政班:"+datas.get(position).getXzb());
        vh.dormrztssearchadapter_type_tv.setText("类型:"+datas.get(position).getType());
        vh.dormrztssearchadapter_bzr_tv.setText("班主任:"+datas.get(position).getTeacherName());
        vh.dormrztssearchadapter_room_tv.setText("宿舍:"+datas.get(position).getHouse());
        return convertView;
    }
    class ViewHolder{
        public XCRoundAndOvalImageView dormrztssearchadapter_photo_img;
               public ImageView dormrztssearchadapter_flag_img,dormrztssearchadapter_sex_img;
        public TextView dormrztssearchadapter_name_tv,dormrztssearchadapter_time_tv,
                dormrztssearchadapter_class_tv,dormrztssearchadapter_type_tv,dormrztssearchadapter_bzr_tv,
                dormrztssearchadapter_room_tv;
    }
}
