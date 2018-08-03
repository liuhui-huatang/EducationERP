package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.dormpart.DormInternet;
import com.htcompany.educationerpforgansu.internet.dormpart.DormPersener;
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormSanitationManageAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormSanitationManageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 卫生管理
 * Created by wrb on 2016/11/23.
 */
public class DormSanitationManageActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{

    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private ImageView dormsanitationmanage_dbsx_wsj_img;
    private XListView dormsanitationmanage_lv;
    private DormSanitationManageAdapter sanitationManageAdapter;
    Intent intent = null;
    private List<DormSanitationManageEntity> manageEntities;
    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormsanitationmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        manageEntities = new ArrayList<DormSanitationManageEntity>();
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormSanitationManageDatas(String.valueOf(pageNum));
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        dormsanitationmanage_dbsx_wsj_img=(ImageView)this.findViewById(R.id.dormsanitationmanage_dbsx_wsj_img);
        dormsanitationmanage_lv = (XListView) this.findViewById(R.id.dormsanitationmanage_lv);
        dormsanitationmanage_lv.setPullRefreshEnable(true);
        dormsanitationmanage_lv.setPullLoadEnable(false);
        dormsanitationmanage_lv.setXListViewListener(this);
        sanitationManageAdapter = new DormSanitationManageAdapter(this,manageEntities);
        dormsanitationmanage_lv.setAdapter(sanitationManageAdapter);
    }

    public void initViewValues() {
        title.setText("卫生管理");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        dormsanitationmanage_lv.setOnItemClickListener(bookManageClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到违纪详情界面
            DormSanitationManageEntity manageEntity =(DormSanitationManageEntity) sanitationManageAdapter.getItem(position-1);
            intent = new Intent(DormSanitationManageActivity.this, DormSanitationDetailsActivity.class);
            intent.putExtra("manageEntity",manageEntity);
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //调到添加卫生检查界面
                intent = new Intent(DormSanitationManageActivity.this, DormSanitationAddActivity.class);
                startActivityForResult(intent,101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    pageNum=1;
                    dormInternet.getDormSanitationManageDatas(String.valueOf(pageNum));
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
                    ToastUtil.showToast("连接超时",DormSanitationManageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DormSanitationManageEntity> datas = dormPersener.parseDormSanitationManageData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(manageEntities.size()==0){
                            dormsanitationmanage_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            dormsanitationmanage_dbsx_wsj_img.setVisibility(View.GONE);
                        }
                        dormsanitationmanage_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",DormSanitationManageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<DormSanitationManageEntity> datas){
        if(pageNum==1){
            if(manageEntities.size()>0){
                manageEntities.clear();
            }
        }
        if(datas.size()>50){
            dormsanitationmanage_lv.setPullLoadEnable(true);
        }
        for(DormSanitationManageEntity entity:datas){
            manageEntities.add(entity);
        }
        if(manageEntities.size()==0){
            dormsanitationmanage_dbsx_wsj_img.setVisibility(View.VISIBLE);
        }else {
            dormsanitationmanage_dbsx_wsj_img.setVisibility(View.GONE);
        }
        sanitationManageAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        dormInternet.getDormSanitationManageDatas(String.valueOf(pageNum));
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        dormInternet.getDormSanitationManageDatas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        dormsanitationmanage_lv.stopRefresh();
        dormsanitationmanage_lv.stopLoadMore();
        dormsanitationmanage_lv.setRefreshTime("刚刚");
    }
}
