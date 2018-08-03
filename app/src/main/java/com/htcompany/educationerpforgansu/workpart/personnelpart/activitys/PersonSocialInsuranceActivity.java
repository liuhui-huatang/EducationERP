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
import com.htcompany.educationerpforgansu.workpart.personnelpart.adapters.PersonSocialInsuranceAdapter;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonSocialInsuranceEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 社会保险记录
 * Created by wrb on 2016/11/18.
 */
public class PersonSocialInsuranceActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private EditText personsocialinsurance_name_tv;
    private XListView personsocialinsurance_lv;
    private ImageView personsocialinsurance_wsj_img;
    private PersonSocialInsuranceAdapter socialInsuranceAdapter;
    //网络请求类
    private WorkPersonnelManageInternet personnelInternet;
    private WorkPersonnelManagePersener personnelPersener;
    private WaitDialog waitDialog=null;
    private List<PersonSocialInsuranceEntity> personSocialInsuranceEntities;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personsocialinsurance_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        personSocialInsuranceEntities = new ArrayList<PersonSocialInsuranceEntity>();
        personnelInternet = new WorkPersonnelManageInternet(this,myHandler);
        personnelPersener = new WorkPersonnelManagePersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        personnelInternet.getPersonSocialInsuranceList(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn =(RelativeLayout)this.findViewById(R.id.reback_btn);
        personsocialinsurance_wsj_img=(ImageView)this.findViewById(R.id.personsocialinsurance_wsj_img);
        personsocialinsurance_name_tv=(EditText)this.findViewById(R.id.personsocialinsurance_name_tv);
        personsocialinsurance_lv = (XListView)this.findViewById(R.id.personsocialinsurance_lv);
        personsocialinsurance_lv.setPullRefreshEnable(true);
        personsocialinsurance_lv.setPullLoadEnable(false);
        personsocialinsurance_lv.setXListViewListener(this);
        socialInsuranceAdapter = new PersonSocialInsuranceAdapter(this,personSocialInsuranceEntities);
        personsocialinsurance_lv.setAdapter(socialInsuranceAdapter);
        personsocialinsurance_lv.setOnItemClickListener(manageClicer);
    }
    public void initViewValues(){
        title.setText("社会保险");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        personsocialinsurance_name_tv.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener manageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            PersonSocialInsuranceEntity entity=(PersonSocialInsuranceEntity) socialInsuranceAdapter.getItem(position-1);
            Intent intent = new Intent(PersonSocialInsuranceActivity.this,PersonSocialInsuranceDetailsActivity.class);
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
            name = personsocialinsurance_name_tv.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(personSocialInsuranceEntities.size()>0){
                    personSocialInsuranceEntities.clear();
                }
                personnelInternet.getPersonSocialInsuranceList(String.valueOf(pageNum),name);
            }else{
                personnelInternet.getPersonSocialInsuranceList(String.valueOf(pageNum),name);
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
                    if(personSocialInsuranceEntities.size()==0){
                        personsocialinsurance_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        personsocialinsurance_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",PersonSocialInsuranceActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<PersonSocialInsuranceEntity> datas = personnelPersener.paresePersonSocialInsurance((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(personSocialInsuranceEntities.size()==0){
                            personsocialinsurance_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            personsocialinsurance_wsj_img.setVisibility(View.GONE);
                        }
                        personsocialinsurance_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",PersonSocialInsuranceActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<PersonSocialInsuranceEntity> datas){
        if(pageNum==1){
            if(personSocialInsuranceEntities.size()>0){
                personSocialInsuranceEntities.clear();
            }
        }
        if(datas.size()>0){
            personsocialinsurance_lv.setPullLoadEnable(true);
        }
        for(PersonSocialInsuranceEntity entity:datas){
            personSocialInsuranceEntities.add(entity);
        }
        if(personSocialInsuranceEntities.size()==0){
            personsocialinsurance_wsj_img.setVisibility(View.VISIBLE);
        }else {
            personsocialinsurance_wsj_img.setVisibility(View.GONE);
        }
        socialInsuranceAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        personnelInternet.getPersonSocialInsuranceList(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        personnelInternet.getPersonSocialInsuranceList(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        personsocialinsurance_lv.stopRefresh();
        personsocialinsurance_lv.stopLoadMore();
        personsocialinsurance_lv.setRefreshTime("刚刚");
    }
}
