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
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormExchageSeachEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 宿舍调换列表查询适配器
 * Created by wrb on 2016/11/22.
 */
public class DormExchageSeachAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<DormExchageSeachEntity> datas;
    public DormExchageSeachAdapter(Context context,List<DormExchageSeachEntity> datas){
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
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView=inflater.inflate(R.layout.dormexchageseachadapter_item,null);
            vh.dormexchageseachadapter_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.dormexchageseachadapter_photo_img);
            vh.dormexchageseachadapter_sex_img=(ImageView)convertView.findViewById(R.id.dormexchageseachadapter_sex_img);
            vh.dormexchageseachadapter_name_tv=(TextView) convertView.findViewById(R.id.dormexchageseachadapter_name_tv);
            vh.dormexchageseachadapter_time_tv=(TextView) convertView.findViewById(R.id.dormexchageseachadapter_time_tv);
            vh.dormexchageseachadapter_xzb_tv=(TextView) convertView.findViewById(R.id.dormexchageseachadapter_xzb_tv);
            vh.dormexchageseachadapter_bzr_tv=(TextView) convertView.findViewById(R.id.dormexchageseachadapter_bzr_tv);
            vh.dormexchageseachadapter_yssname_tv=(TextView) convertView.findViewById(R.id.dormexchageseachadapter_yssname_tv);
            vh.dormexchageseachadapter_dhssname_tv=(TextView) convertView.findViewById(R.id.dormexchageseachadapter_dhssname_tv);
            vh.dormexchageseachadapter_ycwname_tv=(TextView) convertView.findViewById(R.id.dormexchageseachadapter_ycwname_tv);
            vh.dormexchageseachadapter_dhcwname_tv=(TextView) convertView.findViewById(R.id.dormexchageseachadapter_dhcwname_tv);
            convertView.setTag(vh);
        }else{
         vh=(ViewHodler)convertView.getTag();
        }
        if(!"".equals(datas.get(position).getImgurl())&&datas.get(position).getImgurl()!=null){
            ImageLoader.getInstance().displayImage(datas.get(position).getImgurl(),vh.dormexchageseachadapter_photo_img, MyApp.getOptions());
        }
        if("男".equals(datas.get(position).getSex())){
            vh.dormexchageseachadapter_sex_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ssmk_rztsjl_icon3));
        }else{
            vh.dormexchageseachadapter_sex_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ssmk_rztsjl_icon4));
        }
        vh.dormexchageseachadapter_name_tv.setText(datas.get(position).getStu_name());
        vh.dormexchageseachadapter_time_tv.setText(datas.get(position).getDate());
        vh.dormexchageseachadapter_xzb_tv.setText("行政班:"+datas.get(position).getXzb());
        vh.dormexchageseachadapter_bzr_tv.setText("班主任:"+datas.get(position).getTeacherName());
        vh.dormexchageseachadapter_yssname_tv.setText(datas.get(position).getRoom_before());
        vh.dormexchageseachadapter_dhssname_tv.setText(datas.get(position).getRoom_id_change());
        vh.dormexchageseachadapter_ycwname_tv.setText(datas.get(position).getBed_before());
        vh.dormexchageseachadapter_dhcwname_tv.setText(datas.get(position).getBed_change());
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView dormexchageseachadapter_photo_img;
        public ImageView dormexchageseachadapter_sex_img;
        public TextView dormexchageseachadapter_name_tv,dormexchageseachadapter_time_tv,dormexchageseachadapter_xzb_tv,
                dormexchageseachadapter_bzr_tv,dormexchageseachadapter_yssname_tv,
                dormexchageseachadapter_dhssname_tv,dormexchageseachadapter_ycwname_tv,
                dormexchageseachadapter_dhcwname_tv;

    }
}
