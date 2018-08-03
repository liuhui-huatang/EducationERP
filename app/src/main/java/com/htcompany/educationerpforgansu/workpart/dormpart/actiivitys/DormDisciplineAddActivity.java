package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.internet.dormpart.DormInternet;
import com.htcompany.educationerpforgansu.internet.dormpart.DormPersener;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormBulingEntitiy;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormQQLBEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormRoomEntity;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormStudentEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 添加违纪
 * Created by wrb on 2016/11/23.
 */
public class DormDisciplineAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private TextView studentwjadd_ssl_tv,studentwjadd_ss_tv,studentwjadd_student_tv,studentwjadd_time_tv;
    private EditText studentwjadd_wjqk_edt,studentwjadd_clyj_edt;
    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private DateViewDailog dateViewDailog;
    private Intent intent=null;
    //上传字段
    private String dormbuild="";
    private String dormRoom="";
    private String student="";
    //选择实体
    private DormBulingEntitiy bulingEntitiy=null;
    private DormRoomEntity roomEntity=null;
    private DormStudentEntity studentEntity=null;
    private DormQQLBEntity qqlbEntity=null;
    //数据实体类
    private List<DormBulingEntitiy> bulingEntitiys;
    private List<DormRoomEntity> roomEntities;
    private List<DormStudentEntity> studentEntities;
    private SharedPrefUtil sharedPrefUtil=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormdisciplineadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        sharedPrefUtil = new SharedPrefUtil(this,"login");
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormKnowingManageAddSelecDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        rightthree_btn_tv= (TextView)this.findViewById(R.id.rightthree_btn_tv);
        right_three_btn= (RelativeLayout)this.findViewById(R.id.right_three_btn);
        studentwjadd_ssl_tv= (TextView)this.findViewById(R.id.studentwjadd_ssl_tv);
                studentwjadd_ss_tv= (TextView)this.findViewById(R.id.studentwjadd_ss_tv);
                studentwjadd_student_tv= (TextView)this.findViewById(R.id.studentwjadd_student_tv);
                studentwjadd_time_tv= (TextView)this.findViewById(R.id.studentwjadd_time_tv);
        studentwjadd_wjqk_edt=(EditText)this.findViewById(R.id.studentwjadd_wjqk_edt);
                studentwjadd_clyj_edt=(EditText)this.findViewById(R.id.studentwjadd_clyj_edt);
    }
    public void initViewValues(){
        title.setText("违纪添加");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("提交");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        studentwjadd_ssl_tv.setOnClickListener(this);
        studentwjadd_ss_tv.setOnClickListener(this);
        studentwjadd_student_tv.setOnClickListener(this);
        studentwjadd_time_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //天加
                if("".equals(studentwjadd_ssl_tv.getText().toString())){
                    ToastUtil.showToast("请选择宿舍楼",DormDisciplineAddActivity.this);
                }else if("".equals(studentwjadd_ss_tv.getText().toString())){
                    ToastUtil.showToast("请选择宿舍",DormDisciplineAddActivity.this);
                }else if("".equals(studentwjadd_student_tv.getText().toString())){
                    ToastUtil.showToast("请选择学生",DormDisciplineAddActivity.this);
                }else if("".equals(studentwjadd_time_tv.getText().toString())){
                    ToastUtil.showToast("请选择违纪时间",DormDisciplineAddActivity.this);
                }else if("".equals(studentwjadd_wjqk_edt.getText().toString())){
                    ToastUtil.showToast("请输入违纪情况",DormDisciplineAddActivity.this);
                }else if("".equals(studentwjadd_clyj_edt.getText().toString())){
                    ToastUtil.showToast("请输入处理意见",DormDisciplineAddActivity.this);
                }else {
                    waitDialog.show();
                    dormInternet.addDormDisciplineDatas(student,
                           studentwjadd_time_tv.getText().toString(),studentwjadd_wjqk_edt.getText().toString(),studentwjadd_clyj_edt.getText().toString());
                }
                break;
            case R.id.studentwjadd_ssl_tv:
                //选择宿舍楼
                if(bulingEntitiys!=null&&bulingEntitiys.size()>0) {
                    studentwjadd_ss_tv.setText("");
                    studentwjadd_student_tv.setText("");
                    intent = new Intent(DormDisciplineAddActivity.this, DormBulingSelectActivity.class);
                    intent.putExtra("bulingEntitiys",(Serializable) bulingEntitiys);
                    startActivityForResult(intent, 101);
                }else{
                    ToastUtil.showToast("暂无宿舍楼数据",DormDisciplineAddActivity.this);
                }
                break;
            case R.id.studentwjadd_ss_tv:
                //宿舍
                if("".equals(studentwjadd_ssl_tv.getText().toString())){
                    ToastUtil.showToast("请先选择宿舍楼",DormDisciplineAddActivity.this);
                }else{
                    if(roomEntities!=null&&roomEntities.size()>0) {
                        studentwjadd_student_tv.setText("");
                        intent = new Intent(DormDisciplineAddActivity.this, DormRoomSelectActivity.class);
                        intent.putExtra("roomEntities",(Serializable) roomEntities);
                        startActivityForResult(intent, 102);
                    }else{
                        ToastUtil.showToast("暂无宿舍数据",DormDisciplineAddActivity.this);
                    }
                }
                break;
            case R.id.studentwjadd_student_tv:
                //学生
                if("".equals(studentwjadd_ss_tv.getText().toString())){
                    ToastUtil.showToast("请先选择宿舍",DormDisciplineAddActivity.this);
                }else{
                    if(studentEntities!=null&&studentEntities.size()>0) {
                        intent = new Intent(DormDisciplineAddActivity.this, DormStudentSelectActivity.class);
                        intent.putExtra("studentEntities",(Serializable) studentEntities);
                        startActivityForResult(intent, 103);
                    }else{
                        ToastUtil.showToast("暂无学生数据",DormDisciplineAddActivity.this);
                    }
                }
                break;
            case R.id.studentwjadd_time_tv:
                //查询时间
                dateViewDailog = new DateViewDailog(DormDisciplineAddActivity.this,1000,myHandler,false);
                dateViewDailog.show();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    //宿舍楼
                    bulingEntitiy=(DormBulingEntitiy) data.getSerializableExtra("entity");
                    if(bulingEntitiy!=null){
                        studentwjadd_ssl_tv.setText(bulingEntitiy.getBuilding_name());
                        dormbuild=bulingEntitiy.getId();
                        roomEntities=bulingEntitiy.getRoom();
                    }
                    break;
                case 102:
                    //宿舍
                    roomEntity=(DormRoomEntity) data.getSerializableExtra("entity");
                    if(roomEntity!=null){
                        studentwjadd_ss_tv.setText(roomEntity.getNum());
                        dormRoom=roomEntity.getRoom_category_id();
                        studentEntities=roomEntity.getStudents();
                    }
                    break;
                case 103:
                    //学生
                    studentEntity=(DormStudentEntity) data.getSerializableExtra("entity");
                    if(studentEntity!=null){
                        studentwjadd_student_tv.setText(studentEntity.getTruename());
                        student=studentEntity.getStuid();
                    }
                    break;
            }
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    studentwjadd_time_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",DormDisciplineAddActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    dormPersener.parseDormBuldiongSelectManageData((String)msg.obj);
                    break;
                case 202:
                    Bundle b = msg.getData();
                    if(b!=null){
                        bulingEntitiys = (List<DormBulingEntitiy>) b.getSerializable("bulingEntitiys");
                    }
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    //添加成功
                    if(dormPersener.parserSuccessDATAS((String)msg.obj)){
                        Intent intent =new Intent();
                        setResult(101,intent);
                        finish();
                    }else{

                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",DormDisciplineAddActivity.this);
                    break;
            }
        }
    };
}
