package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormBulingSelectAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormBulingEntitiy;

import java.util.List;

/**
 * 宿舍楼选择界面
 * Created by wrb on 2017/4/24.
 */
public class DormBulingSelectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView dormselect_lv;
    private DormBulingSelectAdapter dormBulingSelectAdapter;
    private List<DormBulingEntitiy> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormselect_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initEvnets();
    }
    public void initDatas(){
        datas = (List<DormBulingEntitiy>) getIntent().getSerializableExtra("bulingEntitiys");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        dormselect_lv = (ListView)this.findViewById(R.id.dormselect_lv);
        if(datas!=null&&datas.size()>0) {
            dormBulingSelectAdapter = new DormBulingSelectAdapter(this, datas);
            dormselect_lv.setAdapter(dormBulingSelectAdapter);
        }
    }
    public void initViewVlaues(){
        title.setText("选择宿舍楼");
    }
    public void initEvnets(){
        reback_btn.setOnClickListener(this);
        dormselect_lv.setOnItemClickListener(itemClickListener);
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
            DormBulingEntitiy entity = (DormBulingEntitiy) dormBulingSelectAdapter.getItem(position);
            Intent intent = new Intent();
            intent.putExtra("entity",entity);
            setResult(101,intent);
            finish();
        }
    };
}
