package com.htcompany.educationerpforgansu.messagepart.entity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.messageinternet.MessageInternet;
import com.htcompany.educationerpforgansu.internet.messageinternet.MessagePersener;
import com.htcompany.educationerpforgansu.messagepart.adapter.MainMessageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息详情界面
 * Created by wrb on 2017/5/24.
 */

public class MainMessageDetailsActivity extends BaseActivity implements OnClickListener{
    private RelativeLayout reback_btn;
    private TextView title;
    private TextView mainmessaged_title_tv,mainmessaged_fjr_tv,mainmessaged_time_tv,mainmessaged_content_tv;
    private MainMessageEntity entity;
    private MessageInternet messageInternet=null;
    private MessagePersener messagePersener=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmessagedetails_activity);
        initDatas();
        initViews();
        initValues();
    }
    public void initDatas(){
        entity=(MainMessageEntity) getIntent().getSerializableExtra("entity");
        messageInternet = new MessageInternet(this,myHandler);
        messagePersener=new MessagePersener(this,myHandler);
    }
    public void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        mainmessaged_title_tv=(TextView)this.findViewById(R.id.mainmessaged_title_tv);
                mainmessaged_fjr_tv=(TextView)this.findViewById(R.id.mainmessaged_fjr_tv);
        mainmessaged_time_tv=(TextView)this.findViewById(R.id.mainmessaged_time_tv);
                mainmessaged_content_tv=(TextView)this.findViewById(R.id.mainmessaged_content_tv);
        reback_btn.setOnClickListener(this);
    }
    public void initValues(){
        title.setText("消息详情");
        if(entity!=null){
        mainmessaged_title_tv.setText(entity.getTheme());
        mainmessaged_fjr_tv.setText("发送人:"+entity.getTrue_name());
        mainmessaged_time_tv.setText("发送时间:"+entity.getAddTime());
        mainmessaged_content_tv.setText(entity.getContent());
            messageInternet.readMsg(entity.getId());
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    ToastUtil.showToast("连接超时",MainMessageDetailsActivity.this);
                    break;
                case 200:
                    break;
                case 300:
                    ToastUtil.showToast("数据错误",MainMessageDetailsActivity.this);
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
}
