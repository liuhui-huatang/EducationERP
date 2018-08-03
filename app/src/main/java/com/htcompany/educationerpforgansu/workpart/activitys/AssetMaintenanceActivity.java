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
import com.htcompany.educationerpforgansu.workpart.adapters.AssetMaintenanceAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AssetMaintenanceEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产维护
 * Created by WRB on 2016/11/9.
 */
public class AssetMaintenanceActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout right_three_btn,reback_btn;
    private ImageView assemanintenance_wsj_img;
    private ListView assetmanintenance_lv;
    private AssetMaintenanceAdapter assetMaintenanceAdapter;
    private List<AssetMaintenanceEntity> assetMaintenanceEntities;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assetmaintenance_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initDatas(){
        assetMaintenanceEntities = new ArrayList<AssetMaintenanceEntity>();
        waitDialog = new WaitDialog(this,"");
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        waitDialog.show();
        wokrpersonalInternet.getAsset_RepairListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        rightthree_btn_tv= (TextView)this.findViewById(R.id.rightthree_btn_tv);
        reback_btn= (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn =(RelativeLayout)this.findViewById(R.id.right_three_btn);
        assemanintenance_wsj_img=(ImageView)this.findViewById(R.id.assemanintenance_wsj_img);
        assetmanintenance_lv = (ListView)this.findViewById(R.id.assetmanintenance_lv);
        assetMaintenanceAdapter = new AssetMaintenanceAdapter(this,assetMaintenanceEntities);
        assetmanintenance_lv.setAdapter(assetMaintenanceAdapter);
        assetmanintenance_lv.setOnItemClickListener(itemClickListener);
    }
    public void initViewValues(){
        title.setText("资产维护");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("报修");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AssetMaintenanceEntity entity = (AssetMaintenanceEntity) assetMaintenanceAdapter.getItem(position);
            Intent intent = new Intent(AssetMaintenanceActivity.this,AssetMaintenanceDetialsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                Intent intent = new Intent(AssetMaintenanceActivity.this,AssetRepairsActivity.class);
                startActivityForResult(intent,100);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                wokrpersonalInternet.getAsset_RepairListDatas();
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
                    ToastUtil.showToast("连接超时",AssetMaintenanceActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<AssetMaintenanceEntity> datas = wokrpersonalPersener.parseAssetRepairListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        assemanintenance_wsj_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        assemanintenance_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",AssetMaintenanceActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<AssetMaintenanceEntity> datas){
            if(assetMaintenanceEntities.size()>0){
                assetMaintenanceEntities.clear();
            }
        for(AssetMaintenanceEntity entity:datas){
            assetMaintenanceEntities.add(entity);
        }
        assetMaintenanceAdapter.notifyDataSetChanged();
    }
}
