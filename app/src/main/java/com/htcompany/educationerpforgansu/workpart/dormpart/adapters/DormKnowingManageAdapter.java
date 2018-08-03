package com.htcompany.educationerpforgansu.workpart.dormpart.adapters;

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
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormKnowingManageEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 查寝管理适配器
 * Created by wrb on 2016/11/22.
 */
public class DormKnowingManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<DormKnowingManageEntity> datas;
    public DormKnowingManageAdapter(Context context,List<DormKnowingManageEntity> datas){
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
            convertView =inflater.inflate(R.layout.dormknowingmanageadapter_item,null);
            vh.dormknowingmanageadapter_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.dormknowingmanageadapter_photo_img);
            vh.dormknowingmanageadapter_sex_img=(ImageView)convertView.findViewById(R.id.dormknowingmanageadapter_sex_img);
            vh.dormknowingmanageadapter_name_tv =(TextView)convertView.findViewById(R.id.dormknowingmanageadapter_name_tv);
            vh.dormknowingmanageadapter_time_tv =(TextView)convertView.findViewById(R.id.dormknowingmanageadapter_time_tv);
            vh.dormknowingmanageadapter_status_tv =(TextView)convertView.findViewById(R.id.dormknowingmanageadapter_status_tv);
            vh.dormknowingmanageadapter_room_tv =(TextView)convertView.findViewById(R.id.dormknowingmanageadapter_room_tv);
            vh.dormknowingmanageadapter_roomtype_tv =(TextView)convertView.findViewById(R.id.dormknowingmanageadapter_roomtype_tv);
            vh.dormknowingmanageadapter_bed_tv =(TextView)convertView.findViewById(R.id.dormknowingmanageadapter_bed_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        Glide.with(context).load(datas.get(position).getPhoto())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.dormknowingmanageadapter_photo_img);
        vh.dormknowingmanageadapter_name_tv.setText(datas.get(position).getStuid());
        vh.dormknowingmanageadapter_time_tv.setText(datas.get(position).getDate());
        vh.dormknowingmanageadapter_status_tv.setText(datas.get(position).getShow_absenteeism_category_id());
        vh.dormknowingmanageadapter_room_tv.setText("宿舍:"+datas.get(position).getBuild()+datas.get(position).getRoomNum());
        vh.dormknowingmanageadapter_roomtype_tv.setText("类别:"+datas.get(position).getHouse_type());
        vh.dormknowingmanageadapter_bed_tv .setText(datas.get(position).getBed()+"号床铺");
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView dormknowingmanageadapter_photo_img;
                public ImageView dormknowingmanageadapter_sex_img;
        public TextView dormknowingmanageadapter_name_tv,dormknowingmanageadapter_time_tv,
                dormknowingmanageadapter_status_tv,dormknowingmanageadapter_room_tv,
                dormknowingmanageadapter_roomtype_tv,dormknowingmanageadapter_bed_tv;
    }
}
