package com.htcompany.educationerpforgansu.commonpart.tools;

import android.content.Context;
import android.widget.Toast;

/**  
 * Filename: ToastUtil.java  <br>
 *
 * Description:   <br>
 * 
 * @version: 1.0 <br>
 *
 * @Copyright: Copyright (c)2014 by HLJ <br>
 *  
 */

public class ToastUtil {

//	private static String oldMsg;
//	protected static Toast toast = null;
//	private static long oneTime = 0;
//	private static long twoTime = 0;


	public static void showToast(String things, Context context){
		Toast.makeText(context, things, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, String s) {
		if (toast == null) {
			toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);

			toast.show();
			oneTime = System.currentTimeMillis();
		} else {
			twoTime = System.currentTimeMillis();
			if (s.equals(oldMsg)) {
				if (twoTime - oneTime > Toast.LENGTH_SHORT) {
					toast.show();
				}
			} else {
				oldMsg = s;
				toast.setText(s);
				toast.show();
			}
		}
		oneTime = twoTime;
	}


	private static String oldMsg;
	protected static Toast toast   = null;
	private static long oneTime=0;
	private static long twoTime=0;
	public static void showShortToast(Context mContext, String msg) {
		if(toast==null){
            /*这种写法如果传入Activity的实例进来，将有可能会导致Activvity泄露
            * 因为静态工具类的生存周期*/
//            toast =Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
            /*这样的话，不管传递什么content进来，都只会引用全局唯一的Content，不会产生内存泄露*/
			toast =Toast.makeText(mContext.getApplicationContext(), msg, Toast.LENGTH_SHORT);
			toast.show();
			oneTime=System.currentTimeMillis();
		}else{
			twoTime=System.currentTimeMillis();
			if(msg.equals(oldMsg)){
				if(twoTime-oneTime>Toast.LENGTH_SHORT){
					toast.show();
				}
			}else{
				oldMsg = msg;
				toast.setText(msg);
				toast.show();
			}
		}
		oneTime=twoTime;
	}
}
