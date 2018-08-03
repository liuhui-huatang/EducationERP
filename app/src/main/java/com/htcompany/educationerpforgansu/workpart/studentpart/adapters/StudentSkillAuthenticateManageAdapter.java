package com.htcompany.educationerpforgansu.workpart.studentpart.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;

/**
 * 技能鉴定报名
 * Created by wrb on 2016/11/23.
 */
public class StudentSkillAuthenticateManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    public StudentSkillAuthenticateManageAdapter(Context context){
        this.context= context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder vh;
        if(convertView==null){
            vh = new ViewHoder();
            convertView = inflater.inflate(R.layout.studentskillauthenticatemanageadapter_item,null);
            vh.skillauthenticateadapter_bg_ll = (LinearLayout)convertView.findViewById(R.id.skillauthenticateadapter_bg_ll);
            vh.skillauthenticateadapter_line_tv = (TextView)convertView.findViewById(R.id.skillauthenticateadapter_line_tv);
            vh.skillauthenticateadapter_text_tv = (TextView)convertView.findViewById(R.id.skillauthenticateadapter_text_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHoder) convertView.getTag();
        }
        int viewIndex = position%FunctionDatas.getBG_JNJDDatas().size();
        vh.skillauthenticateadapter_bg_ll.setBackground(context.getResources().getDrawable(FunctionDatas.getBG_JNJDDatas().get(viewIndex)));
        vh.skillauthenticateadapter_line_tv.setBackgroundColor(context.getResources().getColor(FunctionDatas.getTVCOLOR_JNJDDatas().get(viewIndex)));
        vh.skillauthenticateadapter_text_tv.setTextColor(context.getResources().getColor(FunctionDatas.getTVCOLOR_JNJDDatas().get(viewIndex)));
        return convertView;
    }
    class ViewHoder{
        public LinearLayout skillauthenticateadapter_bg_ll;
        public TextView skillauthenticateadapter_line_tv,skillauthenticateadapter_text_tv;
    }
}
