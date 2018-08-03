package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.calendarview.MonthDateView;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenu;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuCreator;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuItem;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuListView;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.MySchedulesAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.BeOnDutyEntity;
import com.htcompany.educationerpforgansu.workpart.entities.ScheduleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 日程
 * Created by wrb on 2016/11/7.
 */
public class MainSchedulesActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn,right_three_btn;

    private MySchedulesAdapter mySchedulesAdapter;
    private SwipeMenuListView mainschedules_lv;
    private List<ScheduleEntity> scheduleEntities = null;
    private TextView mainschedules_notice_tv;

    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_today;
    private MonthDateView monthDateView;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private String dateStr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainschedules_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
     public void initDatas(){
         scheduleEntities = new ArrayList<ScheduleEntity>();
         wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
         wokrpersonalPersener = new WokrpersonalPersener(this);
         waitDialog = new WaitDialog(this,"");
         waitDialog.show();
         }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        mainschedules_notice_tv=(TextView)this.findViewById(R.id.mainschedules_notice_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn=(RelativeLayout)this.findViewById(R.id.right_three_btn);
        mainschedules_lv = (SwipeMenuListView)this.findViewById(R.id.mainschedules_lv);
        mySchedulesAdapter = new MySchedulesAdapter(this,scheduleEntities);
        mainschedules_lv.setAdapter(mySchedulesAdapter);
//        setListViewDeleteBtn();

        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
        tv_date = (TextView) findViewById(R.id.date_text);
        tv_week  =(TextView) findViewById(R.id.week_text);
        tv_today = (TextView) findViewById(R.id.tv_today);
        monthDateView.setTextView(tv_date,tv_week);
        dateStr=String.valueOf(monthDateView.getDateAll());
        wokrpersonalInternet.getMySchedulesList_Datas(dateStr);
        monthDateView.setmDayColor(getResources().getColor(R.color.work_bottomtv_color));
        monthDateView.setmCircleColor(getResources().getColor(R.color.work_bottomtv2_color));
        monthDateView.setmCircleRadius(2);
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
                waitDialog.show();
                dateStr=String.valueOf(monthDateView.getDateAll());
                wokrpersonalInternet.getMySchedulesList_Datas(dateStr);
            }
        });
    }
    public void initViewValues(){
        title.setText("我的日程");
        right_three_btn.setVisibility(View.VISIBLE);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        iv_right.setOnClickListener(this);
        tv_today.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.reback_btn:
              this.finish();
              break;
          case R.id.right_three_btn:
              //调到添加日程界面
              Intent intent = new Intent(MainSchedulesActivity.this,MainAddSchedulesActivity.class);
              startActivityForResult(intent,100);
              break;
          case R.id.iv_left:
              //向左滑动日历
              monthDateView.onLeftClick();
              break;
          case R.id.iv_right:
              //向右滑动日历
              monthDateView.onRightClick();
              break;
          case R.id.tv_today:
              //设置今天
              monthDateView.setTodayToView();
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
                    ToastUtil.showToast("连接超时",MainSchedulesActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ScheduleEntity> datas = wokrpersonalPersener.parseMySchedules_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        mainschedules_notice_tv.setVisibility(View.GONE);
                        setLVDatas(datas);
                    }else{
                        if(scheduleEntities.size()>0){
                            scheduleEntities.clear();
                        }
                        mainschedules_notice_tv.setVisibility(View.VISIBLE);
                        mySchedulesAdapter.notifyDataSetChanged();
//                        beonduty_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MainSchedulesActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<ScheduleEntity> datas){
        if(scheduleEntities.size()>0){
            scheduleEntities.clear();
        }
        for(ScheduleEntity entity:datas){
            scheduleEntities.add(entity);
        }
        mySchedulesAdapter.notifyDataSetChanged();
    }
    /**
     * 设置列表删除按钮
     */
    public void setListViewDeleteBtn(){
        SwipeMenuCreator menuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // TODO Auto-generated method stub
                // create "delete" item 穿件第二个子项
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.mipmap.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        mainschedules_lv.setMenuCreator(menuCreator);
        mainschedules_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                if(scheduleEntities.size()>0){
                    scheduleEntities.remove(scheduleEntities.get(position));
                    mySchedulesAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 100:
                mySchedulesAdapter.notifyDataSetChanged();
                if(scheduleEntities.size()>0){
                    scheduleEntities.clear();
                }
                dateStr=String.valueOf(monthDateView.getDateAll());
                wokrpersonalInternet.getMySchedulesList_Datas(dateStr);
                break;
        }
    }
}
