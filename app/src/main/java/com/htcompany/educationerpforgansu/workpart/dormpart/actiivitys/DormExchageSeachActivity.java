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
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormExchageSeachAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormExchageSeachEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍调换查询
 * Created by wrb on 2016/11/22.
 */
public class DormExchageSeachActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private XListView dormexachage_lv;
    private ImageView dormexachage_dbsx_wsj_img;
    private DormExchageSeachAdapter exchageSeachAdapter;
    private EditText dormexachage_seach_edt;
    private List<DormExchageSeachEntity> exchageSeachEntities;
    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormexchageseach_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        exchageSeachEntities = new ArrayList<DormExchageSeachEntity>();
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormExchageSeachDatas(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        dormexachage_seach_edt=(EditText)this.findViewById(R.id.dormexachage_seach_edt);
        dormexachage_dbsx_wsj_img=(ImageView)this.findViewById(R.id.dormexachage_dbsx_wsj_img);
        dormexachage_lv = (XListView)this.findViewById(R.id.dormexachage_lv);
        dormexachage_lv.setPullRefreshEnable(true);
        dormexachage_lv.setPullLoadEnable(false);
        dormexachage_lv.setXListViewListener(this);
        exchageSeachAdapter = new DormExchageSeachAdapter(this,exchageSeachEntities);
        dormexachage_lv.setAdapter(exchageSeachAdapter);
    }
    public void initViewValues(){
        title.setText("宿舍调换查询");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        dormexachage_seach_edt.addTextChangedListener(serachclier);
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
            name = dormexachage_seach_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(exchageSeachEntities.size()>0){
                    exchageSeachEntities.clear();
                }
                dormInternet.getDormExchageSeachDatas(String.valueOf(pageNum),name);
            }else{
                dormInternet.getDormExchageSeachDatas(String.valueOf(pageNum),name);
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
                    if(exchageSeachEntities.size()==0){
                        dormexachage_dbsx_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        dormexachage_dbsx_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",DormExchageSeachActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DormExchageSeachEntity> datas = dormPersener.parseDormExchageSeachData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(exchageSeachEntities.size()==0){
                            dormexachage_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            dormexachage_dbsx_wsj_img.setVisibility(View.GONE);
                        }
                        dormexachage_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",DormExchageSeachActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<DormExchageSeachEntity> datas){
        if(pageNum==1){
            if(exchageSeachEntities.size()>0){
                exchageSeachEntities.clear();
            }
        }
        if(datas.size()>50){
            dormexachage_lv.setPullLoadEnable(true);
        }
        for(DormExchageSeachEntity entity:datas){
            exchageSeachEntities.add(entity);
        }
        if(exchageSeachEntities.size()==0){
            dormexachage_dbsx_wsj_img.setVisibility(View.VISIBLE);
        }else {
            dormexachage_dbsx_wsj_img.setVisibility(View.GONE);
        }
        exchageSeachAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        dormInternet.getDormExchageSeachDatas(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        dormInternet.getDormExchageSeachDatas(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        dormexachage_lv.stopRefresh();
        dormexachage_lv.stopLoadMore();
        dormexachage_lv.setRefreshTime("刚刚");
    }
}
