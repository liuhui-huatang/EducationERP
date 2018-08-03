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
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormRoomSelectAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormRoomEntity;

import java.util.List;

/**
 * 宿舍选择界面
 * Created by wrb on 2017/4/24.
 */
public class DormRoomSelectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView dormselect_lv;
    private DormRoomSelectAdapter roomSelectAdapter;
    private List<DormRoomEntity> datas;
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
        datas = (List<DormRoomEntity>) getIntent().getSerializableExtra("roomEntities");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        dormselect_lv = (ListView)this.findViewById(R.id.dormselect_lv);
        if(datas!=null&&datas.size()>0) {
            roomSelectAdapter = new DormRoomSelectAdapter(this, datas);
            dormselect_lv.setAdapter(roomSelectAdapter);
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
            DormRoomEntity entity = (DormRoomEntity) roomSelectAdapter.getItem(position);
            Intent intent = new Intent();
            intent.putExtra("entity",entity);
            setResult(102,intent);
            finish();
        }
    };
}
