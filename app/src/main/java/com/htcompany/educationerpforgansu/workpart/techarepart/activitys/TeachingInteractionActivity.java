package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.media.Image;
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
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeachingInteractionAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingInteractionEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * 教学互动
 * Created by wrb on 2016/11/22.
 */
public class TeachingInteractionActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView teachinginteraction_wsj_img;
    private ListView teachinginteraction_lv;
    private TeachingInteractionAdapter interactionAdapter;
    private List<TeachingInteractionEntity> interactionEntities;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachinginteraction_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        interactionEntities = new ArrayList<TeachingInteractionEntity>();
        teacherInternet = new WorkTeacherInternet(this,tableHanler);
        teacherPersener = new WorkTeacherPersener(this,tableHanler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getJXHD_LISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        teachinginteraction_wsj_img=(ImageView)this.findViewById(R.id.teachinginteraction_wsj_img);
        teachinginteraction_lv = (ListView)this.findViewById(R.id.teachinginteraction_lv);
        interactionAdapter = new TeachingInteractionAdapter(this,interactionEntities);
        teachinginteraction_lv.setAdapter(interactionAdapter);
    }
    public void initViewValues(){
        title.setText("教学互动");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        teachinginteraction_lv.setOnItemClickListener(interationClicer);
    }
   public AdapterView.OnItemClickListener interationClicer = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           TeachingInteractionEntity entity = (TeachingInteractionEntity) interactionAdapter.getItem(position);
           Intent intent2 = new Intent(TeachingInteractionActivity.this,TeachingInteractionDetailsActivity.class);
           intent2.putExtra("entity",entity);
           startActivityForResult(intent2,101);
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
                    List<TeachingInteractionEntity> datas = teacherPersener.parseJXHDLISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else {
                        if(interactionEntities.size()==0){
                            teachinginteraction_wsj_img.setVisibility(View.VISIBLE);
                        }else{
                            teachinginteraction_wsj_img.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingInteractionActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(interactionEntities.size()==0){
                        teachinginteraction_wsj_img.setVisibility(View.VISIBLE);
                    }else{
                        teachinginteraction_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接服务器失败",TeachingInteractionActivity.this);
                    break;
            }
        }
    };
   public void setAdapterDatas(List<TeachingInteractionEntity> datas ){
       if (interactionEntities.size()>0){
           interactionEntities.clear();
       }
       for(TeachingInteractionEntity entity:datas){
           interactionEntities.add(entity);
       }
       if(interactionEntities.size()==0){
           teachinginteraction_wsj_img.setVisibility(View.VISIBLE);
       }else{
           teachinginteraction_wsj_img.setVisibility(View.GONE);
       }
       interactionAdapter.notifyDataSetChanged();
   }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            switch (requestCode) {
                case 101:
                    TeachingInteractionEntity entity = (TeachingInteractionEntity)data.getSerializableExtra("entity");
                    if(entity!=null){
                        for(TeachingInteractionEntity entity1:interactionEntities){
                            if(entity.getId().equals(entity1.getId())){
                                entity1.setIs_yes(entity.getIs_yes());
                                entity1.setQuestion_da(entity.getQuestion_da());
                                break;
                            }
                        }
                        interactionAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
}
