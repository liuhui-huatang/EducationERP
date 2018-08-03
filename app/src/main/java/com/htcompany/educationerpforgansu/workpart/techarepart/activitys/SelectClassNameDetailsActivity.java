package com.htcompany.educationerpforgansu.workpart.techarepart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.ClassNameEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 学生详情
 * Created by wrb on 2016/12/20.
 */
public class SelectClassNameDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView class_name_tv,class_code_tv,class_jxb_tv,class_xzb_tv,
            class_xz_tv,class_xb_tv,class_cc_tv,class_xszy_tv,class_nj_tv,class_phone_tv;
    private XCRoundAndOvalImageView selectcalssnamedetail_photo_img;
    private ClassNameEntity entity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectclassnamedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (ClassNameEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        selectcalssnamedetail_photo_img=(XCRoundAndOvalImageView)this.findViewById(R.id.selectcalssnamedetail_photo_img);
        class_name_tv= (TextView)this.findViewById(R.id.class_name_tv);
                class_code_tv= (TextView)this.findViewById(R.id.class_code_tv);
         class_jxb_tv= (TextView)this.findViewById(R.id.class_jxb_tv);
                class_xzb_tv= (TextView)this.findViewById(R.id.class_xzb_tv);
                class_xz_tv= (TextView)this.findViewById(R.id.class_xz_tv);
                class_xb_tv= (TextView)this.findViewById(R.id.class_xb_tv);
                class_cc_tv= (TextView)this.findViewById(R.id.class_cc_tv);
                class_xszy_tv= (TextView)this.findViewById(R.id.class_xszy_tv);
                class_nj_tv= (TextView)this.findViewById(R.id.class_nj_tv);
                class_phone_tv= (TextView)this.findViewById(R.id.class_phone_tv);
    }
    public void initViewValues(){
        title.setText("学生详情");
        if(entity!=null){
            class_name_tv.setText(entity.getTruename());
            class_code_tv.setText(entity.getNumber());
            class_jxb_tv.setText(entity.getTrbd_jxb());
            class_xzb_tv.setText(entity.getXzb());
            class_xz_tv.setText(entity.getTrbd_educational());
            class_xb_tv.setText(entity.getGender());
            class_cc_tv.setText(entity.getTrbd_unity());
            class_xszy_tv.setText(entity.getTrbd_major());
            class_nj_tv.setText(entity.getTrbd_grade());
            class_phone_tv.setText(entity.getPhone());
            ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+entity.getPhoto(),selectcalssnamedetail_photo_img, MyApp.getOptions());
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
