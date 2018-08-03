package com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.VotingManageEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 投票管理适配器
 * Created by wrb on 2016/11/22.
 */
public class WorkVotingManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<VotingManageEntity> datas;
    public WorkVotingManageAdapter(Context context,List<VotingManageEntity> datas){
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
            convertView=inflater.inflate(R.layout.workvotingmanageadapter_item,null);
            vh.schollvoting_itemphoto_img = (ImageView)convertView.findViewById(R.id.schollvoting_itemphoto_img);
            vh.schollvoting_title_tv = (TextView)convertView.findViewById(R.id.schollvoting_title_tv);
            vh.schollvoting_fqr_tv = (TextView)convertView.findViewById(R.id.schollvoting_fqr_tv);
            vh.schollvoting_time_tv = (TextView)convertView.findViewById(R.id.schollvoting_time_tv);
            vh.schollvoting_cyrs_tv = (TextView)convertView.findViewById(R.id.schollvoting_cyrs_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        if(!"".equals(datas.get(position).getHeader_img())&&datas.get(position).getHeader_img()!=null){
            ImageLoader.getInstance().displayImage(datas.get(position).getHeader_img(),vh.schollvoting_itemphoto_img, MyApp.getOptions());
        }
        vh.schollvoting_title_tv.setText(datas.get(position).getName());
        vh.schollvoting_fqr_tv.setText("发起人:"+datas.get(position).getFaqiren());
        vh.schollvoting_time_tv.setText(datas.get(position).getStarttime()+"-"+datas.get(position).getEndtime());
        vh.schollvoting_cyrs_tv.setText(datas.get(position).getCount());
        return convertView;
    }
    class ViewHodler{
        public ImageView schollvoting_itemphoto_img;
        public TextView schollvoting_title_tv,schollvoting_fqr_tv,schollvoting_time_tv,schollvoting_cyrs_tv;
    }
}
