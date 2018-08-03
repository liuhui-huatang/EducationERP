package com.htcompany.educationerpforgansu.workpart.activitys;

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
import com.htcompany.educationerpforgansu.workpart.adapters.AllOneClassSelectAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级单选界面
 * Created by wrb on 2017/4/23.
 */
public class AllOneClassSelectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView alladdress_lv;
    private AllOneClassSelectAdapter termsAdapter;
    private List<AllClassEntity> termsEntities;
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
        termsEntities = new ArrayList<AllClassEntity>();
        teacherInternet = new WorkTeacherInternet(this,myHandler);
        teacherPersener = new WorkTeacherPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getAllClassListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        alladdress_lv = (ListView)this.findViewById(R.id.alladdress_lv);
        termsAdapter = new AllOneClassSelectAdapter(this,termsEntities);
        alladdress_lv.setAdapter(termsAdapter);
    }
    public void initViewValues(){
        title.setText("班级选择");
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
            AllClassEntity entity = (AllClassEntity) termsAdapter.getItem(position);
            if(entity!=null){
                Intent intent = new Intent();
                intent.putExtra("entity",entity);
                setResult(101,intent);
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
                    ToastUtil.showToast("连接超时",AllOneClassSelectActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<AllClassEntity> datas = teacherPersener.parseClassLSITData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",AllOneClassSelectActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<AllClassEntity> datas){
        if(termsEntities.size()>0){
            termsEntities.clear();
        }
        for(AllClassEntity entity:datas){
            termsEntities.add(entity);
        }
        termsAdapter.notifyDataSetChanged();
    }
}
