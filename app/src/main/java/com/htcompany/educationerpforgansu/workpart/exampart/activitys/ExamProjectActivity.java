package com.htcompany.educationerpforgansu.workpart.exampart.activitys;

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
import com.htcompany.educationerpforgansu.internet.exampart.ExamInternet;
import com.htcompany.educationerpforgansu.internet.exampart.ExamPersener;
import com.htcompany.educationerpforgansu.workpart.exampart.adapters.ExamProjectAdapter;
import com.htcompany.educationerpforgansu.workpart.exampart.entitis.ExamProjectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 考试科目列表
 * Created by wrb on 2017/5/8.
 */
public class ExamProjectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView examproject_lv;
    private ExamProjectAdapter examProjectAdapter;
    private List<ExamProjectEntity> examProjectEntities;
    private ExamInternet examInternet;
    private ExamPersener examPersener;
    private WaitDialog waitDialog=null;
    private String flag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examproject_activity);
        initDatas();
        initViews();
        initViewVlaues();
        initOnclicEvents();
    }
    public void initDatas(){
        flag=getIntent().getStringExtra("flag");
        examProjectEntities = new ArrayList<ExamProjectEntity>();
        examInternet = new ExamInternet(this,myHandler);
        examPersener = new ExamPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        examInternet.getExamProjectlistDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        examproject_lv = (ListView)this.findViewById(R.id.examproject_lv);
        examProjectAdapter = new ExamProjectAdapter(this,examProjectEntities);
        examproject_lv.setAdapter(examProjectAdapter);
    }
    public void initViewVlaues(){
        title.setText("选择考试项目");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        examproject_lv.setOnItemClickListener(itemClickListener);
    }
   public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           ExamProjectEntity entity = (ExamProjectEntity)examProjectAdapter.getItem(position);
           Intent intent=null;
           if(entity!=null){
               if("XSKCXXCX".equals(flag)){
                 intent = new Intent(ExamProjectActivity.this,ExampartStudentSearchActivity.class);
                   intent.putExtra("value",entity.getUnkey());
                   intent.putExtra("title",entity.getName());
               }else if("JKXXCX".equals(flag)){
                   intent = new Intent(ExamProjectActivity.this,ExampartInvigilateActivity.class);
                   intent.putExtra("value",entity.getUnkey());
                   intent.putExtra("title",entity.getName());
               }
               startActivity(intent);
           }
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
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",ExamProjectActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ExamProjectEntity> datas = examPersener.parseExamProjectlistData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",ExamProjectActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<ExamProjectEntity> datas){
            if(examProjectEntities.size()>0){
                examProjectEntities.clear();
            }
        for(ExamProjectEntity entity:datas){
            examProjectEntities.add(entity);
        }
        examProjectAdapter.notifyDataSetChanged();
    }
}
