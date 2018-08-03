package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeacherDormManageAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeacherDormManageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍管理
 * Created by wrb on 2016/11/30.
 */
public class TeacherDormManageActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView teacherdormmange_lv;
    private TeacherDormManageAdapter dormManageAdapter;
    private List<TeacherDormManageEntity> dormManageEntities;
    private ImageView teacherdormmange_wsj_img;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherdormmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas() {
        dormManageEntities = new ArrayList<TeacherDormManageEntity>();
        teacherInternet = new WorkTeacherInternet(this, tableHanler);
        teacherPersener = new WorkTeacherPersener(this, tableHanler);
        waitDialog = new WaitDialog(this, "");
        waitDialog.show();
        teacherInternet.getTeacherDormManageListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        teacherdormmange_wsj_img=(ImageView)this.findViewById(R.id.teacherdormmange_wsj_img);
        teacherdormmange_lv = (ListView)this.findViewById(R.id.teacherdormmange_lv);
        dormManageAdapter = new TeacherDormManageAdapter(this,dormManageEntities);
        teacherdormmange_lv.setAdapter(dormManageAdapter);
    }
    public void initViewValues(){
        title.setText("宿舍管理");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        teacherdormmange_lv.setOnItemClickListener(designClicer);
    }
    public AdapterView.OnItemClickListener designClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TeacherDormManageEntity entity = (TeacherDormManageEntity) dormManageAdapter.getItem(position);
            Intent intent = new Intent(TeacherDormManageActivity.this,TeacherDormManageDetailsActivity.class);
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

    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TeacherDormManageEntity> datas = teacherPersener.parseTeacherDormManageListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else{
                        if(dormManageEntities.size()==0){
                            teacherdormmange_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            teacherdormmange_wsj_img.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeacherDormManageActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(dormManageEntities.size()==0){
                        teacherdormmange_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        teacherdormmange_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接服务器失败",TeacherDormManageActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas(List<TeacherDormManageEntity> datas ){
        if (dormManageEntities.size()>0){
            dormManageEntities.clear();
        }
        for(TeacherDormManageEntity entity:datas){
            dormManageEntities.add(entity);
        }
        if(dormManageEntities.size()==0){
            teacherdormmange_wsj_img.setVisibility(View.VISIBLE);
        }else {
            teacherdormmange_wsj_img.setVisibility(View.GONE);
        }
        dormManageAdapter.notifyDataSetChanged();
    }
}
