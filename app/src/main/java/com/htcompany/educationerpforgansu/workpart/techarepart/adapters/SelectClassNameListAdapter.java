package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

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
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNameEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 选修课化名册适配器
 * Created by wrb on 2016/11/14.
 */
public class SelectClassNameListAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassNameEntity> datas;
    public SelectClassNameListAdapter(Context context,List<ClassNameEntity> datas){
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
            convertView=inflater.inflate(R.layout.selectclassnamelistadapter_item,null);
            vh.classnamelistadapter_photo_img = (XCRoundAndOvalImageView) convertView.findViewById(R.id.classnamelistadapter_photo_img);
            vh.classnamelistadapter_name_tv = (TextView) convertView.findViewById(R.id.classnamelistadapter_name_tv);
            vh.classnamelistadapter_bj_tv = (TextView)convertView.findViewById(R.id.classnamelistadapter_bj_tv);
            vh.classnamelistadapter_code_tv = (TextView)convertView.findViewById(R.id.classnamelistadapter_code_tv);
            vh.classnamelistadapter_zy_tv = (TextView)convertView.findViewById(R.id.classnamelistadapter_zy_tv);
            vh.classnamelistadapter_nj_tv = (TextView)convertView.findViewById(R.id.classnamelistadapter_nj_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+datas.get(position).getPhoto(),
                vh.classnamelistadapter_photo_img, MyApp.getOptions());
        vh.classnamelistadapter_name_tv.setText(datas.get(position).getTruename());
        vh.classnamelistadapter_bj_tv.setText("教学班:"+datas.get(position).getTrbd_jxb());
        vh.classnamelistadapter_code_tv.setText("学号:"+datas.get(position).getNumber());
        vh.classnamelistadapter_zy_tv.setText("专业:"+datas.get(position).getTrbd_major());
        vh.classnamelistadapter_nj_tv.setText("年级:"+datas.get(position).getTrbd_grade());
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView classnamelistadapter_photo_img;
        public TextView classnamelistadapter_name_tv,classnamelistadapter_bj_tv,
                classnamelistadapter_code_tv,classnamelistadapter_zy_tv,classnamelistadapter_nj_tv;
    }
}
