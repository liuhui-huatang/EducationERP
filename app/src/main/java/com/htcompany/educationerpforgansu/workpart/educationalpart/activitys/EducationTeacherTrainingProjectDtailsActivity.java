package com.htcompany.educationerpforgansu.workpart.educationalpart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.educationalpart.EducationalPartInternet;
import com.htcompany.educationerpforgansu.internet.educationalpart.EducationalPartPersener;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationTeacherTrainEnity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.Entity.EducationTeacherTrainingPersonEntity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.adapters.EducationTeacherTrainingProjectDtailsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 培训项目详情
 * Created by wrb on 2016/11/25.
 */
public class EducationTeacherTrainingProjectDtailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    //项目详情，报名情况
    private RelativeLayout edu_pxxmxq_rel,edu_pxxmbmqk_rel;
    private TextView edu_pxxmxq_tv,edu_pxxmbmqk_tv;
    private TextView edu_pxxmxqline_tv,edu_pxxmbmqkline_tv;
    private int flag=0;
    private LinearLayout edu_pxxmxq_ll,edu_pxxmbmqk_ll;//详情，报名情况
    //详情布局
    private TextView eduteachertraindetail_xmmc_tv,eduteachertraindetail_xh_tv,
            eduteachertraindetail_pxjd_tv,eduteachertraindetail_pxrs_tv,
            eduteachertraindetail_starttime_tv,eduteachertraindetail_endtime_tv,
            eduteachertraindetail_bmzt_tv,eduteachertraindetail_fzdw_tv,eduteachertraindetail_zsmcjjb_tv;
    //报名情况
    private ListView teachertrain_baomingstatus_lv;
    private EducationTeacherTrainingProjectDtailsAdapter projectDtailsAdapter;
    private List<EducationTeacherTrainingPersonEntity> trainingPersonEntities;
    private EducationTeacherTrainEnity entity=null;

    //网络请求类
    private EducationalPartInternet educationalPartInternet;
    private EducationalPartPersener educationalPartPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationteachertrainingprojectdtails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity=(EducationTeacherTrainEnity)getIntent().getSerializableExtra("entity");
        trainingPersonEntities=new ArrayList<EducationTeacherTrainingPersonEntity>();
        educationalPartInternet = new EducationalPartInternet(this,myHandler);
        educationalPartPersener = new EducationalPartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        educationalPartInternet.getTeacherTrainProjectPerson_ListDatas(entity.getId());
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);

        edu_pxxmxq_rel= (RelativeLayout)this.findViewById(R.id.edu_pxxmxq_rel);
        edu_pxxmbmqk_rel= (RelativeLayout)this.findViewById(R.id.edu_pxxmbmqk_rel);

        edu_pxxmxq_tv = (TextView)this.findViewById(R.id.edu_pxxmxq_tv);
        edu_pxxmbmqk_tv = (TextView)this.findViewById(R.id.edu_pxxmbmqk_tv);

        edu_pxxmxqline_tv= (TextView)this.findViewById(R.id.edu_pxxmxqline_tv);
        edu_pxxmbmqkline_tv= (TextView)this.findViewById(R.id.edu_pxxmbmqkline_tv);

        edu_pxxmxq_ll = (LinearLayout)this.findViewById(R.id.edu_pxxmxq_ll);
        edu_pxxmbmqk_ll = (LinearLayout)this.findViewById(R.id.edu_pxxmbmqk_ll);

        eduteachertraindetail_xmmc_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_xmmc_tv);
        eduteachertraindetail_xh_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_xh_tv);
        eduteachertraindetail_pxjd_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_pxjd_tv);
        eduteachertraindetail_pxrs_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_pxrs_tv);
        eduteachertraindetail_starttime_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_starttime_tv);
        eduteachertraindetail_endtime_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_endtime_tv);
        eduteachertraindetail_bmzt_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_bmzt_tv);
        eduteachertraindetail_fzdw_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_fzdw_tv);
        eduteachertraindetail_zsmcjjb_tv= (TextView)this.findViewById(R.id.eduteachertraindetail_zsmcjjb_tv);
        //报名情况
        teachertrain_baomingstatus_lv = (ListView)this.findViewById(R.id.teachertrain_baomingstatus_lv);
        projectDtailsAdapter=new EducationTeacherTrainingProjectDtailsAdapter(this,trainingPersonEntities,educationalPartInternet);
        teachertrain_baomingstatus_lv.setAdapter(projectDtailsAdapter);
    }

    public void initViewValues() {
        title.setText("培训详情");
//        right_three_btn.setVisibility(View.VISIBLE);
//        rightthree_btn_tv.setText("删除");
        if(entity!=null){
            eduteachertraindetail_xmmc_tv.setText(entity.getPei_name());
            eduteachertraindetail_xh_tv.setText(entity.getPei_xuhao());
            eduteachertraindetail_pxjd_tv.setText(entity.getPei_jidi());
            eduteachertraindetail_pxrs_tv.setText(entity.getPei_num());
            eduteachertraindetail_starttime_tv.setText(entity.getPei_atime());
            eduteachertraindetail_endtime_tv.setText(entity.getPei_btime());
            eduteachertraindetail_bmzt_tv.setText(entity.getPei_status_a());
            eduteachertraindetail_fzdw_tv.setText(entity.getPei_danwei());
            eduteachertraindetail_zsmcjjb_tv.setText(entity.getPei_con());
        }
        setViewSelect(flag);
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        edu_pxxmxq_rel.setOnClickListener(this);
        edu_pxxmbmqk_rel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.edu_pxxmxq_rel:
                flag=0;
                setViewSelect(flag);
                break;
            case R.id.edu_pxxmbmqk_rel:
                flag=1;
                setViewSelect(flag);
                break;
            case R.id.right_three_btn:
                //调到图书分类界面
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
                    ToastUtil.showToast("连接超时",EducationTeacherTrainingProjectDtailsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<EducationTeacherTrainingPersonEntity> datas = educationalPartPersener.parseTeacherTrainPersonListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",EducationTeacherTrainingProjectDtailsActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<EducationTeacherTrainingPersonEntity> datas){
            if (trainingPersonEntities.size() > 0) {
                trainingPersonEntities.clear();
            }
        for(EducationTeacherTrainingPersonEntity entity:datas){
            trainingPersonEntities.add(entity);
        }
        projectDtailsAdapter.notifyDataSetChanged();
    }
    public void setViewNormal() {
        edu_pxxmxq_tv.setTextColor(getResources().getColor(R.color.black2));
        edu_pxxmbmqk_tv.setTextColor(getResources().getColor(R.color.black2));

        edu_pxxmxqline_tv.setVisibility(View.GONE);
        edu_pxxmbmqkline_tv.setVisibility(View.GONE);

        edu_pxxmxq_ll.setVisibility(View.GONE);
        edu_pxxmbmqk_ll.setVisibility(View.GONE);
    }

    public void setViewSelect(int index) {
        setViewNormal();
        switch (index) {
            case 0:
                edu_pxxmxq_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                edu_pxxmxqline_tv.setVisibility(View.VISIBLE);
                edu_pxxmxq_ll.setVisibility(View.VISIBLE);
                break;

            case 1:
                edu_pxxmbmqk_tv.setTextColor(getResources().getColor(
                        R.color.title_bar_color));
                edu_pxxmbmqkline_tv.setVisibility(View.VISIBLE);
                edu_pxxmbmqk_ll.setVisibility(View.VISIBLE);
                break;
        }

    }
}
