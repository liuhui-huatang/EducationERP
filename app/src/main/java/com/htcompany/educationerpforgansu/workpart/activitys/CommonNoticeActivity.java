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
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkNoticesDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters.WorkNoticesAdapters;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 公告界面，根据类型获取公告信息
 * Created by wrb on 2017/4/14.
 */
public class CommonNoticeActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView commonnotices_lv;
    private WorkNoticesAdapters noticesAdapters;
    private ImageView worknotices_wsj_img;
    Intent intent = null;
    private List<WorkNoticeEntity> workNoticeEntities;
    private String noticetype = "";
    private String ismain="";
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worknotices_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initDatas() {
        noticetype = getIntent().getStringExtra("noticetype");
        ismain=getIntent().getStringExtra("ismain");
        workNoticeEntities = new ArrayList<WorkNoticeEntity>();
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this, myHandler);
        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        if("1".equals(noticetype)){
            //部门公告
            wokrpersonalInternet.getWorkNoticeMyPartListDatas();
        }else {
            //全校公告
            wokrpersonalInternet.getWorkNoticePartListDatas(noticetype);
        }
    }

    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        worknotices_wsj_img=(ImageView)this.findViewById(R.id.worknotices_wsj_img);
        commonnotices_lv = (ListView) this.findViewById(R.id.worknotices_lv);
        noticesAdapters = new WorkNoticesAdapters(this, workNoticeEntities);
        commonnotices_lv.setAdapter(noticesAdapters);
    }

    public void initViewValues() {
        if("2".equals(noticetype)){
            title.setText("全校公告");
        }else if("1".equals(noticetype)){
            title.setText("部门公告");
        }

    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        commonnotices_lv.setOnItemClickListener(bookManageClicer);
    }

    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到公告详情界面
            WorkNoticeEntity entity = (WorkNoticeEntity) noticesAdapters.getItem(position);
            if (entity != null) {
                intent = new Intent(CommonNoticeActivity.this, WorkNoticesDetailsActivity.class);
                intent.putExtra("entity", entity);
                intent.putExtra("ismain",ismain);
                startActivity(intent);
            }
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

    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 400:
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("请求网络失败", CommonNoticeActivity.this);
                    break;
                case 202:
                    //处理待办数据
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    //处理全校公告数据
                    List<WorkNoticeEntity> qxdatas = wokrpersonalPersener.parseWorknotcie_ListData((String) msg.obj);
                    if (qxdatas != null && qxdatas.size() > 0) {
                        worknotices_wsj_img.setVisibility(View.GONE);
                        if (workNoticeEntities.size() > 0) {
                            workNoticeEntities.clear();
                        }
                        for (WorkNoticeEntity e1 : qxdatas) {
                            workNoticeEntities.add(e1);
                        }
                        noticesAdapters.notifyDataSetChanged();
                    }else{
                        worknotices_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;

            }
        }

    };
}