package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.GDListView;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.internet.dormpart.DormInternet;
import com.htcompany.educationerpforgansu.internet.dormpart.DormPersener;
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormSanitationAddAddAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormBulingEntitiy;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormRoomEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageSourceEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 卫生检查添加
 * Created by wrb on 2016/11/23.
 */
public class DormSanitationAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private TextView dormsanitationadd_date_tv,dormsanitationadd_ssl_tv,
            dormsanitationadd_ss_tv;
    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private DateViewDailog dateViewDailog;
    private Intent intent=null;
    //上传字段
    private String dormbuild="";
    private String dormRoom="";
    //选择实体
    private DormBulingEntitiy bulingEntitiy=null;
    private DormRoomEntity roomEntity=null;
    //数据实体类
    private List<DormBulingEntitiy> bulingEntitiys;
    private List<DormRoomEntity> roomEntities;
    private SharedPrefUtil sharedPrefUtil=null;

    private GDListView wsjcadd_item_lv;
    private DormSanitationAddAddAdapter addAddAdapter=null;
    private List<DormSanitationManageSourceEntity> sourceEntities=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormsanitationadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        sourceEntities =new ArrayList<DormSanitationManageSourceEntity>();
        sharedPrefUtil = new SharedPrefUtil(this,"login");
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormKnowingManageAddSelecDatas();//选择字典
        dormInternet.gettDormSanitationManageAddItemSelecDatas();//评分项
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        dormsanitationadd_date_tv = (TextView) this.findViewById(R.id.dormsanitationadd_date_tv);
        dormsanitationadd_ssl_tv = (TextView) this.findViewById(R.id.dormsanitationadd_ssl_tv);
        dormsanitationadd_ss_tv = (TextView) this.findViewById(R.id.dormsanitationadd_ss_tv);
        wsjcadd_item_lv=(GDListView)this.findViewById(R.id.wsjcadd_item_lv);
        addAddAdapter = new DormSanitationAddAddAdapter(this,sourceEntities);
        wsjcadd_item_lv.setAdapter(addAddAdapter);
    }

    public void initViewValues() {
        title.setText("卫生检查");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("提交");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        dormsanitationadd_date_tv.setOnClickListener(this);
        dormsanitationadd_ssl_tv.setOnClickListener(this);
        dormsanitationadd_ss_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.dormsanitationadd_date_tv:
                //提交查询日期
                dateViewDailog = new DateViewDailog(DormSanitationAddActivity.this,1000,myHandler,false);
                dateViewDailog.show();
                break;
            case R.id.dormsanitationadd_ssl_tv:
                //提交查询宿舍楼
                if(bulingEntitiys!=null&&bulingEntitiys.size()>0) {
                    intent = new Intent(DormSanitationAddActivity.this, DormBulingSelectActivity.class);
                    intent.putExtra("bulingEntitiys",(Serializable) bulingEntitiys);
                    startActivityForResult(intent, 101);
                }else{
                    ToastUtil.showToast("暂无宿舍楼数据",DormSanitationAddActivity.this);
                }
                break;
            case R.id.dormsanitationadd_ss_tv:
                //提交查询宿舍
                //宿舍
                if("".equals(dormsanitationadd_ssl_tv.getText().toString())){
                    ToastUtil.showToast("请先选择宿舍楼",DormSanitationAddActivity.this);
                }else{
                    if(roomEntities!=null&&roomEntities.size()>0) {
                        intent = new Intent(DormSanitationAddActivity.this, DormRoomSelectActivity.class);
                        intent.putExtra("roomEntities",(Serializable) roomEntities);
                        startActivityForResult(intent, 102);
                    }else{
                        ToastUtil.showToast("暂无宿舍数据",DormSanitationAddActivity.this);
                    }
                }
                break;
            case R.id.right_three_btn:
                //提交查询结果
                if("".equals(dormsanitationadd_date_tv.getText())){
                    ToastUtil.showToast("请选择检查时间",DormSanitationAddActivity.this);
                }else if("".equals(dormsanitationadd_ssl_tv.getText().toString())){
                ToastUtil.showToast("请先选择宿舍楼",DormSanitationAddActivity.this);
            }else{
                     boolean upFlag=true;
                    for(DormSanitationManageSourceEntity entity:sourceEntities){
                        if("".equals(entity.getSource().trim())){
                            upFlag=false;
                            ToastUtil.showToast("请检查完所有项目",DormSanitationAddActivity.this);
                            return;
                        }
                    }
                    if(upFlag){
                        waitDialog.show();
                        dormInternet.addDormWSJCDatas(dormsanitationadd_date_tv.getText().toString(),dormRoom,sourceEntities);
                    }
                }

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    //宿舍楼
                    bulingEntitiy=(DormBulingEntitiy) data.getSerializableExtra("entity");
                    if(bulingEntitiy!=null){
                        dormsanitationadd_ssl_tv.setText(bulingEntitiy.getBuilding_name());
                        dormbuild=bulingEntitiy.getId();
                        roomEntities=bulingEntitiy.getRoom();
                    }
                    break;
                case 102:
                    //宿舍
                    roomEntity=(DormRoomEntity) data.getSerializableExtra("entity");
                    if(roomEntity!=null){
                        dormsanitationadd_ss_tv.setText(roomEntity.getNum());
                        dormRoom=roomEntity.getId();
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
                case 1000:
                    dormsanitationadd_date_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",DormSanitationAddActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    dormPersener.parseDormBuldiongSelectManageData((String)msg.obj);
                    break;
                case 202:
                    Bundle b = msg.getData();
                    if(b!=null){
                        bulingEntitiys = (List<DormBulingEntitiy>) b.getSerializable("bulingEntitiys");
                    }
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    //添加成功
                    if(dormPersener.parserSuccessDATAS((String)msg.obj)){
                        Intent intent =new Intent();
                        setResult(101,intent);
                        finish();
                    }else{

                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",DormSanitationAddActivity.this);
                    break;
                case 800:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DormSanitationManageSourceEntity> datas= dormPersener.parseDormSanitationManageItemData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<DormSanitationManageSourceEntity> datas){
            if(sourceEntities.size()>0){
                sourceEntities.clear();
            }
        for(DormSanitationManageSourceEntity entity:datas){
            sourceEntities.add(entity);
        }
        addAddAdapter.notifyDataSetChanged();
    }
}
