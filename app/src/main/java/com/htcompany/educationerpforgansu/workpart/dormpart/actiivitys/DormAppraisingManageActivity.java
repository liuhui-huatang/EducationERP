package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.dormpart.DormInternet;
import com.htcompany.educationerpforgansu.internet.dormpart.DormPersener;
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormAppraisingManageAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.AppraisingManageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 评优查询
 * Created by wrb on 2016/11/23.
 */
public class DormAppraisingManageActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView appraisingmanage_dbsx_wsj_img;
    private XListView appraisingmanage_lv;
    private EditText appraisingmanage_search_edt;
    private DormAppraisingManageAdapter appraisingManageAdapter;
    private List<AppraisingManageEntity> appraisingManageEntities;
    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormappraisingmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        appraisingManageEntities = new ArrayList<AppraisingManageEntity>();
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormAppraisingManageDatas(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        appraisingmanage_dbsx_wsj_img=(ImageView)this.findViewById(R.id.appraisingmanage_dbsx_wsj_img);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        appraisingmanage_search_edt=(EditText)this.findViewById(R.id.appraisingmanage_search_edt);
        appraisingmanage_lv = (XListView)this.findViewById(R.id.appraisingmanage_lv);
        appraisingmanage_lv.setPullRefreshEnable(true);
        appraisingmanage_lv.setPullLoadEnable(false);
        appraisingmanage_lv.setXListViewListener(this);
        appraisingManageAdapter = new DormAppraisingManageAdapter(this,appraisingManageEntities);
        appraisingmanage_lv.setAdapter(appraisingManageAdapter);

    }
    public void initViewValues(){
        title.setText("评优查询");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        appraisingmanage_search_edt.addTextChangedListener(serachclier);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public TextWatcher serachclier = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            name = appraisingmanage_search_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(appraisingManageEntities.size()>0){
                    appraisingManageEntities.clear();
                }
                dormInternet.getDormAppraisingManageDatas(String.valueOf(pageNum),name);
            }else{
                dormInternet.getDormAppraisingManageDatas(String.valueOf(pageNum),name);
            }
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",DormAppraisingManageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<AppraisingManageEntity> datas = dormPersener.parseDormAppraisingManageData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(appraisingManageEntities.size()==0){
                            appraisingmanage_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }
                        appraisingmanage_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",DormAppraisingManageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<AppraisingManageEntity> datas){
        if(pageNum==1){
            if(appraisingManageEntities.size()>0){
                appraisingManageEntities.clear();
            }
        }
        if(datas.size()>50){
            appraisingmanage_lv.setPullLoadEnable(true);
        }
        for(AppraisingManageEntity entity:datas){
            appraisingManageEntities.add(entity);
        }
        appraisingManageAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        dormInternet.getDormAppraisingManageDatas(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        dormInternet.getDormAppraisingManageDatas(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        appraisingmanage_lv.stopRefresh();
        appraisingmanage_lv.stopLoadMore();
        appraisingmanage_lv.setRefreshTime("刚刚");
    }
}
