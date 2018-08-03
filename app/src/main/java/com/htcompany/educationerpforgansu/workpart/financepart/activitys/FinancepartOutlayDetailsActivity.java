package com.htcompany.educationerpforgansu.workpart.financepart.activitys;

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
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.Finalcepart.FinalcepartInternet;
import com.htcompany.educationerpforgansu.internet.Finalcepart.FinalcepartPersener;
import com.htcompany.educationerpforgansu.workpart.financepart.entitys.OutlayEnity;

/**
 * 支出管理详情
 * Created by wrb on 2016/11/17.
 */
public class FinancepartOutlayDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView outlaydetails_money_tv,outlaydetails_type_tv,outlaydetails_time_tv,
            outlaydetails_name_tv,outlaydetails_code_tv,outlaydetails_term_tv,
            outlaydetails_jg_tv,outlaydetails_gw_tv,outlaydetails_agree_tv,outlaydetails_not_tv;
    private TextView outlaydetails_remark_edt;
    private EditText outlaydetails_reson_edt;
    private LinearLayout outlaydetails_caozuo_ll;
    private TextView outlaydetails_status_tv;
    private OutlayEnity entity;
    //网络数据请求类
    private FinalcepartInternet finalcepartInternet;
    private FinalcepartPersener finalcepartPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financepartoutlaydetails_activity);
        intiDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void intiDatas(){
        entity = (OutlayEnity)getIntent().getSerializableExtra("entity");
        finalcepartInternet = new FinalcepartInternet(this,myHandler);
        finalcepartPersener = new FinalcepartPersener(this,myHandler);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        outlaydetails_money_tv= (TextView)this.findViewById(R.id.outlaydetails_money_tv);
                outlaydetails_type_tv= (TextView)this.findViewById(R.id.outlaydetails_type_tv);
        outlaydetails_time_tv= (TextView)this.findViewById(R.id.outlaydetails_time_tv);
                outlaydetails_name_tv= (TextView)this.findViewById(R.id.outlaydetails_name_tv);
                outlaydetails_code_tv= (TextView)this.findViewById(R.id.outlaydetails_code_tv);
                outlaydetails_term_tv= (TextView)this.findViewById(R.id.outlaydetails_term_tv);
                outlaydetails_jg_tv= (TextView)this.findViewById(R.id.outlaydetails_jg_tv);
                outlaydetails_gw_tv= (TextView)this.findViewById(R.id.outlaydetails_gw_tv);
                outlaydetails_agree_tv= (TextView)this.findViewById(R.id.outlaydetails_agree_tv);
                outlaydetails_not_tv= (TextView)this.findViewById(R.id.outlaydetails_not_tv);
        outlaydetails_reson_edt = (EditText)this.findViewById(R.id.outlaydetails_reson_edt);
        outlaydetails_caozuo_ll = (LinearLayout)this.findViewById(R.id.outlaydetails_caozuo_ll);
        outlaydetails_status_tv = (TextView)this.findViewById(R.id.outlaydetails_status_tv);
        outlaydetails_remark_edt=(TextView)this.findViewById(R.id.outlaydetails_remark_edt);
    }
    public void initViewValues(){
        title.setText("支出管理详情");
        if(entity!=null){
            outlaydetails_money_tv.setText(entity.getMoney());
            outlaydetails_type_tv.setText(entity.getType());
            outlaydetails_time_tv.setText(entity.getTime());
            outlaydetails_name_tv.setText(entity.getTeacherName());
            outlaydetails_code_tv.setText(entity.getNumber());
            outlaydetails_term_tv.setText(entity.getXueqi());
            outlaydetails_jg_tv.setText(entity.getJigou());
            outlaydetails_gw_tv.setText(entity.getGangwei());
            outlaydetails_remark_edt.setText(entity.getNotes());
//            outlaydetails_remark_edt
            if("1".equals(entity.getStatus())){
                outlaydetails_status_tv.setText("未审核");
                outlaydetails_caozuo_ll.setVisibility(View.VISIBLE);
                outlaydetails_reson_edt.setEnabled(true);
            }else if("2".equals(entity.getStatus())){
                //审核通过
                outlaydetails_reson_edt.setEnabled(false);
                outlaydetails_status_tv.setText("审核通过");
                outlaydetails_caozuo_ll.setVisibility(View.GONE);
            }else if("3".equals(entity.getStatus())){
                outlaydetails_status_tv.setText("审核不通过");
                outlaydetails_reson_edt.setEnabled(false);
                outlaydetails_caozuo_ll.setVisibility(View.GONE);
                outlaydetails_reson_edt.setText(entity.getReason());
            }
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        outlaydetails_agree_tv.setOnClickListener(this);
        outlaydetails_not_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.outlaydetails_agree_tv:
                waitDialog.show();
                finalcepartInternet.getOutlayAgreeDatas(entity.getId());
                break;
            case R.id.outlaydetails_not_tv:

                if("".equals(outlaydetails_reson_edt.getText().toString().trim())){
                    ToastUtil.showToast("请输入拒绝原因",this);
                }else{
                    waitDialog.show();
                    finalcepartInternet.getOutlayNotAgreeDatas(entity.getId(),outlaydetails_reson_edt.getText().toString().trim());
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
                    ToastUtil.showToast("连接超时",FinancepartOutlayDetailsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(finalcepartPersener.parserOutlySH((String)msg.obj)){
                        ToastUtil.showToast("提交成功",FinancepartOutlayDetailsActivity.this);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新提交",FinancepartOutlayDetailsActivity.this);
                    }
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(finalcepartPersener.parserOutlySH((String)msg.obj)){
                        ToastUtil.showToast("提交成功",FinancepartOutlayDetailsActivity.this);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新提交",FinancepartOutlayDetailsActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",FinancepartOutlayDetailsActivity.this);
                    break;
            }
        }
    };
}
