package com.htcompany.educationerpforgansu.workpart.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFunctionEntity;

import java.util.List;

/**
 * 全部功能适配器
 * Created by wrb on 2016/12/2.
 */
public class AllFunctionTwoGridAdapter extends BaseAdapter{
    private Context context;
    private List<WorkFunctionEntity> strList;
    private LayoutInflater inflater;
    public static int showAdd=-1;
    private Handler myHandler;
    public AllFunctionTwoGridAdapter(Context context,List<WorkFunctionEntity> strList,Handler myHandler){
        this.context = context;
        this.strList = strList;
        this.myHandler = myHandler;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return strList.size();
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
            convertView = inflater.inflate(R.layout.function_gridview_item, null);
            vh.tv_dgv_item = (TextView)convertView.findViewById(R.id.tv_dgv_item);
            vh.iv_delete_dgv_item = (ImageView)convertView.findViewById(R.id.iv_delete_dgv_item);
            vh.iv_dgv_item= (ImageView)convertView.findViewById(R.id.iv_dgv_item);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHoder) convertView.getTag();
        }
        if(showAdd==1){
            vh.iv_delete_dgv_item.setVisibility(View.VISIBLE);
        }else{
            vh.iv_delete_dgv_item.setVisibility(View.GONE);
        }
        vh.iv_delete_dgv_item.setImageResource(R.mipmap.jwz_add);
        vh.iv_dgv_item.setImageResource(strList.get(position).getFunimg());
        vh.tv_dgv_item.setText(strList.get(position).getFunname());
        convertView.setBackgroundResource(R.drawable.jwz_selector_dgv_item);
        vh.iv_delete_dgv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(FunctionDatas.otherFunctionDatas.size()>0){
//                    FunctionDatas.otherFunctionDatas.remove(strList.get(position));
//                }
                WorkFunctionEntity entity = strList.get(position);
                entity.setIsmain("1");
                entity.setIspermission("0");
                FunctionDatas.mainFunctionDatas.add(entity);
                strList.remove(strList.get(position));
                notifyDataSetChanged();
                //通知我的应用适配器刷新
                myHandler.sendEmptyMessage(100);
            }
        });
        return convertView;
    }
    class ViewHoder{
        public TextView tv_dgv_item;
        public ImageView iv_delete_dgv_item,iv_dgv_item;
    }
    public void showViewAdd(int addpotsion){
        myHandler.sendEmptyMessage(100);
        showAdd=addpotsion;
        notifyDataSetChanged();
    }
    public void hideViewAdd(int addpotion){
        showAdd=addpotion;
        notifyDataSetChanged();
    }
}
