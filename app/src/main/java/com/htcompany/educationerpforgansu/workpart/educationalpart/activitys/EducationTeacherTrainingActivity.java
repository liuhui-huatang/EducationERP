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
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationTeacherTrainEnity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationTeacherTrainingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师培训计划管理
 * Created by wrb on 2016/11/25.
 */
public class EducationTeacherTrainingActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{

    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private ImageView teachertraining_wsj_img;
    private XListView teachertraining_lv;
    private EditText teachertrainproject_name_edt;
    private EducationTeacherTrainingAdapter trainingAdapter;
    //网络请求类
    private EducationalPartInternet educationalPartInternet;
    private EducationalPartPersener educationalPartPersener;
    private WaitDialog waitDialog=null;
    private List<EducationTeacherTrainEnity> teacherTrainEnities;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationteachertraining_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        teacherTrainEnities = new ArrayList<EducationTeacherTrainEnity>();
        educationalPartInternet = new EducationalPartInternet(this,myHandler);
        educationalPartPersener = new EducationalPartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        educationalPartInternet.getTeacherTrainProject_ListDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn= (RelativeLayout)this.findViewById(R.id.right_three_btn);
        teachertraining_wsj_img=(ImageView)this.findViewById(R.id.teachertraining_wsj_img);
        teachertrainproject_name_edt=(EditText)this.findViewById(R.id.teachertrainproject_name_edt);
        rightthree_btn_tv= (TextView)this.findViewById(R.id.rightthree_btn_tv);
        teachertraining_lv = (XListView)this.findViewById(R.id.teachertraining_lv);
        teachertraining_lv.setPullRefreshEnable(true);
        teachertraining_lv.setPullLoadEnable(false);
        teachertraining_lv.setXListViewListener(this);
        trainingAdapter = new EducationTeacherTrainingAdapter(this,teacherTrainEnities);
        teachertraining_lv.setAdapter(trainingAdapter);
    }
    public void initViewVlaues(){
        title.setText("教师培训计划管理");
//        right_three_btn.setVisibility(View.VISIBLE);
//        rightthree_btn_tv.setText("添加");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        teachertraining_lv.setOnItemClickListener(trainingClicer);
        teachertrainproject_name_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener trainingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //跳到培训计划详情界面
            EducationTeacherTrainEnity entity=(EducationTeacherTrainEnity) trainingAdapter.getItem(position-1);
            Intent intent = new Intent(EducationTeacherTrainingActivity.this,EducationTeacherTrainingProjectDtailsActivity.class);
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
            case R.id.right_three_btn:
                //跳到添加培训计划界面
                Intent intent = new Intent(EducationTeacherTrainingActivity.this,EducationTeacherTrainingAddActivity.class);
                startActivity(intent);
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
            username = teachertrainproject_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(teacherTrainEnities.size()>0){
                    teacherTrainEnities.clear();
                }
                educationalPartInternet.getTeacherTrainProject_ListDatas(String.valueOf(pageNum),username);
            }else{
                educationalPartInternet.getTeacherTrainProject_ListDatas(String.valueOf(pageNum),username);
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
                    ToastUtil.showToast("连接超时",EducationTeacherTrainingActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<EducationTeacherTrainEnity> datas = educationalPartPersener.parseTeacherTrainListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(teacherTrainEnities.size()==0){
                            teachertraining_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            teachertraining_wsj_img.setVisibility(View.GONE);
                        }
                        teachertraining_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",EducationTeacherTrainingActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<EducationTeacherTrainEnity> datas){
        if(pageNum==1) {
            if (teacherTrainEnities.size() > 0) {
                teacherTrainEnities.clear();
            }
        }
        if(datas.size()>0){
            teachertraining_lv.setPullLoadEnable(true);
        }
        for(EducationTeacherTrainEnity entity:datas){
            teacherTrainEnities.add(entity);
        }
        if(teacherTrainEnities.size()==0){
            teachertraining_wsj_img.setVisibility(View.VISIBLE);
        }else {
            teachertraining_wsj_img.setVisibility(View.GONE);
        }
        trainingAdapter.notifyDataSetChanged();
        stopListView();
    }
    @Override
    public void onRefresh() {
        pageNum=1;
        educationalPartInternet.getTeacherTrainProject_ListDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        educationalPartInternet.getTeacherTrainProject_ListDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        teachertraining_lv.stopRefresh();
        teachertraining_lv.stopLoadMore();
        teachertraining_lv.setRefreshTime("刚刚");
    }
}
