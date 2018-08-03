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
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormRzTsSearchAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormRzTsSearchEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 入住和退宿查询
 * Created by wrb on 2016/11/22.
 */
public class DormRzTsSearchActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView dormrztu_dbsx_wsj_img;
    private EditText dormrztu_name_edt;
    private XListView dormrztu_lv;
    private DormRzTsSearchAdapter rzTsSearchAdapter;
    private List<DormRzTsSearchEntity> rzTsSearchEntities;
    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormrztssearch_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        rzTsSearchEntities = new ArrayList<DormRzTsSearchEntity>();
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormRzTsSearchDatas(String.valueOf(pageNum),name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        dormrztu_dbsx_wsj_img=(ImageView) this.findViewById(R.id.dormrztu_dbsx_wsj_img);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        dormrztu_name_edt=(EditText)this.findViewById(R.id.dormrztu_name_edt);
        dormrztu_lv = (XListView) this.findViewById(R.id.dormrztu_lv);
        dormrztu_lv.setPullRefreshEnable(true);
        dormrztu_lv.setPullLoadEnable(false);
        dormrztu_lv.setXListViewListener(this);
        rzTsSearchAdapter = new DormRzTsSearchAdapter(this,rzTsSearchEntities);
        dormrztu_lv.setAdapter(rzTsSearchAdapter);
    }
    public void initViewValues(){
        title.setText("入住和退宿查询");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        dormrztu_name_edt.addTextChangedListener(serachclier);
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
            name = dormrztu_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(rzTsSearchEntities.size()>0){
                    rzTsSearchEntities.clear();
                }
                dormInternet.getDormRzTsSearchDatas(String.valueOf(pageNum),name);
            }else{
                dormInternet.getDormRzTsSearchDatas(String.valueOf(pageNum),name);
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
                    if(rzTsSearchEntities.size()==0){
                        dormrztu_dbsx_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        dormrztu_dbsx_wsj_img.setVisibility(View.GONE);

                    }
                    ToastUtil.showToast("连接超时",DormRzTsSearchActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DormRzTsSearchEntity> datas = dormPersener.parseDormRzTsSearchData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(rzTsSearchEntities.size()==0){
                            dormrztu_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            dormrztu_dbsx_wsj_img.setVisibility(View.GONE);

                        }
                        dormrztu_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",DormRzTsSearchActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<DormRzTsSearchEntity> datas){
        if(pageNum==1){
            if(rzTsSearchEntities.size()>0){
                rzTsSearchEntities.clear();
            }
        }
        if(datas.size()>50){
            dormrztu_lv.setPullLoadEnable(true);
        }
        for(DormRzTsSearchEntity entity:datas){
            rzTsSearchEntities.add(entity);
        }
        if(rzTsSearchEntities.size()==0){
            dormrztu_dbsx_wsj_img.setVisibility(View.VISIBLE);
        }else {
            dormrztu_dbsx_wsj_img.setVisibility(View.GONE);

        }
        rzTsSearchAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        dormInternet.getDormRzTsSearchDatas(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        dormInternet.getDormRzTsSearchDatas(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        dormrztu_lv.stopRefresh();
        dormrztu_lv.stopLoadMore();
        dormrztu_lv.setRefreshTime("刚刚");
    }
}
