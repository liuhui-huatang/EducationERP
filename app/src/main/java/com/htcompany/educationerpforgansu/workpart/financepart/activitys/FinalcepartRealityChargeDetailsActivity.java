package com.htcompany.educationerpforgansu.workpart.financepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDListView;
import com.htcompany.educationerpforgansu.workpart.financepart.adapters.FinalcepartRealityChargeItemAdapter;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.RealityChargeEntity;

/**
 * 实际收费记录详情
 * Created by wrb on 2016/11/16.
 */
public class FinalcepartRealityChargeDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView rcdetails_name_tv,rcdetails_sfbz_tv,rcdetails_sfje_tv,
            rcdetails_sftime_tv,rcdetails_sfbh_tv,rcdetails_skr_tv,rcdetails_jxb_tv,
            rcdetails_bzr_tv,rcdetails_studentname_tv,rcdetails_studentcode_tv,
            rcdetails_fcstatus_tv,rcdetails_fcrname_tv,rcdetails_fctime_tv;
    private FinalcepartRealityChargeItemAdapter realityChargeItemAdapter;
    private GDListView rchagetiaomu_lv;
    private RealityChargeEntity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalcepartrealitychargedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (RealityChargeEntity)getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        rcdetails_name_tv= (TextView)this.findViewById(R.id.rcdetails_name_tv);
        rcdetails_sfbz_tv= (TextView)this.findViewById(R.id.rcdetails_sfbz_tv);
        rcdetails_sfje_tv= (TextView)this.findViewById(R.id.rcdetails_sfje_tv);
        rcdetails_sftime_tv= (TextView)this.findViewById(R.id.rcdetails_sftime_tv);
        rcdetails_sfbh_tv= (TextView)this.findViewById(R.id.rcdetails_sfbh_tv);
        rcdetails_skr_tv= (TextView)this.findViewById(R.id.rcdetails_skr_tv);
        rcdetails_jxb_tv= (TextView)this.findViewById(R.id.rcdetails_jxb_tv);
        rcdetails_bzr_tv= (TextView)this.findViewById(R.id.rcdetails_bzr_tv);
        rcdetails_studentname_tv= (TextView)this.findViewById(R.id.rcdetails_studentname_tv);
        rcdetails_studentcode_tv= (TextView)this.findViewById(R.id.rcdetails_studentcode_tv);
        rcdetails_fcstatus_tv= (TextView)this.findViewById(R.id.rcdetails_fcstatus_tv);
        rcdetails_fcrname_tv= (TextView)this.findViewById(R.id.rcdetails_fcrname_tv);
        rcdetails_fctime_tv= (TextView)this.findViewById(R.id.rcdetails_fctime_tv);
        rchagetiaomu_lv = (GDListView)this.findViewById(R.id.rchagetiaomu_lv);
    }
    public void initViewValues(){
        title.setText("收费详情");
        if(entity!=null){
            rcdetails_name_tv.setText(entity.getProject_name());
            rcdetails_sfbz_tv.setText(entity.getBiaozhun());
            rcdetails_sfje_tv.setText(entity.getMoney());
            rcdetails_sftime_tv.setText(entity.getTime());
            rcdetails_sfbh_tv.setText(entity.getShouju());
            rcdetails_skr_tv.setText(entity.getShoukuan());
            rcdetails_jxb_tv.setText(entity.getJiaoxueban());
            rcdetails_bzr_tv.setText(entity.getBanzhuren());
            rcdetails_studentname_tv.setText(entity.getXuesheng());
            rcdetails_studentcode_tv.setText(entity.getXuehao());
            rcdetails_fcstatus_tv.setText(entity.getFengcunStatus());
            rcdetails_fcrname_tv.setText(entity.getFengcun());
            rcdetails_fctime_tv.setText(entity.getFengcunTime());
            if(entity.getTiaomu()!=null&&entity.getTiaomu().size()>0){
                realityChargeItemAdapter = new FinalcepartRealityChargeItemAdapter(this,entity.getTiaomu());
                rchagetiaomu_lv.setAdapter(realityChargeItemAdapter);
            }
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.reback_btn:
               this.finish();
               break;
       }

    }
}
