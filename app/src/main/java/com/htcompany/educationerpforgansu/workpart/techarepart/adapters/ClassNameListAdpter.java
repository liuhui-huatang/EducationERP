package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNameEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 班级名单
 * Created by weiruibin on 2017/6/14.
 */

public class ClassNameListAdpter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassNameEntity> datas;
    public ClassNameListAdpter(Context context,List<ClassNameEntity> datas){
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
            convertView=inflater.inflate(R.layout.classnamelistadpter_item,null);
            vh.classnamegrd_photo_img = (XCRoundAndOvalImageView) convertView.findViewById(R.id.classnamegrd_photo_img);
            vh.classnamegridadapter_name_tv = (TextView) convertView.findViewById(R.id.classnamegridadapter_name_tv);
            vh.classnamegrdadapter_bj_tv = (TextView)convertView.findViewById(R.id.classnamegrdadapter_bj_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+datas.get(position).getPhoto(),
                vh.classnamegrd_photo_img, MyApp.getOptions());
        vh.classnamegridadapter_name_tv.setText(datas.get(position).getTruename());
        vh.classnamegrdadapter_bj_tv.setText(datas.get(position).getTrbd_jxb());
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView classnamegrd_photo_img;
        public TextView classnamegridadapter_name_tv,classnamegrdadapter_bj_tv;
    }
}
