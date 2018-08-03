package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationTaskDistributionAdapter;

/**
 * 授课任务分配
 * Created by wrb on 2016/11/21.
 */
public class EducationTaskDistributionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView educationtaskdistribution_lv;
    private EducationTaskDistributionAdapter taskDistributionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationtaskdistribution_activity);
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        educationtaskdistribution_lv = (ListView)this.findViewById(R.id.educationtaskdistribution_lv);
        taskDistributionAdapter = new EducationTaskDistributionAdapter(this);
        educationtaskdistribution_lv.setAdapter(taskDistributionAdapter);
    }
    public void initViewVlaues(){
        title.setText("授课任务分配");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        educationtaskdistribution_lv.setOnItemClickListener(reserverClicer);
    }
    public AdapterView.OnItemClickListener reserverClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Intent intent = new Intent(EducationTaskDistributionActivity.this,EducationSearchStudent0ptionalClassDetailsActivity.class);
//            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
}
