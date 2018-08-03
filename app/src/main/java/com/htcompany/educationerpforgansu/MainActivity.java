package com.htcompany.educationerpforgansu;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.htcompany.educationerpforgansu.commonpart.BaseFragmenetActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.contactpart.MainContactFragment;
import com.htcompany.educationerpforgansu.dao.daoservice.MainFuctionService;
import com.htcompany.educationerpforgansu.dao.daoservice.PermissionFutionService;
import com.htcompany.educationerpforgansu.dao.daoservice.WorkFunctionService;
import com.htcompany.educationerpforgansu.homepart.MainHomeFragment;
import com.htcompany.educationerpforgansu.messagepart.MainMessageFragment;
import com.htcompany.educationerpforgansu.workpart.MainWorkFragment;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFunctionEntity;

public class MainActivity extends BaseFragmenetActivity implements View.OnClickListener {
    private FrameLayout main_content_fram;
    //消息，通讯录，办公，我的
    private RelativeLayout main_message_rel, main_contact_rel, main_work_rel, main_home_rel;
    private ImageView main_message_img, main_contact_img, main_work_img, main_home_img,image_sign;
    private TextView main_message_tv, main_contact_tv, main_work_tv, main_home_tv;
    private MainMessageFragment mainMessageFragment;
    private MainContactFragment mainContactFragment;
    private MainWorkFragment mainWorkFragment;
    private MainHomeFragment mainHomeFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();//fragement管理器
    private Fragment mContent;//记录选中的fragment

    FragmentTransaction beginTransaction = null;
    private SharedPrefUtil sharedPrefUtil = null;
    //功能数据库操作类
    private MainFuctionService mainService;//首页功能数据操作类
    private PermissionFutionService permissionFutionService;//权限数据操作类
    private WorkFunctionService workFunctionService;//所有数据管理类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (tintManager != null) {
                tintManager.setStatusBarTintEnabled(false);
            }
        }
        setContentView(R.layout.activity_main);
//        VersionUpdataUtils versionUpdataUtils=new VersionUpdataUtils(this);
        initDatas();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initViews();
        initViewValues();
        initOnclicEvents();

        // 判断是否需要更新版本
        TextView tvProgress= (TextView) findViewById(R.id.tv_progress);
        ProgressBar progressBar= (ProgressBar) findViewById(R.id.progressBar);
        LinearLayout rlUpdateLayout= (LinearLayout) findViewById(R.id.rl_update_layout);

