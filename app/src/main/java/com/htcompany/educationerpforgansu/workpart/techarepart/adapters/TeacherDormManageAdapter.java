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
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherDormManageEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 宿舍管理适配器
 * Created by wrb on 2016/11/30.
 */
public class TeacherDormManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeacherDormManageEntity> datas;
    public TeacherDormManageAdapter(Context context,List<TeacherDormManageEntity> datas){
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
            convertView=inflater.inflate(R.layout.teacherdormmanage_adapter,null);
            vh.teacherdormmanage_photo_img = (XCRoundAndOvalImageView) convertView.findViewById(R.id.teacherdormmanage_photo_img);
            vh.teacherdormmanage_name_tv = (TextView) convertView.findViewById(R.id.teacherdormmanage_name_tv);
            vh.teacherdormmanage_ssroom_tv = (TextView) convertView.findViewById(R.id.teacherdormmanage_ssroom_tv);
            vh.teacherdormmanage_xcode_tv = (TextView) convertView.findViewById(R.id.teacherdormmanage_xcode_tv);
            vh.teacherdormmanage_cw_tv = (TextView) convertView.findViewById(R.id.teacherdormmanage_cw_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+datas.get(position).getPhoto(),
                vh.teacherdormmanage_photo_img, MyApp.getOptions());
        vh.teacherdormmanage_name_tv.setText(datas.get(position).getTruename());
        vh.teacherdormmanage_ssroom_tv.setText("宿舍:"+datas.get(position).getHouse_name());
        vh.teacherdormmanage_xcode_tv.setText("学号:"+datas.get(position).getNumber());
        vh.teacherdormmanage_cw_tv.setText(datas.get(position).getBed());
        return convertView;
    }
    class ViewHodler{
        public XCRoundAndOvalImageView teacherdormmanage_photo_img;
        public TextView teacherdormmanage_name_tv,teacherdormmanage_ssroom_tv,teacherdormmanage_xcode_tv,teacherdormmanage_cw_tv;
    }
}
