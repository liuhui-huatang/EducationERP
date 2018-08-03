package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.QKCJEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EdtucationClearExamAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 清考成绩查询
 * Created by wrb on 2016/11/25.
 */
public class EdtucationClearExamActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView educationclearexam_wsj_img;
    private XListView educationclearexam_lv;
    private EditText clearexamseach_name_edt;
    private EdtucationClearExamAdapter examAdapter;
    private List<QKCJEntity> qkcjEntities;
    //网络请求类
    private EducationalPartInternet educationalPartInternet;
    private EducationalPartPersener educationalPartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edtucationclearexam_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        qkcjEntities = new ArrayList<QKCJEntity>();
        educationalPartInternet = new EducationalPartInternet(this,myHandler);
        educationalPartPersener = new EducationalPartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        educationalPartInternet.getClearExam_ListDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        educationclearexam_wsj_img=(ImageView)this.findViewById(R.id.educationclearexam_wsj_img);
        clearexamseach_name_edt = (EditText)this.findViewById(R.id.clearexamseach_name_edt);
        educationclearexam_lv = (XListView)this.findViewById(R.id.educationclearexam_lv);
        educationclearexam_lv.setPullRefreshEnable(true);
        educationclearexam_lv.setPullLoadEnable(false);
        educationclearexam_lv.setXListViewListener(this);
        examAdapter = new EdtucationClearExamAdapter(this,qkcjEntities);
        educationclearexam_lv.setAdapter(examAdapter);
    }
    public void initViewVlaues(){
        title.setText("清考成绩查询");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        clearexamseach_name_edt.addTextChangedListener(serachclier);
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
            username = clearexamseach_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(qkcjEntities.size()>0){
                    qkcjEntities.clear();
                }
                educationalPartInternet.getClearExam_ListDatas(String.valueOf(pageNum),username);
            }else{
                educationalPartInternet.getClearExam_ListDatas(String.valueOf(pageNum),username);
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
                    ToastUtil.showToast("连接超时",EdtucationClearExamActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<QKCJEntity> datas = educationalPartPersener.parseClearExamListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(qkcjEntities.size()==0){
                            educationclearexam_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            educationclearexam_wsj_img.setVisibility(View.GONE);
                        }
                        educationclearexam_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",EdtucationClearExamActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<QKCJEntity> datas){
        if(pageNum==1){
            if(qkcjEntities.size()>0){
                qkcjEntities.clear();
            }
        }
        if(datas.size()>0){
            educationclearexam_lv.setPullLoadEnable(true);
        }
        for(QKCJEntity entity:datas){
            qkcjEntities.add(entity);
        }
        if(qkcjEntities.size()==0){
            educationclearexam_wsj_img.setVisibility(View.VISIBLE);
        }else {
            educationclearexam_wsj_img.setVisibility(View.GONE);
        }
        examAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        educationalPartInternet.getClearExam_ListDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        educationalPartInternet.getClearExam_ListDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        educationclearexam_lv.stopRefresh();
        educationclearexam_lv.stopLoadMore();
        educationclearexam_lv.setRefreshTime("刚刚");
    }
}
