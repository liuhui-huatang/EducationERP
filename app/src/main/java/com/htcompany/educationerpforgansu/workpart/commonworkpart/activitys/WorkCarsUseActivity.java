package com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters.WorkCarsUseAdapter;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkCarEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆使用管理
 * Created by wrb on 2016/11/22.
 */
public class WorkCarsUseActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView workcaruse_dbsx_wsj_img;
    private ListView workcaruse_lv;
    private WorkCarsUseAdapter useCarsAdapter;
    private List<WorkCarEntity> carEntities;
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workcarsuse_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        carEntities = new ArrayList<WorkCarEntity>();
        workInternet = new CommonWorkInternet(this,myHandler);
        workPersener = new CommonWorkPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        workInternet.getUseCarsListDatas("1");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        workcaruse_dbsx_wsj_img=(ImageView)this.findViewById(R.id.workcaruse_dbsx_wsj_img);
        workcaruse_lv = (ListView)this.findViewById(R.id.workcaruse_lv);
        useCarsAdapter = new WorkCarsUseAdapter(this,carEntities);
        workcaruse_lv.setAdapter(useCarsAdapter);
    }
    public void initViewValues(){
        title.setText("车辆使用管理");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        workcaruse_lv.setOnItemClickListener(usecarClicer);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public AdapterView.OnItemClickListener usecarClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            WorkCarEntity entity = (WorkCarEntity) useCarsAdapter.getItem(position);
            Intent intent = new Intent(WorkCarsUseActivity.this,WorkCarsUseDetailsActivity.class);
            intent.putExtra("carEntity",entity);
            startActivityForResult(intent,101);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    workInternet.getUseCarsListDatas("1");
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
                    ToastUtil.showToast("连接超时",WorkCarsUseActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<WorkCarEntity> datas = workPersener.parseUserCars_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        workcaruse_dbsx_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",WorkCarsUseActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<WorkCarEntity> datas){
        if(carEntities.size()>0){
            carEntities.clear();
        }
        for(WorkCarEntity entity:datas){
            carEntities.add(entity);
        }
        useCarsAdapter.notifyDataSetChanged();
    }
}
