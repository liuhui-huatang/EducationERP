package com.htcompany.educationerpforgansu.workpart.exampart.activitys;

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
import com.htcompany.educationerpforgansu.internet.exampart.ExamInternet;
import com.htcompany.educationerpforgansu.internet.exampart.ExamPersener;
import com.htcompany.educationerpforgansu.workpart.exampart.adapters.ExampartStudentSearchAdapter;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExampartStudentSearchEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生考试信息查询
 * Created by wrb on 2016/11/24.
 */
public class ExampartStudentSearchActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView exampartstudentsearch_wsj_img;
    private XListView exampartstudentsearch_lv;
    private ExampartStudentSearchAdapter studentSearchAdapter;
    private EditText exampartstudentsearch_name_edt;
    private String valuekey="";
    private String kstitle="";
    private List<ExampartStudentSearchEntity> studentSearchEntities;
    private ExamInternet examInternet;
    private ExamPersener examPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exampartstudentsearch_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        valuekey=getIntent().getStringExtra("value");
        kstitle=getIntent().getStringExtra("title");
        studentSearchEntities = new ArrayList<ExampartStudentSearchEntity>();
        examInternet = new ExamInternet(this,myHandler);
        examPersener = new ExamPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        examInternet.getExampartStudentSearchlistDatas(String.valueOf(pageNum),name,valuekey);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        exampartstudentsearch_wsj_img=(ImageView)this.findViewById(R.id.exampartstudentsearch_wsj_img);
        exampartstudentsearch_name_edt=(EditText)this.findViewById(R.id.exampartstudentsearch_name_edt);
        exampartstudentsearch_lv = (XListView)this.findViewById(R.id.exampartstudentsearch_lv);
        exampartstudentsearch_lv.setPullRefreshEnable(true);
        exampartstudentsearch_lv.setPullLoadEnable(false);
        exampartstudentsearch_lv.setXListViewListener(this);
        studentSearchAdapter = new ExampartStudentSearchAdapter(this,studentSearchEntities);
        exampartstudentsearch_lv.setAdapter(studentSearchAdapter);
    }
    public void initViewVlaues(){
        title.setText(kstitle);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        exampartstudentsearch_name_edt.addTextChangedListener(serachclier);
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
            name = exampartstudentsearch_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(studentSearchEntities.size()>0){
                    studentSearchEntities.clear();
                }
                examInternet.getExampartStudentSearchlistDatas(String.valueOf(pageNum),name,valuekey);
            }else{
                examInternet.getExampartStudentSearchlistDatas(String.valueOf(pageNum),name,valuekey);
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
                    ToastUtil.showToast("连接超时",ExampartStudentSearchActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ExampartStudentSearchEntity> datas = examPersener.parseExampartStudentSearchlistData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(studentSearchEntities.size()==0){
                            exampartstudentsearch_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            exampartstudentsearch_wsj_img.setVisibility(View.GONE);
                        }
                        exampartstudentsearch_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",ExampartStudentSearchActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<ExampartStudentSearchEntity> datas){
        if(pageNum==1){
            if(datas.size()>0){
                studentSearchEntities.clear();
            }
        }
        if(datas.size()>0){
            exampartstudentsearch_lv.setPullLoadEnable(true);
        }
        for(ExampartStudentSearchEntity entity:datas){
            studentSearchEntities.add(entity);
        }
        if(studentSearchEntities.size()==0){
            exampartstudentsearch_wsj_img.setVisibility(View.VISIBLE);
        }else {
            exampartstudentsearch_wsj_img.setVisibility(View.GONE);
        }
        studentSearchAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        examInternet.getExampartStudentSearchlistDatas(String.valueOf(pageNum),name,valuekey);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        examInternet.getExampartStudentSearchlistDatas(String.valueOf(pageNum),name,valuekey);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        exampartstudentsearch_lv.stopRefresh();
        exampartstudentsearch_lv.stopLoadMore();
        exampartstudentsearch_lv.setRefreshTime("刚刚");
    }
}
