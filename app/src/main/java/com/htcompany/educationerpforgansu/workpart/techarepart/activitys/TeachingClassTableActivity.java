package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.TimeTableModel;
import com.htcompany.educationerpforgansu.commonpart.views.TimeTableView;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.dailogs.WeekSelectPopWindow;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassTableEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.WeeksEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 教学课表
 * Created by wrb on 2017/5/9.
 */
public class TeachingClassTableActivity extends BaseActivity implements View.OnClickListener {
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_btn;
    private TimeTableView mTimaTableView;
    private List<TimeTableModel> mList;
    private List<WeeksEntity> weeksEntities;//周次实体类
    private List<ClassTableEntity> tableEntities;
    private WeekSelectPopWindow weekSelectPopWindow;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classtabletime_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initDatas(){
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getClassTableDatas("");
        mList = new ArrayList<TimeTableModel>();
        weeksEntities = new ArrayList<WeeksEntity>();
        tableEntities = new ArrayList<ClassTableEntity>();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_btn= (RelativeLayout)this.findViewById(R.id.right_btn);
        mTimaTableView=(TimeTableView)this.findViewById(R.id.main_timetable_ly);
    }
    public void initViewValues(){
        title.setText("教学课表");
        right_btn_tv.setText("选择周次");
        right_btn.setVisibility(View.VISIBLE);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_btn:
                //选择周次
                weekSelectPopWindow = new WeekSelectPopWindow(TeachingClassTableActivity.this,weeksEntities,tableHanler);
                weekSelectPopWindow.showPopupWindow(right_btn);
                break;
        }
    }
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    //周次选择
                    right_btn_tv.setText("第"+(String)msg.obj+"周");
                    weekSelectPopWindow.dismiss();
                    waitDialog.show();
                    if(mList.size()>0){
                        mList.clear();
                    }
                    mTimaTableView.setTimeTable(mList);
                    teacherInternet.getClassTableDatas((String)msg.obj);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    teacherPersener.parseClassTableData((String)msg.obj);
                    break;
                case 201:
                    //接收解析课表数据
                    right_btn_tv.setText("第"+(String)msg.obj+"周");
                    Bundle bundle = msg.getData();
                    List<WeeksEntity> weekdatas = (List<WeeksEntity>) bundle.getSerializable("weekdatas");
                    if(weekdatas!=null&&weekdatas.size()>0){
                        initWeeksDatas(weekdatas);
                    }
                    //课表数据
                    List<ClassTableEntity> tabledatas = (List<ClassTableEntity>) bundle.getSerializable("tableEntities");
                    if(tabledatas!=null&&tabledatas.size()>0){
                        initClassTableDatas(tabledatas);
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingClassTableActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",TeachingClassTableActivity.this);
                    break;
            }
        }
    };

    /**
     * 初始化周次数据
     */
    public void initWeeksDatas(List<WeeksEntity> weekdatas){
        if(weeksEntities.size()>0){
            weeksEntities.clear();
        }
        for(WeeksEntity entity:weekdatas){
            weeksEntities.add(entity);
        }
    }

    /**
     * 初始化课表数据
     */
    public void initClassTableDatas(List<ClassTableEntity> tabledatas){
        if(mList.size()>0){
            mList.clear();
        }
        for(ClassTableEntity tableEntity:tabledatas){
            TimeTableModel tableModel = new TimeTableModel();
            tableModel.setClassroom(tableEntity.getRoomname());//教室
            tableModel.setStartnum(tableEntity.getSecid());//开始节数
            tableModel.setEndnum(tableEntity.getSecid());//节数节数
            tableModel.setStarttime(tableEntity.getTime());//开始时间
            tableModel.setEndtime(tableEntity.getTime());//结束时间
            tableModel.setName(tableEntity.getCourcename());//课程名称
            tableModel.setId(tableEntity.getId());
            tableModel.setWeek(tableEntity.getWeek());
            tableModel.setIstodm("1");
            tableModel.setClassname(tableEntity.getClassname());//班级名称
            mList.add(tableModel);
        }
        mTimaTableView.setTimeTable(mList);
    }
}
