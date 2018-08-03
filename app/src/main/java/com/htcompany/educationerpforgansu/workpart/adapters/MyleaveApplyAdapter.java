package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.MyLeaveApplyEntity;

import java.util.List;

/**
 * 请假列表适配器
 * Created by wrb on 2016/11/10.
 */
public class MyleaveApplyAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MyLeaveApplyEntity> datas;
    public MyleaveApplyAdapter(Context context,List<MyLeaveApplyEntity> datas){
        this.context = context;
        this.datas = datas;
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
            convertView = inflater.inflate(R.layout.myleaveapply_lv_adpter,null);
            vh.myleaveapply_part_tv = (TextView)convertView.findViewById(R.id.myleaveapply_part_tv);
            vh.myleaveapply_name_tv = (TextView)convertView.findViewById(R.id.myleaveapply_name_tv);
            vh.myleaveapply_time_tv = (TextView)convertView.findViewById(R.id.myleaveapply_time_tv);
            vh.myleaveapply_content_tv = (TextView)convertView.findViewById(R.id.myleaveapply_content_tv);
            vh.myleaveapply_shzt_tv = (TextView)convertView.findViewById(R.id.myleaveapply_shzt_tv);
            vh.myleaveapply_shzt_img=(ImageView) convertView.findViewById(R.id.myleaveapply_shzt_img);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.myleaveapply_part_tv.setText(datas.get(position).getOrg());
        vh.myleaveapply_name_tv.setText("类型:"+datas.get(position).getTypename());
        vh.myleaveapply_time_tv.setText(datas.get(position).getStart()+"至"+datas.get(position).getEnd());
        vh.myleaveapply_content_tv.setText("事由:"+datas.get(position).getReason());
        if("审核通过".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_shzt_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_shzt_tv.setTextColor(context.getResources().getColor(R.color.qj_btncolor));
            vh.myleaveapply_shzt_img.setImageResource(R.mipmap.qj_togguo);
        }else if("已提交".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_shzt_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_shzt_tv.setTextColor(context.getResources().getColor(R.color.qj_btn2color));
            vh.myleaveapply_shzt_img.setImageResource(R.mipmap.qj_yitij);
        }else if("审核未通过".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_shzt_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_shzt_tv.setTextColor(context.getResources().getColor(R.color.qj_btn3color));
            vh.myleaveapply_shzt_img.setImageResource(R.mipmap.qj_weitongguo);
        }else if("已销假".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_shzt_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_shzt_tv.setTextColor(context.getResources().getColor(R.color.qj_btn4color));
            vh.myleaveapply_shzt_img.setImageResource(R.mipmap.qj_xiaoij);
        }
        return convertView;
    }

    public class ViewHodler{
        public TextView myleaveapply_part_tv,myleaveapply_name_tv,myleaveapply_time_tv,myleaveapply_content_tv;
        public TextView myleaveapply_shzt_tv;
        public ImageView myleaveapply_shzt_img;
    }
}
