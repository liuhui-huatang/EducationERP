package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.MyleaveApplyAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.MyLeaveApplyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 请假申请
 * Created wrb wrb on 2016/11/10.
 */
public class MyLeaveApplyActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private XListView myleaveapply_lv;
    private ImageView myleaveapply_wsj_img;
    private MyleaveApplyAdapter myleaveApplyAdapter;
    private List<MyLeaveApplyEntity> myLeaveApplyEntities;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myleaveapply_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        myLeaveApplyEntities = new ArrayList<MyLeaveApplyEntity>();
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalPersener = new WokrpersonalPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        wokrpersonalInternet.getMyLeaveApplyList_Datas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        rightthree_btn_tv = (TextView)this.findViewById(R.id.rightthree_btn_tv);
        myleaveapply_wsj_img=(ImageView)this.findViewById(R.id.myleaveapply_wsj_img);
        myleaveapply_lv = (XListView)this.findViewById(R.id.myleaveapply_lv);
        myleaveapply_lv.setPullRefreshEnable(true);
        myleaveapply_lv.setPullLoadEnable(false);
        myleaveapply_lv.setXListViewListener(this);
        myleaveApplyAdapter = new MyleaveApplyAdapter(this,myLeaveApplyEntities);
        myleaveapply_lv.setAdapter(myleaveApplyAdapter);
    }
    public void initViewValues(){
        title.setText("请假申请");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("申请");
    }
  public void initOnclicEvents(){
      right_three_btn.setOnClickListener(this);
      reback_btn.setOnClickListener(this);
  }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                Intent intent = new Intent(MyLeaveApplyActivity.this,MyLeaveApplyAddActivity.class);
                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 100:
                if(data!=null) {
                    pageNum=1;
                    wokrpersonalInternet.getMyLeaveApplyList_Datas(String.valueOf(pageNum));
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
                    ToastUtil.showToast("连接超时",MyLeaveApplyActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MyLeaveApplyEntity> datas = wokrpersonalPersener.parseMyLeaveApply_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        myleaveapply_lv.setPullLoadEnable(false);
                        stopListView();
                        if(myLeaveApplyEntities.size()==0){
                            myleaveapply_wsj_img.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyLeaveApplyActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<MyLeaveApplyEntity> datas){
        if(pageNum==1){
            if(myLeaveApplyEntities.size()>0){
                myLeaveApplyEntities.clear();
            }
        }
        if(datas.size()>0){
            myleaveapply_lv.setPullLoadEnable(true);
        }
        for(MyLeaveApplyEntity entity:datas){
            myLeaveApplyEntities.add(entity);
        }
        myleaveApplyAdapter.notifyDataSetChanged();
        stopListView();
    }
    @Override
    public void onRefresh() {
        pageNum=1;
        wokrpersonalInternet.getMyLeaveApplyList_Datas(String.valueOf(pageNum));
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        wokrpersonalInternet.getMyLeaveApplyList_Datas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        myleaveapply_lv.stopRefresh();
        myleaveapply_lv.stopLoadMore();
        myleaveapply_lv.setRefreshTime("刚刚");
    }
}

