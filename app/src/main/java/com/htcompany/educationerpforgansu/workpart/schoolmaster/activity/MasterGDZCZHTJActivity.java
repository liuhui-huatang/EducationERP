package com.htcompany.educationerpforgansu.workpart.schoolmaster.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.SchoolMaster.SchoolMaterInternet;
import com.htcompany.educationerpforgansu.internet.SchoolMaster.SchoolMaterPersener;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter.MasterGDZCZHTJAdapter;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.MasterGDZCZHTJEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 固定资产综合统计
 * Created by wrb on 2017/4/6.
 */
public class MasterGDZCZHTJActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView mastergdzctj_wsj_img;
    private ListView mastergdzctj_lv;
    private MasterGDZCZHTJAdapter gdzczhtjAdapter;
    //网络请求类
    private SchoolMaterInternet materInternet;
    private SchoolMaterPersener materPesener;
    private WaitDialog waitDialog=null;
    private List<MasterGDZCZHTJEntity> gdzczhtjEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_gdzczhtj_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        gdzczhtjEntities = new ArrayList<MasterGDZCZHTJEntity>();
        materInternet = new SchoolMaterInternet(this,myHandler);
        materPesener=new SchoolMaterPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        materInternet.getMasterGDZCTJ_ListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        mastergdzctj_wsj_img=(ImageView)this.findViewById(R.id.mastergdzctj_wsj_img);
        mastergdzctj_lv=(ListView)this.findViewById(R.id.mastergdzctj_lv);
        gdzczhtjAdapter=new MasterGDZCZHTJAdapter(this,gdzczhtjEntities);
        mastergdzctj_lv.setAdapter(gdzczhtjAdapter);
    }
    public void initViewValues(){
        title.setText("固定资产统计");
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
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",MasterGDZCZHTJActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MasterGDZCZHTJEntity> datas = materPesener.parseGDZCTJ_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        mastergdzctj_wsj_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        mastergdzctj_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MasterGDZCZHTJActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<MasterGDZCZHTJEntity> datas){
        if(gdzczhtjEntities.size()>0){
            gdzczhtjEntities.clear();
        }
        for(MasterGDZCZHTJEntity entity:datas){
            gdzczhtjEntities.add(entity);
        }
        gdzczhtjAdapter.notifyDataSetChanged();
    }
}
