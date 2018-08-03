package com.htcompany.educationerpforgansu.workpart.studentpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.studentpart.entity.StudentMessageEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 学生详情项
 * Created by wrb on 2016/11/23.
 */
public class StudentMessageDetailItemActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private XCRoundAndOvalImageView studentmessagedetail_photo_img;
    private TextView studentmessagedetail_name_tv,studentmessagedetail_code_tv,studentmessagedetail_jxb_tv,
            studentmessagedetail_xzb_tv;
    //==================================基本信息部分===========================================
    private TextView persioninfomation_name_tv,persioninfomation_sex_tv,persioninfomation_zy_tv,persioninfomation_xz_tv,persioninfomation_cc_tv,
            persioninfomation_nj_tv,persioninfomation_class_tv,persioninfomation_xsphone_tv,persioninfomation_xh_tv,persioninfomation_xjh_tv,
            persioninfomation_csrq_tv,persioninfomation_mz_tv,persioninfomation_sfswyxmz_tv,persioninfomation_myxuexing_tv,persioninfomation_sfzjlx_tv,
            persioninfomation_sfzjcode_tv,persioninfomation_xsjkzk_tv,persioninfomation_xszzmm_tv,persioninfomation_sfldrk_tv,persioninfomation_sfsqzn_tv;
   private StudentMessageEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmessagedetailitem_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
   public void initDatas(){
       entity = (StudentMessageEntity) getIntent().getSerializableExtra("entity");
   }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        studentmessagedetail_photo_img =(XCRoundAndOvalImageView)this.findViewById(R.id.studentmessagedetail_photo_img);
        studentmessagedetail_name_tv= (TextView)this.findViewById(R.id.studentmessagedetail_name_tv);
        studentmessagedetail_code_tv= (TextView)this.findViewById(R.id.studentmessagedetail_code_tv);
        studentmessagedetail_jxb_tv= (TextView)this.findViewById(R.id.studentmessagedetail_jxb_tv);
        studentmessagedetail_xzb_tv= (TextView)this.findViewById(R.id.studentmessagedetail_xzb_tv);
        //==================================基本信息部分===========================================
        persioninfomation_name_tv= (TextView)this.findViewById(R.id.persioninfomation_name_tv);
        persioninfomation_sex_tv= (TextView)this.findViewById(R.id.persioninfomation_sex_tv);
        persioninfomation_zy_tv= (TextView)this.findViewById(R.id.persioninfomation_zy_tv);
        persioninfomation_xz_tv= (TextView)this.findViewById(R.id.persioninfomation_xz_tv);
        persioninfomation_cc_tv= (TextView)this.findViewById(R.id.persioninfomation_cc_tv);
        persioninfomation_nj_tv= (TextView)this.findViewById(R.id.persioninfomation_nj_tv);
        persioninfomation_class_tv= (TextView)this.findViewById(R.id.persioninfomation_class_tv);
        persioninfomation_xsphone_tv= (TextView)this.findViewById(R.id.persioninfomation_xsphone_tv);
        persioninfomation_xh_tv= (TextView)this.findViewById(R.id.persioninfomation_xh_tv);
        persioninfomation_xjh_tv= (TextView)this.findViewById(R.id.persioninfomation_xjh_tv);
        persioninfomation_csrq_tv= (TextView)this.findViewById(R.id.persioninfomation_csrq_tv);
        persioninfomation_mz_tv= (TextView)this.findViewById(R.id.persioninfomation_mz_tv);
        persioninfomation_sfswyxmz_tv= (TextView)this.findViewById(R.id.persioninfomation_sfswyxmz_tv);
        persioninfomation_myxuexing_tv= (TextView)this.findViewById(R.id.persioninfomation_myxuexing_tv);
        persioninfomation_sfzjlx_tv= (TextView)this.findViewById(R.id.persioninfomation_sfzjlx_tv);
        persioninfomation_sfzjcode_tv= (TextView)this.findViewById(R.id.persioninfomation_sfzjcode_tv);
        persioninfomation_xsjkzk_tv= (TextView)this.findViewById(R.id.persioninfomation_xsjkzk_tv);
        persioninfomation_xszzmm_tv= (TextView)this.findViewById(R.id.persioninfomation_xszzmm_tv);
        persioninfomation_sfldrk_tv= (TextView)this.findViewById(R.id.persioninfomation_sfldrk_tv);
        persioninfomation_sfsqzn_tv= (TextView)this.findViewById(R.id.persioninfomation_sfsqzn_tv);

    }
    public void initViewValues(){
        title.setText("学生详情");
        if(entity!=null){
            Glide.with(this).load(InterfaceManager.siteURLIP+entity.getPhoto())
                    .placeholder(R.mipmap.defult_photo_icon)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(studentmessagedetail_photo_img);
            studentmessagedetail_name_tv.setText(entity.getTruename());
            studentmessagedetail_code_tv.setText(entity.getNumber());
            studentmessagedetail_jxb_tv.setText(entity.getTrbd_jxb());
            studentmessagedetail_xzb_tv.setText(entity.getXzb());
            //==================================基本信息部分===========================================
            persioninfomation_name_tv.setText(entity.getTruename());
            persioninfomation_sex_tv.setText(entity.getGender());
            persioninfomation_zy_tv.setText(entity.getTrbd_major());
            persioninfomation_xz_tv.setText(entity.getTrbd_educational());
            persioninfomation_cc_tv.setText(entity.getTrbd_unity());
            persioninfomation_nj_tv.setText(entity.getTrbd_grade());
            persioninfomation_class_tv.setText(entity.getTrbd_jxb());
            persioninfomation_xsphone_tv.setText(entity.getPhone());
            persioninfomation_xh_tv.setText(entity.getNumber());
            persioninfomation_xjh_tv.setText(entity.getXji_num());
            persioninfomation_csrq_tv.setText(entity.getBirthday());
            persioninfomation_mz_tv.setText(entity.getTrbd_nation());
            if("1".equals(entity.getIs_swyxmz())){
                persioninfomation_sfswyxmz_tv.setText("是");
            }else  if("2".equals(entity.getIs_swyxmz())){
                persioninfomation_sfswyxmz_tv.setText("否");
            }

            persioninfomation_myxuexing_tv.setText(entity.getBlood());
            persioninfomation_sfzjlx_tv.setText(entity.getTrbd_identity());
            persioninfomation_sfzjcode_tv.setText(entity.getIdentitynum());
            persioninfomation_xsjkzk_tv.setText(entity.getTrbd_health());
            persioninfomation_xszzmm_tv.setText(entity.getTrbd_political());
            if("1".equals(entity.getIs_ldrk())){
                persioninfomation_sfldrk_tv.setText("是");
            }else  if("2".equals(entity.getIs_ldrk())){
                persioninfomation_sfldrk_tv.setText("否");
            }
            if("1".equals(entity.getIs_sqzn())){
                persioninfomation_sfsqzn_tv.setText("是");
            }else  if("2".equals(entity.getIs_sqzn())){
                persioninfomation_sfsqzn_tv.setText("否");
            }
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
}
