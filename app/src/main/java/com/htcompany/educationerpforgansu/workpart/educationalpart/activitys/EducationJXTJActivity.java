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
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationJXTEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationJXTJAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 教学互动统计
 * Created by wrb on 2016/11/25.
 */
public class EducationJXTJActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView educationjxtj_wsj_img;
    private XListView educationjxtj_lv;
    private EducationJXTJAdapter jxtjAdapter;
    private EditText seacherjxhttj_name_edt;
    //网络请求类
    private EducationalPartInternet educationalPartInternet;
    private EducationalPartPersener educationalPartPersener;
    private WaitDialog waitDialog=null;
    private List<EducationJXTEntity> jxtEntities;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationjxtj_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        jxtEntities = new ArrayList<EducationJXTEntity>();
        educationalPartInternet = new EducationalPartInternet(this,myHandler);
        educationalPartPersener = new EducationalPartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        educationalPartInternet.getTeacherJXHDTJ_ListDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        educationjxtj_wsj_img=(ImageView)this.findViewById(R.id.educationjxtj_wsj_img);
        seacherjxhttj_name_edt = (EditText)this.findViewById(R.id.seacherjxhttj_name_edt);
        educationjxtj_lv = (XListView)this.findViewById(R.id.educationjxtj_lv);
        educationjxtj_lv.setPullRefreshEnable(true);
        educationjxtj_lv.setPullLoadEnable(false);
        educationjxtj_lv.setXListViewListener(this);
        jxtjAdapter = new EducationJXTJAdapter(this,jxtEntities);
        educationjxtj_lv.setAdapter(jxtjAdapter);
    }
    public void initViewVlaues(){
        title.setText("教学互动统计");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        seacherjxhttj_name_edt.addTextChangedListener(serachclier);
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
            username = seacherjxhttj_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(jxtEntities.size()>0){
                    jxtEntities.clear();
                }
                educationalPartInternet.getTeacherJXHDTJ_ListDatas(String.valueOf(pageNum),username);
            }else{
                educationalPartInternet.getTeacherJXHDTJ_ListDatas(String.valueOf(pageNum),username);
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
                    ToastUtil.showToast("连接超时",EducationJXTJActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<EducationJXTEntity> datas = educationalPartPersener.parseTeacherJXHDTJListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(jxtEntities.size()==0){
                            educationjxtj_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            educationjxtj_wsj_img.setVisibility(View.GONE);
                        }
                        educationjxtj_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",EducationJXTJActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<EducationJXTEntity> datas){
        if(pageNum==1){
            if(jxtEntities.size()>0){
                jxtEntities.clear();
            }
        }
        if(datas.size()>0){
            educationjxtj_lv.setPullLoadEnable(true);
        }
        for(EducationJXTEntity entity:datas){
            jxtEntities.add(entity);
        }
        if(jxtEntities.size()==0){
            educationjxtj_wsj_img.setVisibility(View.VISIBLE);
        }else {
            educationjxtj_wsj_img.setVisibility(View.GONE);
        }
        jxtjAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        educationalPartInternet.getTeacherJXHDTJ_ListDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        educationalPartInternet.getTeacherJXHDTJ_ListDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        educationjxtj_lv.stopRefresh();
        educationjxtj_lv.stopLoadMore();
        educationjxtj_lv.setRefreshTime("刚刚");
    }
}
