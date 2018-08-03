package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SystemBarTintManager;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.dailogs.MyLeaveTypeDailog;
import com.htcompany.educationerpforgansu.workpart.entities.MyLeaveApplyEntity;
import com.htcompany.educationerpforgansu.workpart.entities.MyLeaveTypeEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 添加请假
 * Created by wrb on 2016/11/10.
 */
public class MyLeaveApplyAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private TextView myleaveadd_type_tv;
    private String  leaveType="";//1事假 2 病假
    private TextView leaveadd_starttime_tv,leaveadd_endtime_tv;
    private EditText leaveadd_qjsy_edt;
    private DateViewDailog dateViewDailog;
    private MyLeaveTypeDailog typeDailog;
    private List<MyLeaveTypeEntity> typeEntities;
    //===========身份选择=========
    private RadioGroup leaveapply_shenfen_rg;
    private RadioButton leaveapply_ptjzg_rd,leaveapply_zcld_rd;
    private String sfType="1";//1普通教职工，2中层领导
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private TimePickerView pickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myleaveapplyadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        typeEntities=new ArrayList<MyLeaveTypeEntity>();
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalPersener = new WokrpersonalPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        wokrpersonalInternet.getMyLeaveApplyTypesList_Datas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        rightthree_btn_tv = (TextView)this.findViewById(R.id.rightthree_btn_tv);
        leaveadd_starttime_tv= (TextView)this.findViewById(R.id.leaveadd_starttime_tv);
        leaveadd_endtime_tv= (TextView)this.findViewById(R.id.leaveadd_endtime_tv);
        leaveadd_qjsy_edt = (EditText)this.findViewById(R.id.leaveadd_qjsy_edt);
        myleaveadd_type_tv=(TextView)this.findViewById(R.id.myleaveadd_type_tv);

        leaveapply_shenfen_rg=(RadioGroup)this.findViewById(R.id.leaveapply_shenfen_rg);
        leaveapply_ptjzg_rd=(RadioButton)this.findViewById(R.id.leaveapply_ptjzg_rd);
        leaveapply_zcld_rd=(RadioButton)this.findViewById(R.id.leaveapply_zcld_rd);
        leaveapply_shenfen_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==leaveapply_ptjzg_rd.getId()){
                    sfType="1";
                }else if(checkedId==leaveapply_zcld_rd.getId()){
                    sfType="2";
                }
            }
        });
    }
    public void initViewValues(){
        title.setText("请假申请");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("提交");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        leaveadd_starttime_tv.setOnClickListener(this);
        leaveadd_endtime_tv.setOnClickListener(this);
        myleaveadd_type_tv.setOnClickListener(this);
    }
     public AdapterView.OnItemClickListener typeCliceer = new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             MyLeaveTypeEntity typeEntity = (MyLeaveTypeEntity) typeEntities.get(position);
             leaveType=typeEntity.getId();
             myleaveadd_type_tv.setText(typeEntity.getName());
             typeDailog.dismiss();
         }
     };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.myleaveadd_type_tv:
                //选择请假类型
                if(typeEntities.size()>0){
                    typeDailog = new MyLeaveTypeDailog(this,typeEntities,typeCliceer);
                    typeDailog.show();
                }else{
                }
                break;
            case R.id.right_three_btn:
                //提交请假
                if("".equals(myleaveadd_type_tv.getText().toString())){
                    ToastUtil.showToast("请选择请假类型",MyLeaveApplyAddActivity.this);
                }else if("".equals(leaveadd_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请选择开始时间",MyLeaveApplyAddActivity.this);
                }else if("".equals(leaveadd_endtime_tv.getText().toString())){
                    ToastUtil.showToast("请选择结束时间",MyLeaveApplyAddActivity.this);
                }else if("".equals(leaveadd_qjsy_edt.getText().toString())){
                    ToastUtil.showToast("请添加请假事由",MyLeaveApplyAddActivity.this);
                }else{
                    waitDialog.show();
                    wokrpersonalInternet.addMyLeaveApplyList_Datas(leaveadd_starttime_tv.getText().toString(),
                            leaveadd_endtime_tv.getText().toString(),
                            leaveadd_qjsy_edt.getText().toString(),
                            leaveType,sfType);
                }
                break;
            case R.id.leaveadd_starttime_tv:
                //开始时间
//                dateViewDailog = new DateViewDailog(MyLeaveApplyAddActivity.this,1000,myHandler,true);
//                dateViewDailog.show();
                pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        if(BaseUtils.DateCompare(BaseUtils.getTime(date),BaseUtils.getSystemTime())){
                            leaveadd_starttime_tv.setText(BaseUtils.getTime(date));
                        }else {
                            ToastUtil.showToast("开始时间必须大于当前时间",MyLeaveApplyAddActivity.this);
                        }
                    }
                }).setType(new boolean[]{true, true, true, true, true, false})
                        .setLabel("", "", "", "点", "分", "")
                        .isCenterLabel(false)
                        .isCyclic(true)
                        .setDividerColor(Color.DKGRAY)
                        .setContentSize(21)
                        .setTitleText("选择时间")
                        .setTitleColor(Color.BLUE)
                        .setTitleBgColor(Color.WHITE)
                        .isDialog(true)
                        .build();
                pickerView.show();
                break;
            case R.id.leaveadd_endtime_tv:
                //结束时间
//                dateViewDailog = new DateViewDailog(MyLeaveApplyAddActivity.this,2000,myHandler,true);
//                dateViewDailog.show();
                if("".equals(leaveadd_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请先选择开始时间",MyLeaveApplyAddActivity.this);
                }else {
                    pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            if (BaseUtils.DateCompare(BaseUtils.getTime(date), leaveadd_starttime_tv.getText().toString())) {
                                leaveadd_endtime_tv.setText(BaseUtils.getTime(date));
                            } else {
                                ToastUtil.showToast("结束时间必须大于开始时间", MyLeaveApplyAddActivity.this);
                            }
                        }
                    }).setType(new boolean[]{true, true, true, true, true, false})
                            .setLabel("", "", "", "点", "分", "")
                            .isCenterLabel(false)
                            .isCyclic(true)
                            .setDividerColor(Color.DKGRAY)
                            .setContentSize(21)
                            .setTitleText("选择时间")
                            .setTitleColor(Color.BLUE)
                            .setTitleBgColor(Color.WHITE)
                            .isDialog(true)
                            .build();
                    pickerView.show();
                }
                break;
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    leaveadd_starttime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 2000:
                    leaveadd_endtime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",MyLeaveApplyAddActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(wokrpersonalPersener.parserAddAssetReapir((String)msg.obj)){
                        Intent intent = new Intent();
                    setResult(100,intent);
                    finish();
                    }
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MyLeaveTypeEntity> datas = wokrpersonalPersener.parseMyLeaveTypes_ListData((String)msg.obj);
                      if(datas!=null&&datas.size()>0){
                          if(typeEntities.size()>0){
                              typeEntities.clear();
                          }
                          for(MyLeaveTypeEntity entity:datas){
                              typeEntities.add(entity);
                          }
                      }

                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyLeaveApplyAddActivity.this);
                    break;
            }
        }
    };
}
