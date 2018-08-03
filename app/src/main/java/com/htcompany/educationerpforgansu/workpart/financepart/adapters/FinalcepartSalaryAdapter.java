package com.htcompany.educationerpforgansu.workpart.financepart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.SalaryEntity;

import java.util.List;

/**
 * //工资管理
 * Created by wrb on 2016/12/29.
 */
public class FinalcepartSalaryAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<SalaryEntity> datas;
    public FinalcepartSalaryAdapter(Context context,List<SalaryEntity> datas){
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
            vh =new ViewHodler();
            convertView =inflater.inflate(R.layout.mysalaries_lv_item,null);
            vh.salarieslv_name_tv=(TextView)convertView.findViewById(R.id.salarieslv_name_tv);
            vh.salarieslv_time_tv=(TextView)convertView.findViewById(R.id.salarieslv_time_tv);
            vh.salarieslv_money_tv=(TextView)convertView.findViewById(R.id.salarieslv_money_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler) convertView.getTag();
        }
        vh.salarieslv_name_tv.setText(datas.get(position).getUserName());
        vh.salarieslv_time_tv.setText(datas.get(position).getTime());
        vh.salarieslv_money_tv.setText("¥"+datas.get(position).getMoney());
        return convertView;
    }
    class ViewHodler{
        public TextView salarieslv_name_tv,salarieslv_time_tv,salarieslv_money_tv;
    }
}
