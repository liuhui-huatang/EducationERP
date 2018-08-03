package com.htcompany.educationerpforgansu.homepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.GDListView;
import com.htcompany.educationerpforgansu.workpart.financepart.adapters.FinalSalaryItemAdapter;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.SalaryEntity;

/**
 * 工资详情
 * Created by wrb on 2016/11/8.
 */
public class MySalariesDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView mysalariesdetials_name_tv,mysalariesdetials_time_tv,mysalariesdetials_gzcode_tv,mysalariesdetials_hjje_tv;
    private GDListView salaryiesgd_lv;
    private FinalSalaryItemAdapter salaryItemAdapter;
    private SalaryEntity entity =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysalariesdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (SalaryEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        mysalariesdetials_hjje_tv= (TextView)this.findViewById(R.id.mysalariesdetials_hjje_tv);
        mysalariesdetials_name_tv= (TextView)this.findViewById(R.id.mysalariesdetials_name_tv);
        mysalariesdetials_time_tv= (TextView)this.findViewById(R.id.mysalariesdetials_time_tv);
        mysalariesdetials_gzcode_tv= (TextView)this.findViewById(R.id.mysalariesdetials_gzcode_tv);
        salaryiesgd_lv = (GDListView)this.findViewById(R.id.salaryiesgd_lv);
    }
    public void initViewValues(){
        title.setText("工资详情");
        if(entity!=null){
            mysalariesdetials_hjje_tv.setText(entity.getMoney());
            mysalariesdetials_name_tv.setText(entity.getUserName());
            mysalariesdetials_time_tv.setText(entity.getTime());
            mysalariesdetials_gzcode_tv.setText(entity.getKahao());
            if(entity.getTiaomu()!=null&&entity.getTiaomu().size()>0){
                salaryItemAdapter = new FinalSalaryItemAdapter(this,entity.getTiaomu());
                salaryiesgd_lv.setAdapter(salaryItemAdapter);
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
