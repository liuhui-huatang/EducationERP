package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentMessageEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.ClassNameListAdpter;
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.SelectClassNameListAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级名单
 * Created by wrb on 2016/11/14.
 */
public class ClassNameListActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView classname_wsj_img;
    private GridView classname_lv;
    private ClassNameListAdpter classNameListAdapter;
    private List<ClassNameEntity> classNameEntities;
    private EditText bjhmcsearcher_name_edt;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classnamelist_activity);
        initDatas();
        initView();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        classNameEntities = new ArrayList<ClassNameEntity>();
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getAllClassNameListDatas("",username);
    }
    public void initView(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        classname_wsj_img=(ImageView)this.findViewById(R.id.classname_wsj_img);
        bjhmcsearcher_name_edt = (EditText)this.findViewById(R.id.bjhmcsearcher_name_edt);
        classname_lv = (GridView)this.findViewById(R.id.classname_lv);
        classNameListAdapter = new ClassNameListAdpter(this,classNameEntities);
        classname_lv.setAdapter(classNameListAdapter);
    }
    public void initViewValues(){
        title.setText("班级名单");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        classname_lv.setOnItemClickListener(nameCLicer);
        bjhmcsearcher_name_edt.addTextChangedListener(serachclier);
    }
    public AdapterView.OnItemClickListener nameCLicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ClassNameEntity entity = (ClassNameEntity) classNameListAdapter.getItem(position);
            Intent intent = new Intent(ClassNameListActivity.this,SelectClassNameDetailsActivity.class);
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
    public TextWatcher serachclier = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            username = bjhmcsearcher_name_edt.getText().toString().trim();
            pageNum=1;
            if(!"".equals(username)){
                if(classNameEntities.size()>0){
                    classNameEntities.clear();
                }
                teacherInternet.getAllClassNameListDatas(String.valueOf(pageNum),username);
            }else{
                teacherInternet.getAllClassNameListDatas(String.valueOf(pageNum),username);
            }
        }
    };
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
                    List<ClassNameEntity> datas = teacherPersener.parseCLassNameData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else{
                        if(classNameEntities.size()==0){
                            classname_wsj_img.setVisibility(View.VISIBLE);
                        }else{
                            classname_wsj_img.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",ClassNameListActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(classNameEntities.size()==0){
                        classname_wsj_img.setVisibility(View.VISIBLE);
                    }else{
                        classname_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接服务器失败",ClassNameListActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas(List<ClassNameEntity> datas ){
            if (classNameEntities.size()>0){
                classNameEntities.clear();
            }
        for(ClassNameEntity entity:datas){
            classNameEntities.add(entity);
        }
        if(classNameEntities.size()==0){
            classname_wsj_img.setVisibility(View.VISIBLE);
        }else{
            classname_wsj_img.setVisibility(View.GONE);
        }
        classNameListAdapter.notifyDataSetChanged();
    }
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<StudentMessageEntity> datas){

    }


}
