package com.htcompany.educationerpforgansu.commonpart.views.photoview;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.htcompany.educationerpforgansu.R;


/**
 * 拍照的悬浮窗口
 * @author Administrator
 *
 */
public class GetPhotoPopwindow extends PopupWindow {
	private Context context;
	private LayoutInflater inflater;
	private Handler handler;
	private View view;
	private LinearLayout ll_popup;
   public GetPhotoPopwindow(Context context, Handler handler){
	   this.context = context;
	   this.handler = handler;
	   inflater = LayoutInflater.from(context);
	   view = inflater.inflate(R.layout.photo_popupwindows, null);
	   initView();
	   this.setWidth(LayoutParams.MATCH_PARENT);
	   this.setHeight(LayoutParams.WRAP_CONTENT);
	   this.setBackgroundDrawable(new BitmapDrawable());
	 //设置SelectPicPopupWindow弹出窗体动画效果
       this.setAnimationStyle(R.style.AnimBottom);
	   this.setFocusable(true);
	   this.setOutsideTouchable(true);
	   this.setContentView(view);
   }
   public void initView(){
	   RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view
				.findViewById(R.id.item_popupwindows_camera);
		Button bt2 = (Button) view
				.findViewById(R.id.item_popupwindows_Photo);
		Button bt3 = (Button) view
				.findViewById(R.id.item_popupwindows_cancel);
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handler.sendEmptyMessage(10000);
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handler.sendEmptyMessage(20000);
	         
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				GetPhotoPopwindow.this.dismiss();
			}
		});
   }
   /**
    * 从底部展示
    * @param parent
    */
   public void showPopupWindowBottom(View parent) {
       if (!this.isShowing()) {  
       	 this.showAtLocation(
       			 parent,
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
       } else {  
           this.dismiss();  
       }  
   }
	
}
