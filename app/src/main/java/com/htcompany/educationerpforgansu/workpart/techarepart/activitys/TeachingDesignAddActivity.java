package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherInternet;
import com.htcompany.educationerpforgansu.internet.workteacher.WorkTeacherPersener;
import com.htcompany.educationerpforgansu.workpart.activitys.AllLessonActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.AllTermsActivity;
import com.htcompany.educationerpforgansu.workpart.entities.AllLessonEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllTermsEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.RepalceClassOrTeacherEntity;

/**
 * 添加代课调课
 * Created by wrb on 2016/11/14.
 */
public class TeachingDesignAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,title_rightitem_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private LinearLayout teachingdesignadd_dk_ll,teachingdesignadd_tk_ll;
    private TextView teachingdesignadd_dk_tv,teachingdesignadd_tk_tv;
    private TextView teachingdesignadd_dkline_tv,teachingdesignadd_tkline_tv;
    private TextView teachingdesignadd_tkkccontetn_tv,teachingdesignadd_dkjs_tv;
    private RelativeLayout tiaokekc_rel,dkjs_rel;
    private int slectFlag=1;
    private TextView teachingdesignadd_term_tv,teachingdesignadd_kc_tv,
            teachingdesignadd_zc_tv,teachingdesignadd_tkkc_tv,teachingdesignadd_dkteacher_tv;
    private Intent intent=null;
    private AllTermsEntity termsEntity=null;//学期实体类
    private String termKey="";
    private String weeklist="";//周次
    private AllLessonEntity lessonentity=null;
    private String nomarlClassKey="";//正常课程
    private RepalceClassOrTeacherEntity repalceClassOrTeacherEntity=null;
    private String surldate="";
    private String type="1";//代课或者调课
    //网络请求类
    private WorkTeacherInternet teacherInternet;
    private WorkTeacherPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachingdesignadd_activity);
        MyApp.notice="";
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        teacherInternet = new WorkTeacherInternet(this,myHandler);
        teacherPersener = new WorkTeacherPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout)this.findViewById(R.id.right_three_btn);
        teachingdesignadd_dk_ll=(LinearLayout)this.findViewById(R.id.teachingdesignadd_dk_ll);
        teachingdesignadd_tk_ll=(LinearLayout)this.findViewById(R.id.teachingdesignadd_tk_ll);
        teachingdesignadd_dk_tv = (TextView)this.findViewById(R.id.teachingdesignadd_dk_tv);
        teachingdesignadd_tk_tv = (TextView)this.findViewById(R.id.teachingdesignadd_tk_tv);
        teachingdesignadd_dkline_tv = (TextView)this.findViewById(R.id.teachingdesignadd_dkline_tv);
        teachingdesignadd_tkline_tv = (TextView)this.findViewById(R.id.teachingdesignadd_tkline_tv);
        teachingdesignadd_tkkccontetn_tv = (TextView)this.findViewById(R.id.teachingdesignadd_tkkccontetn_tv);
        teachingdesignadd_dkjs_tv = (TextView)this.findViewById(R.id.teachingdesignadd_dkjs_tv);

        teachingdesignadd_term_tv= (TextView)this.findViewById(R.id.teachingdesignadd_term_tv);
        teachingdesignadd_kc_tv= (TextView)this.findViewById(R.id.teachingdesignadd_kc_tv);
        teachingdesignadd_zc_tv= (TextView)this.findViewById(R.id.teachingdesignadd_zc_tv);
        teachingdesignadd_tkkc_tv= (TextView)this.findViewById(R.id.teachingdesignadd_tkkc_tv);
        teachingdesignadd_dkteacher_tv= (TextView)this.findViewById(R.id.teachingdesignadd_dkteacher_tv);

        tiaokekc_rel=(RelativeLayout)this.findViewById(R.id.tiaokekc_rel);
                dkjs_rel=(RelativeLayout)this.findViewById(R.id.dkjs_rel);
    }
    public void initViewValues(){
        right_three_btn.setVisibility(View.VISIBLE);
        title.setText("代课调课申请");
        initSelect(slectFlag);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        teachingdesignadd_dk_ll.setOnClickListener(this);
        teachingdesignadd_tk_ll.setOnClickListener(this);
        teachingdesignadd_term_tv.setOnClickListener(this);
        teachingdesignadd_zc_tv.setOnClickListener(this);
        teachingdesignadd_kc_tv.setOnClickListener(this);
        teachingdesignadd_tkkc_tv.setOnClickListener(this);
        teachingdesignadd_dkteacher_tv.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //提交申请
                if("".equals(teachingdesignadd_term_tv.getText().toString())){
                    ToastUtil.showToast("请先选择学期",TeachingDesignAddActivity.this);
                }else if("".equals(teachingdesignadd_zc_tv.getText().toString())){
                    ToastUtil.showToast("请先选择周次",TeachingDesignAddActivity.this);
                }else if("".equals(teachingdesignadd_kc_tv.getText().toString())){
                    ToastUtil.showToast("请先选择课程",TeachingDesignAddActivity.this);
                }else {
                    if("1".equals(type)){
                        //代课教师
                        if("".equals(teachingdesignadd_dkteacher_tv.getText().toString())){
                            ToastUtil.showToast("请先选择代课教师",TeachingDesignAddActivity.this);
                        }else{
                            waitDialog.show();
                            teacherInternet.addReplaceTeacherClassDatas(termKey, weeklist, type, nomarlClassKey, surldate, "2");
                        }
                    }else  if("2".equals(type)){
                        //调课课程
                        if("".equals(teachingdesignadd_tkkc_tv.getText().toString())){
                            ToastUtil.showToast("请先选择代课教师",TeachingDesignAddActivity.this);
                        }else{
                            waitDialog.show();
                            teacherInternet.addReplaceTeacherClassDatas(termKey, weeklist, type, nomarlClassKey, surldate, "2");
                        }
                    }

                }
                    break;
            case R.id.teachingdesignadd_dk_ll:
                slectFlag=1;
                initSelect(slectFlag);
                break;
            case R.id.teachingdesignadd_tk_ll:
                slectFlag=2;
                initSelect(slectFlag);
                break;
            case R.id.teachingdesignadd_term_tv:
                //选择学期
                intent = new Intent(TeachingDesignAddActivity.this, AllTermsActivity.class);
                startActivityForResult(intent,101);
                break;
            case R.id.teachingdesignadd_zc_tv:
                if("".equals(teachingdesignadd_term_tv.getText().toString())){
                    ToastUtil.showToast("请先选择学期",TeachingDesignAddActivity.this);
                }else {
                    //选择周次
                    intent = new Intent(TeachingDesignAddActivity.this, WeeksSelectDatasActivity.class);
                    intent.putExtra("weeksnum", termsEntity.getWeeks());
                    startActivityForResult(intent, 102);
                }
                break;
            case R.id.teachingdesignadd_kc_tv:
                //选择课程
                if("".equals(teachingdesignadd_zc_tv.getText().toString())){
                    ToastUtil.showToast("请先选择周次",TeachingDesignAddActivity.this);
                }else {
                    intent = new Intent(TeachingDesignAddActivity.this, AllLessonActivity.class);
                    intent.putExtra("termKey", termKey);
                    intent.putExtra("weeklist", weeklist);
                    startActivityForResult(intent, 103);
                }
                break;
            case R.id.teachingdesignadd_tkkc_tv:
                //选择调课课程
                if("".equals(teachingdesignadd_kc_tv.getText().toString())){
                    ToastUtil.showToast("请先选择课程",TeachingDesignAddActivity.this);
                }else {
                    intent = new Intent(TeachingDesignAddActivity.this, RepalceClassOrTeacherActivity.class);
                    intent.putExtra("termKey", termKey);
                    intent.putExtra("weeklist", weeklist);
                    intent.putExtra("type", "2");
                    intent.putExtra("paikeid", nomarlClassKey);
                    intent.putExtra("resultcode", 104);
                    startActivityForResult(intent, 104);
                }
                break;
            case R.id.teachingdesignadd_dkteacher_tv:
                //选择代课教师
                if("".equals(teachingdesignadd_kc_tv.getText().toString())){
                    ToastUtil.showToast("请先选择课程",TeachingDesignAddActivity.this);
                }else {
                    intent = new Intent(TeachingDesignAddActivity.this, RepalceClassOrTeacherActivity.class);
                    intent.putExtra("termKey", termKey);
                    intent.putExtra("weeklist", weeklist);
                    intent.putExtra("type", "1");
                    intent.putExtra("paikeid", nomarlClassKey);
                    intent.putExtra("resultcode", 105);
                    startActivityForResult(intent, 105);
                }
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
                    ToastUtil.showToast("连接超时",TeachingDesignAddActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(teacherPersener.parserTeacherTrainPlanBMDATAS((String)msg.obj)){
                        //申请成功
                        if(!"".equals(MyApp.notice)) {
                            ToastUtil.showToast(MyApp.notice, TeachingDesignAddActivity.this);
                        }
                        Intent intent = new Intent();
                        setResult(101,intent);
                        finish();
                    }else{
                        if(!"".equals(MyApp.notice)) {
                            ToastUtil.showToast(MyApp.notice, TeachingDesignAddActivity.this);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingDesignAddActivity.this);
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    termsEntity = (AllTermsEntity) data.getSerializableExtra("entity");
                    teachingdesignadd_term_tv.setText(termsEntity.getLabel());
                    termKey = termsEntity.getValue();
                    break;
                case 102:
                    weeklist=data.getStringExtra("weekStr");
                    teachingdesignadd_zc_tv.setText(weeklist);
                    break;
                case 103:
                    lessonentity = (AllLessonEntity) data.getSerializableExtra("entity");
                    nomarlClassKey = lessonentity.getValue();
                    teachingdesignadd_kc_tv.setText(lessonentity.getLabel());
                    teachingdesignadd_tkkc_tv.setText("");
                    teachingdesignadd_dkteacher_tv.setText("");
                    break;
                case 104:
                    repalceClassOrTeacherEntity = (RepalceClassOrTeacherEntity) data.getSerializableExtra("entity");
                    surldate = repalceClassOrTeacherEntity.getValue();
                    teachingdesignadd_tkkc_tv.setText(repalceClassOrTeacherEntity.getLabel());
                    break;
                case 105:
                    repalceClassOrTeacherEntity = (RepalceClassOrTeacherEntity) data.getSerializableExtra("entity");
                    surldate = repalceClassOrTeacherEntity.getValue();
                    teachingdesignadd_dkteacher_tv.setText(repalceClassOrTeacherEntity.getLabel());
                    break;
            }
        }
    }

    public void initNomal(){
        teachingdesignadd_dk_tv.setTextColor(getResources().getColor(R.color.ptjs_tv23_color));
        teachingdesignadd_tk_tv.setTextColor(getResources().getColor(R.color.ptjs_tv23_color));
        teachingdesignadd_dkline_tv.setVisibility(View.GONE);
        teachingdesignadd_tkline_tv.setVisibility(View.GONE);
        tiaokekc_rel.setVisibility(View.GONE);
        dkjs_rel.setVisibility(View.GONE);
    }
    public void initSelect(int index){
        initNomal();
        switch (index){
            case 1:
                //代课
                type="1";
                teachingdesignadd_dk_tv.setTextColor(getResources().getColor(R.color.ptjs_tv22_color));
                teachingdesignadd_dkline_tv.setVisibility(View.VISIBLE);
                dkjs_rel.setVisibility(View.VISIBLE);
                break;
            case 2:
                //调课
                type="2";
                teachingdesignadd_tk_tv.setTextColor(getResources().getColor(R.color.ptjs_tv22_color));
                teachingdesignadd_tkline_tv.setVisibility(View.VISIBLE);
                tiaokekc_rel.setVisibility(View.VISIBLE);
                break;
        }
    }
}
