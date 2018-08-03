package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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
import com.htcompany.educationerpforgansu.workpart.adapters.AllPersonMoreSelectAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.AllJZGEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 人员多选
 * Created by wrb on 2016/12/13.
 */
public class AllPersonMoreSelectActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn,right_three_btn;
    private ListView allpersonmoreselect_lv;
    private AllPersonMoreSelectAdapter moreSelectAdapter;
    private List<AllJZGEntity> allPersonEntities;
    private List<AllJZGEntity> selectDatas;
    private String oneOrMoreFlag="";
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
        oneOrMoreFlag = getIntent().getStringExtra("personFlag");
        allPersonEntities = new ArrayList<AllJZGEntity>();
        selectDatas = new ArrayList<AllJZGEntity>();
        //
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
        moreSelectAdapter = new AllPersonMoreSelectAdapter(this,allPersonEntities,myHandler);
        allpersonmoreselect_lv.setAdapter(moreSelectAdapter);
    }
    public void initViewValues(){
        title.setText("人员选择");
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
                intent.putExtra("persondatas",(Serializable) selectDatas);
                if("one".equals(oneOrMoreFlag)){
                    setResult(103,intent);
                }else if("more".equals(oneOrMoreFlag)){
                    setResult(102,intent);
                }
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
                    AllJZGEntity entity = (AllJZGEntity) b.getSerializable("personEntity");
                    addSelectDatas(entity);
                    break;
                case 102:
                    b = msg.getData();
                    AllJZGEntity entity2 = (AllJZGEntity) b.getSerializable("personEntity");
                    removeSelectDatas(entity2);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("请求网络失败",AllPersonMoreSelectActivity.this);
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
    //添加选中人员数据
    public void addSelectDatas(AllJZGEntity entity){
        selectDatas.add(entity);
    }
    public void removeSelectDatas(AllJZGEntity entity){
        if(selectDatas.size()>0){
            selectDatas.remove(entity);
        }
    }
}
