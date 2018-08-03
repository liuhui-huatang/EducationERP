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
import com.htcompany.educationerpforgansu.workpart.entities.ScheduleEntity;

import java.util.Date;
import java.util.List;

/**
 * 添加日程界面
 * Created by wrb on 2016/11/7.
 */
public class  MainAddSchedulesActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;

    private RelativeLayout addschedules_starttime_rel,addschedules_endtime_rel;
    private TextView addschedules_starttime_tv,addschedules_endtime_tv;
    private EditText addschedules_content_edt;
    private DateViewDailog dateViewDailog;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private TimePickerView pickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainaddschedules_activity);
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
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        addschedules_starttime_tv = (TextView)this.findViewById(R.id.addschedules_starttime_tv);
        addschedules_starttime_rel= (RelativeLayout)this.findViewById(R.id.addschedules_starttime_rel);
        addschedules_endtime_tv = (TextView)this.findViewById(R.id.addschedules_endtime_tv);
        addschedules_endtime_rel= (RelativeLayout)this.findViewById(R.id.addschedules_endtime_rel);
        addschedules_content_edt=(EditText)this.findViewById(R.id.addschedules_content_edt);
    }
    public void initViewValues(){
        title.setText("添加日程");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("完成");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        addschedules_starttime_rel.setOnClickListener(this);
        addschedules_endtime_rel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //添加日程
                if("".equals(addschedules_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请选择开始时间",MainAddSchedulesActivity.this);
                }else if("".equals(addschedules_endtime_tv.getText().toString())){
                    ToastUtil.showToast("请选择结束时间",MainAddSchedulesActivity.this);
                }else if("".equals(addschedules_content_edt.getText().toString())){
                    ToastUtil.showToast("请输入日程内容",MainAddSchedulesActivity.this);
                }else{
                    waitDialog.show();
                    wokrpersonalInternet.addMySchedulesList_Datas(addschedules_starttime_tv.getText().toString(),
                            addschedules_endtime_tv.getText().toString(),addschedules_content_edt.getText().toString());
                }
                break;
            case R.id.addschedules_starttime_rel:
                //选择开始时间
//                dateViewDailog = new DateViewDailog(MainAddSchedulesActivity.this,1000,myHandler,true);
//                dateViewDailog.show();
                  pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    if(BaseUtils.DateCompare(BaseUtils.getTime(date),BaseUtils.getSystemTime())){
                        addschedules_starttime_tv.setText(BaseUtils.getTime(date));
                    }else {
                        ToastUtil.showToast("开始时间必须大于当前时间",MainAddSchedulesActivity.this);
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
            case R.id.addschedules_endtime_rel:
                //选择结束时间
//                dateViewDailog = new DateViewDailog(MainAddSchedulesActivity.this,1100,myHandler,true);
//                dateViewDailog.show();
                if("".equals(addschedules_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请先选择开始时间",MainAddSchedulesActivity.this);
                }else {
                    pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            if (BaseUtils.DateCompare(BaseUtils.getTime(date), addschedules_starttime_tv.getText().toString())) {
                                addschedules_endtime_tv.setText(BaseUtils.getTime(date));
                            } else {
                                ToastUtil.showToast("结束时间必须大于开始时间", MainAddSchedulesActivity.this);
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
                    //选择开始时间
//                        if(BaseUtils.DateCompare((String)msg.obj,BaseUtils.getSystemTime())){
                            addschedules_starttime_tv.setText((String)msg.obj);
                            dateViewDailog.dismiss();
//                        }else {
//                            ToastUtil.showToast("开始时间必须大于当前时间",MainAddSchedulesActivity.this);
//                        }
                    break;
                case 1100:
                    //选择结束时间
//                    if(BaseUtils.DateCompare((String)msg.obj,addschedules_starttime_tv.getText().toString())){
                        addschedules_endtime_tv.setText((String)msg.obj);
                        dateViewDailog.dismiss();
//                    }else {
//                        ToastUtil.showToast("结束时间必须大于开始时间",MainAddSchedulesActivity.this);
//                    }

                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",MainAddSchedulesActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(wokrpersonalPersener.parserAddAssetReapir((String)msg.obj)){
                        Intent intent = new Intent();
                        setResult(100,intent);
                        finish();
                        ToastUtil.showToast("添加成功",MainAddSchedulesActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MainAddSchedulesActivity.this);
                    break;
            }
        }
    };
}
