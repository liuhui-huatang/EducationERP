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
import com.htcompany.educationerpforgansu.workpart.adapters.BeOnDutyAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.BeOnDutyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的值班
 * Created by wrb on 2016/11/11.
 */
public class BeOnDutyActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView beonduty_lv;
    private ImageView beonduty_wsj_img;
    private BeOnDutyAdapter beOnDutyAdapter;
    private List<BeOnDutyEntity> beOnDutyEntities;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beonduty_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        beOnDutyEntities = new ArrayList<BeOnDutyEntity>();
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalPersener = new WokrpersonalPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        wokrpersonalInternet.getMyBeonDutyList_Datas();
    }
    public void initViews(){
        beonduty_wsj_img=(ImageView)this.findViewById(R.id.beonduty_wsj_img);
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        beonduty_lv = (ListView)this.findViewById(R.id.beonduty_lv);
        beOnDutyAdapter = new BeOnDutyAdapter(this,beOnDutyEntities);
        beonduty_lv.setAdapter(beOnDutyAdapter);
    }
    public void initViewValues(){
        title.setText("部门值班");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        beonduty_lv.setOnItemClickListener(itemClcier);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public AdapterView.OnItemClickListener itemClcier =new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            BeOnDutyEntity entity = (BeOnDutyEntity) beOnDutyAdapter.getItem(position);
            Intent intent=new Intent(BeOnDutyActivity.this,BeOnDutyDetailsActivity.class);
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
                    ToastUtil.showToast("连接超时",BeOnDutyActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<BeOnDutyEntity> datas = wokrpersonalPersener.parseMyBeOnDuty_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        beonduty_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",BeOnDutyActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<BeOnDutyEntity> datas){
        if(beOnDutyEntities.size()>0){
            beOnDutyEntities.clear();
        }
        for(BeOnDutyEntity entity:datas){
            beOnDutyEntities.add(entity);
        }
        beOnDutyAdapter.notifyDataSetChanged();
    }

}
