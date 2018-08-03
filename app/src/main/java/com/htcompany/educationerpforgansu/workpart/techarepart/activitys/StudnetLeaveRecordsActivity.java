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
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.StudentLeaveApplyDetailsManageActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.adapters.StudentLeaveApplyManageAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.StudentLeaveApplyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生请假
 * Created by wrb on 2016/11/25.
 */
public class StudnetLeaveRecordsActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
private TextView title;
    private RelativeLayout reback_btn;
    private XListView personleaveapplymanage_lv;
    private StudentLeaveApplyManageAdapter leaveApplyManageAdapter;
    private List<StudentLeaveApplyEntity> applyEntities;
    private Intent intent=null;
    private EditText seacherxsqj_name_edt;
    private ImageView personleaveapplymanage_wsj_img;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private String username="";
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personleaveapplymanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        applyEntities = new ArrayList<StudentLeaveApplyEntity>();
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getStudentLeaveRecordsListDatas(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        personleaveapplymanage_wsj_img=(ImageView)this.findViewById(R.id.personleaveapplymanage_wsj_img);
        personleaveapplymanage_lv = (XListView)this.findViewById(R.id.personleaveapplymanage_lv);
        personleaveapplymanage_lv.setPullRefreshEnable(true);
        personleaveapplymanage_lv.setPullLoadEnable(false);
        personleaveapplymanage_lv.setXListViewListener(this);
        seacherxsqj_name_edt = (EditText)this.findViewById(R.id.seacherxsqj_name_edt);
        seacherxsqj_name_edt.setHint("输入学生姓名");
        leaveApplyManageAdapter = new StudentLeaveApplyManageAdapter(this,applyEntities);
        personleaveapplymanage_lv.setAdapter(leaveApplyManageAdapter);
    }
    public void initViewValues(){
        title.setText("学生请假");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        seacherxsqj_name_edt.addTextChangedListener(serachclier);
        personleaveapplymanage_lv.setOnItemClickListener(leaveManageClicer);
    }
    public AdapterView.OnItemClickListener leaveManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到请假详情界面
            StudentLeaveApplyEntity entity = (StudentLeaveApplyEntity) leaveApplyManageAdapter.getItem(position-1);
            intent = new Intent(StudnetLeaveRecordsActivity.this, StudentLeaveApplyDetailsManageActivity.class);
            intent.putExtra("applyEntity",entity);
            startActivityForResult(intent,100);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    teacherInternet.getStudentLeaveRecordsListDatas(String.valueOf(pageNum),username);
                    break;
            }
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
            username = seacherxsqj_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(applyEntities.size()>0){
                    applyEntities.clear();
                }
                teacherInternet.getStudentLeaveRecordsListDatas(String.valueOf(pageNum),username);
            }else{
                teacherInternet.getStudentLeaveRecordsListDatas(String.valueOf(pageNum),username);
            }
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
                    List<StudentLeaveApplyEntity> datas = teacherPersener.parseStudentLeaveRecordsListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else {
                        personleaveapplymanage_lv.setPullLoadEnable(false);
                        stopListView();
                        if(applyEntities.size()==0){
                            personleaveapplymanage_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            personleaveapplymanage_wsj_img.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudnetLeaveRecordsActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(applyEntities.size()==0){
                        personleaveapplymanage_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        personleaveapplymanage_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接服务器失败",StudnetLeaveRecordsActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<StudentLeaveApplyEntity> datas){
        if(pageNum==1){
            if (applyEntities.size()>0){
                applyEntities.clear();
            }
        }
        if(datas.size()>0){
            personleaveapplymanage_lv.setPullLoadEnable(true);
        }
        for(StudentLeaveApplyEntity entity:datas){
            applyEntities.add(entity);
        }
        if(applyEntities.size()==0){
            personleaveapplymanage_wsj_img.setVisibility(View.VISIBLE);
        }else {
            personleaveapplymanage_wsj_img.setVisibility(View.GONE);
        }
        leaveApplyManageAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        teacherInternet.getStudentLeaveRecordsListDatas(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        teacherInternet.getStudentLeaveRecordsListDatas(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        personleaveapplymanage_lv.stopRefresh();
        personleaveapplymanage_lv.stopLoadMore();
        personleaveapplymanage_lv.setRefreshTime("刚刚");
    }
}
