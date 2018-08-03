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
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingApplyCHRYAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllAddressEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllJZGEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 申请会议
 * Created by wrb on 2016/11/9.
 */
public class MeetingApplyActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    //会议名称，会议编号，会议内容
    private EditText meetingapply_name_edt,meetingapply_code_edt,meetingapply_content_edt;
    //开始时间，结束时间，会议地址，会议主持人
    private TextView meetingapply_starttime_tv,meetingapply_endtime_tv,meetingapply_adres_tv,meetingapply_zcr_tv;
    //参会人员
    private RelativeLayout meetingapply_chry_rel;
    //才会人员名单
    private GDGridView meetingapply_rymd_grd;
    private MeetingApplyCHRYAdapter chryAdapter;
    private DateViewDailog dateViewDailog;
    private Intent intent=null;
    private List<AllJZGEntity> chrydatas;
    private String zcrKey="";
    private String chryKey="";
    private String roomid="";
    private List<String> persongDatas;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private TimePickerView pickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetingapply_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        chrydatas = new ArrayList<AllJZGEntity>();
        persongDatas  =new ArrayList<String>();
        waitDialog = new WaitDialog(this,"");
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
    }
    public void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        rightthree_btn_tv=(TextView)this.findViewById(R.id.rightthree_btn_tv);
        right_three_btn= (RelativeLayout)this.findViewById(R.id.right_three_btn);
        reback_btn= (RelativeLayout)this.findViewById(R.id.reback_btn);

        meetingapply_name_edt=(EditText)this.findViewById(R.id.meetingapply_name_edt);
        meetingapply_code_edt=(EditText)this.findViewById(R.id.meetingapply_code_edt);
        meetingapply_content_edt=(EditText)this.findViewById(R.id.meetingapply_content_edt);

        meetingapply_starttime_tv=(TextView)this.findViewById(R.id.meetingapply_starttime_tv);
        meetingapply_endtime_tv=(TextView)this.findViewById(R.id.meetingapply_endtime_tv);
        meetingapply_adres_tv=(TextView)this.findViewById(R.id.meetingapply_adres_tv);
        meetingapply_zcr_tv=(TextView)this.findViewById(R.id.meetingapply_zcr_tv);

        meetingapply_chry_rel = (RelativeLayout)this.findViewById(R.id.meetingapply_chry_rel);
        meetingapply_rymd_grd = (GDGridView)this.findViewById(R.id.meetingapply_rymd_grd);
        chryAdapter = new MeetingApplyCHRYAdapter(this,persongDatas);
        meetingapply_rymd_grd.setAdapter(chryAdapter);
    }
    public void initViewValues(){
        title.setText("会议申请");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("提交");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        meetingapply_starttime_tv.setOnClickListener(this);
        meetingapply_endtime_tv.setOnClickListener(this);
        meetingapply_adres_tv.setOnClickListener(this);
        meetingapply_chry_rel.setOnClickListener(this);
        meetingapply_zcr_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //提交会议申请
                if("".equals(meetingapply_name_edt.getText().toString())){
                    ToastUtil.showToast("请填写会议名称",MeetingApplyActivity.this);
                }else if("".equals(meetingapply_code_edt.getText().toString())){
                    ToastUtil.showToast("请填写会议编号",MeetingApplyActivity.this);
                }else if("".equals(meetingapply_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请选择会议开始时间",MeetingApplyActivity.this);
                }else if("".equals(meetingapply_endtime_tv.getText().toString())){
                    ToastUtil.showToast("请选择会议结束时间",MeetingApplyActivity.this);
                }else if("".equals(meetingapply_adres_tv.getText().toString())){
                    ToastUtil.showToast("请选择会议召开地点",MeetingApplyActivity.this);
                }else if("".equals(meetingapply_zcr_tv.getText().toString())){
                    ToastUtil.showToast("请选择会议主持人",MeetingApplyActivity.this);
                }else if(persongDatas.size()==0){
                    ToastUtil.showToast("请选择参会人员",MeetingApplyActivity.this);
                }else if("".equals(meetingapply_content_edt.getText().toString())){
                    ToastUtil.showToast("请填写会议主要内容",MeetingApplyActivity.this);
                }else{
                  waitDialog.show();
                    wokrpersonalInternet.addMyMeetins_Datas(meetingapply_name_edt.getText().toString(),
                            roomid,zcrKey,chryKey,meetingapply_content_edt.getText().toString(),
                            meetingapply_code_edt.getText().toString(),
                            meetingapply_starttime_tv.getText().toString(),
                            meetingapply_endtime_tv.getText().toString());
                }

                break;
            case R.id.meetingapply_starttime_tv:
                //会议开始时间
//                dateViewDailog = new DateViewDailog(MeetingApplyActivity.this,1000,myHandler,false);
//                dateViewDailog.show();
                pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        if(BaseUtils.DateCompare(BaseUtils.getTime(date),BaseUtils.getSystemTime())){
                            meetingapply_starttime_tv.setText(BaseUtils.getTime(date));
                        }else {
                            ToastUtil.showToast("开始时间必须大于当前时间",MeetingApplyActivity.this);
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
            case R.id.meetingapply_endtime_tv:
                //会议结束时间
//                dateViewDailog = new DateViewDailog(MeetingApplyActivity.this,2000,myHandler,false);
//                dateViewDailog.show();
                if("".equals(meetingapply_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请先选择开始时间",MeetingApplyActivity.this);
                }else {
                    pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            if (BaseUtils.DateCompare(BaseUtils.getTime(date), meetingapply_starttime_tv.getText().toString())) {
                                meetingapply_endtime_tv.setText(BaseUtils.getTime(date));
                            } else {
                                ToastUtil.showToast("结束时间必须大于开始时间", MeetingApplyActivity.this);
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
            case R.id.meetingapply_adres_tv:
                //会议地点
                intent = new Intent(MeetingApplyActivity.this,AllAddressActivity.class);
                startActivityForResult(intent,101);
                break;
            case R.id.meetingapply_chry_rel:
                //参会人员
                intent = new Intent(MeetingApplyActivity.this,AllPersonMoreSelectActivity.class);
                intent.putExtra("personFlag","more");
                startActivityForResult(intent,102);
                break;
            case R.id.meetingapply_zcr_tv:
                //会议主持人
                intent = new Intent(MeetingApplyActivity.this,AllOneTeacherSelectActivity.class);
                startActivityForResult(intent,103);
                break;
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    //会议开始时间
                    meetingapply_starttime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 2000:
                    //会议结束时间
                    meetingapply_endtime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",MeetingApplyActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(wokrpersonalPersener.parserAddAssetReapir((String)msg.obj)){
                        ToastUtil.showToast("添加成功",MeetingApplyActivity.this);
                        Intent intent = new Intent();
                        setResult(100,intent);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新添加",MeetingApplyActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MeetingApplyActivity.this);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
        switch (requestCode){
            case 101:
//                会议地址
                AllAddressEntity entity = (AllAddressEntity) data.getSerializableExtra("addressEntity");
                meetingapply_adres_tv.setText(entity.getName());
                roomid=entity.getKey();
                break;
            case 102:
                //会议参会人员
                List<AllJZGEntity> datas = (List<AllJZGEntity>) data.getSerializableExtra("persondatas");
                 if(datas!=null&&datas.size()>0){
                     if(persongDatas.size()>0){
                         persongDatas.clear();
                     }
                     for(AllJZGEntity personEntity:datas){
                         if("".equals(chryKey)){
                             chryKey=personEntity.getUid();
                         }else{
                             chryKey = chryKey+","+personEntity.getUid();
                         }
                         persongDatas.add(personEntity.getUsername());
                     }
                     chryAdapter.notifyDataSetChanged();
                 }
                break;
            case 103:
                //会议主持人
                AllJZGEntity jzgEntity =(AllJZGEntity)data.getSerializableExtra("persondatas");
                if(jzgEntity!=null){
                    meetingapply_zcr_tv.setText(jzgEntity.getUsername());
                    zcrKey = jzgEntity.getUid();
                }
                break;
        }
        }
    }
}
