package com.htcompany.educationerpforgansu.workpart.financepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.Finalcepart.FinalcepartInternet;
import com.htcompany.educationerpforgansu.internet.Finalcepart.FinalcepartPersener;
import com.htcompany.educationerpforgansu.workpart.financepart.adapters.FinalcepartStatisticsAdapter;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.StatisticsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 费用统计
 * Created by wrb on 2016/11/16.
 */
public class FinalcepartStatisticsActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
   private TextView title;
    private RelativeLayout reback_btn;
    private ImageView finalcepartstatistics_wsj_img;
    private XListView finalcepartstatistics_lv;
    private FinalcepartStatisticsAdapter statisticsAdapter;
    //网络数据请求类
    private List<StatisticsEntity> statisticsEntities;
    private FinalcepartInternet finalcepartInternet;
    private FinalcepartPersener finalcepartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalcepartstatistics_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        statisticsEntities = new ArrayList<StatisticsEntity>();
        finalcepartInternet = new FinalcepartInternet(this,myHandler);
        finalcepartPersener = new FinalcepartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        finalcepartInternet.getStatisticsDatas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        finalcepartstatistics_wsj_img=(ImageView)this.findViewById(R.id.finalcepartstatistics_wsj_img);
        finalcepartstatistics_lv = (XListView)this.findViewById(R.id.finalcepartstatistics_lv);
        finalcepartstatistics_lv.setPullRefreshEnable(true);
        finalcepartstatistics_lv.setPullLoadEnable(false);
        finalcepartstatistics_lv.setXListViewListener(this);
        statisticsAdapter = new FinalcepartStatisticsAdapter(this,statisticsEntities);
        finalcepartstatistics_lv.setAdapter(statisticsAdapter);
    }

    public void initViewValues(){
        title.setText("费用统计");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        finalcepartstatistics_lv.setOnItemClickListener(statisticClicer);
    }
   public AdapterView.OnItemClickListener statisticClicer = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           StatisticsEntity entity = (StatisticsEntity) statisticsAdapter.getItem(position-1);
           Intent intent = new Intent(FinalcepartStatisticsActivity.this,FinalcepartStatisticsDetailsActivity.class);
           intent.putExtra("entity",entity);
           startActivity(intent);
       }
   };
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
                    if(statisticsEntities.size()==0){
                        finalcepartstatistics_wsj_img.setVisibility(View.VISIBLE);
                    }else{
                        finalcepartstatistics_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",FinalcepartStatisticsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<StatisticsEntity> datas = finalcepartPersener.parseStatisticsData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        if(statisticsEntities.size()==0){
                            finalcepartstatistics_wsj_img.setVisibility(View.VISIBLE);
                        }else{
                            finalcepartstatistics_wsj_img.setVisibility(View.GONE);
                        }
                        finalcepartstatistics_lv.setPullLoadEnable(false);
                        stopListView();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",FinalcepartStatisticsActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<StatisticsEntity> datas){
        if(pageNum==1){
            if(statisticsEntities.size()>0){
                statisticsEntities.clear();
            }
        }
        if(datas.size()>0){
            finalcepartstatistics_lv.setPullLoadEnable(true);
        }
        for(StatisticsEntity entity:datas){
            statisticsEntities.add(entity);
        }
        if(statisticsEntities.size()==0){
            finalcepartstatistics_wsj_img.setVisibility(View.VISIBLE);
        }else{
            finalcepartstatistics_wsj_img.setVisibility(View.GONE);
        }
        statisticsAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        finalcepartInternet.getStatisticsDatas(String.valueOf(pageNum));
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        finalcepartInternet.getStatisticsDatas(String.valueOf(pageNum));
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        finalcepartstatistics_lv.stopRefresh();
        finalcepartstatistics_lv.stopLoadMore();
        finalcepartstatistics_lv.setRefreshTime("刚刚");
    }
}
