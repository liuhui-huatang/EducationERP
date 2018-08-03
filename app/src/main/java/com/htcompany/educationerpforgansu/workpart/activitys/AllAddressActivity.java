package com.htcompany.educationerpforgansu.workpart.activitys;

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
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.AllAddressAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllAddressEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有会议地点及教师地点
 * Created by wrb on 2016/12/13.
 */
public class AllAddressActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView alladdress_lv;
    private AllAddressAdapter addressAdapter;
    private List<AllAddressEntity> allAddressEntities;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
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
        allAddressEntities = new ArrayList<AllAddressEntity>();
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalPersener = new WokrpersonalPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        wokrpersonalInternet.getMeetinsAddressList_Datas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        alladdress_lv = (ListView)this.findViewById(R.id.alladdress_lv);
        addressAdapter = new AllAddressAdapter(this,allAddressEntities);
        alladdress_lv.setAdapter(addressAdapter);
    }
    public void initViewValues(){
        title.setText("地址选择");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        alladdress_lv.setOnItemClickListener(itemClickListener);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AllAddressEntity entity = (AllAddressEntity) addressAdapter.getItem(position);
            if(entity!=null){
                Intent intent = new Intent();
                intent.putExtra("addressEntity",entity);
                setResult(101,intent);
                finish();
            }

        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b=null;
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",AllAddressActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<AllAddressEntity> datas = wokrpersonalPersener.parseMeetingAddress_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",AllAddressActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<AllAddressEntity> datas){
        if(allAddressEntities.size()>0){
            allAddressEntities.clear();
        }
        for(AllAddressEntity entity:datas){
            allAddressEntities.add(entity);
        }
        addressAdapter.notifyDataSetChanged();
    }

}
