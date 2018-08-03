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
import com.htcompany.educationerpforgansu.workpart.exampart.adapters.ExampartInvigilateAdapter;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExampartInvigilateEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 监考信息查询
 * Created by wrb on 2016/11/24.
 */
public class ExampartInvigilateActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView exampartinvigilate_wsj_img;
    private EditText exampartinvigilate_name_edt;
    private XListView exampartinvigilate_lv;
    private ExampartInvigilateAdapter invigilateAdapter;
    private String valuekey="";
    private String kstitle="";
    private List<ExampartInvigilateEntity> invigilateEntities;
    private ExamInternet examInternet;
    private ExamPersener examPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exampartinvigilate_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        valuekey=getIntent().getStringExtra("value");
        kstitle=getIntent().getStringExtra("title");
        invigilateEntities = new ArrayList<ExampartInvigilateEntity>();
        examInternet = new ExamInternet(this,myHandler);
        examPersener = new ExamPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        examInternet.getExampartInvigilateSearchlistDatas(String.valueOf(pageNum),name,valuekey);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        exampartinvigilate_wsj_img=(ImageView)this.findViewById(R.id.exampartinvigilate_wsj_img);
        exampartinvigilate_name_edt=(EditText)this.findViewById(R.id.exampartinvigilate_name_edt) ;
        exampartinvigilate_lv = (XListView)this.findViewById(R.id.exampartinvigilate_lv);
        exampartinvigilate_lv.setPullRefreshEnable(true);
        exampartinvigilate_lv.setPullLoadEnable(false);
        exampartinvigilate_lv.setXListViewListener(this);
        invigilateAdapter = new ExampartInvigilateAdapter(this,invigilateEntities);
        exampartinvigilate_lv.setAdapter(invigilateAdapter);
    }
    public void initViewVlaues(){
        title.setText(kstitle);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        exampartinvigilate_name_edt.addTextChangedListener(serachclier);
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
            name = exampartinvigilate_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(invigilateEntities.size()>0){
                    invigilateEntities.clear();
                }
                examInternet.getExampartInvigilateSearchlistDatas(String.valueOf(pageNum),name,valuekey);
            }else{
                examInternet.getExampartInvigilateSearchlistDatas(String.valueOf(pageNum),name,valuekey);
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
                    ToastUtil.showToast("连接超时",ExampartInvigilateActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ExampartInvigilateEntity> datas = examPersener.parseExampartInvigilateSearchlistData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(invigilateEntities.size()==0){
                            exampartinvigilate_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            exampartinvigilate_wsj_img.setVisibility(View.GONE);
                        }
                        exampartinvigilate_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",ExampartInvigilateActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<ExampartInvigilateEntity> datas){
        if(pageNum==1){
            if(datas.size()>0){
                invigilateEntities.clear();
            }
        }
        if(datas.size()>0){
            exampartinvigilate_lv.setPullLoadEnable(true);
        }
        for(ExampartInvigilateEntity entity:datas){
            invigilateEntities.add(entity);
        }
        if(invigilateEntities.size()==0){
            exampartinvigilate_wsj_img.setVisibility(View.VISIBLE);
        }else {
            exampartinvigilate_wsj_img.setVisibility(View.GONE);
        }
        invigilateAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        examInternet.getExampartInvigilateSearchlistDatas(String.valueOf(pageNum),name,valuekey);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        examInternet.getExampartInvigilateSearchlistDatas(String.valueOf(pageNum),name,valuekey);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        exampartinvigilate_lv.stopRefresh();
        exampartinvigilate_lv.stopLoadMore();
        exampartinvigilate_lv.setRefreshTime("刚刚");
    }
}
