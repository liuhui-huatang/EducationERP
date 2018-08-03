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
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.TeacherPJEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationTeacherPjAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师评教
 * Created by wrb on 2016/11/25.
 */
public class EducationTeacherPjActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private SwipeMenuListView teacherpjmanage_lv;
    private EducationTeacherPjAdapter manageAdapter;
    Intent intent = null;
    private List<TeacherPJEntity> pjEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationteacherpj_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        pjEntities = new ArrayList<TeacherPJEntity>();
        TeacherPJEntity entity = new TeacherPJEntity();
        entity.setXq("2015-2016第一学期");
        entity.setBpjs("李老师");
        entity.setPjjs("王老师，魏老师");
        TeacherPJEntity entity1 = new TeacherPJEntity();
        entity1.setXq("2015-2016第二学期");
        entity1.setBpjs("王老师");
        entity1.setPjjs("李老师，张老师");
        pjEntities.add(entity);
        pjEntities.add(entity1);
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        teacherpjmanage_lv = (SwipeMenuListView) this.findViewById(R.id.teacherpjmanage_lv);
        manageAdapter = new EducationTeacherPjAdapter(this,pjEntities);
        teacherpjmanage_lv.setAdapter(manageAdapter);
        setListViewDeleteBtn();
    }

    public void initViewValues() {
        title.setText("教师评教管理");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("添加");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        teacherpjmanage_lv.setOnItemClickListener(bookManageClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到评教详情界面
//            intent = new Intent(EducationStudentPJManageActivity.this, EducationStudentPJDetailsActivity.class);
//            startActivity(intent);
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
                intent = new Intent(EducationTeacherPjActivity.this, EducationTeacherAddPjActivity.class);
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
        teacherpjmanage_lv.setMenuCreator(menuCreator);
        teacherpjmanage_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
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
