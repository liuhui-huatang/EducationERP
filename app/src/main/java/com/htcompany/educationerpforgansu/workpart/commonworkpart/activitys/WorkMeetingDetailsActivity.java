package com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingDetailsRYMDAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

/**
 * 会议详情
 * Created by wrb on 2016/11/22.
 */
public class WorkMeetingDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView workmeetingdetails_hyname_tv,workmeetingdetails_hycode_tv,workmeetingdetails_hystarttime_tv,
            workmeetingdetails_hyendtime_tv,workmeetingdetails_hyzt_tv,
            workmeetingdetails_hyzkdd_tv,workmeetingdetails_hyzcr_tv,workmeetingdetails_hysqr_tv,workmeetingdetails_hycontent_tv;
    private GDGridView workmeetingdetails_rymd_grd;
    private MeetingDetailsRYMDAdapter rymdAdapter;
    private MyMeetingEntity myMeetingEntity;
   private LinearLayout meetingbottom_btn_ll;
    private TextView workmeetingdetails_shbtg_tv,workmeetingdetails_shtg_tv;
    private int shFlag=0;

    private LinearLayout workmeetingmanage_sbyy_ll;
    private EditText workmeetingdetails_sbreson_edt;
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workmeetingdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        myMeetingEntity = (MyMeetingEntity) getIntent().getSerializableExtra("entity");
        workInternet = new CommonWorkInternet(this,myHandler);
        workPersener = new CommonWorkPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);

        workmeetingdetails_hyname_tv= (TextView) this.findViewById(R.id.workmeetingdetails_hyname_tv);
        workmeetingdetails_hycode_tv= (TextView) this.findViewById(R.id.workmeetingdetails_hycode_tv);
        workmeetingdetails_hystarttime_tv= (TextView) this.findViewById(R.id.workmeetingdetails_hystarttime_tv);
        workmeetingdetails_hyendtime_tv= (TextView) this.findViewById(R.id.workmeetingdetails_hyendtime_tv);
        workmeetingdetails_hyzkdd_tv= (TextView) this.findViewById(R.id.workmeetingdetails_hyzkdd_tv);
        workmeetingdetails_hyzcr_tv= (TextView) this.findViewById(R.id.workmeetingdetails_hyzcr_tv);
        workmeetingdetails_hysqr_tv= (TextView) this.findViewById(R.id.workmeetingdetails_hysqr_tv);
        workmeetingdetails_hycontent_tv= (TextView) this.findViewById(R.id.workmeetingdetails_hycontent_tv);
        workmeetingdetails_rymd_grd = (GDGridView)this.findViewById(R.id.workmeetingdetails_rymd_grd);

        meetingbottom_btn_ll=(LinearLayout)this.findViewById(R.id.meetingbottom_btn_ll);
        workmeetingdetails_shbtg_tv=(TextView)this.findViewById(R.id.workmeetingdetails_shbtg_tv);
        workmeetingdetails_shtg_tv=(TextView)this.findViewById(R.id.workmeetingdetails_shtg_tv);

        workmeetingdetails_hyzt_tv = (TextView)this.findViewById(R.id.workmeetingdetails_hyzt_tv);

        workmeetingmanage_sbyy_ll=(LinearLayout)this.findViewById(R.id.workmeetingmanage_sbyy_ll);
        workmeetingdetails_sbreson_edt = (EditText)this.findViewById(R.id.workmeetingdetails_sbreson_edt);
    }

    public void initViewValues() {
        title.setText("会议详情");
        if(myMeetingEntity!=null){
            workmeetingdetails_hyname_tv.setText(myMeetingEntity.getName());
            workmeetingdetails_hycode_tv.setText(myMeetingEntity.getNumber());
            workmeetingdetails_hystarttime_tv.setText(myMeetingEntity.getStart());
            workmeetingdetails_hyendtime_tv.setText(myMeetingEntity.getEnd());
            workmeetingdetails_hyzkdd_tv.setText(myMeetingEntity.getRoom());
            workmeetingdetails_hyzcr_tv.setText(myMeetingEntity.getHost());
            workmeetingdetails_hysqr_tv.setText(myMeetingEntity.getApply());
            workmeetingdetails_hyzt_tv.setText(myMeetingEntity.getStatuss());
            workmeetingdetails_hycontent_tv.setText(myMeetingEntity.getContent());
            if("1".equals(myMeetingEntity.getStatus())){
                meetingbottom_btn_ll.setVisibility(View.VISIBLE);
            }else{
                meetingbottom_btn_ll.setVisibility(View.GONE);
                workmeetingmanage_sbyy_ll.setVisibility(View.GONE);
            }
            if(myMeetingEntity.getMm_people_json()!=null&&myMeetingEntity.getMm_people_json().size()>0) {
                rymdAdapter = new MeetingDetailsRYMDAdapter(WorkMeetingDetailsActivity.this,myMeetingEntity.getMm_people_json() );
                workmeetingdetails_rymd_grd.setAdapter(rymdAdapter);
            }
        }

    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        workmeetingdetails_shbtg_tv.setOnClickListener(this);
        workmeetingdetails_shtg_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.workmeetingdetails_shbtg_tv:
                //审核通过
                shFlag=0;
                setSelectBG(shFlag);
                break;
            case R.id.workmeetingdetails_shtg_tv:
                //审核不通过
                shFlag=1;
                setSelectBG(shFlag);
                break;
        }
    }
     public void setNomal(){
         workmeetingdetails_shbtg_tv.setBackgroundColor(getResources().getColor(R.color.bghy_details_btnmrcolor));
         workmeetingdetails_shtg_tv.setBackgroundColor(getResources().getColor(R.color.bghy_details_btnmrcolor));
     }
     public void setSelectBG(int index){
         setNomal();
         switch (index){
             case 0:
                 workmeetingdetails_shbtg_tv.setBackgroundColor(getResources().getColor(R.color.bghy_details_btnxzcolor));
                 waitDialog.show();
                 workInternet.assessWorkMeetingsDatas(myMeetingEntity.getId());
                 break;
             case 1:
                 workmeetingdetails_shtg_tv.setBackgroundColor(getResources().getColor(R.color.bghy_details_btnxzcolor));
                 if("".equals(workmeetingdetails_sbreson_edt.getText().toString())){
                     ToastUtil.showToast("请输入不通过原因",WorkMeetingDetailsActivity.this);
                 }else{
                     waitDialog.show();
                     workInternet.notAssessWorkMeetingsDatas(myMeetingEntity.getId(),workmeetingdetails_sbreson_edt.getText().toString());
                 }
                 break;
         }
     }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",WorkMeetingDetailsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(workPersener.parserDeleteWorkNotice((String)msg.obj)){
                        ToastUtil.showToast("审核通过",WorkMeetingDetailsActivity.this);
                        Intent intent=new Intent();
                        setResult(101,intent);
                        finish();
                    }else {

                    }
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(workPersener.parserDeleteWorkNotice((String)msg.obj)){
                        ToastUtil.showToast("审核不通过",WorkMeetingDetailsActivity.this);
                        Intent intent=new Intent();
                        setResult(101,intent);
                        finish();
                    }else {

                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",WorkMeetingDetailsActivity.this);
                    break;
            }
        }
    };
}
