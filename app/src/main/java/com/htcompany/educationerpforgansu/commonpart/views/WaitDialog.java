package com.htcompany.educationerpforgansu.commonpart.views;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;

/**
 * dialog加载框
 */
public class WaitDialog extends Dialog {
	private Context context;
	private String waittext;
	private final int SHOW_TIPS = 0;

	private boolean isScaning = false;
    private int type=0;
	public WaitDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public WaitDialog(Context context, String waittext) {
		super(context, R.style.myDialogTheme);
		this.context = context;
		this.waittext = waittext;
		// TODO Auto-generated constructor stub
	}
	public WaitDialog(Context context, String waittext,int type) {
		super(context, R.style.myDialogTheme);
		this.context = context;
		this.waittext = waittext;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.waitdialog);
		this.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));//取消对话框黑底色
		TextView text = (TextView) findViewById(R.id.pbview_dd);
		text.setText("");
		if(type==1){
			text.setVisibility(View.VISIBLE);
		}
		setCanceledOnTouchOutside(false);
	}

	@Override
	public void dismiss() {
		super.dismiss();

	}
	
}
