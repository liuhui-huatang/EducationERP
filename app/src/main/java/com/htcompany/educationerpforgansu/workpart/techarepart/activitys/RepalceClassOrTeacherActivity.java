package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.RepalceClassOrTeacherAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.RepalceClassOrTeacherEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 代课调课教师选择界面
 * Created by wrb on 2017/4/22.
 */
public class RepalceClassOrTeacherActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView alladdress_lv;
    private RepalceClassOrTeacherAdapter lessonAdapter;
    private List<RepalceClassOrTeacherEntity> lessonEntities;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private String term="",weeks="",type="",paikeid="";
    private int resultCode;
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
        term = getIntent().getStringExtra("termKey");
        weeks = getIntent().getStringExtra("weeklist");
        type= getIntent().getStringExtra("type");
        paikeid= getIntent().getStringExtra("paikeid");
        resultCode=getIntent().getIntExtra("resultcode",0);
        lessonEntities = new ArrayList<RepalceClassOrTeacherEntity>();
        teacherInternet = new WorkTeacherInternet(this,myHandler);
        teacherPersener = new WorkTeacherPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getReplaceTeacherClassDatas(term,weeks,type,paikeid);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        alladdress_lv = (ListView)this.findViewById(R.id.alladdress_lv);
        lessonAdapter = new RepalceClassOrTeacherAdapter(this,lessonEntities);
        alladdress_lv.setAdapter(lessonAdapter);
    }
    public void initViewValues(){
        if("2".equals(type)){
            title.setText("课程选择");
        }else if("1".equals(type)){
            title.setText("教师选择");
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        alladdress_lv.setOnItemClickListener(clicer);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public AdapterView.OnItemClickListener clicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            RepalceClassOrTeacherEntity entity = (RepalceClassOrTeacherEntity) lessonAdapter.getItem(position);
            if(entity!=null){
                Intent intent = new Intent();
                intent.putExtra("entity",entity);
                setResult(resultCode,intent);
                finish();
            }
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",RepalceClassOrTeacherActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<RepalceClassOrTeacherEntity> datas = teacherPersener.parseRepalceClassOrTeacherLSITData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",RepalceClassOrTeacherActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<RepalceClassOrTeacherEntity> datas){
        if(lessonEntities.size()>0){
            lessonEntities.clear();
        }
        for(RepalceClassOrTeacherEntity entity:datas){
            if(!"0".equals(entity.getValue())) {
                lessonEntities.add(entity);
            }
        }
        lessonAdapter.notifyDataSetChanged();
    }
}
