package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.MyVotingAdapter;

/**
 * 投票
 * Created by wrb on 2016/11/11.
 */
public class MyVotingActivity extends BaseActivity implements View.OnClickListener{
    private ListView myvoting_lv;
    private TextView title;
    private RelativeLayout reback_btn;
    private MyVotingAdapter myVotingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myvoting_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public  void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        myvoting_lv = (ListView)this.findViewById(R.id.myvoting_lv);
        myVotingAdapter = new MyVotingAdapter(this);
        myvoting_lv.setAdapter(myVotingAdapter);
        myvoting_lv.setOnItemClickListener(votingClicer);
    }
    public void initViewValues(){
        title.setText("投票");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener votingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MyVotingActivity.this,MyVotingDetailsActivity.class);
            startActivity(intent);
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
