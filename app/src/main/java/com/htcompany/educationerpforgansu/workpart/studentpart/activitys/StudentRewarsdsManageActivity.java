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
import com.htcompany.educationerpforgansu.workpart.studentpart.adapters.StudentRewarsdsManageAdapter;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentRewarsdsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生奖励管理
 * Created by wrb on 2016/11/23.
 */
public class StudentRewarsdsManageActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private SwipeMenuListView studnetrewarsdmanage_lv;
    private StudentRewarsdsManageAdapter rewarsdsManageAdapter;
    Intent intent = null;
    //网络数据请求类
    private List<StudentRewarsdsEntity> rewarsdsEntities;
    private StudentRewarsdsEntity entity=null;
    private StudentPartInternet studentPartInternet;
    private StudentPartPersener studentPartPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentrewarsdsmanage_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        rewarsdsEntities = new ArrayList<StudentRewarsdsEntity>();
        studentPartInternet = new StudentPartInternet(this,myHandler);
        studentPartPersener = new StudentPartPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        studentPartInternet.getStudentRewarsdList();
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        studnetrewarsdmanage_lv = (SwipeMenuListView) this.findViewById(R.id.studnetrewarsdmanage_lv);
        rewarsdsManageAdapter = new StudentRewarsdsManageAdapter(this,rewarsdsEntities);
        studnetrewarsdmanage_lv.setAdapter(rewarsdsManageAdapter);
        setListViewDeleteBtn();
    }

    public void initViewValues() {
        title.setText("学生奖励");
//        right_three_btn.setVisibility(View.VISIBLE);
//        rightthree_btn_tv.setText("添加");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        studnetrewarsdmanage_lv.setOnItemClickListener(bookManageClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到奖励详情界面
            intent = new Intent(StudentRewarsdsManageActivity.this, StudentRewarsdsDetailsActivity.class);
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
//            case R.id.right_three_btn:
//                //调到添加技能大赛界面
//                intent = new Intent(StudentRewarsdsManageActivity.this, StudentRewarsdsAddActivity.class);
//                startActivity(intent);
//                break;
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
                    ToastUtil.showToast("连接超时",StudentRewarsdsManageActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<StudentRewarsdsEntity> datas = studentPartPersener.parseStudentRewarsdsListDatas((String)msg.obj);
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
                        rewarsdsEntities.remove(entity);
                        rewarsdsManageAdapter.notifyDataSetChanged();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentRewarsdsManageActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<StudentRewarsdsEntity> datas){
        if(rewarsdsEntities.size()>0){
            rewarsdsEntities.clear();
        }
        for(StudentRewarsdsEntity entity:datas){
            rewarsdsEntities.add(entity);
        }
        rewarsdsManageAdapter.notifyDataSetChanged();
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
        studnetrewarsdmanage_lv.setMenuCreator(menuCreator);
        studnetrewarsdmanage_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                entity = (StudentRewarsdsEntity) rewarsdsManageAdapter.getItem(position);
                waitDialog.show();
                studentPartInternet.deleteStudentRewardsList("");
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
