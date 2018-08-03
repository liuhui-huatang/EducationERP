package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenu;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuCreator;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuItem;
import com.htcompany.educationerpforgansu.commonpart.views.swipemenulistview.SwipeMenuListView;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.StudentPJEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationStudentPJManageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生评教管理
 * Created by wrb on 2016/11/25.
 */
public class EducationStudentPJManageActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private SwipeMenuListView studentpjmanage_lv;
    private EducationStudentPJManageAdapter manageAdapter;
    private List<StudentPJEntity> pjEntities;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationstudentpjmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
   public void initDatas(){
       pjEntities = new ArrayList<StudentPJEntity>();
       StudentPJEntity entity1 = new StudentPJEntity();
       entity1.setWjbt("评教教师教学方式");
       entity1.setStarttime("2016-12-01");
       entity1.setEndtime("2016-12-03");
       StudentPJEntity entity2 = new StudentPJEntity();
       entity2.setWjbt("评教教师上课态度");
       entity2.setStarttime("2016-12-01");
       entity2.setEndtime("2016-12-03");
       pjEntities.add(entity1);
       pjEntities.add(entity2);
   }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        studentpjmanage_lv = (SwipeMenuListView) this.findViewById(R.id.studentpjmanage_lv);
        manageAdapter = new EducationStudentPJManageAdapter(this,pjEntities);
        studentpjmanage_lv.setAdapter(manageAdapter);
        setListViewDeleteBtn();
    }

    public void initViewValues() {
        title.setText("学生评教管理");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        studentpjmanage_lv.setOnItemClickListener(bookManageClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到评教详情界面
            intent = new Intent(EducationStudentPJManageActivity.this, EducationStudentPJDetailsActivity.class);
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
                //调到评教添加界面
                intent = new Intent(EducationStudentPJManageActivity.this, EducationStudentPJAddctivity.class);
                startActivity(intent);
                break;
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
        studentpjmanage_lv.setMenuCreator(menuCreator);
        studentpjmanage_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }



}
