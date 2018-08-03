package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.AllClassAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有班级
 * Created by wrb on 2016/12/19.
 */
public class AllClassActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn,right_three_btn;
    private ListView alladdress_lv;
    private AllClassAdapter addressAdapter;
    private List<AllClassEntity> allAddressEntities;
    private List<AllClassEntity> selectDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alladdress_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        selectDatas = new ArrayList<AllClassEntity>();
        allAddressEntities = new ArrayList<AllClassEntity>();
        AllClassEntity entity1 = new AllClassEntity();
        entity1.setCname("电子0902");
        AllClassEntity entity2 = new AllClassEntity();
        entity2.setCname("软件0903");
        AllClassEntity entity3 = new AllClassEntity();
        entity3.setCname("会计0901");
        allAddressEntities.add(entity1);
        allAddressEntities.add(entity2);
        allAddressEntities.add(entity3);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        alladdress_lv = (ListView)this.findViewById(R.id.alladdress_lv);
        addressAdapter = new AllClassAdapter(this,allAddressEntities,myHandler);
        alladdress_lv.setAdapter(addressAdapter);
    }
    public void initViewValues(){
        title.setText("班级选择");
        right_three_btn.setVisibility(View.VISIBLE);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //确定班级人员
                Intent intent = new Intent();
                intent.putExtra("classdatas",(Serializable) selectDatas);
                setResult(102,intent);
                this.finish();
                break;
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b=null;
            switch (msg.what){
                case 101:
                    b = msg.getData();
                    AllClassEntity entity = (AllClassEntity) b.getSerializable("AllPartEntity");
                    addSelectDatas(entity);
                    break;
                case 102:
                    b = msg.getData();
                    AllClassEntity entity2 = (AllClassEntity) b.getSerializable("AllPartEntity");
                    removeSelectDatas(entity2);
                    break;
            }
        }
    };
    //添加选中人员数据
    public void addSelectDatas(AllClassEntity entity){
        selectDatas.add(entity);
    }
    public void removeSelectDatas(AllClassEntity entity){
        if(selectDatas.size()>0){
            selectDatas.remove(entity);
        }
    }
}
