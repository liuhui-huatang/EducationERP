package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.entities.AllCarsTypeEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllJZGEntity;

import java.util.Date;

/**
 * 添加车辆
 * Created by wrb on 2016/11/10.
 */
public class MyUseCarsAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reaback_btn,right_three_btn;

    private TextView myusercarsadd_cattype_tv,myusercarsadd_starttime_tv,myusercarsadd_endtime_tv,myusercarsadd_cardriver_tv;
    private EditText myusercarsadd_mdd_edt,myusercarsadd_lcs_tv,myusercarsadd_ccly_tv;

    private DateViewDailog dateViewDailog;
    private Intent intent=null;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private String carId="";
    private String userid="";
    private TimePickerView pickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myusecarsadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalPersener = new WokrpersonalPersener(this);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView)this.findViewById(R.id.rightthree_btn_tv);
        reaback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);

        myusercarsadd_cattype_tv= (TextView)this.findViewById(R.id.myusercarsadd_cattype_tv);
        myusercarsadd_starttime_tv= (TextView)this.findViewById(R.id.myusercarsadd_starttime_tv);
        myusercarsadd_endtime_tv= (TextView)this.findViewById(R.id.myusercarsadd_endtime_tv);
        myusercarsadd_cardriver_tv= (TextView)this.findViewById(R.id.myusercarsadd_cardriver_tv);

        myusercarsadd_mdd_edt=(EditText)this.findViewById(R.id.myusercarsadd_mdd_edt);
        myusercarsadd_lcs_tv=(EditText)this.findViewById(R.id.myusercarsadd_lcs_tv);
        myusercarsadd_ccly_tv=(EditText)this.findViewById(R.id.myusercarsadd_ccly_tv);
    }
    public void initViewValues(){
        title.setText("车辆使用");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("提交");
    }
    public void initOnclicEvents(){
        reaback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        myusercarsadd_cattype_tv.setOnClickListener(this);
        myusercarsadd_starttime_tv.setOnClickListener(this);
        myusercarsadd_endtime_tv.setOnClickListener(this);
        myusercarsadd_cardriver_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //添加车辆
                if("".equals(myusercarsadd_cattype_tv.getText().toString())){
                    ToastUtil.showToast("请选择车辆类型",MyUseCarsAddActivity.this);
                }else if("".equals(myusercarsadd_mdd_edt.getText().toString())){
                    ToastUtil.showToast("请输入目的地",MyUseCarsAddActivity.this);
                }else if("".equals(myusercarsadd_lcs_tv.getText().toString())){
                    ToastUtil.showToast("请输入里程数",MyUseCarsAddActivity.this);
                }else if("".equals(myusercarsadd_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请选择出车时间",MyUseCarsAddActivity.this);
                }else if("".equals(myusercarsadd_endtime_tv.getText().toString())){
                    ToastUtil.showToast("请选择还车时间",MyUseCarsAddActivity.this);
                }else if("".equals(myusercarsadd_cardriver_tv.getText().toString())){
                    ToastUtil.showToast("请选择司机",MyUseCarsAddActivity.this);
                }else if("".equals(myusercarsadd_ccly_tv.getText().toString())){
                    ToastUtil.showToast("请输入出车理由",MyUseCarsAddActivity.this);
                }else{
                    waitDialog.show();
                    wokrpersonalInternet.addMyUserCarList_Datas(carId,myusercarsadd_mdd_edt.getText().toString(),
                            myusercarsadd_lcs_tv.getText().toString(),myusercarsadd_starttime_tv.getText().toString(),
                            myusercarsadd_endtime_tv.getText().toString(),userid,myusercarsadd_ccly_tv.getText().toString());
                }
                break;
            case R.id.myusercarsadd_cattype_tv:
                intent = new Intent(MyUseCarsAddActivity.this,AllCarsTypeActivity.class);
                startActivityForResult(intent,101);
                break;
            case R.id.myusercarsadd_starttime_tv:
                //开始时间
//                dateViewDailog = new DateViewDailog(MyUseCarsAddActivity.this,1000,myHandler,false);
//                dateViewDailog.show();
                pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        if(BaseUtils.DateCompare(BaseUtils.getTime(date),BaseUtils.getSystemTime())){
                            myusercarsadd_starttime_tv.setText(BaseUtils.getTime(date));
                        }else {
                            ToastUtil.showToast("开始时间必须大于当前时间",MyUseCarsAddActivity.this);
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
            case R.id.myusercarsadd_endtime_tv:
                //结束时间
//                dateViewDailog = new DateViewDailog(MyUseCarsAddActivity.this,2000,myHandler,false);
//                dateViewDailog.show();
                if("".equals(myusercarsadd_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请先选择开始时间",MyUseCarsAddActivity.this);
                }else {
                    pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            if (BaseUtils.DateCompare(BaseUtils.getTime(date), myusercarsadd_starttime_tv.getText().toString())) {
                                myusercarsadd_endtime_tv.setText(BaseUtils.getTime(date));
                            } else {
                                ToastUtil.showToast("结束时间必须大于开始时间", MyUseCarsAddActivity.this);
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
            case R.id.myusercarsadd_cardriver_tv:
                //选择司机
                intent = new Intent(MyUseCarsAddActivity.this,AllOneTeacherSelectActivity.class);
                startActivityForResult(intent,103);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (requestCode){
                case 101:
//                车辆类型
                    AllCarsTypeEntity entity = (AllCarsTypeEntity) data.getSerializableExtra("entity");
                    if(entity!=null) {
                        myusercarsadd_cattype_tv.setText(entity.getName());
                        carId = entity.getValue();
                    }
                    break;
                case 103:
                    //会议主持人
                    AllJZGEntity jzgentity = (AllJZGEntity) data.getSerializableExtra("persondatas");
                    if(jzgentity!=null) {
                        myusercarsadd_cardriver_tv.setText(jzgentity.getUsername());
                        userid = jzgentity.getUid();
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
                    //开始时间
                    myusercarsadd_starttime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 2000:
                    //结束时间
                    myusercarsadd_endtime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",MyUseCarsAddActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(wokrpersonalPersener.parserAddAssetReapir((String)msg.obj)){
                        Intent intent = new Intent();
                        setResult(101,intent);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新申请",MyUseCarsAddActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyUseCarsAddActivity.this);
                    break;
            }
        }
    };
}
