package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters.WorkMeetingAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的会议新界面
 * Created by wrb on 2017/4/21.
 */
public class MyMeetingNewActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private ListView workmeeting_lv;
    private WorkMeetingAdapter meetingAdapter;
    private ImageView workmeeting_wsj_img;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private List<MyMeetingEntity> meetingEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workmeeting_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        meetingEntities = new ArrayList<MyMeetingEntity>();
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalPersener = new WokrpersonalPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        wokrpersonalInternet.getMyMeetinsList_Datas();
    }
    public void initViews() {
        workmeeting_wsj_img=(ImageView)this.findViewById(R.id.workmeeting_wsj_img);
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        rightthree_btn_tv = (TextView)this.findViewById(R.id.rightthree_btn_tv);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        workmeeting_lv = (ListView)this.findViewById(R.id.workmeeting_lv);
        meetingAdapter = new WorkMeetingAdapter(this,meetingEntities);
        workmeeting_lv.setAdapter(meetingAdapter);
    }

    public void initViewValues() {
        title.setText("会议");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("申请");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        workmeeting_lv.setOnItemClickListener(meetingClicer);
    }
    public AdapterView.OnItemClickListener meetingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyMeetingEntity entity = (MyMeetingEntity) meetingAdapter.getItem(position);
            Intent intent = new Intent(MyMeetingNewActivity.this, MyMeetingAccessDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                Intent intent = new Intent(MyMeetingNewActivity.this, MeetingApplyActivity.class);
                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    wokrpersonalInternet.getMyMeetinsList_Datas();
                    break;
            }
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
                    ToastUtil.showToast("连接超时",MyMeetingNewActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MyMeetingEntity> datas = wokrpersonalPersener.parseMeetings_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        workmeeting_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyMeetingNewActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<MyMeetingEntity> datas){
        if(meetingEntities.size()>0){
            meetingEntities.clear();
        }
        for(MyMeetingEntity entity:datas){
            meetingEntities.add(entity);
        }
        meetingAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
