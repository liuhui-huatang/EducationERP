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
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.MakeupCjEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationMakeupCjAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 补考成绩查询
 * Created by wrb on 2016/11/24.
 */
public class EducationMakeupCjActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private EditText makeexam_name_edt;
    private ImageView makeupperformanc_wsj_img;
    private XListView makeupperformance_lv;
    private EducationMakeupCjAdapter cjAdapter;
    private List<MakeupCjEntity> makeupCjEntities;
    //网络请求类
    private EducationalPartInternet educationalPartInternet;
    private EducationalPartPersener educationalPartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationmakeupcj_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        makeupCjEntities = new ArrayList<MakeupCjEntity>();
        educationalPartInternet = new EducationalPartInternet(this,myHandler);
        educationalPartPersener = new EducationalPartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        educationalPartInternet.getMakeUpExam_ListDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        makeupperformanc_wsj_img=(ImageView)this.findViewById(R.id.makeupperformanc_wsj_img);
        makeexam_name_edt = (EditText)this.findViewById(R.id.makeexam_name_edt);
        makeupperformance_lv = (XListView)this.findViewById(R.id.makeupperformance_lv);
        makeupperformance_lv.setPullRefreshEnable(true);
        makeupperformance_lv.setPullLoadEnable(false);
        makeupperformance_lv.setXListViewListener(this);
        cjAdapter = new EducationMakeupCjAdapter(this,makeupCjEntities);
        makeupperformance_lv.setAdapter(cjAdapter);
    }
    public void initViewVlaues(){
        title.setText("补考成绩查询");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        makeexam_name_edt.addTextChangedListener(serachclier);
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
            username = makeexam_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(makeupCjEntities.size()>0){
                    makeupCjEntities.clear();
                }
                educationalPartInternet.getMakeUpExam_ListDatas(String.valueOf(pageNum),username);
            }else{
                educationalPartInternet.getMakeUpExam_ListDatas(String.valueOf(pageNum),username);
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
                    ToastUtil.showToast("连接超时",EducationMakeupCjActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MakeupCjEntity> datas = educationalPartPersener.parseMakeUpExamListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(makeupCjEntities.size()==0){
                            makeupperformanc_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            makeupperformanc_wsj_img.setVisibility(View.GONE);
                        }
                        makeupperformance_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",EducationMakeupCjActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<MakeupCjEntity> datas){
        if(pageNum==1){
            if(makeupCjEntities.size()>0){
                makeupCjEntities.clear();
            }
        }
        if(datas.size()>0){
            makeupperformance_lv.setPullLoadEnable(true);
        }
        for(MakeupCjEntity entity:datas){
            makeupCjEntities.add(entity);
        }
        if(makeupCjEntities.size()==0){
            makeupperformanc_wsj_img.setVisibility(View.VISIBLE);
        }else {
            makeupperformanc_wsj_img.setVisibility(View.GONE);
        }
        cjAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        educationalPartInternet.getMakeUpExam_ListDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        educationalPartInternet.getMakeUpExam_ListDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        makeupperformance_lv.stopRefresh();
        makeupperformance_lv.stopLoadMore();
        makeupperformance_lv.setRefreshTime("刚刚");
    }
}
