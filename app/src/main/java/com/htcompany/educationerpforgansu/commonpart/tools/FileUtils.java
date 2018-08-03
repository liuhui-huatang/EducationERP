package com.htcompany.educationerpforgansu.commonpart.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

/**
 * Filename: FileUtils.java <br>
 * 
 * Description: <br>
 * 
 * @author: WRB <br>
 * @version: 1.0 <br>
 * @Createtime: 2016-10-8 <br>
 * 
 * @Copyright: Copyright (c)2016 by WRB <br>
 * 打开文件
 */
public class FileUtils {
	
	// 日志工具标签
	private String tag = "FileUtils";
	
	private String SDCARD = null;

	public FileUtils() {
		SDCARD = Environment.getExternalStorageDirectory() + "/";
	}

	/**
	 * 在手机data上创建文件
	 * @param fileName  文件名称
	 * @return
	 * @throws IOException
	 * @author: HLJ  
	 * @Createtime: 2013-2-19
	 */
	public File createDataFile(String fileName) throws IOException {
		Log.i(tag, "<<<<<<<<创建目录>>>>>>>"+fileName);
		File file = new File(fileName);
		file.createNewFile();
		return file;

	}
	
	/**
	 * 在SD卡上创建目录
	 * @param dirName 路径名称
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2013-2-19
	 */
	public File createSDDir(String dirName) {
		File fileDir = new File(SDCARD + dirName);
		fileDir.mkdir();
		return fileDir;
	}
	

	// 创建一个文件；
	public File createSDFile(String fileName) throws IOException {
		File file = new File(SDCARD+fileName);   //注意在这里一定要加上主目录 SDCARD中，才可以，不然会找不到目录 。
		file.createNewFile();
		return file;
	}

	// 判断SD卡上的文件是不是存在；
	public boolean existSDFile(String fileName) {
		File file = new File(SDCARD + fileName);
		return file.exists();
	}

	// 将一个流对象写入SDCARD
	public File write2SDCARDFromInputSteam(String path, String fileName,
			InputStream is) {
		File file = null;
		OutputStream os = null;
		try {
			createSDDir(path);
			file = createSDFile(path + fileName);
			os = new FileOutputStream(file);
			byte[] buffer = new byte[4 * 1024];
			int length;
			while((length=(is.read(buffer))) >0){
				os.write(buffer,0,length);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return file;
	}


	/*******************************************************************************************************
	 * Android里面，要打开各种类型的文件，例如PDF,PPT,WORD,EXCEL,CHM,HTML,TEXT,AUDIO,VIDEO
	 * ，可以通过Intent来办到。 PDF,PPT,WORD,EXCEL,CHM,HTML,TEXT,AUDIO,VIDEO 
	 * 示例: 
	 * Intent it = getTextFileIntent("/sdcard/hello.txt",false); 
	 * startActivity(it);
	 *********************************************************************************************************/

	// 如果没有使用UncaughtExceptionHandler类来处理全局异常，
	// 那么程序将异常退出造成不好的用户体验。
	// 为了防止ActivityNotFoundException错误的出现，在启动Activity之前先判断Intent是否存在
	public static boolean isIntentAvailable(Context context, Intent intent) {
		final PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.GET_ACTIVITIES);
		return list.size() > 0;
	}

	// android获取一个用于打开HTML文件的intent
	public static Intent getHtmlFileIntent(String param) {
		Uri uri = Uri.parse(param).buildUpon()
				.encodedAuthority("com.android.htmlfileprovider")
				.scheme("content").encodedPath(param).build();
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setDataAndType(uri, "text/html");
		return intent;
	}

	// android获取一个用于打开图片文件的intent
	public static Intent getImageFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "image/*");
		return intent;
	}

	// android获取一个用于打开PDF文件的intent
	public static Intent getPdfFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/pdf");
		return intent;
	}

	// android获取一个用于打开文本文件的intent
	public static Intent getTextFileIntent(String param, boolean paramBoolean) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (paramBoolean) {
			Uri uri1 = Uri.parse(param);
			intent.setDataAndType(uri1, "text/plain");
		} else {
			Uri uri2 = Uri.fromFile(new File(param));
			intent.setDataAndType(uri2, "text/plain");
		}
		return intent;
	}

	// android获取一个用于打开音频文件的intent
	public static Intent getAudioFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "audio/*");
		return intent;
	}

	// android获取一个用于打开视频文件的intent
	public static Intent getVideoFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "video/*");
		return intent;
	}

	// android获取一个用于打开CHM文件的intent
	public static Intent getChmFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/x-chm");
		return intent;
	}

	// android获取一个用于打开Word文件的intent
	public static Intent getWordFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/msword");
		return intent;
	}

	// android获取一个用于打开Excel文件的intent
	public static Intent getExcelFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-excel");
		return intent;
	}

	// android获取一个用于打开PPT文件的intent
	public static Intent getPptFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
		return intent;
	}

}
