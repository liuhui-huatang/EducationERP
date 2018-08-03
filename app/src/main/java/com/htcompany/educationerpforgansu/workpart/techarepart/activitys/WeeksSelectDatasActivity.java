package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.WeeksSelectDatasAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 代课调课周次选择
 * Created by wrb on 2017/4/22.
 */
public class WeeksSelectDatasActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn,right_three_btn;
    private ListView weekselectdata_lv;
    private WeeksSelectDatasAdapter weeksAdapter;
    private List<String> allWeeksEntities;
    private TextView weeksselectbottom_qx_tv,weeksselectbottom_cancle_tv;
    int weeksnum;
    private String weekslist="";
    public List<Integer> selectSTR=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weeksselectdatas_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        allWeeksEntities = new ArrayList<String>();
        weeksnum= getIntent().getIntExtra("weeksnum",0);
        if(weeksnum>0) {
            for (int i = 0; i < weeksnum; i++) {
                allWeeksEntities.add(i+1+"");
            }
        }
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        weeksselectbottom_qx_tv= (TextView)this.findViewById(R.id.weeksselectbottom_qx_tv);
        weeksselectbottom_cancle_tv= (TextView)this.findViewById(R.id.weeksselectbottom_cancle_tv);
        weekselectdata_lv = (ListView)this.findViewById(R.id.weekselectdata_lv);
        weeksAdapter = new WeeksSelectDatasAdapter(this,allWeeksEntities);
        weekselectdata_lv.setAdapter(weeksAdapter);
    }
    public void initViewValues(){
        title.setText("周次选择");
        right_three_btn.setVisibility(View.VISIBLE);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        weeksselectbottom_qx_tv.setOnClickListener(this);
        weeksselectbottom_cancle_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //确定选择人员
                Iterator iterator = weeksAdapter.map.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry entry = (Map.Entry) iterator.next();//把hashmap转成Iterator再迭代到entry
                    int key = (Integer)entry.getKey();	//从entry获取key
                    boolean val = (Boolean) entry.getValue();	//从entry获取value
                    if(val){
                        selectSTR.add(key+1);
                    }
                }
                Collections.sort(selectSTR,new Comparator<Integer>(){
                    public int compare(Integer arg0, Integer arg1) {
                        return arg0.compareTo(arg1);
                    }
                });
                for(Integer srt:selectSTR){
                    if("".equals(weekslist)){
                            weekslist=srt+"";
                        }else{
                            weekslist=weekslist+","+srt;
                        }
                }
                Intent intent = new Intent();
                intent.putExtra("weekStr",weekslist);
                setResult(102,intent);
                this.finish();
                break;
            case R.id.weeksselectbottom_qx_tv:
                //全选
                if(weeksnum>0) {
                    for (int i = 0; i < weeksnum; i++) {
                        weeksAdapter.map.put(i,true);
                        weeksAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.weeksselectbottom_cancle_tv:
                //取消
                if(weeksAdapter.map!=null){
                    weeksAdapter.map.clear();
                }
                weeksAdapter.notifyDataSetChanged();
                break;
        }
    }
}
