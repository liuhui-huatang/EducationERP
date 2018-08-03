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
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonnelFilesEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 人事档案适配器
 * Created by wrb on 2016/11/18.
 */
public class PersonnelFilesAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<PersonnelFilesEntity> datas;
    public PersonnelFilesAdapter(Context context,List<PersonnelFilesEntity> datas){
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
            convertView=inflater.inflate(R.layout.personnelfilesadapter_item,null);
            vh.personnelfilesadapter_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.personnelfilesadapter_photo_img);
            vh.personnelfilesadapter_adress_tv=(TextView) convertView.findViewById(R.id.personnelfilesadapter_adress_tv);
            vh.personnelfilesadapter_code_tv=(TextView) convertView.findViewById(R.id.personnelfilesadapter_code_tv);
            vh.personnelfilesadapter_name_tv=(TextView) convertView.findViewById(R.id.personnelfilesadapter_name_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        Glide.with(context).load(InterfaceManager.siteURLIP+datas.get(position).getHeader_img())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.personnelfilesadapter_photo_img);
        if(!"null".equals(datas.get(position).getPf_position())&&datas.get(position).getPf_position()!=null){
            vh.personnelfilesadapter_adress_tv.setText("档案位置:"+datas.get(position).getPf_position());
        }else{
            vh.personnelfilesadapter_adress_tv.setText("档案位置:");
        }
        vh.personnelfilesadapter_code_tv.setText("档案编号:"+datas.get(position).getPf_number());
        vh.personnelfilesadapter_name_tv.setText(datas.get(position).getTeacher_name());
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView personnelfilesadapter_photo_img;
        public TextView personnelfilesadapter_adress_tv,personnelfilesadapter_code_tv,personnelfilesadapter_name_tv;
    }
}
