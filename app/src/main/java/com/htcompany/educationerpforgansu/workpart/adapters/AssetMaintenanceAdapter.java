package com.htcompany.educationerpforgansu.workpart.adapters;

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
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.entities.AssetMaintenanceEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 资产维护适配器
 * Created by wrb on 2016/11/9.
 */
public class AssetMaintenanceAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<AssetMaintenanceEntity> datas;
    public AssetMaintenanceAdapter(Context context,List<AssetMaintenanceEntity> datas){
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
            convertView=inflater.inflate(R.layout.assetmaintenance_lv_item,null);
            vh.assetmaintenance_photo_img = (ImageView)convertView.findViewById(R.id.assetmaintenance_photo_img);
            vh.assetmaintenance_title_tv = (TextView)convertView.findViewById(R.id.assetmaintenance_title_tv);
            vh.assetmaintenance_ms_tv = (TextView)convertView.findViewById(R.id.assetmaintenance_ms_tv);
            vh.assetmaintenance_adress_tv = (TextView)convertView.findViewById(R.id.assetmaintenance_adress_tv);
            vh.assetmaintenance_time_tv = (TextView)convertView.findViewById(R.id.assetmaintenance_time_tv);
            vh.assetmaintenance_zt_tv = (TextView)convertView.findViewById(R.id.assetmaintenance_zt_tv);
            vh.assetmaintenance_zt_img=(ImageView)convertView.findViewById(R.id.assetmaintenance_zt_img);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.assetmaintenance_title_tv.setText(datas.get(position).getTitle());
        vh.assetmaintenance_ms_tv.setText(datas.get(position).getMiaoshu());
        vh.assetmaintenance_adress_tv.setText(datas.get(position).getPlace());
        vh.assetmaintenance_time_tv.setText(datas.get(position).getRepair_time());
        vh.assetmaintenance_zt_tv.setText(datas.get(position).getShow_treatment_status_id());
        if("已处理".equals(datas.get(position).getShow_treatment_status_id())) {
            vh.assetmaintenance_zt_tv.setText(datas.get(position).getShow_treatment_status_id());
            vh.assetmaintenance_zt_tv.setTextColor(context.getResources().getColor(R.color.qj_btncolor));
            vh.assetmaintenance_zt_img.setImageResource(R.mipmap.qj_togguo);
        }else if("未处理".equals(datas.get(position).getShow_treatment_status_id())) {
            vh.assetmaintenance_zt_tv.setText(datas.get(position).getShow_treatment_status_id());
            vh.assetmaintenance_zt_tv.setTextColor(context.getResources().getColor(R.color.qj_btn2color));
            vh.assetmaintenance_zt_img.setImageResource(R.mipmap.qj_yitij);
        }else if("已派工".equals(datas.get(position).getShow_treatment_status_id())) {
            vh.assetmaintenance_zt_tv.setText(datas.get(position).getShow_treatment_status_id());
            vh.assetmaintenance_zt_tv.setTextColor(context.getResources().getColor(R.color.qj_btn4color));
            vh.assetmaintenance_zt_img.setImageResource(R.mipmap.qj_xiaoij);
        }
        Glide.with(context).load(datas.get(position).getImgurl())
                .placeholder(R.mipmap.bottombg_show_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.assetmaintenance_photo_img);
        return convertView;
    }
    class ViewHodler{
        public ImageView assetmaintenance_zt_img;
        public ImageView assetmaintenance_photo_img;
        public TextView assetmaintenance_title_tv,assetmaintenance_ms_tv,assetmaintenance_adress_tv,assetmaintenance_time_tv,assetmaintenance_zt_tv;
    }

}
