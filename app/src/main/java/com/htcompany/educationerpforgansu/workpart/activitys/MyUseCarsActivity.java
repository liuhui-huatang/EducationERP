package com.htcompany.educationerpforgansu.workpart.activitys;

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
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.MyUseCarsAdapter;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkCarEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆使用记录
 * Created by wrb on 2016/11/10.
 * 包含得审核，同意，结束 ，取消状态，审核失败 五中状态
 */
public class MyUseCarsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private ImageView myusercars_wsj_img;
    private ListView myusercars_lv;
    private MyUseCarsAdapter useCarsAdapter;
    private List<WorkCarEntity> carEntities;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myusecars_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        carEntities = new ArrayList<WorkCarEntity>();
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalPersener = new WokrpersonalPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        wokrpersonalInternet.getMyUserCarList_Datas("0");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        rightthree_btn_tv = (TextView)this.findViewById(R.id.rightthree_btn_tv);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        myusercars_wsj_img=(ImageView) this.findViewById(R.id.myusercars_wsj_img);
        myusercars_lv = (ListView)this.findViewById(R.id.myusercars_lv);
        useCarsAdapter = new MyUseCarsAdapter(this,carEntities);
        myusercars_lv.setAdapter(useCarsAdapter);
    }
    public void initViewValues(){
        title.setText("车辆使用");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("申请");
    }
    public void initOnclicEvents(){
        right_three_btn.setOnClickListener(this);
        myusercars_lv.setOnItemClickListener(usecarClicer);
        reback_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                Intent intent = new Intent(MyUseCarsActivity.this,MyUseCarsAddActivity.class);
                startActivityForResult(intent,101);
                break;
        }
    }
    public AdapterView.OnItemClickListener usecarClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            WorkCarEntity entity = (WorkCarEntity) useCarsAdapter.getItem(position);
            Intent intent = new Intent(MyUseCarsActivity.this,MyUseCarDetialsActivity.class);
            intent.putExtra("carEntity",entity);
            startActivity(intent);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    wokrpersonalInternet.getMyUserCarList_Datas("0");
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
                    ToastUtil.showToast("连接超时",MyUseCarsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<WorkCarEntity> datas = wokrpersonalPersener.parseMyUsercars_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        myusercars_wsj_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        myusercars_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyUseCarsActivity.this);
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
