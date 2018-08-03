package com.htcompany.educationerpforgansu.commonpart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 版本更新工具类
 * @author Administrator
 *
 */
public class VersionUpdataUtils {
	/**
	 * 服务器读到的版本更新信息
	 */
	private String jsonstr = "";

	private String newVersion = "";
	/**
	 * apk下载路径
	 */
	private String apkURL = "";
	/**
	 * 手机上的旧版本
	 */
	private String localVersion = "";
	/**
	 * apk名称
	 */
	private String filename="";
	private String noticeMessage="";
	private String file_path = Environment.getExternalStorageDirectory()
			+ "/newdocotr/";
     private Context context;
     private SharedPrefUtil sharedPrefUtil;
	public VersionUpdataUtils(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		sharedPrefUtil = new SharedPrefUtil(context, "login");
		getJsonSTr();
	}
	/**
	 * 弹出提示版本更新
	 *
	 * @author: wrb
	 * @Createtime: 2014年08月31日
	 */
	public void alertVersion(final String apkurl,String noticeMsg) {
		final AlertDialog dialog = new AlertDialog.Builder(context)
				.create();
		// 显示对话框
		dialog.show();
		dialog.setCancelable(false);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.show_version_layout, null);
		Button btn_sure = (Button) view.findViewById(R.id.btn_datetime_sure);
		Button btn_cancel = (Button)view
				.findViewById(R.id.btn_datetime_cancel);
		TextView showcontent = (TextView) view.findViewById(R.id.showcontent);
		showcontent.setText(noticeMsg);
		// 确定
		btn_sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
					sharedPrefUtil.clear();
					sharedPrefUtil.commit();
					downFile(apkurl);
				} else {
					Toast.makeText(context, "请先插入SD卡！",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		// 取消
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				MyApp.getInstance().exit();
			}
		});
		dialog.setContentView(view);
	}
	/**
	 * 弹出提示版本更新
	 *
	 * @author: wrb
	 * @Createtime: 2014年08月31日
	 */
	ProgressBar progress_horizonta_pro;
	TextView down_jd_tv;
	private Dialog dialog2;
	public void downProgressVersion() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.downprogeresversion_view, null);
		progress_horizonta_pro = (ProgressBar) view.findViewById(R.id.progress_horizonta_pro);
		down_jd_tv = (TextView) view.findViewById(R.id.down_jd_tv);
	      dialog2 = new Dialog(context);
	      dialog2.setContentView(view);
	      dialog2.getWindow().setBackgroundDrawable(
					new ColorDrawable(Color.TRANSPARENT));//取消对话框黑底色
	      WindowManager windowManager = ((Activity) context).getWindowManager();
	      Display display = windowManager.getDefaultDisplay();
	      WindowManager.LayoutParams lp = dialog2.getWindow().getAttributes();
	      lp.width = (int)(display.getWidth()*0.9f); //设置宽度
	      dialog2.getWindow().setAttributes(lp);
		// 显示对话框
		dialog2.setCancelable(false);
		dialog2.show();
	}

	/**
	 * 下载最新apk
	 *
	 * @author: 苏晓明
	 * @Createtime: 2014年08月31日
	 */
	@SuppressWarnings("unchecked")
	public void downFile(final String apkurl) {
		// TODO Auto-generated method stub
		HttpUtils  httputils = new HttpUtils();
		sharedPrefUtil.clear();
		sharedPrefUtil.commit();
		File f = new File(file_path);
		if (!f.exists()) {
			f.mkdir();
		}
		// apk下载地址
		String downurl = apkurl;
		httputils.download(downurl, file_path +filename, new RequestCallBack<File>() {
			@Override
			public void onSuccess(ResponseInfo<File> arg0) {
				// TODO Auto-generated method stub
				dialog2.dismiss();
				Toast.makeText(context, "文件下载成功！",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(
						Uri.fromFile(new File(file_path+filename)),
						"application/vnd.android.package-archive");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				((Activity) context).startActivityForResult(intent, 1);
			}
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				Toast.makeText(context, "开始下载。。。",
						Toast.LENGTH_SHORT).show();
				downProgressVersion();
			}
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				// TODO Auto-generated method stub
				super.onLoading(total, current, isUploading);
				progress_horizonta_pro.setProgress((int) (current / (float) total * 100));
				down_jd_tv.setText("已下载："
						+ (int) (current / (float) total * 100) + "%");
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "下载失败。。。",
						Toast.LENGTH_SHORT).show();
				dialog2.dismiss();
			}
		});

	}
	/**
	 * 得到本机的版本
	 *
	 * @author: 苏晓明
	 * @Createtime: 2014年08月31日
	 */
	public void getNowVersion() {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (null != info) {
			// 当前应用的版本名称
			String versionName = info.versionName;
			// 当前版本的版本号
			int versionCode = info.versionCode;
			localVersion = versionName;
			// 当前版本的包名
			String packageNames = info.packageName;
		}
	}
	class GetNewVersionRunnable extends Thread {
		@Override
		public void run() {
			Message msg = new Message();
			if (!"".equals(jsonstr)) {// 如果有数据
				JSONObject object;
				try {
					// 1.得到服务器上的最新版本

					object = new JSONObject(jsonstr);
					newVersion = object.getString("versionname");
					apkURL = object.getString("apkurl");
					filename= object.getString("filename");
					noticeMessage =object.getString("noticeMessage");
					// 2.得到手机现在的版本
					getNowVersion();
					if(!newVersion.equals(localVersion)){
						msg.obj = apkURL;
						msg.what = 8001;
					}else{
						msg.what = 8002;
					}
				} catch (JSONException e) {
					e.printStackTrace();
					msg.what = 7001;
				}
			} else {
				msg.what = 7001;
			}
			myHandler.sendMessage(msg);
		}
	}
	/**
	 * 从服务器获取版本号
	 */
	public void getJSON() {
		new Thread() {
			public void run() {
				// 从服务器上得到版本json
				if (BaseUtils.isNetworkAvailable(context)) {
					jsonstr = requestCheckUpdateJson();
					if (jsonstr != null && !"".equals(jsonstr)) {
						new GetNewVersionRunnable().start();
					} else {
						myHandler.sendEmptyMessage(7005);
					}
				} else {
					myHandler.sendEmptyMessage(7002);
				}
			};
		}.start();
	}
	/**
	 * 获取服务器版本号
	 * @return
	 */
	public static String requestCheckUpdateJson() {
		String returnLine = "";
		try {
			String url = InterfaceManager.siteURLIP+"/apk/versonupdate.php";
			System.out.print(url);
			URL my_url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				returnLine += line;
			}
			reader.close();
			connection.disconnect();
			System.out.println("========返回的结果的为========" + returnLine);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return returnLine;
	}
	 /**
	    * 消息接收类
	    */
	   public Handler myHandler = new Handler(){
		   public void handleMessage(Message msg) {
			   switch (msg.what) {
			case 7002:
				ToastUtil.showToast("请链接网络!", context);
				break;
			case 7005:
				ToastUtil.showToast("版本更新检测失败!", context);
				break;
			case 8002:
				System.out.print("已是最新版本");
//				ToastUtil.showToast("已是最新版本!", context);
				break;
			case 8001:
				alertVersion(apkURL,noticeMessage);
				break;
			case 1200:
				jsonstr = (String) msg.obj;
				getJSON();
				break;
			}
		   };
	   };
	   /**
	    * 获取版本更新字符串
	    */
	   public void getJsonSTr(){
//		   jsonstr=requestCheckUpdateJson();
		   getJSON();
	   }
}
