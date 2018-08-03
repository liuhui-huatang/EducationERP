package com.htcompany.educationerpforgansu.commonpart.views.dateview;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.wheelview.ScreenInfo;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.wheelview.WheelMain;

import java.util.Calendar;

/**
 * 时间选择框
 * @author Administrator
 *
 */
public class DateViewDailog extends AlertDialog implements View.OnClickListener{
	    private Handler handler;
	    private int event;
	    private Context context;
	    private TextView  dialog_cancel_order_tv,datedailog_cancle_btn;
	    private Button datedailog_sure_btn;//选择时间
	    WheelMain wheelMain;
		ScreenInfo screenInfo;
		LayoutInflater inflater;
		private LinearLayout timepickerview1;
	    private boolean isHaveTime;//判断是否展示时间
	    public DateViewDailog(Context context,int event,Handler handler,boolean isHaveTime) {
	        super(context);
	        this.context = context;
	        this.event = event;
	        this.handler = handler;
			this.isHaveTime = isHaveTime;
	        // TODO Auto-generated constructor stub
	        inflater = LayoutInflater.from(context);
	    }
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.datedailogview);
	        this.setCanceledOnTouchOutside(true);
	        this.setCancelable(true);
	        initViews();
	        initDatas();
	    }
	    public void initViews(){
	    	datedailog_sure_btn = (Button)this.findViewById(R.id.datedailog_sure_btn);
	    	datedailog_sure_btn.setOnClickListener(this);
	    	datedailog_cancle_btn= (Button) findViewById(R.id.datedailog_cancle_btn);
	    	datedailog_cancle_btn.setOnClickListener(this);
	    }
	    public void initDatas(){
	    	timepickerview1 = (LinearLayout)findViewById(R.id.timePicker1);
	    	screenInfo = new ScreenInfo(context);
			wheelMain = new WheelMain(timepickerview1,isHaveTime);
			wheelMain.screenheight = screenInfo.getHeight();
			Calendar calendar1 = Calendar.getInstance();
			int year1 = calendar1.get(Calendar.YEAR);
			int month1 = calendar1.get(Calendar.MONTH);
			int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
			wheelMain.initDateTimePicker(year1, month1, day1);
	    }
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.datedailog_sure_btn:
				Message msg = new Message();
				msg.what = event;
				msg.obj = wheelMain.getTime();
				handler.sendMessage(msg);
				break;
			case R.id.datedailog_cancle_btn:
				this.dismiss();
				break;
			}
		}
}