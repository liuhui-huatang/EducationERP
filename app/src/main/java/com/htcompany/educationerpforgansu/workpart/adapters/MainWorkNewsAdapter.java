package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.entities.DYNewsEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by weiruibin on 2017/6/21.
 */

public class MainWorkNewsAdapter extends BaseAdapter{
    private Context context;
    private List<DYNewsEntity> datas;
    private LayoutInflater inflater;
    public MainWorkNewsAdapter(Context context,List<DYNewsEntity> datas){
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
            convertView=inflater.inflate(R.layout.mainworknewsadapter_item,null);
            vh.mainnews_postion_tv=(TextView)convertView.findViewById(R.id.mainnews_postion_tv);
            vh.mainnews_title_tv=(TextView)convertView.findViewById(R.id.mainnews_title_tv);
            vh.mainnews_content_tv=(TextView)convertView.findViewById(R.id.mainnews_content_tv);
            vh.mainnews_photo_img=(ImageView) convertView.findViewById(R.id.mainnews_photo_img);
            convertView.setTag(vh);
        }else{
            vh=(ViewHodler) convertView.getTag();
        }
        vh.mainnews_postion_tv.setText(position+1+"");
        vh.mainnews_title_tv.setText(datas.get(position).getTitle());
        vh.mainnews_content_tv.setText(Html.fromHtml(datas.get(position).getContent()));
        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+datas.get(position).getImage(),vh.mainnews_photo_img, MyApp.getOthterOptions());
        return convertView;
    }
    class ViewHodler{
        public TextView mainnews_postion_tv,mainnews_title_tv,mainnews_content_tv;
        public ImageView mainnews_photo_img;
    }
}
