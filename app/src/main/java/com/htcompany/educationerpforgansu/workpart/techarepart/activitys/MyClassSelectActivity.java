package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.MyClassSelectAadapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.MyClassSelectEntity;

import java.util.List;

/**
 * 我的班级选择
 * Created by wrb on 2017/5/9.
 */
public class MyClassSelectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView teachermyclassselect_lv;
    private MyClassSelectAadapter titleSelectAdapter;
    private List<MyClassSelectEntity> datas;
    private String flag="";
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_myclassselect_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initEvnets();
    }
    public void initDatas(){
        flag = getIntent().getStringExtra("flag");
        teacherInternet = new WorkTeacherInternet(this,myHandler);
        teacherPersener=new WorkTeacherPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getMyClassSelectDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        teachermyclassselect_lv = (ListView)this.findViewById(R.id.teachermyclassselect_lv);
    }
    public void initViewVlaues(){
        title.setText("选择班级");
    }
    public void initEvnets(){
        reback_btn.setOnClickListener(this);
        teachermyclassselect_lv.setOnItemClickListener(itemClickListener);
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
            MyClassSelectEntity entity=(MyClassSelectEntity)titleSelectAdapter.getItem(position);
            Intent intent = new Intent(MyClassSelectActivity.this,ClassTableTimeActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",MyClassSelectActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    datas = teacherPersener.parseMyClassSelectData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        titleSelectAdapter= new MyClassSelectAadapter(MyClassSelectActivity.this,datas);
                        teachermyclassselect_lv.setAdapter(titleSelectAdapter);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyClassSelectActivity.this);
                    break;
            }
        }
    };
}
