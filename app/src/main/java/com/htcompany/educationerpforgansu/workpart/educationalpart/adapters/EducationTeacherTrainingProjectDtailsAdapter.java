package com.htcompany.educationerpforgansu.workpart.educationalpart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.internet.educationalpart.EducationalPartInternet;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationTeacherTrainingPersonEntity;

import java.util.List;

/**
 * 教师培训人员报名适配器
 * Created by wrb on 2017/5/6.
 */
public class EducationTeacherTrainingProjectDtailsAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<EducationTeacherTrainingPersonEntity> datas;
    private EducationalPartInternet educationalPartInternet;
    public EducationTeacherTrainingProjectDtailsAdapter(Context context,List<EducationTeacherTrainingPersonEntity> datas,EducationalPartInternet educationalPartInternet){
        this.context = context;
        this.educationalPartInternet = educationalPartInternet;
        this.datas=datas;
        inflater =LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView=inflater.inflate(R.layout.educationteachertrainingprojectdtailsadapter_item,null);
            vh.educationteachertrainingprojectdtailsadapter_name_tv=(TextView)convertView.findViewById(R.id.educationteachertrainingprojectdtailsadapter_name_tv);
            vh.educationteachertrainingprojectdtailsadapter_zgh_tv=(TextView)convertView.findViewById(R.id.educationteachertrainingprojectdtailsadapter_zgh_tv);
            vh.educationteachertrainingprojectdtailsadapter_agree_tv=(TextView)convertView.findViewById(R.id.educationteachertrainingprojectdtailsadapter_agree_tv);
            vh.educationteachertrainingprojectdtailsadapter_reback_tv=(TextView)convertView.findViewById(R.id.educationteachertrainingprojectdtailsadapter_reback_tv);
            vh.educationteachertrainingprojectdtailsadapter_czbtn_ll=(LinearLayout)convertView.findViewById(R.id.educationteachertrainingprojectdtailsadapter_czbtn_ll);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.educationteachertrainingprojectdtailsadapter_name_tv.setText(datas.get(position).getUser_name());
        if("1".equals(datas.get(position).getStatus())){
            vh.educationteachertrainingprojectdtailsadapter_zgh_tv.setText("待审核");
            vh.educationteachertrainingprojectdtailsadapter_czbtn_ll.setVisibility(View.VISIBLE);
            vh.educationteachertrainingprojectdtailsadapter_zgh_tv.setTextColor(context.getResources().getColor(R.color.qj_btn2color));
        }else if("2".equals(datas.get(position).getStatus())){
            vh.educationteachertrainingprojectdtailsadapter_zgh_tv.setText("已通过");
            vh.educationteachertrainingprojectdtailsadapter_czbtn_ll.setVisibility(View.GONE);
            vh.educationteachertrainingprojectdtailsadapter_zgh_tv.setTextColor(context.getResources().getColor(R.color.qj_btncolor));

        }else if("3".equals(datas.get(position).getStatus())){
            vh.educationteachertrainingprojectdtailsadapter_zgh_tv.setText("未通过");
            vh.educationteachertrainingprojectdtailsadapter_czbtn_ll.setVisibility(View.GONE);
            vh.educationteachertrainingprojectdtailsadapter_zgh_tv.setTextColor(context.getResources().getColor(R.color.qj_btn3color));

        }

        vh.educationteachertrainingprojectdtailsadapter_agree_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmMethods(datas.get(position).getId(),"2");
                datas.get(position).setStatus("2");
                notifyDataSetChanged();
            }
        });
        vh.educationteachertrainingprojectdtailsadapter_reback_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmMethods(datas.get(position).getId(),"3");
                datas.get(position).setStatus("3");
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    class ViewHodler{
        public TextView educationteachertrainingprojectdtailsadapter_name_tv,educationteachertrainingprojectdtailsadapter_zgh_tv,
                educationteachertrainingprojectdtailsadapter_agree_tv,educationteachertrainingprojectdtailsadapter_reback_tv;
        public LinearLayout educationteachertrainingprojectdtailsadapter_czbtn_ll;
    }
    public void bmMethods(String id,String status){
        educationalPartInternet.uploadTeacherTrainProjectPersonDatas(id,status);
    }
}
