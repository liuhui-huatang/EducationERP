package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.MyBusinessTravelActivity;
import com.htcompany.educationerpforgansu.MyVacationActivity;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.WorkFlowStartSendAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFlowStartSendEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * 发起流程列表
 * Created by wrb on 2017/4/6.
 */
public class WorkFlowStartSendActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView workflowstartsend_lv;
    private WorkFlowStartSendAdapter startSendAdapter;
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private List<WorkFlowStartSendEntity> startSendEntities;
    private WaitDialog waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workflowstartsend_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initDatas() {
        waitDialog = new WaitDialog(this, "");
        startSendEntities = new ArrayList<WorkFlowStartSendEntity>();
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this, myHandler);
        waitDialog.show();
        wokrpersonalInternet.getWorkFlowStartSendTypeList();
    }

    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        workflowstartsend_lv = (ListView) this.findViewById(R.id.workflowstartsend_lv);
        startSendAdapter = new WorkFlowStartSendAdapter(this, startSendEntities);
        workflowstartsend_lv.setAdapter(startSendAdapter);
    }

    public void initViewValues() {
        title.setText("发起流程");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        workflowstartsend_lv.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }

    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            WorkFlowStartSendEntity entity = (WorkFlowStartSendEntity) startSendAdapter.getItem(position);
            Intent intent = null;
            if (entity != null) {
                if ("2".equals(entity.getType())) {
                    //调到自由工作流发起界面
                    intent = new Intent(WorkFlowStartSendActivity.this, WorkFreeFlowStartSendActivity.class);
                    intent.putExtra("flowEntity", entity);
                    startActivity(intent);

                } else if ("1".equals(entity.getType())) {

                    //调到固定工作流发起界面
                    if ("1".equals(entity.getYuansheng())) {
                        //请假和外出原生页面
                        MyVacationActivity.startIntent(WorkFlowStartSendActivity.this, entity);
                    } else if ("2".equals(entity.getYuansheng())) {
                        //公务出差原生页面
                        MyBusinessTravelActivity.startIntent(WorkFlowStartSendActivity.this, entity);
                    } else {
                        //webview页面
                        intent = new Intent(WorkFlowStartSendActivity.this, WorkGDH5FlowStartSendActivity.class);
                        intent.putExtra("flowEntity", entity);
                        startActivity(intent);
                    }

                }
            }
        }
    };
    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 400:
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("请求网络失败", WorkFlowStartSendActivity.this);
                    break;
                case 200:
                    //处理数据
                    List<WorkFlowStartSendEntity> datas = wokrpersonalPersener.parseWrokFlowStartSendListDatas((String) msg.obj);
                    if (datas != null && datas.size() > 0) {
                        if (startSendEntities.size() > 0) {
                            startSendEntities.clear();
                        }
                        for (WorkFlowStartSendEntity entity : datas) {
                            startSendEntities.add(entity);
                        }
                        startSendAdapter.notifyDataSetChanged();
                    }
                    if (waitDialog != null) {
                        waitDialog.dismiss();
                    }
                    break;
            }
        }
    };
}
