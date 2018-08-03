package com.htcompany.educationerpforgansu.workpart.studentpart.activitys;

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
import com.htcompany.educationerpforgansu.internet.studentpart.StudentPartInternet;
import com.htcompany.educationerpforgansu.internet.studentpart.StudentPartPersener;
import com.htcompany.educationerpforgansu.workpart.studentpart.adapters.StudentMessageAdapter;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentMessageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生查询
 * Created by wrb on 2016/11/23.
 */
public class StudentMessageActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private XListView studentmessage_lv;
    private StudentMessageAdapter messageAdapter;
    private EditText studentmessage_search_edt;
    private ImageView studentmessage_wsj_img;
    //网络数据请求类
    private List<StudentMessageEntity> messageEntities;
    private StudentPartInternet studentPartInternet;
    private StudentPartPersener studentPartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmessage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        messageEntities = new ArrayList<StudentMessageEntity>();
        studentPartInternet = new StudentPartInternet(this,myHandler);
        studentPartPersener = new StudentPartPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        studentPartInternet.getStudentMessageList(String.valueOf(pageNum),username);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        studentmessage_wsj_img=(ImageView)this.findViewById(R.id.studentmessage_wsj_img);
        studentmessage_search_edt = (EditText)this.findViewById(R.id.studentmessage_search_edt);
        studentmessage_lv = (XListView)this.findViewById(R.id.studentmessage_lv);
        studentmessage_lv.setPullRefreshEnable(true);
        studentmessage_lv.setPullLoadEnable(false);
        studentmessage_lv.setXListViewListener(this);
        messageAdapter = new StudentMessageAdapter(this,messageEntities);
        studentmessage_lv.setAdapter(messageAdapter);

    }
    public void initViewValues(){
        title.setText("学生信息");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        studentmessage_lv.setOnItemClickListener(messageClicer);
        studentmessage_search_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener messageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            StudentMessageEntity entity = (StudentMessageEntity) messageAdapter.getItem(position-1);
            if(entity!=null) {
                Intent intent = new Intent(StudentMessageActivity.this, StudentMessageDetailItemActivity.class);
                intent.putExtra("entity",entity);
                startActivity(intent);
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
    public TextWatcher serachclier = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            username = studentmessage_search_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(messageEntities.size()>0){
                    messageEntities.clear();
                }
                studentPartInternet.getStudentMessageList(String.valueOf(pageNum),username);
            }else{
                studentPartInternet.getStudentMessageList(String.valueOf(pageNum),username);
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
                    if(messageEntities.size()==0){
                        studentmessage_wsj_img.setVisibility(View.VISIBLE);
                    }else{
                        studentmessage_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",StudentMessageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<StudentMessageEntity> datas = studentPartPersener.parseStudentMessageListDatas((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(messageEntities.size()==0){
                            studentmessage_wsj_img.setVisibility(View.VISIBLE);
                        }else{
                            studentmessage_wsj_img.setVisibility(View.GONE);
                        }
                        studentmessage_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentMessageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<StudentMessageEntity> datas){
        if(pageNum==1){
            if(messageEntities.size()>0){
                messageEntities.clear();
            }
        }
        if(datas.size()>50){
            studentmessage_lv.setPullLoadEnable(true);
        }
        for(StudentMessageEntity entity:datas){
            messageEntities.add(entity);
        }
        if(messageEntities.size()==0){
            studentmessage_wsj_img.setVisibility(View.VISIBLE);
        }else{
            studentmessage_wsj_img.setVisibility(View.GONE);
        }
        messageAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        studentPartInternet.getStudentMessageList(String.valueOf(pageNum),username);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        studentPartInternet.getStudentMessageList(String.valueOf(pageNum),username);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        studentmessage_lv.stopRefresh();
        studentmessage_lv.stopLoadMore();
        studentmessage_lv.setRefreshTime("刚刚");
    }
}
