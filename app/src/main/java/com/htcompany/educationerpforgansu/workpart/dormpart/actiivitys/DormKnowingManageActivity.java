package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormKnowingManageAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormKnowingManageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 查寝管理
 * Created by wrb on 2016/11/22.
 */
public class DormKnowingManageActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private ImageView dormhnowing_dbsx_wsj_img;
    private EditText dormknoing_search_edt;
    private XListView dormhnowing_lv;
    private DormKnowingManageAdapter rzTsSearchAdapter;
    private List<DormKnowingManageEntity> manageEntities;

    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormknowingmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        manageEntities = new ArrayList<DormKnowingManageEntity>();
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormKnowingManageDatas(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        rightthree_btn_tv= (TextView)this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn= (RelativeLayout)this.findViewById(R.id.right_three_btn);
        dormhnowing_dbsx_wsj_img=(ImageView)this.findViewById(R.id.dormhnowing_dbsx_wsj_img);
        dormknoing_search_edt=(EditText)this.findViewById(R.id.dormknoing_search_edt);
        dormhnowing_lv = (XListView)this.findViewById(R.id.dormhnowing_lv);
        dormhnowing_lv.setPullRefreshEnable(true);
        dormhnowing_lv.setPullLoadEnable(false);
        dormhnowing_lv.setXListViewListener(this);
        rzTsSearchAdapter = new DormKnowingManageAdapter(this,manageEntities);
        dormhnowing_lv.setAdapter(rzTsSearchAdapter);

    }
    public void initViewValues(){
        title.setText("查寝管理");
        right_three_btn.setVisibility(View.VISIBLE);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        dormhnowing_lv.setOnItemClickListener(knowingClicer);
        dormknoing_search_edt.addTextChangedListener(serachclier);
    }
    public TextWatcher serachclier = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            name = dormknoing_search_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(manageEntities.size()>0){
                    manageEntities.clear();
                }
                dormInternet.getDormKnowingManageDatas(String.valueOf(pageNum),name);
            }else{
                dormInternet.getDormKnowingManageDatas(String.valueOf(pageNum),name);
            }
        }
    };
    public AdapterView.OnItemClickListener knowingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DormKnowingManageEntity entity=(DormKnowingManageEntity) rzTsSearchAdapter.getItem(position-1);
            if(entity!=null) {
                Intent intent = new Intent(DormKnowingManageActivity.this, DormKnowingManageDetailsActivity.class);
                intent.putExtra("entity",entity);
                startActivity(intent);
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                Intent intent = new Intent(DormKnowingManageActivity.this,DormKnowingManageAddActivity.class);
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
                    dormInternet.getDormKnowingManageDatas(String.valueOf(pageNum),name);
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
                    if(manageEntities.size()==0){
                        dormhnowing_dbsx_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        dormhnowing_dbsx_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",DormKnowingManageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DormKnowingManageEntity> datas = dormPersener.parseDormKnowingManageData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(manageEntities.size()==0){
                            dormhnowing_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            dormhnowing_dbsx_wsj_img.setVisibility(View.GONE);
                        }
                        dormhnowing_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",DormKnowingManageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<DormKnowingManageEntity> datas){
        if(pageNum==1){
            if(manageEntities.size()>0){
                manageEntities.clear();
            }
        }
        if(datas.size()>50){
            dormhnowing_lv.setPullLoadEnable(true);
        }
        for(DormKnowingManageEntity entity:datas){
            manageEntities.add(entity);
        }
        if(manageEntities.size()==0){
            dormhnowing_dbsx_wsj_img.setVisibility(View.VISIBLE);
        }else {
            dormhnowing_dbsx_wsj_img.setVisibility(View.GONE);
        }
        rzTsSearchAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        dormInternet.getDormKnowingManageDatas(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        dormInternet.getDormKnowingManageDatas(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        dormhnowing_lv.stopRefresh();
        dormhnowing_lv.stopLoadMore();
        dormhnowing_lv.setRefreshTime("刚刚");
    }
}
