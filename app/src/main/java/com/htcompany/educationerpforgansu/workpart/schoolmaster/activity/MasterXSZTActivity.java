package com.htcompany.educationerpforgansu.workpart.schoolmaster.activity;

import android.content.Intent;
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
import com.htcompany.educationerpforgansu.workpart.schoolmaster.adapter.MasterXSZTTJAdapter;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.GradEntity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys.MasterXSZTEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生状态统计
 * Created by wrb on 2017/1/19.
 */
public class MasterXSZTActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView bmaster_xszt_img;
    private ListView master_xszt_lv;
    private MasterXSZTTJAdapter xszttjAdapter;
    private List<MasterXSZTEntity> xsztEntities;
    private List<GradEntity> gradEntities;
    private TextView master_xsztselectgrad_tv;//选择年级
    //网络请求类
    private SchoolMaterInternet materInternet;
    private SchoolMaterPersener materPersener;
    private WaitDialog waitDialog=null;
    private String gradName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_xszt_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        xsztEntities = new ArrayList<MasterXSZTEntity>();
        gradEntities=new ArrayList<GradEntity>();
        materInternet = new SchoolMaterInternet(this,myHandler);
        materPersener=new SchoolMaterPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        materInternet.getMasterXSZT_ListDatas(gradName);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        bmaster_xszt_img=(ImageView)this.findViewById(R.id.bmaster_xszt_img);
        master_xsztselectgrad_tv=(TextView)this.findViewById(R.id.master_xsztselectgrad_tv);
        master_xszt_lv=(ListView)this.findViewById(R.id.master_xszt_lv);
        xszttjAdapter = new MasterXSZTTJAdapter(this,xsztEntities);
        master_xszt_lv.setAdapter(xszttjAdapter);
    }
    public void initViewValues(){
        title.setText("学生状态统计");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        master_xsztselectgrad_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.master_xsztselectgrad_tv:
                //选择年级
                Intent intent = new Intent(MasterXSZTActivity.this, MasterGradSelectActivity.class);
                intent.putExtra("gradlists",(Serializable) gradEntities);
                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){

            switch (resultCode){
                case 100:
                    GradEntity entity = (GradEntity) data.getSerializableExtra("grad");
                    if(entity!=null){
                        master_xsztselectgrad_tv.setText(entity.getLabel());
                        gradName=entity.getValue();
                        materInternet.getMasterXSZT_ListDatas(gradName);
                    }
                    break;
            }

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
                    ToastUtil.showToast("连接超时",MasterXSZTActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    materPersener.parseXSZT_LISTData((String)msg.obj);
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    Bundle b=msg.getData();
                    List<GradEntity> gradedatas=(List<GradEntity>) b.getSerializable("gradedatas");
                    if(gradedatas!=null&&gradedatas.size()>0){
                        if (gradEntities.size()>0){
                            gradEntities.clear();
                        }
                        for(GradEntity entity:gradedatas){
                            gradEntities.add(entity);
                        }
                    }else{

                    }
                    //本周所有课程
                    List<MasterXSZTEntity> datas=(List<MasterXSZTEntity>) b.getSerializable("xsztEntities");
                    if(datas!=null&&datas.size()>0){
                        bmaster_xszt_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        if(xsztEntities.size()>0){
                            xsztEntities.clear();
                        }
                        xszttjAdapter.notifyDataSetChanged();
                        bmaster_xszt_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MasterXSZTActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<MasterXSZTEntity> datas){
            if(xsztEntities.size()>0){
                xsztEntities.clear();
            }
        for(MasterXSZTEntity entity:datas){
            xsztEntities.add(entity);
        }
        xszttjAdapter.notifyDataSetChanged();
    }
}
