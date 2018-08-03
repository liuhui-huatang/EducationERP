package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.text.Html;
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
import com.htcompany.educationerpforgansu.workpart.entities.DYNewsEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by weiruibin on 2017/7/10.
 */

public class MainNewsMoreAdapter extends BaseAdapter{
    private Context context;
    private List<DYNewsEntity> datas;
    private LayoutInflater inflater;
    public MainNewsMoreAdapter(Context context,List<DYNewsEntity> datas){
        this.context=context;
        this.datas=datas;
        inflater=LayoutInflater.from(context);
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
            vh=new ViewHodler();
            convertView=inflater.inflate(R.layout.mainnewsmoreadapter_item,null);
            vh.mainnewsmore_title_tv=(TextView)convertView.findViewById(R.id.mainnewsmore_title_tv);
            vh.mainnewsmore_content_tv=(TextView)convertView.findViewById(R.id.mainnewsmore_content_tv);
            vh.mainnewsmore_photo_img=(ImageView) convertView.findViewById(R.id.mainnewsmore_photo_img);
            convertView.setTag(vh);
        }else{
            vh=(ViewHodler) convertView.getTag();
        }
        vh.mainnewsmore_title_tv.setText(datas.get(position).getTitle());
        vh.mainnewsmore_content_tv.setText(Html.fromHtml(datas.get(position).getContent()));
        Glide.with(context)
                .load(InterfaceManager.siteURLIP+datas.get(position).getImage())
                .placeholder(R.mipmap.default_banner)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.mainnewsmore_photo_img);
        return convertView;
    }
    class ViewHodler{
        public TextView mainnewsmore_title_tv,mainnewsmore_content_tv;
        public ImageView mainnewsmore_photo_img;
    }
}
