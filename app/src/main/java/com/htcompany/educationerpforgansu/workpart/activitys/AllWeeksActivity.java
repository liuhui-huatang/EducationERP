package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.AllWeeksAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllWeeksEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 周次选择界面，单选或多选或全选
 * Created by wrb on 2017/4/19.
 */
public class AllWeeksActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn,right_three_btn;
    private ListView alladdress_lv;
    private AllWeeksAdapter weeksAdapter;
    private List<AllWeeksEntity> allWeeksEntities;
    private List<AllWeeksEntity> selectDatas;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alladdress_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        selectDatas = new ArrayList<AllWeeksEntity>();
        allWeeksEntities = new ArrayList<AllWeeksEntity>();
        teacherInternet = new WorkTeacherInternet(this,myHandler);
        teacherPersener = new WorkTeacherPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getWeeksListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        alladdress_lv = (ListView)this.findViewById(R.id.alladdress_lv);
        weeksAdapter = new AllWeeksAdapter(this,allWeeksEntities,myHandler);
        alladdress_lv.setAdapter(weeksAdapter);
    }
    public void initViewValues(){
        title.setText("周次选择");
        right_three_btn.setVisibility(View.VISIBLE);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //确定选择人员
                Intent intent = new Intent();
                intent.putExtra("partdatas",(Serializable) selectDatas);
                setResult(101,intent);
                this.finish();
                break;
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b=null;
            switch (msg.what){
                case 101:
                    b = msg.getData();
                    AllWeeksEntity entity = (AllWeeksEntity) b.getSerializable("AllPartEntity");
                    addSelectDatas(entity);
                    break;
                case 102:
                    b = msg.getData();
                    AllWeeksEntity entity2 = (AllWeeksEntity) b.getSerializable("AllPartEntity");
                    removeSelectDatas(entity2);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",AllWeeksActivity.this);
                    break;
                case 200:
                    //添加办公公告
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<AllWeeksEntity> datas = teacherPersener.parseWeeksLSITData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",AllWeeksActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<AllWeeksEntity> datas){
        if(allWeeksEntities.size()>0){
            allWeeksEntities.clear();
        }
        for(AllWeeksEntity entity:datas){
            allWeeksEntities.add(entity);
        }
        weeksAdapter.notifyDataSetChanged();
    }
    //添加选中周次数据
    public void addSelectDatas(AllWeeksEntity entity){
        selectDatas.add(entity);
    }
    public void removeSelectDatas(AllWeeksEntity entity){
        if(selectDatas.size()>0){
            selectDatas.remove(entity);
        }
    }
}
