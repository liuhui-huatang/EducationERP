package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.htcompany.educationerpforgansu.workpart.techarepart.adapters.TeachingDesignAdapter;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.TeachingDesignEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 代课调课
 * Created by wrb on 2016/11/14.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class TeachingDesignActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout rebackselector_btn,rightselector_three_btn;
    private TextView title_leftitem_tv,title_rightitem_tv;
    private ImageView teachingdesign_wsj_img;
    private EditText teachingdesign_search_edt;//搜索框
    private int titleItemShowFlag=1;//1代课，2调课
    private String sea_type="1";//1调课，2代课
    private ListView teachingdesign_lv;
    private TeachingDesignAdapter designAdapter;
    private List<TeachingDesignEntity> dkEnties;
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachingdesign_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        dkEnties = new ArrayList<TeachingDesignEntity>();
        teacherInternet = new WorkTeacherInternet(this,myHandler);
        teacherPersener = new WorkTeacherPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        rebackselector_btn = (RelativeLayout)this.findViewById(R.id.rebackselector_btn);
        rightselector_three_btn = (RelativeLayout)this.findViewById(R.id.rightselector_three_btn);
        teachingdesign_wsj_img=(ImageView)this.findViewById(R.id.teachingdesign_wsj_img);
        title_leftitem_tv=(TextView)this.findViewById(R.id.title_leftitem_tv);
        title_rightitem_tv=(TextView)this.findViewById(R.id.title_rightitem_tv);
        teachingdesign_lv = (ListView)this.findViewById(R.id.teachingdesign_lv);
        designAdapter = new TeachingDesignAdapter(this,dkEnties);
        teachingdesign_lv.setAdapter(designAdapter);
    }
    public void initViewValues(){
        rightselector_three_btn.setVisibility(View.VISIBLE);
        title_leftitem_tv.setText("代课");
        title_rightitem_tv.setText("调课");
        initSelectViews(titleItemShowFlag);
    }
    public void initOnclicEvents(){
        rebackselector_btn.setOnClickListener(this);
        rightselector_three_btn.setOnClickListener(this);
        title_leftitem_tv.setOnClickListener(this);
        title_rightitem_tv.setOnClickListener(this);
        teachingdesign_lv.setOnItemClickListener(onclicer);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rebackselector_btn:
                this.finish();
                break;
            case R.id.rightselector_three_btn:
                //申请代课调课
                Intent intent = new Intent(TeachingDesignActivity.this,TeachingDesignAddActivity.class);
                startActivityForResult(intent,101);
                break;
            case R.id.title_leftitem_tv:
                titleItemShowFlag=1;
                initSelectViews(titleItemShowFlag);
                break;
            case R.id.title_rightitem_tv:
                titleItemShowFlag=2;
                initSelectViews(titleItemShowFlag);
                break;
        }
    }
   public AdapterView.OnItemClickListener onclicer = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           //代课调课详情
           TeachingDesignEntity designEntity = (TeachingDesignEntity) designAdapter.getItem(position);
           Intent intent = new Intent(TeachingDesignActivity.this,TeachingDesignDetailsActivity.class);
           intent.putExtra("designEntity",designEntity);
           startActivity(intent);
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
                    if(dkEnties.size()==0){
                        teachingdesign_wsj_img.setVisibility(View.VISIBLE);
                    }else {
                        teachingdesign_wsj_img.setVisibility(View.GONE);
                    }
                    ToastUtil.showToast("连接超时",TeachingDesignActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TeachingDesignEntity> datas = teacherPersener.parseTeachingDesignData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                      if(dkEnties.size()==0){
                          teachingdesign_wsj_img.setVisibility(View.VISIBLE);
                      }else {
                          teachingdesign_wsj_img.setVisibility(View.GONE);
                      }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingDesignActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<TeachingDesignEntity> datas){
            if(dkEnties.size()>0){
                dkEnties.clear();
            }
        for(TeachingDesignEntity entity:datas){
            dkEnties.add(entity);
        }
        if(dkEnties.size()==0){
            teachingdesign_wsj_img.setVisibility(View.VISIBLE);
        }else {
            teachingdesign_wsj_img.setVisibility(View.GONE);
        }
        designAdapter.notifyDataSetChanged();
    }


    public void initNormalViews(){
        title_leftitem_tv.setTextColor(getResources().getColor(R.color.white));
        title_leftitem_tv.setBackground(getResources().getDrawable(R.drawable.title_tcolor_left_shape));

        title_rightitem_tv.setTextColor(getResources().getColor(R.color.white));
        title_rightitem_tv.setBackground(getResources().getDrawable(R.drawable.title_tcolor_right_shape));
    }
    public void initSelectViews(int viewFlag){
        initNormalViews();
        switch (viewFlag){
            case 1:
                if(dkEnties.size()>0){
                    dkEnties.clear();
                }
                designAdapter.notifyDataSetChanged();
                waitDialog.show();
                sea_type="1";
                teacherInternet.getTeachingDesignDatas(sea_type);
                title_leftitem_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                title_leftitem_tv.setBackground(getResources().getDrawable(R.drawable.title_whit_left_shape));
                break;
            case 2:
                if(dkEnties.size()>0){
                    dkEnties.clear();
                }
                designAdapter.notifyDataSetChanged();
                waitDialog.show();
                sea_type="2";
                teacherInternet.getTeachingDesignDatas(sea_type);
                title_rightitem_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                title_rightitem_tv.setBackground(getResources().getDrawable(R.drawable.title_whit_right_shape));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    teacherInternet.getTeachingDesignDatas(sea_type);
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
