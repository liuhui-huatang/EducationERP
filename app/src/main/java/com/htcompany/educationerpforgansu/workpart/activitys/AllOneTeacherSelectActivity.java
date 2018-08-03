package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.adapters.AllPersonOneSelectAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllJZGEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 单选教师列表
 * Created by wrb on 2017/4/20.
 */
public class AllOneTeacherSelectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn,right_three_btn;
    private ListView allpersonmoreselect_lv;
    private AllPersonOneSelectAdapter moreSelectAdapter;
    private List<AllJZGEntity> allPersonEntities;
    //网络请求
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    private String name="";
    private EditText person_seach_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allpersonmoreselect_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        allPersonEntities = new ArrayList<AllJZGEntity>();
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
        wokrpersonalInternet.getTeachers(name);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        person_seach_edt = (EditText)this.findViewById(R.id.person_seach_edt);
        allpersonmoreselect_lv = (ListView)this.findViewById(R.id.allpersonmoreselect_lv);
        moreSelectAdapter = new AllPersonOneSelectAdapter(this,allPersonEntities);
        allpersonmoreselect_lv.setAdapter(moreSelectAdapter);
    }
    public void initViewValues(){
        title.setText("人员选择");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        person_seach_edt.addTextChangedListener(search);
        allpersonmoreselect_lv.setOnItemClickListener(clicer);
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
            AllJZGEntity jzgEntity = (AllJZGEntity) moreSelectAdapter.getItem(position);
            Intent intent = new Intent();
            intent.putExtra("persondatas",(Serializable) jzgEntity);
            setResult(103,intent);
            finish();
        }
    };

   public TextWatcher search = new TextWatcher() {
       @Override
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {

       }

       @Override
       public void onTextChanged(CharSequence s, int start, int before, int count) {

       }

       @Override
       public void afterTextChanged(Editable s) {
           name = person_seach_edt.getText().toString().trim();
           if(!"".equals(name)){
               wokrpersonalInternet.getTeachers(name);
           }else{
               wokrpersonalInternet.getTeachers(name);
           }
       }
   };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b=null;
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("请求网络失败",AllOneTeacherSelectActivity.this);
                    break;
                case 200:
                    //处理数据
                    List<AllJZGEntity> datas= wokrpersonalPersener.parseJZGFlow((String) msg.obj);
                    if(datas!=null&&datas.size()>0){
                        if(allPersonEntities.size()>0){
                            allPersonEntities.clear();
                        }
                        for(AllJZGEntity entity1:datas){
                            allPersonEntities.add(entity1);
                        }
                        moreSelectAdapter.notifyDataSetChanged();
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
            }
        }
    };
}
