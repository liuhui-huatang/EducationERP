package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.homepart.entity.PersonalAssessmentEntity;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomeInternet;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomePersener;
import com.htcompany.educationerpforgansu.workpart.adapters.PersonalAssessmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人考核
 * Created by wrb on 2016/11/8.
 */
public class PersonalAssessmentActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView personalassessment_lv;
    private ImageView personalassessment_wsj_img;
    private PersonalAssessmentAdapter personalAssessmentAdapter;
    private List<PersonalAssessmentEntity> assessmentEntities;
    //=========================网络请求类==========================
    private MainHomeInternet homeInternet;
    private MainHomePersener homePersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalassessment_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas() {
        assessmentEntities = new ArrayList<PersonalAssessmentEntity>();
        homeInternet = new MainHomeInternet(this, myHandler);
        homePersener = new MainHomePersener(this, myHandler);
        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        homeInternet.getPersonalAsseMentListMsg();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        personalassessment_wsj_img=(ImageView)this.findViewById(R.id.personalassessment_wsj_img);
        personalassessment_lv = (ListView)this.findViewById(R.id.personalassessment_lv);
        personalAssessmentAdapter = new PersonalAssessmentAdapter(this,assessmentEntities);
        personalassessment_lv.setAdapter(personalAssessmentAdapter);
    }
    public void initViewValues(){
        title.setText("个人考核");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
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
                    ToastUtil.showToast("连接超时",PersonalAssessmentActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<PersonalAssessmentEntity> datas = homePersener.parsePersonalAssessment_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        personalassessment_wsj_img.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        personalassessment_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",PersonalAssessmentActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<PersonalAssessmentEntity> datas){
        if(assessmentEntities.size()>0){
            assessmentEntities.clear();
        }
        for(PersonalAssessmentEntity entity:datas){
            assessmentEntities.add(entity);
        }
        personalAssessmentAdapter.notifyDataSetChanged();
    }
}
