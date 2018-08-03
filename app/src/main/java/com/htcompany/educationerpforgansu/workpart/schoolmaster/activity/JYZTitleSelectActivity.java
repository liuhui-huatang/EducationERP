package com.htcompany.educationerpforgansu.workpart.schoolmaster.activity;

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
import com.htcompany.educationerpforgansu.internet.SchoolMaster.SchoolMaterInternet;
import com.htcompany.educationerpforgansu.internet.SchoolMaster.SchoolMaterPersener;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter.JYZTitleSelectAdapter;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.JYZTitleEntity;

import java.util.List;

/**
 * 教研组主题选择
 * Created by wrb on 2017/5/13.
 */
public class JYZTitleSelectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView alladdress_lv;
    private JYZTitleSelectAdapter titleSelectAdapter;
    private List<JYZTitleEntity> datas;
    //网络请求类
    private SchoolMaterInternet materInternet;
    private SchoolMaterPersener materPersener;
    private WaitDialog waitDialog=null;
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
        materInternet = new SchoolMaterInternet(this,myHandler);
        materPersener=new SchoolMaterPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        materInternet.getJYZKH_title_ListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        alladdress_lv = (ListView)this.findViewById(R.id.alladdress_lv);
    }
    public void initViewVlaues(){
        title.setText("选择主题");
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
            JYZTitleEntity entity = (JYZTitleEntity) titleSelectAdapter.getItem(position);
            Intent intent = new Intent();
            intent.putExtra("entity",entity);
            setResult(100,intent);
            finish();
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
                    ToastUtil.showToast("连接超时",JYZTitleSelectActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    datas = materPersener.parseJYZ_TITLE_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        titleSelectAdapter= new JYZTitleSelectAdapter(JYZTitleSelectActivity.this,datas);
                        alladdress_lv.setAdapter(titleSelectAdapter);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",JYZTitleSelectActivity.this);
                    break;
            }
        }
    };
}
