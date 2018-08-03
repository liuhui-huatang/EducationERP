package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
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
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormDisciplineSearchAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.DormDisciplineEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍违纪
 * Created by wrb on 2016/11/23.
 */
public class DormDisciplineSearchActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private ImageView dormdisciplinessearch_dbsx_wsj_img;
    private XListView dormdisciplinessearch_lv;
    private DormDisciplineSearchAdapter disciplineSearchAdapter;
    private EditText dormdisciplinesearch_search_edt;
    Intent intent = null;
    //网路请求
    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private List<DormDisciplineEntity> disciplineEntities;
    private int pageNum=1;//页数
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormdisciplinesearch_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        disciplineEntities = new ArrayList<DormDisciplineEntity>();
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormDisciplineDatas(String.valueOf(pageNum),name);
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        dormdisciplinessearch_dbsx_wsj_img=(ImageView)this.findViewById(R.id.dormdisciplinessearch_dbsx_wsj_img);
        dormdisciplinesearch_search_edt=(EditText)this.findViewById(R.id.dormdisciplinesearch_search_edt);
        dormdisciplinessearch_lv = (XListView) this.findViewById(R.id.dormdisciplinessearch_lv);
        dormdisciplinessearch_lv.setPullRefreshEnable(true);
        dormdisciplinessearch_lv.setPullLoadEnable(false);
        dormdisciplinessearch_lv.setXListViewListener(this);
        disciplineSearchAdapter = new DormDisciplineSearchAdapter(this,disciplineEntities);
        dormdisciplinessearch_lv.setAdapter(disciplineSearchAdapter);
    }

    public void initViewValues() {
        title.setText("违纪情况");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        dormdisciplinessearch_lv.setOnItemClickListener(bookManageClicer);
        dormdisciplinesearch_search_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到违纪详情界面
            DormDisciplineEntity entity= (DormDisciplineEntity) disciplineSearchAdapter.getItem(position-1);
            intent = new Intent(DormDisciplineSearchActivity.this, DormDisciplineDetailsActivity.class);
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
                //调到添加违纪界面
                intent = new Intent(DormDisciplineSearchActivity.this, DormDisciplineAddActivity.class);
                startActivityForResult(intent,101);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pageNum=1;
        name="";
        dormInternet.getDormDisciplineDatas(String.valueOf(pageNum),name);
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
            name = dormdisciplinesearch_search_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(name)){
                if(disciplineEntities.size()>0){
                    disciplineEntities.clear();
                }
                dormInternet.getDormDisciplineDatas(String.valueOf(pageNum),name);
            }else{
                dormInternet.getDormDisciplineDatas(String.valueOf(pageNum),name);
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
                    ToastUtil.showToast("连接超时",DormDisciplineSearchActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DormDisciplineEntity> datas = dormPersener.parseDormDisciplineManageData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(disciplineEntities.size()==0){
                            dormdisciplinessearch_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }else{
                            dormdisciplinessearch_dbsx_wsj_img.setVisibility(View.GONE);
                        }
                        dormdisciplinessearch_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",DormDisciplineSearchActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<DormDisciplineEntity> datas){
        if(pageNum==1){
            if(disciplineEntities.size()>0){
                disciplineEntities.clear();
            }
        }
        if(datas.size()>50){
            dormdisciplinessearch_lv.setPullLoadEnable(true);
        }
        for(DormDisciplineEntity entity:datas){
            disciplineEntities.add(entity);
        }
        if(disciplineEntities.size()==0){
            dormdisciplinessearch_dbsx_wsj_img.setVisibility(View.VISIBLE);
        }else{
            dormdisciplinessearch_dbsx_wsj_img.setVisibility(View.GONE);
        }
        disciplineSearchAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        dormInternet.getDormDisciplineDatas(String.valueOf(pageNum),name);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        dormInternet.getDormDisciplineDatas(String.valueOf(pageNum),name);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        dormdisciplinessearch_lv.stopRefresh();
        dormdisciplinessearch_lv.stopLoadMore();
        dormdisciplinessearch_lv.setRefreshTime("刚刚");
    }
}
