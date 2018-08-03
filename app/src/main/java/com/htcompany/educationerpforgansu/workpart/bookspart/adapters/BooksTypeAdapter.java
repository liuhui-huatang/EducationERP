package com.htcompany.educationerpforgansu.workpart.bookspart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.bookspart.entity.BooksTypeEntity;

import java.util.List;

/**
 * 图书类别适配
 * Created by wrb on 2016/11/15.
 */
public class BooksTypeAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<BooksTypeEntity> datas;
    public BooksTypeAdapter(Context context, List<BooksTypeEntity> datas){
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
            vh= new ViewHodler();
            convertView  = inflater.inflate(R.layout.bookstypesadapter_item,null);
            vh.bookstypesadp_tv = (TextView)convertView.findViewById(R.id.bookstypesadp_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.bookstypesadp_tv.setText(datas.get(position).getName());
        return convertView;
    }
    class ViewHodler{
        public TextView bookstypesadp_tv;
    }
}
