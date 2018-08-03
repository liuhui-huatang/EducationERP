package com.htcompany.educationerpforgansu.workpart.techarepart.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassDMCEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassDMStatusEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 点名册
 * Created by WRB on 2017/1/19.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class StudentRollBookAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassDMCEntity> datas;
    private WorkTeacherInternet teacherInternet;
    private String kebiao_id="";
    public StudentRollBookAdapter(Context context,List<ClassDMCEntity> datas,WorkTeacherInternet teacherInternet,String kebiao_id){
        this.context = context;
        this.kebiao_id = kebiao_id;
        this.teacherInternet =teacherInternet;
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
            convertView = inflater.inflate(R.layout.studentrollbookadapter_item,null);
            vh.studentrollbook_photo_img = (ImageView)convertView.findViewById(R.id.studentrollbook_photo_img);
            vh.studentrollbook_name_tv = (TextView)convertView.findViewById(R.id.studentrollbook_name_tv);
            vh.studentrollbook_dm_grd = (GDGridView)convertView.findViewById(R.id.studentrollbook_dm_grd);
            convertView.setTag(vh);
        }else {
            vh = (ViewHodler) convertView.getTag();
        }
        Glide.with(context).load(InterfaceManager.siteURLIP+datas.get(position).getPhoto())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.studentrollbook_photo_img);
        vh.studentrollbook_name_tv.setText(datas.get(position).getTruename());
        StatusAdapter statusAdapter = new StatusAdapter(context,datas.get(position).getShuju(),datas.get(position).getUid());
        vh.studentrollbook_dm_grd.setAdapter(statusAdapter);
        return convertView;
    }
    class ViewHodler{
        public ImageView studentrollbook_photo_img;
        public TextView studentrollbook_name_tv,studentrollbook_qj_tv,studentrollbook_cd_tv,studentrollbook_zt_tv,studentrollbook_kk_tv;
        public GDGridView studentrollbook_dm_grd;
    }
    public class StatusAdapter extends BaseAdapter{
        private Context context;
        private LayoutInflater inflater;
        private List<ClassDMStatusEntity> datas2;
        private String student_id;
        public StatusAdapter(Context context,List<ClassDMStatusEntity> datas2,String student_id){
            this.context = context;
            this.student_id =student_id;
            inflater = LayoutInflater.from(context);
            this.datas2 =datas2;
        }
        @Override
        public int getCount() {
            return datas2.size();
        }

        @Override
        public Object getItem(int position) {
            return datas2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHodler vh;
            if(convertView==null){
                vh = new ViewHodler();
                convertView = inflater.inflate(R.layout.studentroll_dmzt_item,null);
                vh.studentrollbook_photo_img=(XCRoundAndOvalImageView)convertView.findViewById(R.id.studentrollbook_photo_img);
                vh.studentrollbook_zt_tv = (TextView)convertView.findViewById(R.id.studentrollbook_zt_tv);
                convertView.setTag(vh);
            }else{
                vh = (ViewHodler) convertView.getTag();
            }
            vh.studentrollbook_zt_tv.setText(datas2.get(position).getName());
            if("1".equals(datas2.get(position).getStatus())){
                vh.studentrollbook_zt_tv.setBackground(context.getResources().getDrawable(R.drawable.login_shape_editext_red));
                vh.studentrollbook_zt_tv.setTextColor(context.getResources().getColor(R.color.white));
            }else{
                vh.studentrollbook_zt_tv.setBackground(context.getResources().getDrawable(R.drawable.login_shape_editxt));
                vh.studentrollbook_zt_tv.setTextColor(context.getResources().getColor(R.color.xsgl_tv1_color));
            }
            vh.studentrollbook_zt_tv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //1 选中，0不选中
                    if("0".equals(datas2.get(position).getStatus())){
                        vh.studentrollbook_zt_tv.setBackground(context.getResources().getDrawable(R.drawable.login_shape_editext_red));
                        vh.studentrollbook_zt_tv.setTextColor(context.getResources().getColor(R.color.white));
                        datas2.get(position).setStatus("1");
                        notifyDataSetChanged();
                        //提交点名数据
                        teacherInternet.uploadClassDMZT(datas2.get(position).getKpi_id(),student_id,kebiao_id);
                    }{
                        vh.studentrollbook_zt_tv.setBackground(context.getResources().getDrawable(R.drawable.login_shape_editxt));
                        vh.studentrollbook_zt_tv.setTextColor(context.getResources().getColor(R.color.xsgl_tv1_color));
                        datas2.get(position).setStatus("0");
                        notifyDataSetChanged();
                        teacherInternet.uploadClassDMZT(datas2.get(position).getKpi_id(),student_id,kebiao_id);
                    }
                }
            });
            return convertView;
        }
        class ViewHodler{
            public TextView studentrollbook_zt_tv;
            public XCRoundAndOvalImageView studentrollbook_photo_img;
        }
    }
}
