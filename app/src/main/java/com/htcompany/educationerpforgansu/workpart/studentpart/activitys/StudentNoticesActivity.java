package com.htcompany.educationerpforgansu.workpart.studentpart.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
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
import com.htcompany.educationerpforgansu.internet.studentpart.StudentPartInternet;
import com.htcompany.educationerpforgansu.internet.studentpart.StudentPartPersener;
import com.htcompany.educationerpforgansu.workpart.studentpart.adapters.StudentNoticesAdapter;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentNoticesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生公告
 * Created by wrb on 2016/11/23.
 */
public class StudentNoticesActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private SwipeMenuListView studnetnotices_lv;
    private StudentNoticesAdapter noticesAdapters;
    private List<StudentNoticesEntity> noticesEntities;
    private StudentNoticesEntity entity=null;
    Intent intent = null;
    //网络数据请求类
    private StudentPartInternet studentPartInternet;
    private StudentPartPersener studentPartPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentnotices_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        noticesEntities = new ArrayList<StudentNoticesEntity>();
        studentPartInternet = new StudentPartInternet(this,myHandler);
        studentPartPersener = new StudentPartPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        studentPartInternet.getStudentNoticeList();
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        studnetnotices_lv = (SwipeMenuListView) this.findViewById(R.id.studnetnotices_lv);
        noticesAdapters = new StudentNoticesAdapter(this,noticesEntities);
        studnetnotices_lv.setAdapter(noticesAdapters);
        setListViewDeleteBtn();
    }

    public void initViewValues() {
        title.setText("学生公告");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        studnetnotices_lv.setOnItemClickListener(bookManageClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到公告详情界面
            intent = new Intent(StudentNoticesActivity.this, StudentNoticesDetailsActivity.class);
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
                //调到添加公告界面
                intent = new Intent(StudentNoticesActivity.this, StudentNoticesAddActivity.class);
                startActivity(intent);
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
                    ToastUtil.showToast("连接超时",StudentNoticesActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<StudentNoticesEntity> datas = studentPartPersener.parseStudentNoticeListDatas((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(studentPartPersener.parserSucessDatas((String)msg.obj)){
                        noticesEntities.remove(entity);
                        noticesAdapters.notifyDataSetChanged();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentNoticesActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<StudentNoticesEntity> datas){
            if(noticesEntities.size()>0){
                noticesEntities.clear();
            }
        for(StudentNoticesEntity entity:datas){
            noticesEntities.add(entity);
        }
        noticesAdapters.notifyDataSetChanged();
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
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
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
        studnetnotices_lv.setMenuCreator(menuCreator);
        studnetnotices_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                entity = (StudentNoticesEntity) noticesAdapters.getItem(position);
                waitDialog.show();
                studentPartInternet.deleteStudentNoticeList(entity.getWcontent());
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
