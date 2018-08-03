package com.htcompany.educationerpforgansu.workpart.personnelpart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;

/**
 * 惩处撤销
 * Created by wrb on 2016/11/21.
 */
public class PersonPunishmentDetailsManageActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private CheckBox personpunishment_iscx_cb;
    private LinearLayout personpunishment_cxccpart_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personpunishmentdetailsmanage_activity);
        initViews();
        initViewValues();
        initOnclicerEvent();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        rightthree_btn_tv = (TextView)this.findViewById(R.id.rightthree_btn_tv);
        right_three_btn= (RelativeLayout)this.findViewById(R.id.right_three_btn);
        personpunishment_iscx_cb = (CheckBox)this.findViewById(R.id.personpunishment_iscx_cb);
        personpunishment_cxccpart_ll = (LinearLayout)this.findViewById(R.id.personpunishment_cxccpart_ll);
    }
    public void initViewValues(){
        title.setText("惩处详情");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("撤销");
    }
    public void initOnclicerEvent(){
        reback_btn.setOnClickListener(this);
        personpunishment_iscx_cb.setOnCheckedChangeListener(iscxCb);
    }
   public CompoundButton.OnCheckedChangeListener iscxCb = new CompoundButton.OnCheckedChangeListener() {
       @Override
       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
           if (isChecked){
               personpunishment_cxccpart_ll.setVisibility(View.VISIBLE);
           }else{
               personpunishment_cxccpart_ll.setVisibility(View.GONE);
           }
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
}
