package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
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
import com.htcompany.educationerpforgansu.workpart.personnelpart.adapters.PersonnelFilesAdapter;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonnelFilesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 档案管理
 * Created by wrb on 2016/11/18.
 */
public class PersonnelFilesActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView personnelfiles_wsj_img;
    private EditText personnelfiles_search_edt;
    private XListView personnelfiles_lv;
    private PersonnelFilesAdapter filesAdapter;
    //网络请求类
    private WorkPersonnelManageInternet personnelInternet;
    private WorkPersonnelManagePersener personnelPersener;
    private WaitDialog waitDialog=null;
    private List<PersonnelFilesEntity> personnelFilesEntities;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personnelfiles_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        personnelFilesEntities = new ArrayList<PersonnelFilesEntity>();
        personnelInternet = new WorkPersonnelManageInternet(this,myHandler);
        personnelPersener = new WorkPersonnelManagePersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        personnelInternet.getPersonFilesList(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        personnelfiles_wsj_img=(ImageView)this.findViewById(R.id.personnelfiles_wsj_img);
        personnelfiles_lv = (XListView)this.findViewById(R.id.personnelfiles_lv);
        personnelfiles_lv.setPullRefreshEnable(true);
        personnelfiles_lv.setPullLoadEnable(false);
        personnelfiles_lv.setXListViewListener(this);
        personnelfiles_search_edt=(EditText)this.findViewById(R.id.personnelfiles_search_edt) ;
        filesAdapter = new PersonnelFilesAdapter(this,personnelFilesEntities);
        personnelfiles_lv.setAdapter(filesAdapter);
        personnelfiles_lv.setOnItemClickListener(manageClicer);
    }
    public void initViewValues(){
        title.setText("人事档案");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        personnelfiles_search_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener manageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            PersonnelFilesEntity entity=(PersonnelFilesEntity) filesAdapter.getItem(position-1);
            Intent intent = new Intent(PersonnelFilesActivity.this,PersonnelFilesDetailsActivity.class);
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
            name = personnelfiles_search_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(personnelFilesEntities.size()>0){
                    personnelFilesEntities.clear();
                }
                personnelInternet.getPersonFilesList(String.valueOf(pageNum),name);
            }else{
                personnelInternet.getPersonFilesList(String.valueOf(pageNum),name);
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
                    ToastUtil.showToast("连接超时",PersonnelFilesActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<PersonnelFilesEntity> datas = personnelPersener.paresePersonFile((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(personnelFilesEntities.size()==0){
                            personnelfiles_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            personnelfiles_wsj_img.setVisibility(View.GONE);
                        }
                        personnelfiles_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",PersonnelFilesActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<PersonnelFilesEntity> datas){
        if(pageNum==1){
            if(personnelFilesEntities.size()>0){
                personnelFilesEntities.clear();
            }
        }
        if(datas.size()>0){
            personnelfiles_lv.setPullLoadEnable(true);
        }
        for(PersonnelFilesEntity entity:datas){
            personnelFilesEntities.add(entity);
        }
        if(personnelFilesEntities.size()==0){
            personnelfiles_wsj_img.setVisibility(View.VISIBLE);
        }else {
            personnelfiles_wsj_img.setVisibility(View.GONE);
        }
        filesAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        personnelInternet.getPersonFilesList(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        personnelInternet.getPersonFilesList(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        personnelfiles_lv.stopRefresh();
        personnelfiles_lv.stopLoadMore();
        personnelfiles_lv.setRefreshTime("刚刚");
    }
}
