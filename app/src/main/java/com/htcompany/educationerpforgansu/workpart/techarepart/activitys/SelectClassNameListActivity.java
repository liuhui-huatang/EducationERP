package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.SelectClassNameListAdapter;
/**
 * 选修课班级化名册
 * Created by wrb on 2016/11/14.
 */
public class SelectClassNameListActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private GridView selectclassname_grid;
    private SelectClassNameListAdapter classNameListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectclassnamelist_activity);
        initView();
        initViewValues();
        initOnclicEvents();
    }
    public void initView(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        selectclassname_grid = (GridView)this.findViewById(R.id.selectclassname_grid);
//        classNameListAdapter = new SelectClassNameListAdapter(this);
//        selectclassname_grid.setAdapter(classNameListAdapter);
    }
    public void initViewValues(){
        title.setText("学生名单");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        selectclassname_grid.setOnItemClickListener(gridClicer);
    }
    public AdapterView.OnItemClickListener gridClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SelectClassNameListActivity.this,SelectClassNameDetailsActivity.class);
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
