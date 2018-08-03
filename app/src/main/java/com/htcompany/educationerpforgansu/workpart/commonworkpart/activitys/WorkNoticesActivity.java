package com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenu;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuCreator;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuItem;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuListView;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters.WorkNoticesAdapters;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 办公公告
 * Created by WRB on 2016/11/22.
 */
public class WorkNoticesActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private ImageView worknotices_wsj_img;
    private SwipeMenuListView worknotices_lv;
    private WorkNoticesAdapters noticesAdapters;
    Intent intent = null;
    public List<WorkNoticeEntity> workNoticeEntities;
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    private WorkNoticeEntity noticeentity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worknotices_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        workNoticeEntities = new ArrayList<WorkNoticeEntity>();
        workInternet = new CommonWorkInternet(this,myHandler);
        workPersener = new CommonWorkPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        workInternet.getWorkNoticeListDatas("0");
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        worknotices_wsj_img=(ImageView)this.findViewById(R.id.worknotices_wsj_img);
        worknotices_lv = (SwipeMenuListView) this.findViewById(R.id.worknotices_lv);
        noticesAdapters = new WorkNoticesAdapters(this,workNoticeEntities);
        worknotices_lv.setAdapter(noticesAdapters);
//        setListViewDeleteBtn();
    }

    public void initViewValues() {
        title.setText("办公公告");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
    }
    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        worknotices_lv.setOnItemClickListener(bookManageClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到公告详情界面
            noticeentity = (WorkNoticeEntity) noticesAdapters.getItem(position);
            if(noticeentity!=null){
                intent = new Intent(WorkNoticesActivity.this, WorkNoticesDetailsActivity.class);
                intent.putExtra("entity",noticeentity);
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
            case R.id.right_three_btn:
                //调到添加公告界面
                intent = new Intent(WorkNoticesActivity.this, WorkNoticesAddActivity.class);
                startActivityForResult(intent,101);
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
                    ToastUtil.showToast("连接超时",WorkNoticesActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<WorkNoticeEntity> datas = workPersener.parseWorknotcie_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        worknotices_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 201:
                    //删除办公公告
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(workPersener.parserDeleteWorkNotice((String)msg.obj)){
                        workNoticeEntities.remove(noticeentity);
                        noticesAdapters.notifyDataSetChanged();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",WorkNoticesActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<WorkNoticeEntity> datas){
            if(workNoticeEntities.size()>0){
                workNoticeEntities.clear();
            }
        for(WorkNoticeEntity entity:datas){
            workNoticeEntities.add(entity);
        }
        noticesAdapters.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            switch (requestCode) {
                case 101:
                    //新增公告
                    WorkNoticeEntity entity = (WorkNoticeEntity) data.getSerializableExtra("noticeEntity");
                    workNoticeEntities.add(entity);
                    noticesAdapters.notifyDataSetChanged();
                    workInternet.getWorkNoticeListDatas("0");//刷新添加列表
                    break;
            }
        }
    }

    /**
     * 设置列表删除按钮
     */
    public void setListViewDeleteBtn() {
        SwipeMenuCreator menuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // TODO Auto-generated method stub
                // create "delete" item 穿件第二个子项
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.mipmap.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        worknotices_lv.setMenuCreator(menuCreator);
        worknotices_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                noticeentity = workNoticeEntities.get(position);
                if(noticeentity!=null){
                    waitDialog.show();
                    workInternet.deleteWorkNoticeListItemDatas(noticeentity.getId());
                }
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
