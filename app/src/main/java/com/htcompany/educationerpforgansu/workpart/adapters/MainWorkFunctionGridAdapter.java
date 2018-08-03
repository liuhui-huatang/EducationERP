package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFunctionEntity;

import java.util.List;

/**
 * 主页功能列表适配器
 * Created by wrb on 2016/12/2.
 */
public class MainWorkFunctionGridAdapter extends BaseAdapter{
    private Context context;
    private List<WorkFunctionEntity> strList;
    private LayoutInflater inflater;
    public MainWorkFunctionGridAdapter(Context context,List<WorkFunctionEntity> strList){
        this.context = context;
        this.strList = strList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        if(strList==null){
            return 0;
        }else {
            return strList.size();
        }
    }
    @Override
    public Object getItem(int position) {
        return strList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoder vh;
        if(convertView == null) {
            vh = new ViewHoder();
            convertView = inflater.inflate(R.layout.mainfunction_gridview_item, null);
            vh.mainfunction_name_tv = (TextView)convertView.findViewById(R.id.mainfunction_name_tv);
            vh.mainfunction_icon_img = (ImageView)convertView.findViewById(R.id.mainfunction_icon_img);
            vh.mainfunction_icon_tv=(TextView)convertView.findViewById(R.id.mainfunction_icon_tv);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHoder) convertView.getTag();
        }
        if(FunctionDatas.WORK_GRBG_GZL.equals(strList.get(position).getFunflag())){
            vh.mainfunction_icon_tv.setVisibility(View.VISIBLE);
            vh.mainfunction_icon_tv.setText(MyApp.msendcount+"条消息");
        }else{
            vh.mainfunction_icon_tv.setVisibility(View.GONE);
        }
        vh.mainfunction_name_tv.setText(strList.get(position).getFunname());
        vh.mainfunction_icon_img.setImageResource(strList.get(position).getFunimg());
        convertView.setBackgroundResource(R.drawable.mainfuntion_selctor);
        return convertView;
    }
    class ViewHoder{
        public TextView mainfunction_name_tv,mainfunction_icon_tv;
        public ImageView mainfunction_icon_img;
    }
}
