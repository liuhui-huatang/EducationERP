package com.htcompany.educationerpforgansu.workpart.personnelpart.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonLeaveApplyEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 教职工请假适配器
 * Created by wrb on 2017/5/8.
 */
public class PersonLeaveApplyManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<PersonLeaveApplyEntity> datas;
    public PersonLeaveApplyManageAdapter(Context context, List<PersonLeaveApplyEntity> datas){
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView =inflater.inflate(R.layout.personleaveapplymanageadapter_item,null);
            vh.leaveapplymanage_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.leaveapplymanage_photo_img);
            vh.leaveapplymanage_name_tv = (TextView)convertView.findViewById(R.id.leaveapplymanage_name_tv);
            vh.leaveapplymanage_zt_tv = (TextView)convertView.findViewById(R.id.leaveapplymanage_zt_tv);
            vh.leaveapplymanage_type_tv = (TextView)convertView.findViewById(R.id.leaveapplymanage_type_tv);
            vh.leaveapplymanage_time_tv = (TextView)convertView.findViewById(R.id.leaveapplymanage_time_tv);
            vh.leaveapplymanage_zt_img=(ImageView)convertView.findViewById(R.id.leaveapplymanage_zt_img);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }

        Glide.with(context)
                .load(InterfaceManager.siteURLIP+datas.get(position).getHeader_img())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.leaveapplymanage_photo_img);
        vh.leaveapplymanage_name_tv.setText(datas.get(position).getUsername());
//        status 3审核通过，2审核未通过，1已提交
        if("审核通过".equals(datas.get(position).getStatus())) {
            vh.leaveapplymanage_zt_tv.setText(datas.get(position).getStatus());
            vh.leaveapplymanage_zt_tv.setTextColor(context.getResources().getColor(R.color.qj_btncolor));
            vh.leaveapplymanage_zt_img.setImageResource(R.mipmap.qj_togguo);
        }else if("已提交".equals(datas.get(position).getStatus())) {
            vh.leaveapplymanage_zt_tv.setText(datas.get(position).getStatus());
            vh.leaveapplymanage_zt_tv.setTextColor(context.getResources().getColor(R.color.qj_btn2color));
            vh.leaveapplymanage_zt_img.setImageResource(R.mipmap.qj_yitij);
        }else if("审核未通过".equals(datas.get(position).getStatus())) {
            vh.leaveapplymanage_zt_tv.setText(datas.get(position).getStatus());
            vh.leaveapplymanage_zt_tv.setTextColor(context.getResources().getColor(R.color.qj_btn3color));
            vh.leaveapplymanage_zt_img.setImageResource(R.mipmap.qj_weitongguo);
        }else if("已销假".equals(datas.get(position).getStatus())) {
            vh.leaveapplymanage_zt_tv.setText(datas.get(position).getStatus());
            vh.leaveapplymanage_zt_tv.setTextColor(context.getResources().getColor(R.color.qj_btn4color));
            vh.leaveapplymanage_zt_img.setImageResource(R.mipmap.qj_xiaoij);
        }
        vh.leaveapplymanage_zt_tv.setText(datas.get(position).getStatus());
            vh.leaveapplymanage_type_tv.setText(datas.get(position).getTypename());
        vh.leaveapplymanage_time_tv.setText(datas.get(position).getStart()+"至"+datas.get(position).getEnd());
        return convertView;
    }
    class ViewHodler{
        private XCRoundAndOvalImageView leaveapplymanage_photo_img;
        private ImageView leaveapplymanage_zt_img;
        public TextView leaveapplymanage_name_tv,leaveapplymanage_zt_tv,leaveapplymanage_type_tv,
                leaveapplymanage_time_tv;
    }
}
