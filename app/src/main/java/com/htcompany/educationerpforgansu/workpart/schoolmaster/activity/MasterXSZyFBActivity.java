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
import com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter.MasterXSZYHZAdapter;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.MasterXSZYFBEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生专业分布统计
 * Created by WRB on 2017/4/6.
 */
public class MasterXSZyFBActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView bmaster_xszyfb_img;
    private ListView xszyfb_lv;
    private MasterXSZYHZAdapter xshzAdapter;
    //网络请求类
    private SchoolMaterInternet materInternet;
    private SchoolMaterPersener materPersener;
    private WaitDialog waitDialog=null;
    private List<MasterXSZYFBEntity> xszyfbEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_xszyfb_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        xszyfbEntities = new ArrayList<MasterXSZYFBEntity>();
        materInternet = new SchoolMaterInternet(this,myHandler);
        materPersener=new SchoolMaterPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        materInternet.getMasterXSZYFB_ListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        bmaster_xszyfb_img=(ImageView)this.findViewById(R.id.bmaster_xszyfb_img);
        xszyfb_lv = (ListView)this.findViewById(R.id.xszyfb_lv);
        xshzAdapter = new MasterXSZYHZAdapter(this);
        xszyfb_lv.setAdapter(xshzAdapter);
    }
    public void initViewValues(){
        title.setText("学生专业分布统计");
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
                    ToastUtil.showToast("连接超时",MasterXSZyFBActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MasterXSZYFBEntity> datas = materPersener.parseXSZYFBB_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        bmaster_xszyfb_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        bmaster_xszyfb_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MasterXSZyFBActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<MasterXSZYFBEntity> datas){
        if(xszyfbEntities.size()>0){
            xszyfbEntities.clear();
        }
        for(MasterXSZYFBEntity entity:datas){
            xszyfbEntities.add(entity);
        }
        xshzAdapter.notifyDataSetChanged();
    }
}
