package com.htcompany.educationerpforgansu.workpart.personnelpart.adapters;

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
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonManageEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 人事管理适配器
 * Created by wrb on 2016/11/17.
 */
public class PersonManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<PersonManageEntity> datas;
    public PersonManageAdapter(Context context,List<PersonManageEntity> datas){
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
            vh =new ViewHodler();
            convertView=inflater.inflate(R.layout.personmanageadapter_lv,null);
            vh.personmanageadapter_photo_img = (XCRoundAndOvalImageView)convertView.findViewById(R.id.personmanageadapter_photo_img);
            vh.personmanageadapter_name_tv=(TextView)convertView.findViewById(R.id.personmanageadapter_name_tv);
            vh.personmanageadapter_jg_tv=(TextView)convertView.findViewById(R.id.personmanageadapter_jg_tv);
            vh.personmanageadapter_gh_tv=(TextView)convertView.findViewById(R.id.personmanageadapter_gh_tv);
            vh.personmanageadapter_gw_tv=(TextView)convertView.findViewById(R.id.personmanageadapter_gw_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        Glide.with(context).load(InterfaceManager.siteURLIP+datas.get(position).getHeader_img())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.personmanageadapter_photo_img);
        vh.personmanageadapter_name_tv.setText(datas.get(position).getUsername());
        vh.personmanageadapter_jg_tv.setText("机构:"+datas.get(position).getOrganization());
        vh.personmanageadapter_gh_tv.setText("工号:"+datas.get(position).getWorker_number());
        vh.personmanageadapter_gw_tv.setText("岗位:"+datas.get(position).getOrganization_job());
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView personmanageadapter_photo_img;
        public TextView personmanageadapter_name_tv,personmanageadapter_jg_tv,personmanageadapter_gh_tv,personmanageadapter_gw_tv;
    }
}
