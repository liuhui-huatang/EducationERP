package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

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
import com.htcompany.educationerpforgansu.internet.educationalpart.EducationalPartInternet;
import com.htcompany.educationerpforgansu.internet.educationalpart.EducationalPartPersener;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.XXClassEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationSearchStudent0ptionalClassAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 选修课学生查询
 * Created by wrb on 2016/11/21.
 */
public class EducationSearchStudent0ptionalClassActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView optionalclass_wsj_img;
    private XListView optionalclass_lv;
    private EditText optionalclass_seachr_edt;
    private EducationSearchStudent0ptionalClassAdapter ptionalClassAdapter;
    //网络请求类
    private EducationalPartInternet educationalPartInternet;
    private EducationalPartPersener educationalPartPersener;
    private WaitDialog waitDialog=null;
    private List<XXClassEntity> xxClassEntities;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationsearchstudentoptionalclass_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        xxClassEntities = new ArrayList<XXClassEntity>();
        educationalPartInternet = new EducationalPartInternet(this,myHandler);
        educationalPartPersener = new EducationalPartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        educationalPartInternet.getStudentSelectClass_ListDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        optionalclass_wsj_img=(ImageView)this.findViewById(R.id.optionalclass_wsj_img);
        optionalclass_lv = (XListView)this.findViewById(R.id.optionalclass_lv);
        optionalclass_seachr_edt=(EditText)this.findViewById(R.id.optionalclass_seachr_edt);
        optionalclass_lv.setPullRefreshEnable(true);
        optionalclass_lv.setPullLoadEnable(false);
        optionalclass_lv.setXListViewListener(this);
        ptionalClassAdapter = new EducationSearchStudent0ptionalClassAdapter(this,xxClassEntities);
        optionalclass_lv.setAdapter(ptionalClassAdapter);
    }
    public void initViewVlaues(){
        title.setText("选修课查询");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        optionalclass_lv.setOnItemClickListener(reserverClicer);
        optionalclass_seachr_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener reserverClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            XXClassEntity entity = (XXClassEntity) ptionalClassAdapter.getItem(position-1);
            Intent intent = new Intent(EducationSearchStudent0ptionalClassActivity.this,EducationSearchStudent0ptionalClassDetailsActivity.class);
            intent.putExtra("classEntity",entity);
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
            username = optionalclass_seachr_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(xxClassEntities.size()>0){
                    xxClassEntities.clear();
                }
                educationalPartInternet.getStudentSelectClass_ListDatas(String.valueOf(pageNum),username);
            }else{
                educationalPartInternet.getStudentSelectClass_ListDatas(String.valueOf(pageNum),username);
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
                    ToastUtil.showToast("连接超时",EducationSearchStudent0ptionalClassActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<XXClassEntity> datas = educationalPartPersener.parseStudentSelecrClassListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(xxClassEntities.size()==0){
                            optionalclass_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            optionalclass_wsj_img.setVisibility(View.GONE);
                        }
                        optionalclass_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",EducationSearchStudent0ptionalClassActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<XXClassEntity> datas){
        if(pageNum==1){
            if(xxClassEntities.size()>0){
                xxClassEntities.clear();
            }
        }
        if(datas.size()>0){
            optionalclass_lv.setPullLoadEnable(true);
        }
        for(XXClassEntity entity:datas){
            xxClassEntities.add(entity);
        }
        if(xxClassEntities.size()==0){
            optionalclass_wsj_img.setVisibility(View.VISIBLE);
        }else {
            optionalclass_wsj_img.setVisibility(View.GONE);
        }
        ptionalClassAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        educationalPartInternet.getStudentSelectClass_ListDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        educationalPartInternet.getStudentSelectClass_ListDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        optionalclass_lv.stopRefresh();
        optionalclass_lv.stopLoadMore();
        optionalclass_lv.setRefreshTime("刚刚");
    }
}
