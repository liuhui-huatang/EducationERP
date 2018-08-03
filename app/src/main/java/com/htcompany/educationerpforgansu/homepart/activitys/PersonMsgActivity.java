package com.htcompany.educationerpforgansu.homepart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.homepart.entity.PersonMsgEntity;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomeInternet;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomePersener;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 个人信息
 * Created by wrb on 2016/11/7.
 */
public class PersonMsgActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    //基本信息，入职信息
    private LinearLayout homepersonmsg_basexxpart_ll,homepersonmsg_rzxxpart_ll;
    private LinearLayout homepersonmsg_basexxbtnt_ll,homepersonmsg_rzxxbtn_ll;
    private TextView homepersonmsg_basexxtv_tv,homepersonmsg_basexxline_tv,homepersonmsg_rzxxtv_tv,homepersonmsg_rzxxline_tv;
    private int partFlag=0;
    //===============个人信息=======================
    private XCRoundAndOvalImageView homeperson_photo_img;//头像
    //姓名，工号，岗位，类别
    private TextView homeperson_name_tv,homeperson_ghcode_tv,homeperson_gwpotiosn_tv,homeperson_lb_tv;
    //======================个人基本信息==============================
    //证件类型，证件号码，性别，生日，民族，血型
    private TextView homepersonmsg_zjtype_tv,homepersonmsg_zjcode_tv,homepersonmsg_sex_tv,
            homepersonmsg_birth_tv,homepersonmsg_mz_tv,homepersonmsg_xx_tv;
    //健康状况，婚姻状态，政治面貌，是否港台，国籍地区，宗教信仰
    private TextView homepersonmsg_jkzk_tv,homepersonmsg_hyzt_tv,homepersonmsg_zzmm_tv,
            homepersonmsg_sfgatqy_tv,homepersonmsg_gjdq_tv,homepersonmsg_zjxy_tv;
    //出生地，籍贯，户口类别，户口所在地，当前住址，住址邮编
    private TextView homepersonmsg_csd_tv,homepersonmsg_jg_tv,homepersonmsg_hklb_tv,
            homepersonmsg_hkszd_tv,homepersonmsg_dqzz_tv,homepersonmsg_zzyb_tv;
    //从教工作时间，从教年月，编制类别，兼职教师，双师型教师，行政机构
    private TextView homepersonmsg_cjgztime_tv,homepersonmsg_cjny_tv,homepersonmsg_bzlb_tv,
            homepersonmsg_fjjzjs_tv,homepersonmsg_sfssxjs_tv,homepersonmsg_xzjg_tv;
    //======================入职信息信息==============================
    //来单位时间，原单位名称，来源，原单位职务，原单位政治面貌，来源地区，元从事学科，原工资总额，来单位原因，原教护津贴
    private TextView homepersonmsg_ldwtime_tv,homepersonmsg_ydwmc_tv,homepersonmsg_ly_tv,
            homepersonmsg_ydwzw_tv,homepersonmsg_yzzmm_tv,homepersonmsg_lydq_tv,
            homepersonmsg_ycsxk_tv,homepersonmsg_ygzze_tv,homepersonmsg_creson_tv,
            homepersonmsg_yjwjt_tv;
    //=========================网络请求类==========================
    private MainHomeInternet homeInternet;
    private MainHomePersener homePersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepersonmsg_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicer();
    }

    public void initDatas(){
          homeInternet = new MainHomeInternet(this,myHandler);
          homePersener = new MainHomePersener(this,myHandler);
          waitDialog = new WaitDialog(this,"");
          waitDialog.show();
          homeInternet.getPersonMsg();
    }
    public  void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        homepersonmsg_basexxpart_ll=(LinearLayout)this.findViewById(R.id.homepersonmsg_basexxpart_ll);
        homepersonmsg_rzxxpart_ll=(LinearLayout)this.findViewById(R.id.homepersonmsg_rzxxpart_ll);
        homepersonmsg_basexxbtnt_ll=(LinearLayout)this.findViewById(R.id.homepersonmsg_basexxbtnt_ll);
        homepersonmsg_rzxxbtn_ll=(LinearLayout)this.findViewById(R.id.homepersonmsg_rzxxbtn_ll);
        homepersonmsg_basexxtv_tv=(TextView)this.findViewById(R.id.homepersonmsg_basexxtv_tv);
        homepersonmsg_basexxline_tv=(TextView)this.findViewById(R.id.homepersonmsg_basexxline_tv);
        homepersonmsg_rzxxtv_tv=(TextView)this.findViewById(R.id.homepersonmsg_rzxxtv_tv);
        homepersonmsg_rzxxline_tv=(TextView)this.findViewById(R.id.homepersonmsg_rzxxline_tv);
        //个人信息
        homeperson_photo_img=(XCRoundAndOvalImageView)this.findViewById(R.id.homeperson_photo_img);
        homeperson_name_tv=(TextView)this.findViewById(R.id.homeperson_name_tv);
        homeperson_ghcode_tv=(TextView)this.findViewById(R.id.homeperson_ghcode_tv);
        homeperson_gwpotiosn_tv=(TextView)this.findViewById(R.id.homeperson_gwpotiosn_tv);
        homeperson_lb_tv=(TextView)this.findViewById(R.id.homeperson_lb_tv);
        //基本信息
        homepersonmsg_zjtype_tv=(TextView)this.findViewById(R.id.homepersonmsg_zjtype_tv);
        homepersonmsg_zjcode_tv=(TextView)this.findViewById(R.id.homepersonmsg_zjcode_tv);
        homepersonmsg_sex_tv=(TextView)this.findViewById(R.id.homepersonmsg_sex_tv);
        homepersonmsg_birth_tv=(TextView)this.findViewById(R.id.homepersonmsg_birth_tv);
        homepersonmsg_mz_tv=(TextView)this.findViewById(R.id.homepersonmsg_mz_tv);
        homepersonmsg_xx_tv=(TextView)this.findViewById(R.id.homepersonmsg_xx_tv);
        homepersonmsg_jkzk_tv=(TextView)this.findViewById(R.id.homepersonmsg_jkzk_tv);
        homepersonmsg_hyzt_tv=(TextView)this.findViewById(R.id.homepersonmsg_hyzt_tv);
        homepersonmsg_zzmm_tv=(TextView)this.findViewById(R.id.homepersonmsg_zzmm_tv);
        homepersonmsg_sfgatqy_tv=(TextView)this.findViewById(R.id.homepersonmsg_sfgatqy_tv);
        homepersonmsg_gjdq_tv=(TextView)this.findViewById(R.id.homepersonmsg_gjdq_tv);
        homepersonmsg_zjxy_tv=(TextView)this.findViewById(R.id.homepersonmsg_zjxy_tv);
        homepersonmsg_csd_tv=(TextView)this.findViewById(R.id.homepersonmsg_csd_tv);
        homepersonmsg_jg_tv=(TextView)this.findViewById(R.id.homepersonmsg_jg_tv);
        homepersonmsg_hklb_tv=(TextView)this.findViewById(R.id.homepersonmsg_hklb_tv);
        homepersonmsg_hkszd_tv=(TextView)this.findViewById(R.id.homepersonmsg_hkszd_tv);
        homepersonmsg_dqzz_tv=(TextView)this.findViewById(R.id.homepersonmsg_dqzz_tv);
        homepersonmsg_zzyb_tv=(TextView)this.findViewById(R.id.homepersonmsg_zzyb_tv);
        homepersonmsg_cjgztime_tv=(TextView)this.findViewById(R.id.homepersonmsg_cjgztime_tv);
        homepersonmsg_cjny_tv=(TextView)this.findViewById(R.id.homepersonmsg_cjny_tv);
        homepersonmsg_bzlb_tv=(TextView)this.findViewById(R.id.homepersonmsg_bzlb_tv);
        homepersonmsg_fjjzjs_tv=(TextView)this.findViewById(R.id.homepersonmsg_fjjzjs_tv);
        homepersonmsg_sfssxjs_tv=(TextView)this.findViewById(R.id.homepersonmsg_sfssxjs_tv);
        homepersonmsg_xzjg_tv=(TextView)this.findViewById(R.id.homepersonmsg_xzjg_tv);
        //入职信息
        homepersonmsg_ldwtime_tv=(TextView)this.findViewById(R.id.homepersonmsg_ldwtime_tv);
        homepersonmsg_ydwmc_tv=(TextView)this.findViewById(R.id.homepersonmsg_ydwmc_tv);
        homepersonmsg_ly_tv=(TextView)this.findViewById(R.id.homepersonmsg_ly_tv);
        homepersonmsg_ydwzw_tv=(TextView)this.findViewById(R.id.homepersonmsg_ydwzw_tv);
        homepersonmsg_yzzmm_tv=(TextView)this.findViewById(R.id.homepersonmsg_yzzmm_tv);
        homepersonmsg_lydq_tv=(TextView)this.findViewById(R.id.homepersonmsg_lydq_tv);
        homepersonmsg_ycsxk_tv=(TextView)this.findViewById(R.id.homepersonmsg_ycsxk_tv);
        homepersonmsg_ygzze_tv=(TextView)this.findViewById(R.id.homepersonmsg_ygzze_tv);
        homepersonmsg_creson_tv=(TextView)this.findViewById(R.id.homepersonmsg_creson_tv);
        homepersonmsg_yjwjt_tv=(TextView)this.findViewById(R.id.homepersonmsg_yjwjt_tv);
    }
    public void initViewValues(){
        title.setText("基本信息");
        selectPart(partFlag);
    }
    public void initOnclicer(){
        reback_btn.setOnClickListener(this);
        homepersonmsg_basexxbtnt_ll.setOnClickListener(this);
        homepersonmsg_rzxxbtn_ll.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.homepersonmsg_basexxbtnt_ll:
                partFlag=0;
                selectPart(partFlag);
                break;
            case R.id.homepersonmsg_rzxxbtn_ll:
                partFlag=1;
                selectPart(partFlag);
                break;
        }
    }

    public void setNomalr(){
        homepersonmsg_basexxpart_ll.setVisibility(View.GONE);
        homepersonmsg_rzxxpart_ll.setVisibility(View.GONE);
        homepersonmsg_basexxtv_tv.setTextColor(getResources().getColor(R.color.grys_jbxxtv7_color));
        homepersonmsg_basexxline_tv.setVisibility(View.GONE);
        homepersonmsg_rzxxtv_tv.setTextColor(getResources().getColor(R.color.grys_jbxxtv7_color));
        homepersonmsg_rzxxline_tv.setVisibility(View.GONE);
    }
    public void selectPart(int index){
        setNomalr();
        switch (index){
            case 0:
                homepersonmsg_basexxpart_ll.setVisibility(View.VISIBLE);
                homepersonmsg_basexxtv_tv.setTextColor(getResources().getColor(R.color.grys_jbxxtv6_color));
                homepersonmsg_basexxline_tv.setVisibility(View.VISIBLE);
                break;
            case 1:
                homepersonmsg_rzxxpart_ll.setVisibility(View.VISIBLE);
                homepersonmsg_rzxxtv_tv.setTextColor(getResources().getColor(R.color.grys_jbxxtv6_color));
                homepersonmsg_rzxxline_tv.setVisibility(View.VISIBLE);
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
                    ToastUtil.showToast("请求网络失败",PersonMsgActivity.this);
                    break;
                case 200:
                    //处理数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    PersonMsgEntity entity = homePersener.parsePersonMsgData((String)msg.obj);
                    if(entity!=null){
                        setViewVlaues(entity);
                    }
                    break;
                case 300:
                    //处理数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",PersonMsgActivity.this);
                    break;
            }
        }
    };

    /**
     * 设置界面控件值
     * @param entity
     */
    public void setViewVlaues(PersonMsgEntity entity){
        //个人信息
        Glide.with(this).load(InterfaceManager.siteURLIP+entity.getPhoto())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(homeperson_photo_img);
// homeperson_photo_img=(ImageView)this.findViewById(R.id.homeperson_photo_img);
        homeperson_name_tv.setText(entity.getUsername());
        homeperson_ghcode_tv.setText(entity.getWorker_number());
        homeperson_gwpotiosn_tv.setText(entity.getOrganization_job());
        homeperson_lb_tv.setText(entity.getOrganization());
        //基本信息
        homepersonmsg_zjtype_tv.setText(entity.getDocument_type_show());
        homepersonmsg_zjcode_tv.setText(entity.getDocument_number());
        homepersonmsg_sex_tv.setText(entity.getSex());
        homepersonmsg_birth_tv.setText(entity.getBirthday());
        homepersonmsg_mz_tv.setText(entity.getNation_show());
        homepersonmsg_xx_tv.setText(entity.getBlood_type_show());
        homepersonmsg_jkzk_tv.setText(entity.getHealth_condition_show());
        homepersonmsg_hyzt_tv.setText(entity.getMarital_status_show());
        homepersonmsg_zzmm_tv.setText(entity.getPolitics_status_show());
        homepersonmsg_sfgatqy_tv.setText(entity.getGang_ao_tai_show());
        homepersonmsg_gjdq_tv.setText(entity.getNationality_show());
        homepersonmsg_zjxy_tv.setText(entity.getReligious_beliefs_show());
        homepersonmsg_csd_tv.setText(entity.getPlace_birth());
        homepersonmsg_jg_tv.setText(entity.getNative_place());
        homepersonmsg_hklb_tv.setText(entity.getAccount_type_show());
        homepersonmsg_hkszd_tv.setText(entity.getAccount_location());
        homepersonmsg_dqzz_tv.setText(entity.getCurrent_address());
        homepersonmsg_zzyb_tv.setText(entity.getZip_code());
        homepersonmsg_cjgztime_tv.setText(entity.getWorker_time());
        homepersonmsg_cjny_tv.setText(entity.getTeacher_time());
        homepersonmsg_bzlb_tv.setText(entity.getCompile_type_show());
        homepersonmsg_fjjzjs_tv.setText(entity.getIs_part_time_teacher());
        homepersonmsg_sfssxjs_tv.setText(entity.getIs_biform());
        homepersonmsg_xzjg_tv.setText(entity.getOrganization());
        //入职信息
        homepersonmsg_ldwtime_tv.setText(entity.getDate_entry());
        homepersonmsg_ydwmc_tv.setText(entity.getOld_unit_name());
        homepersonmsg_ly_tv.setText(entity.getSource_show());
        homepersonmsg_ydwzw_tv.setText(entity.getOld_technology_job());
        homepersonmsg_yzzmm_tv.setText(entity.getOld_party_government_posts());
        homepersonmsg_lydq_tv.setText(entity.getSource_location());
        homepersonmsg_ycsxk_tv.setText(entity.getOld_subject());
        homepersonmsg_ygzze_tv.setText(entity.getOld_wage_total());
        homepersonmsg_creson_tv.setText(entity.getTo_unit_why());
        homepersonmsg_yjwjt_tv.setText(entity.getOld_age_allowance());
    }
}
