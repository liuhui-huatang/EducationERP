package com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkInternet;
import com.htcompany.educationerpforgansu.internet.commonworkpart.CommonWorkPersener;
import com.htcompany.educationerpforgansu.workpart.activitys.AllPartsActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.adapters.WorkNoticePartMDAdapter;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;
import com.htcompany.educationerpforgansu.workpart.entities.AllPartEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加办公公告
 * Created by wrb on 2016/11/22.
 */
public class WorkNoticesAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    //全校公告，部门公告
    private RelativeLayout gamedetails_csdw_rel,gamedetails_hddw_rel;
    private TextView gamedetails_csdw_tv,gamedetails_hddw_tv;
    private TextView gamedetails_csdwline_tv,gamedetails_hddwline_tv;
    private int flag=0;
    //部门公告时才显示,公告发布部门
    private LinearLayout worknoticeadd_jg_ll,worknoticeadd_jgxz_ll;
    private GDGridView worknoticeadd_bmmd_grd;//部门名单
    private WorkNoticePartMDAdapter partMDAdapter;
    private List<AllPartEntity> ggPartDatas;
    //公告标题，公告内容
    private EditText worknoticeadd_title_edt,worknoticeadd_cotent_edt;
    private TextView worknoticeadd_fbtime_tv;

    private SharedPrefUtil sharedPrefUtil=null;
    private String noticeType="2";//公告类型,1部门，2全校
    private WorkNoticeEntity noticeEntity=null;//公告实体类
    private String partId="";//部门id
    private DateViewDailog dateViewDailog;//时间选择框
    //网络请求类
    private CommonWorkInternet workInternet;
    private CommonWorkPersener workPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worknoticesadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        sharedPrefUtil = new SharedPrefUtil(this,"login");
        ggPartDatas =new ArrayList<AllPartEntity>();
        workInternet = new CommonWorkInternet(this,myHandler);
        workPersener = new CommonWorkPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);

        gamedetails_csdw_rel= (RelativeLayout)this.findViewById(R.id.gamedetails_csdw_rel);
        gamedetails_hddw_rel= (RelativeLayout)this.findViewById(R.id.gamedetails_hddw_rel);
        gamedetails_csdw_tv = (TextView)this.findViewById(R.id.gamedetails_csdw_tv);
        gamedetails_hddw_tv = (TextView)this.findViewById(R.id.gamedetails_hddw_tv);
        gamedetails_csdwline_tv= (TextView)this.findViewById(R.id.gamedetails_csdwline_tv);
        gamedetails_hddwline_tv= (TextView)this.findViewById(R.id.gamedetails_hddwline_tv);
        worknoticeadd_jg_ll = (LinearLayout)this.findViewById(R.id.worknoticeadd_jg_ll);

        worknoticeadd_title_edt=(EditText)this.findViewById(R.id.worknoticeadd_title_edt);
        worknoticeadd_cotent_edt=(EditText)this.findViewById(R.id.worknoticeadd_cotent_edt);
        worknoticeadd_fbtime_tv= (TextView)this.findViewById(R.id.worknoticeadd_fbtime_tv);
        worknoticeadd_jgxz_ll=(LinearLayout)this.findViewById(R.id.worknoticeadd_jgxz_ll);
        worknoticeadd_bmmd_grd = (GDGridView)this.findViewById(R.id.worknoticeadd_bmmd_grd);
        partMDAdapter = new WorkNoticePartMDAdapter(this,ggPartDatas);
        worknoticeadd_bmmd_grd.setAdapter(partMDAdapter);

    }

    public void initViewValues() {
        title.setText("添加公告");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("提交");
        setViewSelect(flag);
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        gamedetails_csdw_rel.setOnClickListener(this);
        gamedetails_hddw_rel.setOnClickListener(this);

        worknoticeadd_fbtime_tv.setOnClickListener(this);
        worknoticeadd_jgxz_ll.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.gamedetails_csdw_rel:
                flag=0;
                noticeType="2";
                setViewSelect(flag);
                break;
            case R.id.gamedetails_hddw_rel:
                flag=1;
                noticeType="1";
                setViewSelect(flag);
                break;
            case R.id.right_three_btn:
                //提交公告信息
                if("".equals(worknoticeadd_title_edt.getText().toString())){
                    ToastUtil.showToast("请输入公告标题",WorkNoticesAddActivity.this);
                }else if("".equals(worknoticeadd_cotent_edt.getText().toString())){
                    ToastUtil.showToast("请输入正文",WorkNoticesAddActivity.this);
                }else{
                    if("1".equals(noticeType)){
                        //部门
                        if(ggPartDatas.size()==0){
                            ToastUtil.showToast("请选择部门",WorkNoticesAddActivity.this);
                        }else{
                            waitDialog.show();
                            workInternet.addWorkNoticeDatas(worknoticeadd_title_edt.getText().toString(), BaseUtils.getSystemDate(),
                                    worknoticeadd_cotent_edt.getText().toString(),noticeType, partId);
                        }
                    }else if("2".equals(noticeType)){
                        //全校
                        waitDialog.show();
                        workInternet.addWorkNoticeDatas(worknoticeadd_title_edt.getText().toString(),BaseUtils.getSystemDate(),
                                worknoticeadd_cotent_edt.getText().toString(),noticeType, partId);
                    }
                }
                break;
            case R.id.worknoticeadd_fbtime_tv:
                dateViewDailog = new DateViewDailog(WorkNoticesAddActivity.this,1000,myHandler,false);
                dateViewDailog.show();
                break;
            case R.id.worknoticeadd_jgxz_ll:
                //选择部门
                Intent intent = new Intent(WorkNoticesAddActivity.this, AllPartsActivity.class);
                startActivityForResult(intent,101);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (requestCode){
                case 101:
                    //会议参会人员
                    List<AllPartEntity> datas = (List<AllPartEntity>) data.getSerializableExtra("partdatas");
                    if(datas!=null&&datas.size()>0){
                        if(ggPartDatas.size()>0){
                            ggPartDatas.clear();
                        }
                        for(AllPartEntity personEntity:datas){
                            if("".equals(partId)){
                                partId = personEntity.getId();
                            }else{
                                partId=partId+","+personEntity.getId();
                            }
                            ggPartDatas.add(personEntity);
                        }

                        partMDAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

  public Handler myHandler = new Handler(){
      @Override
      public void handleMessage(Message msg) {
          super.handleMessage(msg);
          switch (msg.what){
              case 1000:
                  //发布时间
                  worknoticeadd_fbtime_tv.setText((String)msg.obj);
                  dateViewDailog.dismiss();
                  break;
              case 400:
                  if(waitDialog!=null){
                      waitDialog.dismiss();
                  }
                  ToastUtil.showToast("连接超时",WorkNoticesAddActivity.this);
                  break;
              case 200:
                  //添加办公公告
                  if(waitDialog!=null){
                      waitDialog.dismiss();
                  }
                  if(workPersener.parserDeleteWorkNotice((String)msg.obj)){
                     noticeEntity = new WorkNoticeEntity();
                    noticeEntity.setType(noticeType);
                    noticeEntity.setB_title(worknoticeadd_title_edt.getText().toString());
                    noticeEntity.setUsername(sharedPrefUtil.getString("username",null));
                    noticeEntity.setB_send_date(worknoticeadd_fbtime_tv.getText().toString());
                    noticeEntity.setB_content(worknoticeadd_cotent_edt.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("noticeEntity",noticeEntity);
                    setResult(101,intent);
                    finish();
                  }
                  break;
              case 300:
                  if(waitDialog!=null){
                      waitDialog.dismiss();
                  }
                  ToastUtil.showToast("数据异常",WorkNoticesAddActivity.this);
                  break;
          }
      }
  };
    public void setViewNormal() {
        gamedetails_csdw_tv.setTextColor(getResources().getColor(R.color.bggg_details_title2_color));
        gamedetails_hddw_tv.setTextColor(getResources().getColor(R.color.bggg_details_title2_color));

        gamedetails_csdwline_tv.setVisibility(View.GONE);
        gamedetails_hddwline_tv.setVisibility(View.GONE);
        worknoticeadd_jg_ll.setVisibility(View.GONE);
    }

    public void setViewSelect(int index) {
        setViewNormal();
        switch (index) {
            case 0:

                gamedetails_csdw_tv.setTextColor(getResources().getColor(R.color.bggg_details_title_color));
                gamedetails_csdwline_tv.setVisibility(View.VISIBLE);
                worknoticeadd_jg_ll.setVisibility(View.GONE);
                break;

            case 1:
                gamedetails_hddw_tv.setTextColor(getResources().getColor(
                        R.color.bggg_details_title_color));
                gamedetails_hddwline_tv.setVisibility(View.VISIBLE);
                worknoticeadd_jg_ll.setVisibility(View.VISIBLE);
                break;
        }

    }
}
