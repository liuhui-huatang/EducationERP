package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

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
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeachPartClassExamAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassExamEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级成绩
 * Created by wrb on 2016/11/25.
 */
public class TeachPartClassExamActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private XListView teachpartclassexam_lv;
    private TeachPartClassExamAdapter examAdapter;
    private List<ClassExamEntity> examEntities;
    private EditText bzrexame_name_edt;
    private ImageView teachpartclassexam_wsj_img;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachpartclassexam_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        examEntities = new ArrayList<ClassExamEntity>();
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getAllClassExamListDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        bzrexame_name_edt=(EditText)this.findViewById(R.id.bzrexame_name_edt);
        teachpartclassexam_wsj_img=(ImageView)this.findViewById(R.id.teachpartclassexam_wsj_img);
        teachpartclassexam_lv = (XListView)this.findViewById(R.id.teachpartclassexam_lv);
        teachpartclassexam_lv.setPullRefreshEnable(true);
        teachpartclassexam_lv.setPullLoadEnable(false);
        teachpartclassexam_lv.setXListViewListener(this);
        examAdapter = new TeachPartClassExamAdapter(this,examEntities);
        teachpartclassexam_lv.setAdapter(examAdapter);
    }
    public void initViewValues(){
        title.setText("班级成绩");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        teachpartclassexam_lv.setOnItemClickListener(recordClicer);
        bzrexame_name_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener recordClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ClassExamEntity entity = (ClassExamEntity) examAdapter.getItem(position-1);
            Intent intent = new Intent(TeachPartClassExamActivity.this,TeachPartClassExamDetailsActivity.class);
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
            username = bzrexame_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(examEntities.size()>0){
                    examEntities.clear();
                }
                teacherInternet.getAllClassExamListDatas(String.valueOf(pageNum),username);
            }else{
                pageNum=1;
                username="";
                teacherInternet.getAllClassExamListDatas(String.valueOf(pageNum),username);
            }
        }
    };
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ClassExamEntity> datas = teacherPersener.parseCLassExamData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else{
                        if(examEntities.size()==0){
                            teachpartclassexam_wsj_img.setVisibility(View.VISIBLE);
                        }else{
                            teachpartclassexam_wsj_img.setVisibility(View.GONE);
                        }
                        teachpartclassexam_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachPartClassExamActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(examEntities.size()==0){
                        teachpartclassexam_wsj_img.setVisibility(View.VISIBLE);
                    }else{
                        teachpartclassexam_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接服务器失败",TeachPartClassExamActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setAdapterDatas(List<ClassExamEntity> datas){
        if(pageNum==1){
            if(examEntities.size()>0){
                examEntities.clear();
            }
        }
        if(datas.size()>50){
            teachpartclassexam_lv.setPullLoadEnable(true);
        }
        for(ClassExamEntity entity:datas){
            examEntities.add(entity);
        }
        if(examEntities.size()==0){
            teachpartclassexam_wsj_img.setVisibility(View.VISIBLE);
        }else{
            teachpartclassexam_wsj_img.setVisibility(View.GONE);
        }
        examAdapter.notifyDataSetChanged();
        stopListView();
    }
    @Override
    public void onRefresh() {
        pageNum=1;
        teacherInternet.getAllClassExamListDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        teacherInternet.getAllClassExamListDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        teachpartclassexam_lv.stopRefresh();
        teachpartclassexam_lv.stopLoadMore();
        teachpartclassexam_lv.setRefreshTime("刚刚");
    }
}
