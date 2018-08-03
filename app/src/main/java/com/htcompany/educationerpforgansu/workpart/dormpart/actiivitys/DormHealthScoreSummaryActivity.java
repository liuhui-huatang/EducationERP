package com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys;

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
import com.htcompany.educationerpforgansu.internet.dormpart.DormInternet;
import com.htcompany.educationerpforgansu.internet.dormpart.DormPersener;
import com.htcompany.educationerpforgansu.workpart.dormpart.adapters.DormHealthScoreSummaryAdapter;
import com.htcompany.educationerpforgansu.workpart.dormpart.entity.ScoreSummaryEntitiy;

import java.util.ArrayList;
import java.util.List;

/**
 * 卫生得分汇总
 * Created by wrb on 2016/11/23.
 */
public class DormHealthScoreSummaryActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView healthscoresummary_dbsx_wsj_img;
    private XListView healthscoresummary_lv;
    private DormHealthScoreSummaryAdapter healthScoreSummaryAdapter;
    private List<ScoreSummaryEntitiy> scoreSummaryEntitiys;
    private DormInternet dormInternet;
    private DormPersener dormPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dormhealthscoresummary_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        scoreSummaryEntitiys = new ArrayList<ScoreSummaryEntitiy>();
        dormInternet = new DormInternet(this,myHandler);
        dormPersener = new DormPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        dormInternet.getDormHealthScoreSummaryDatas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        healthscoresummary_dbsx_wsj_img=(ImageView)this.findViewById(R.id.healthscoresummary_dbsx_wsj_img);
        healthscoresummary_lv = (XListView)this.findViewById(R.id.healthscoresummary_lv);
        healthscoresummary_lv.setPullRefreshEnable(true);
        healthscoresummary_lv.setPullLoadEnable(false);
        healthscoresummary_lv.setXListViewListener(this);
        healthScoreSummaryAdapter = new DormHealthScoreSummaryAdapter(this,scoreSummaryEntitiys);
        healthscoresummary_lv.setAdapter(healthScoreSummaryAdapter);
    }
    public void initViewValues(){
        title.setText("卫生得分汇总");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
                    ToastUtil.showToast("连接超时",DormHealthScoreSummaryActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ScoreSummaryEntitiy> datas = dormPersener.parseDormHealthScoreSummaryData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        healthscoresummary_lv.setPullLoadEnable(false);
                        stopListView();
                        if(scoreSummaryEntitiys.size()==0){
                            healthscoresummary_dbsx_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            healthscoresummary_dbsx_wsj_img.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据错误",DormHealthScoreSummaryActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<ScoreSummaryEntitiy> datas){
        if(pageNum==1){
            if(scoreSummaryEntitiys.size()>0){
                scoreSummaryEntitiys.clear();
            }
        }
        if(datas.size()>50){
            healthscoresummary_lv.setPullLoadEnable(true);
        }
        for(ScoreSummaryEntitiy entity:datas){
            scoreSummaryEntitiys.add(entity);
        }
        if(scoreSummaryEntitiys.size()==0){
            healthscoresummary_dbsx_wsj_img.setVisibility(View.VISIBLE);
        }else {
            healthscoresummary_dbsx_wsj_img.setVisibility(View.GONE);
        }
        healthScoreSummaryAdapter.notifyDataSetChanged();
        stopListView();
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        dormInternet.getDormHealthScoreSummaryDatas(String.valueOf(pageNum));
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        dormInternet.getDormHealthScoreSummaryDatas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        healthscoresummary_lv.stopRefresh();
        healthscoresummary_lv.stopLoadMore();
        healthscoresummary_lv.setRefreshTime("刚刚");
    }
}
