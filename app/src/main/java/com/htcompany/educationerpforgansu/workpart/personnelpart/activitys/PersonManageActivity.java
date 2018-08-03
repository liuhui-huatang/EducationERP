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
import com.htcompany.educationerpforgansu.workpart.personnelpart.adapters.PersonManageAdapter;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonManageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 人事管理
 * Created by wrb on 2016/11/17.
 */
public class PersonManageActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView personmanage_wsj_img;
    private XListView personmanage_lv;
    private PersonManageAdapter manageAdapter;
    private EditText personmanage_seacher_edt;
    //网络请求类
    private WorkPersonnelManageInternet personnelInternet;
    private WorkPersonnelManagePersener personnelPersener;
    private WaitDialog waitDialog=null;
    private List<PersonManageEntity> personManageEntities;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        personManageEntities = new ArrayList<PersonManageEntity>();
        personnelInternet = new WorkPersonnelManageInternet(this,myHandler);
        personnelPersener = new WorkPersonnelManagePersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        personnelInternet.getPersoManageList(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        personmanage_wsj_img=(ImageView)this.findViewById(R.id.personmanage_wsj_img);
        personmanage_seacher_edt=(EditText)this.findViewById(R.id.personmanage_seacher_edt);
        personmanage_lv = (XListView)this.findViewById(R.id.personmanage_lv);
        personmanage_lv.setPullRefreshEnable(true);
        personmanage_lv.setPullLoadEnable(false);
        personmanage_lv.setXListViewListener(this);
        manageAdapter = new PersonManageAdapter(this,personManageEntities);
        personmanage_lv.setAdapter(manageAdapter);
        personmanage_lv.setOnItemClickListener(manageClicer);
    }
    public void initViewValues(){
        title.setText("教职工管理");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        personmanage_seacher_edt.addTextChangedListener(serachclier);
    }
   public AdapterView.OnItemClickListener manageClicer = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           PersonManageEntity entity = (PersonManageEntity) manageAdapter.getItem(position-1);
           Intent intent = new Intent(PersonManageActivity.this,PersonManageDetailsActivity.class);
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
            name = personmanage_seacher_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(personManageEntities.size()>0){
                    personManageEntities.clear();
                }
                personnelInternet.getPersoManageList(String.valueOf(pageNum),name);
            }else{
                personnelInternet.getPersoManageList(String.valueOf(pageNum),name);
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
                    if(personManageEntities.size()==0){
                        personmanage_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        personmanage_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",PersonManageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<PersonManageEntity> datas = personnelPersener.paresePersonManageDatas((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(personManageEntities.size()==0){
                            personmanage_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            personmanage_wsj_img.setVisibility(View.GONE);
                        }
                        personmanage_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",PersonManageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<PersonManageEntity> datas){
        if(pageNum==1){
            if(personManageEntities.size()>0){
                personManageEntities.clear();
            }
        }
        if(datas.size()>0){
            personmanage_lv.setPullLoadEnable(true);
        }
        for(PersonManageEntity entity:datas){
            personManageEntities.add(entity);
        }
        if(personManageEntities.size()==0){
            personmanage_wsj_img.setVisibility(View.VISIBLE);
        }else {
            personmanage_wsj_img.setVisibility(View.GONE);
        }
        manageAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        personnelInternet.getPersoManageList(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        personnelInternet.getPersoManageList(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        personmanage_lv.stopRefresh();
        personmanage_lv.stopLoadMore();
        personmanage_lv.setRefreshTime("刚刚");
    }
}
