package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDListView;
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormSanitationManasgeDetailsAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageEntity;

/**
 * 卫生查询详情
 * Created by wrb on 2016/11/23.
 */
public class DormSanitationDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private DormSanitationManageEntity manageEntity=null;
    Intent intent = null;
    private TextView sanitationd_time_tv,sanitationd_ssl_tv,sanitationd_room_tv,
            sanitationd_roomtype_tv,sanitationd_zcount_tv;
    private GDListView detialsfxx_lv;
   private DormSanitationManasgeDetailsAdapter dormSanitationManasgeDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormsanitationdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        manageEntity = (DormSanitationManageEntity) getIntent().getSerializableExtra("manageEntity");
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        sanitationd_time_tv= (TextView) this.findViewById(R.id.sanitationd_time_tv);
                sanitationd_ssl_tv= (TextView) this.findViewById(R.id.sanitationd_ssl_tv);
                sanitationd_room_tv= (TextView) this.findViewById(R.id.sanitationd_room_tv);
                sanitationd_roomtype_tv= (TextView) this.findViewById(R.id.sanitationd_roomtype_tv);
        sanitationd_zcount_tv=(TextView)this.findViewById(R.id.sanitationd_zcount_tv);
        detialsfxx_lv=(GDListView)this.findViewById(R.id.detialsfxx_lv);
    }

    public void initViewValues() {
        title.setText("卫生详情");
        if(manageEntity!=null){
            sanitationd_time_tv.setText(manageEntity.getDa_data());
            sanitationd_ssl_tv.setText(manageEntity.getSushe_lou());
            sanitationd_room_tv.setText(manageEntity.getSushe_num());
            sanitationd_roomtype_tv.setText(manageEntity.getSushe_type());
            sanitationd_zcount_tv.setText(manageEntity.getDa_allpoint());
            if(manageEntity.getOtherField()!=null&&manageEntity.getOtherField().size()>0){
                dormSanitationManasgeDetailsAdapter = new DormSanitationManasgeDetailsAdapter(this,manageEntity.getOtherField());
                detialsfxx_lv.setAdapter(dormSanitationManasgeDetailsAdapter);
            }
        }
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }

}