//        AppDownloadUtils.init(tvProgress, progressBar, rlUpdateLayout);
        AppDownloadUtils.getApkInfo(this,"http://ecampus.gipc.edu.cn:8081/apkinfo.txt");

    }

    public void initDatas() {

        sharedPrefUtil = new SharedPrefUtil(this, "login");
        workFunctionService = new WorkFunctionService(this);
        if (FunctionDatas.mainFunctionDatas.size() > 0) {
            FunctionDatas.mainFunctionDatas.clear();
        }
        //初始化主页菜单数据，值初始化一次，应该从数据库中取（现在还没做）
        FunctionDatas.addInitData();
        //根据权限集合添加功能到mainFunctionDatas集合
        FunctionDatas.mainFunctionDatas.addAll(FunctionDatas.getMyselfFunction(FunctionDatas.jsQXdATAS));
        /**
         *  如果是同意账号等不不对数据进行操作，如果是不通账号登录需要先清除数据库中数据，再进行插入。
         *  1.首先：初始化角色功能数据，如果同一账号功能登录不需要初始化如果不是需要初始化，并清除本地数据库数据
         *  2.第二：初始化好的功能插入到数据库
         */

        if (!"1".equals(sharedPrefUtil.getString("ishave", ""))) {
            //判断是否已经保存过功能
            saveMainFunction();
        }
        mainMessageFragment = new MainMessageFragment();
        mainContactFragment = new MainContactFragment();
        mainWorkFragment = new MainWorkFragment();
        mainHomeFragment = new MainHomeFragment();
    }

    public void initViews() {
        main_content_fram = (FrameLayout) this.findViewById(R.id.main_content_fram);
        main_message_rel = (RelativeLayout) this.findViewById(R.id.main_message_rel);
        main_contact_rel = (RelativeLayout) this.findViewById(R.id.main_contact_rel);
        main_work_rel = (RelativeLayout) this.findViewById(R.id.main_work_rel);
        main_home_rel = (RelativeLayout) this.findViewById(R.id.main_home_rel);
        main_message_img = (ImageView) this.findViewById(R.id.main_message_img);
        main_contact_img = (ImageView) this.findViewById(R.id.main_contact_img);
        main_work_img = (ImageView) this.findViewById(R.id.main_work_img);
        main_home_img = (ImageView) this.findViewById(R.id.main_home_img);
        main_message_tv = (TextView) this.findViewById(R.id.main_message_tv);
        main_contact_tv = (TextView) this.findViewById(R.id.main_contact_tv);
        main_work_tv = (TextView) this.findViewById(R.id.main_work_tv);
        main_home_tv = (TextView) this.findViewById(R.id.main_home_tv);
        image_sign = (ImageView) this.findViewById(R.id.image_sign);


    }

    public void initViewValues() {

        setBottomTitleNormal();
        beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(R.id.main_content_fram, mainWorkFragment).commit();
        mContent = mainWorkFragment;

    }

    public void initOnclicEvents() {
        main_message_rel.setOnClickListener(this);
        main_contact_rel.setOnClickListener(this);
        main_work_rel.setOnClickListener(this);
        main_home_rel.setOnClickListener(this);
        image_sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_message_rel:
                //办公
                switchContent(mainWorkFragment);

                main_work_img.setImageDrawable(getResources().getDrawable(R.mipmap.home_tab_btn_st_icon1));
                main_work_tv.setTextColor(getResources().getColor(R.color.work_bottomtv2_color));
                break;
            case R.id.main_contact_rel:
                //通讯录
                switchContent(mainContactFragment);

                main_contact_img.setImageDrawable(getResources().getDrawable(R.mipmap.home_tab_btn_st_icon2));
                main_contact_tv.setTextColor(getResources().getColor(R.color.work_bottomtv2_color));
                break;
            case R.id.main_work_rel:
                //消息
                switchContent(mainMessageFragment);

                main_message_img.setImageDrawable(getResources().getDrawable(R.mipmap.home_tab_btn_st_icon3));
                main_message_tv.setTextColor(getResources().getColor(R.color.work_bottomtv2_color));
                break;
            case R.id.main_home_rel:
                //我的
                switchContent(mainHomeFragment);

                main_home_img.setImageDrawable(getResources().getDrawable(R.mipmap.home_tab_btn_st_icon4));
                main_home_tv.setTextColor(getResources().getColor(R.color.work_bottomtv2_color));
                break;

            case R.id.image_sign:
                MySignActivity.startIntent(this);
                break;
        }
    }

    public void switchContent(Fragment fragment) {

        setBottomTitleNormal();
        beginTransaction = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) { // 先判断是否被add过
            beginTransaction.hide(mContent).add(R.id.main_content_fram, fragment).commit();
        } else {
            beginTransaction.hide(mContent).show(fragment).commit();
        }
        mContent = fragment;

    }

    /**
     * 设置底部导航初始状态
     */
    public void setBottomTitleNormal() {
        main_message_img.setImageDrawable(getResources().getDrawable(R.mipmap.home_tab_btn_icon3));
        main_contact_img.setImageDrawable(getResources().getDrawable(R.mipmap.home_tab_btn_icon2));
        main_work_img.setImageDrawable(getResources().getDrawable(R.mipmap.home_tab_btn_icon1));
        main_home_img.setImageDrawable(getResources().getDrawable(R.mipmap.home_tab_btn_icon4));

        main_message_tv.setTextColor(getResources().getColor(R.color.work_bottomtv_color));
        main_contact_tv.setTextColor(getResources().getColor(R.color.work_bottomtv_color));
        main_work_tv.setTextColor(getResources().getColor(R.color.work_bottomtv_color));
        main_home_tv.setTextColor(getResources().getColor(R.color.work_bottomtv_color));
    }

    /**
     * 如果第一次登陆或其他权限登陆，首先判断删除两张数据表数据，然后存储首页功能跟权限功能，存储完成后调取数据
     */
    public void saveMainFunction() {
        for (WorkFunctionEntity entity : FunctionDatas.mainFunctionDatas) {
            workFunctionService.save(entity);
        }
        sharedPrefUtil.putString("ishave", "1");//已经初始过值
        sharedPrefUtil.commit();
    }

    //--------------使用onKeyDown()干掉他--------------

    //记录用户首次点击返回键的时间
    private long firstTime=0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                firstTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
