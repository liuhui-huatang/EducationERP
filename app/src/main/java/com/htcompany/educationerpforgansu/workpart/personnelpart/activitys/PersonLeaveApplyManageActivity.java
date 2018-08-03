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
import com.htcompany.educationerpforgansu.workpart.personnelpart.adapters.PersonLeaveApplyManageAdapter;
import com.htcompany.educationerpforgansu.workpart.personnelpart.entitys.PersonLeaveApplyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 教职工请假管理
 * Created by wrb on 2016/11/21.
 */
public class PersonLeaveApplyManageActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView personleaveapplymanage_wsj_img;
    private EditText seacherxsqj_name_edt;
    private XListView personleaveapplymanage_lv;
    private PersonLeaveApplyManageAdapter leaveApplyManageAdapter;
    private Intent intent=null;
    //网络请求类
    private WorkPersonnelManageInternet personnelInternet;
    private WorkPersonnelManagePersener personnelPersener;
    private WaitDialog waitDialog=null;
    private List<PersonLeaveApplyEntity> applyEntities;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personleaveapplymanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        applyEntities = new ArrayList<PersonLeaveApplyEntity>();
        personnelInternet = new WorkPersonnelManageInternet(this,myHandler);
        personnelPersener = new WorkPersonnelManagePersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        personnelInternet.getPersoLeaveApplyManageList(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        personleaveapplymanage_wsj_img=(ImageView)this.findViewById(R.id.personleaveapplymanage_wsj_img);
        seacherxsqj_name_edt=(EditText)this.findViewById(R.id.seacherxsqj_name_edt);
        personleaveapplymanage_lv = (XListView)this.findViewById(R.id.personleaveapplymanage_lv);
        personleaveapplymanage_lv.setPullRefreshEnable(true);
        personleaveapplymanage_lv.setPullLoadEnable(false);
        personleaveapplymanage_lv.setXListViewListener(this);
        leaveApplyManageAdapter = new PersonLeaveApplyManageAdapter(this,applyEntities);
        personleaveapplymanage_lv.setAdapter(leaveApplyManageAdapter);
    }
    public void initViewValues(){
        title.setText("请假管理");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        personleaveapplymanage_lv.setOnItemClickListener(leaveManageClicer);
        seacherxsqj_name_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener leaveManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            PersonLeaveApplyEntity entity=(PersonLeaveApplyEntity) leaveApplyManageAdapter.getItem(position-1);
            intent = new Intent(PersonLeaveApplyManageActivity.this, PersonLeaveApplyDetailsManageActivity.class);
            intent.putExtra("applyEntity",entity);
            startActivityForResult(intent,100);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    personnelInternet.getPersoLeaveApplyManageList(String.valueOf(pageNum),name);
                    break;
            }
        }
    }

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
            name = seacherxsqj_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(applyEntities.size()>0){
                    applyEntities.clear();
                }
                personnelInternet.getPersoLeaveApplyManageList(String.valueOf(pageNum),name);
            }else{
                personnelInternet.getPersoLeaveApplyManageList(String.valueOf(pageNum),name);
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
                    if(applyEntities.size()==0){
                        personleaveapplymanage_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        personleaveapplymanage_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",PersonLeaveApplyManageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<PersonLeaveApplyEntity> datas = personnelPersener.paresePersonLeavewApplyManageDatas((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(applyEntities.size()==0){
                            personleaveapplymanage_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            personleaveapplymanage_wsj_img.setVisibility(View.GONE);
                        }
                        personleaveapplymanage_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",PersonLeaveApplyManageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<PersonLeaveApplyEntity> datas){
        if(pageNum==1){
            if(applyEntities.size()>0){
                applyEntities.clear();
            }
        }
        if(datas.size()>0){
            personleaveapplymanage_lv.setPullLoadEnable(true);
        }
        for(PersonLeaveApplyEntity entity:datas){
            applyEntities.add(entity);
        }
        if(applyEntities.size()==0){
            personleaveapplymanage_wsj_img.setVisibility(View.VISIBLE);
        }else {
            personleaveapplymanage_wsj_img.setVisibility(View.GONE);
        }
        leaveApplyManageAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        personnelInternet.getPersoLeaveApplyManageList(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        personnelInternet.getPersoLeaveApplyManageList(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        personleaveapplymanage_lv.stopRefresh();
        personleaveapplymanage_lv.stopLoadMore();
        personleaveapplymanage_lv.setRefreshTime("刚刚");
    }
}
