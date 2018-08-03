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
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonContractEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 劳动合同适配器
 * Created by wrb on 2016/11/17.
 */
public class PersonContractManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<PersonContractEntity> datas;
    public PersonContractManageAdapter(Context context,List<PersonContractEntity> datas){
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
            convertView=inflater.inflate(R.layout.personcontractmanageadapter_item,null);
            vh.personcontractmanageadapter_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.personcontractmanageadapter_photo_img);
            vh.personcontractmanageadapter_status_img=(ImageView)convertView.findViewById(R.id.personcontractmanageadapter_status_img);
            vh.personcontractmanageadapter_name_tv=(TextView) convertView.findViewById(R.id.personcontractmanageadapter_name_tv);
            vh.personcontractmanageadapter_code_tv=(TextView)convertView.findViewById(R.id.personcontractmanageadapter_code_tv);
            vh.personcontractmanageadapter_zgh_tv=(TextView)convertView.findViewById(R.id.personcontractmanageadapter_zgh_tv);
            vh.personcontractmanageadapter_status_tv=(TextView)convertView.findViewById(R.id.personcontractmanageadapter_status_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        Glide.with(context).load(InterfaceManager.siteURLIP+datas.get(position).getHeader_img())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.personcontractmanageadapter_photo_img);
        vh.personcontractmanageadapter_name_tv.setText(datas.get(position).getUsername());
        vh.personcontractmanageadapter_code_tv.setText("合同编号:"+datas.get(position).getNumber());
        vh.personcontractmanageadapter_zgh_tv.setText("工号:"+datas.get(position).getWorker_number());
        if("1".equals(datas.get(position).getStatus())){
            vh.personcontractmanageadapter_status_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.rs_ht_icon3));
            vh.personcontractmanageadapter_status_tv.setText("状态:新签");
            vh.personcontractmanageadapter_status_tv.setTextColor(context.getResources().getColor(R.color.rsgl_ht3_color));
        }else if("2".equals(datas.get(position).getStatus())){
            vh.personcontractmanageadapter_status_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.rs_ht_icon4));
            vh.personcontractmanageadapter_status_tv.setText("状态:续签");
            vh.personcontractmanageadapter_status_tv.setTextColor(context.getResources().getColor(R.color.rsgl_ht4_color));
        }else if("3".equals(datas.get(position).getStatus())){
            vh.personcontractmanageadapter_status_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.rs_ht_icon1));
            vh.personcontractmanageadapter_status_tv.setText("状态:终止");
            vh.personcontractmanageadapter_status_tv.setTextColor(context.getResources().getColor(R.color.rsgl_ht1_color));
        }
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView personcontractmanageadapter_photo_img;
                public ImageView personcontractmanageadapter_status_img;
        public TextView personcontractmanageadapter_name_tv,personcontractmanageadapter_code_tv,
                personcontractmanageadapter_zgh_tv,personcontractmanageadapter_status_tv;
    }
}
