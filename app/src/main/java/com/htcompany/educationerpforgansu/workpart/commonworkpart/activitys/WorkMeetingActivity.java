package com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys;

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
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters.WorkMeetingAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 会议审核列表
 * Created by wrb on 2016/11/22.
 */
public class WorkMeetingActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView workmeeting_wsj_img;
    private ListView workmeeting_lv;
    private WorkMeetingAdapter meetingAdapter;
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    private List<MyMeetingEntity> meetingEntities;
    private int pageNum=1;//页数
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
        workInternet = new CommonWorkInternet(this,myHandler);
        workPersener = new CommonWorkPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        workInternet.getWorkMeetingsListDatas();
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        workmeeting_wsj_img=(ImageView)this.findViewById(R.id.workmeeting_wsj_img);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        workmeeting_lv = (ListView)this.findViewById(R.id.workmeeting_lv);
        meetingAdapter = new WorkMeetingAdapter(this,meetingEntities);
        workmeeting_lv.setAdapter(meetingAdapter);
    }

    public void initViewValues() {
        title.setText("会议");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        workmeeting_lv.setOnItemClickListener(meetingClicer);
    }
    public AdapterView.OnItemClickListener meetingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyMeetingEntity entity = (MyMeetingEntity) meetingAdapter.getItem(position);
            Intent intent = new Intent(WorkMeetingActivity.this, WorkMeetingDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivityForResult(intent,101);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
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
                    ToastUtil.showToast("连接超时",WorkMeetingActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MyMeetingEntity> datas = workPersener.parseMeetings_ListData((String)msg.obj);
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
                    ToastUtil.showToast("数据异常",WorkMeetingActivity.this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    workInternet.getWorkMeetingsListDatas();
                    break;
            }
        }
    }
}
