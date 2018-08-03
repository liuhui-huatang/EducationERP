package com.htcompany.educationerpforgansu.workpart.personnelpart.adapters;

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
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonSocialInsuranceEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 社会记录适配器
 * Created by wrb on 2016/11/18.
 */
public class PersonSocialInsuranceAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<PersonSocialInsuranceEntity> datas;
    public PersonSocialInsuranceAdapter(Context context,List<PersonSocialInsuranceEntity> datas){
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
            convertView=inflater.inflate(R.layout.personsocialinsuranceadapter_item,null);
            vh.personsocialinsuranceadapter_photo_img=(XCRoundAndOvalImageView) convertView.findViewById(R.id.personsocialinsuranceadapter_photo_img);
            vh.personsocialinsuranceadapter_name_tv=(TextView)convertView.findViewById(R.id.personsocialinsuranceadapter_name_tv);
            vh.personsocialinsuranceadapter_starttime_tv=(TextView)convertView.findViewById(R.id.personsocialinsuranceadapter_starttime_tv);
            vh.personsocialinsuranceadapter_code_tv=(TextView)convertView.findViewById(R.id.personsocialinsuranceadapter_code_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        Glide.with(context).load(InterfaceManager.siteURLIP+datas.get(position).getHeader_img())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.personsocialinsuranceadapter_photo_img);
        vh.personsocialinsuranceadapter_name_tv.setText(datas.get(position).getUsername());
        vh.personsocialinsuranceadapter_starttime_tv.setText("缴纳开始时间:"+datas.get(position).getSi_start_time());
        vh.personsocialinsuranceadapter_code_tv.setText("社会保险号码:"+datas.get(position).getSi_number());
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView personsocialinsuranceadapter_photo_img;
        public TextView personsocialinsuranceadapter_name_tv,
                personsocialinsuranceadapter_starttime_tv,personsocialinsuranceadapter_code_tv;
    }
}
