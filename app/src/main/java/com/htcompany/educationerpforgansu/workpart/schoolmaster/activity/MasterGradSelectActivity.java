package com.htcompany.educationerpforgansu.workpart.schoolmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter.MasterGradSelectAdapter;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.GradEntity;

import java.util.List;

/**
 * 年级选择界面
 * Created by wrb on 2017/5/11.
 */
public class MasterGradSelectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView alladdress_lv;
    private MasterGradSelectAdapter gradSelectAdapter;
    private List<GradEntity> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alladdress_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initEvnets();
    }
    public void initDatas(){
        datas=(List<GradEntity>) getIntent().getSerializableExtra("gradlists");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        alladdress_lv = (ListView)this.findViewById(R.id.alladdress_lv);
        if(datas!=null&&datas.size()>0){
            gradSelectAdapter = new MasterGradSelectAdapter(this,datas);
            alladdress_lv.setAdapter(gradSelectAdapter);
        }
    }
    public void initViewVlaues(){
        title.setText("选择年级");
    }
    public void initEvnets(){
        reback_btn.setOnClickListener(this);
        alladdress_lv.setOnItemClickListener(itemClickListener);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
        }
    }

    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            GradEntity entity = (GradEntity) gradSelectAdapter.getItem(position);
            Intent intent = new Intent();
            intent.putExtra("grad",entity);
            setResult(100,intent);
            finish();
        }
    };
}
