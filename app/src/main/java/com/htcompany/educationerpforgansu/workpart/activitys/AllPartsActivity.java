package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.AllPartsAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllPartEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门
 * Created by wrb on 2016/12/14.
 */
public class AllPartsActivity extends BaseActivity implements View.OnClickListener{

    private TextView title;
    private RelativeLayout reback_btn,right_three_btn;
    private ListView alladdress_lv;
    private AllPartsAdapter partsAdapter;
    private List<AllPartEntity> allPartEntities;
    private List<AllPartEntity> selectDatas;
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alladdress_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        selectDatas = new ArrayList<AllPartEntity>();
        allPartEntities = new ArrayList<AllPartEntity>();
        workInternet = new CommonWorkInternet(this,myHandler);
        workPersener = new CommonWorkPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        workInternet.getpartListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        alladdress_lv = (ListView)this.findViewById(R.id.alladdress_lv);
        partsAdapter = new AllPartsAdapter(this,allPartEntities,myHandler);
        alladdress_lv.setAdapter(partsAdapter);
    }
    public void initViewValues(){
        title.setText("部门选择");
        right_three_btn.setVisibility(View.VISIBLE);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //确定选择人员
                Intent intent = new Intent();
                intent.putExtra("partdatas",(Serializable) selectDatas);
                setResult(101,intent);
                this.finish();
                break;
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b=null;
            switch (msg.what){
                case 101:
                    b = msg.getData();
                    AllPartEntity entity = (AllPartEntity) b.getSerializable("AllPartEntity");
                    addSelectDatas(entity);
                    break;
                case 102:
                    b = msg.getData();
                    AllPartEntity entity2 = (AllPartEntity) b.getSerializable("AllPartEntity");
                    removeSelectDatas(entity2);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",AllPartsActivity.this);
                    break;
                case 200:
                    //添加办公公告
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<AllPartEntity> datas = workPersener.parseAllParts_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",AllPartsActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<AllPartEntity> datas){
        if(allPartEntities.size()>0){
            allPartEntities.clear();
        }
        for(AllPartEntity entity:datas){
            allPartEntities.add(entity);
        }
        partsAdapter.notifyDataSetChanged();
    }
    //添加选中人员数据
    public void addSelectDatas(AllPartEntity entity){
        selectDatas.add(entity);
    }
    public void removeSelectDatas(AllPartEntity entity){
        if(selectDatas.size()>0){
            selectDatas.remove(entity);
        }
    }
}
