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
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.ClassCjEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationClassPerformanceAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级成绩
 * Created by wrb on 2016/11/24.
 */
public class EducationClassPerformanceActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView classperformance_wsj_img;
    private XListView classperformance_lv;
    private EditText bjexamhz_name_edt;
    private EducationClassPerformanceAdapter performanceAdapter;
    private List<ClassCjEntity> cjEntities;
    //网络请求类
    private EducationalPartInternet educationalPartInternet;
    private EducationalPartPersener educationalPartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationclassperformance_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        cjEntities = new ArrayList<ClassCjEntity>();
        educationalPartInternet = new EducationalPartInternet(this,myHandler);
        educationalPartPersener = new EducationalPartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        educationalPartInternet.getClassExamHz_ListDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        classperformance_wsj_img=(ImageView)this.findViewById(R.id.classperformance_wsj_img);
        bjexamhz_name_edt=(EditText)this.findViewById(R.id.bjexamhz_name_edt);
        classperformance_lv = (XListView)this.findViewById(R.id.classperformance_lv);
        classperformance_lv.setPullRefreshEnable(true);
        classperformance_lv.setPullLoadEnable(false);
        classperformance_lv.setXListViewListener(this);
        performanceAdapter = new EducationClassPerformanceAdapter(this,cjEntities);
        classperformance_lv.setAdapter(performanceAdapter);
    }
    public void initViewVlaues(){
        title.setText("班级成绩汇总");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        bjexamhz_name_edt.addTextChangedListener(serachclier);
        classperformance_lv.setOnItemClickListener(reserverClicer);
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
            username = bjexamhz_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(cjEntities.size()>0){
                    cjEntities.clear();
                }
                educationalPartInternet.getClearExam_ListDatas(String.valueOf(pageNum),username);
            }else{
                educationalPartInternet.getClearExam_ListDatas(String.valueOf(pageNum),username);
            }
        }
    };
    public AdapterView.OnItemClickListener reserverClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ClassCjEntity entity = (ClassCjEntity) performanceAdapter.getItem(position-1);
            Intent intent = new Intent(EducationClassPerformanceActivity.this,EducationClassPerformanceDetailsActivity.class);
            intent.putExtra("cjEntity",entity);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:

                    break;
            }
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
                    if(cjEntities.size()==0){
                        classperformance_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        classperformance_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",EducationClassPerformanceActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ClassCjEntity> datas = educationalPartPersener.parseClassExamHZListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(cjEntities.size()==0){
                            classperformance_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            classperformance_wsj_img.setVisibility(View.GONE);
                        }
                        classperformance_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",EducationClassPerformanceActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<ClassCjEntity> datas){
        if(pageNum==1){
            if(cjEntities.size()>0){
                cjEntities.clear();
            }
        }
        if(datas.size()>0){
            classperformance_lv.setPullLoadEnable(true);
        }
        for(ClassCjEntity entity:datas){
            cjEntities.add(entity);
        }
        if(cjEntities.size()==0){
            classperformance_wsj_img.setVisibility(View.VISIBLE);
        }else {
            classperformance_wsj_img.setVisibility(View.GONE);
        }
        performanceAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        educationalPartInternet.getClassExamHz_ListDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        educationalPartInternet.getClassExamHz_ListDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        classperformance_lv.stopRefresh();
        classperformance_lv.stopLoadMore();
        classperformance_lv.setRefreshTime("刚刚");
    }
}
