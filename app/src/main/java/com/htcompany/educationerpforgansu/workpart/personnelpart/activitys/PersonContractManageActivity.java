package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

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
import com.htcompany.educationerpforgansu.internet.workpersonnel.WorkPersonnelManageInternet;
import com.htcompany.educationerpforgansu.internet.workpersonnel.WorkPersonnelManagePersener;
import com.htcompany.educationerpforgansu.workpart.personnelpart.adapters.PersonContractManageAdapter;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonContractEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 合同管理
 * Created by wrb on 2016/11/17.
 */
public class PersonContractManageActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView personcontractmanage_wsj_img;
    private EditText personcontractmanage_search_edt;
    private XListView personcontractmanage_lv;
    private PersonContractManageAdapter contractManageAdapter;
    //网络请求类
    private WorkPersonnelManageInternet personnelInternet;
    private WorkPersonnelManagePersener personnelPersener;
    private WaitDialog waitDialog=null;
    private List<PersonContractEntity> personContractEntities;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personcontractmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        personContractEntities = new ArrayList<PersonContractEntity>();
        personnelInternet = new WorkPersonnelManageInternet(this,myHandler);
        personnelPersener = new WorkPersonnelManagePersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        personnelInternet.getPersonContractList(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        personcontractmanage_wsj_img=(ImageView)this.findViewById(R.id.personcontractmanage_wsj_img);
        personcontractmanage_search_edt=(EditText)this.findViewById(R.id.personcontractmanage_search_edt);
        personcontractmanage_lv = (XListView)this.findViewById(R.id.personcontractmanage_lv);
        personcontractmanage_lv.setPullRefreshEnable(true);
        personcontractmanage_lv.setPullLoadEnable(false);
        personcontractmanage_lv.setXListViewListener(this);
        contractManageAdapter = new PersonContractManageAdapter(this,personContractEntities);
        personcontractmanage_lv.setAdapter(contractManageAdapter);
        personcontractmanage_lv.setOnItemClickListener(manageClicer);
    }
    public void initViewValues(){
        title.setText("合同管理");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        personcontractmanage_search_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener manageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            PersonContractEntity entity =(PersonContractEntity)contractManageAdapter.getItem(position-1);
            Intent intent = new Intent(PersonContractManageActivity.this,PersonContractManageDetailsActivity.class);
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
        }
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
            name = personcontractmanage_search_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(personContractEntities.size()>0){
                    personContractEntities.clear();
                }
                personnelInternet.getPersonContractList(String.valueOf(pageNum),name);
            }else{
                personnelInternet.getPersonContractList(String.valueOf(pageNum),name);
            }
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
                    ToastUtil.showToast("连接超时",PersonContractManageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<PersonContractEntity> datas = personnelPersener.paresePersonContract((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(personContractEntities.size()==0){
                            personcontractmanage_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            personcontractmanage_wsj_img.setVisibility(View.GONE);
                        }
                        personcontractmanage_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",PersonContractManageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<PersonContractEntity> datas){
        if(pageNum==1){
            if(personContractEntities.size()>0){
                personContractEntities.clear();
            }
        }
        if(datas.size()>0){
            personcontractmanage_lv.setPullLoadEnable(true);
        }
        for(PersonContractEntity entity:datas){
            personContractEntities.add(entity);
        }
        if(personContractEntities.size()==0){
            personcontractmanage_wsj_img.setVisibility(View.VISIBLE);
        }else {
            personcontractmanage_wsj_img.setVisibility(View.GONE);
        }
        contractManageAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        personnelInternet.getPersonContractList(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        personnelInternet.getPersonContractList(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        personcontractmanage_lv.stopRefresh();
        personcontractmanage_lv.stopLoadMore();
        personcontractmanage_lv.setRefreshTime("刚刚");
    }
}
