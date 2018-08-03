package com.htcompany.educationerpforgansu;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.lidroid.xutils.util.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Request;


/**
 * app下载类
 */
public class AppDownloadUtils {

//    private static String downInfoUrl = "http://61.138.108.36/download/apkinfo.txt";

    static TextView mtvProgress;
    static ProgressBar mprogressBar;
    static LinearLayout mrlUpdateLayout;
//
//    public static void init(TextView tvProgress, ProgressBar progressBar, LinearLayout rlUpdateLayout) {
//        mtvProgress = tvProgress;
//        mprogressBar = progressBar;
//        mrlUpdateLayout = rlUpdateLayout;
//    }

    //    获取线上版本信息
    public static void getApkInfo(final Activity context, String downInfoUrl) {
        HttpTask httpTask = new HttpTask(context);
        httpTask.doGet(downInfoUrl, new HttpTask.SdkHttpListener() {
            @Override
            public void onResponse(String response) {

                LogUtils.d("getApkInfo=>" + response);
                try {
                    org.json.JSONObject jsonObject = new org.json.JSONObject(response);
                    int code = jsonObject.getInt("code");
                    String name = jsonObject.getString("name");
                    String url = jsonObject.getString("url");

                    LogUtils.d("getApkInfo=>getApkNumber" + getApkNumber(context));
                    LogUtils.d("getApkInfo=>code" + code);

                    if (getApkNumber(context) < code) {
                        updateDialog(context, url, name, code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtils.d("getApkInfo=>JSONException" + e.getMessage());
                }

            }

            @Override
            public void onCancelled() {

            }
        });
    }

    /**
     * 弹出版本更新对话框
     */
    public static void updateDialog(final Activity context, final String downApkUrl, final String apkName, final int apkCode) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本更新提示");
        builder.setMessage("最新版本上线了，快点更新吧！");

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {

                //下载新版本
//                mrlUpdateLayout.setVisibility(View.VISIBLE);
//                downloadFile(context, downApkUrl, apkName, apkCode);
                okHttpDownloadFile(context, downApkUrl, apkName, apkCode);
                dialog.dismiss();

            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public static void okHttpDownloadFile(final Activity context, final String downloadUrl, String fileName, int apkNum) {

        OkHttpUtils.get()
                .url(downloadUrl)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName + "_" + apkNum + ".apk") {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "onError :" + e.getMessage());
                    }

                    @Override
                    public void inProgress( float progress, long total, int id) {
                        super.inProgress(progress, total, id);
//                        Log.e("TAG", "inProgress :" + progress + "==" + total);

                        ToastUtil.showShortToast(context,"正在下载最新版本:  "+(int)(progress * 100)+"/100");
//                        context.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                mprogressBar.setProgress((int) progress * 100);
//                            }
//                        });

                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Log.e("TAG", "onResponse :" + response.getAbsolutePath());

                        installApk(context, response.getAbsolutePath());
                    }
                });
    }


    //    下载最新版apk

    public static void downloadFile(final Activity context, final String downloadUrl, String fileName, int apkNum) {

        LogUtils.d("getApkInfo=>downloadFile" + downloadUrl);

        final File saveFile = createFile(fileName, apkNum);
        if (saveFile != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        URL url = new URL(downloadUrl);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        if (null != conn) {
                            conn.setRequestProperty("Connection", "Keep-Alive");
                            conn.setReadTimeout(10000);
                            conn.setRequestMethod("GET");
                            conn.setRequestProperty("Accept-Encoding", "identity");
                            conn.setDoInput(true);
                            conn.connect();

                            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                                final int fileSize = conn.getContentLength();
                                LogUtils.d("getApkInfo=>fileSize" + fileSize);

                                context.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        mtvProgress.setText(0 + "/" + 100);
                                        mprogressBar.setMax(fileSize);
//                                        LogUtils.d("getApkInfo=>fileSize" + fileSize);
                                    }
                                });


                                InputStream is = conn.getInputStream();
                                FileOutputStream fos = new FileOutputStream(saveFile);
                                byte[] buffer = new byte[1024];
                                int i = 0;
                                int downFileSize = 0;
                                while ((i = is.read(buffer)) != -1) {

                                    LogUtils.d("getApkInfo=>i" + i);
                                    fos.write(buffer, 0, i);
                                    downFileSize = downFileSize + i;
                                    LogUtils.d("getApkInfo=>downFileSize" + downFileSize);

                                    final int finalDownFileSize = downFileSize;
                                    context.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mprogressBar.setProgress(finalDownFileSize);
                                        }
                                    });
                                }
                                fos.flush();
                                fos.close();
                                is.close();

//                                context.runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        mrlUpdateLayout.setVisibility(View.GONE);
//                                        installApk(context, saveFile.getPath());
//                                    }
//                                });
                            }
                        }
                    } catch (Exception e) {
                        Log.e("TAG", "downloadFile catch Exception:", e);
                    }
                }
            }).start();
        }
    }


    //    根据名称版本号，创建文件
    public static File createFile(String fileName, int apkNum) {
        try {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {

                File sdcardDir = Environment.getExternalStorageDirectory();
                File saveFile = new File(sdcardDir, fileName + "_" + apkNum + ".apk");
                if (saveFile.exists()) {
                    saveFile.createNewFile();
                }
                return saveFile;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("TAG", "AppFileDownUtils catch Exception:", e);
            return null;
        }
    }


    //    获取当前应用版本号
    public static int getApkNumber(Context context) {

        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionCode;
    }

    /**
     * 安装apk
     */
    public static void installApk(Context context, String path) {
        File apkfile = new File(path);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        context.startActivity(i);

    }


    /**
     * 判断是否需要更新版本
     *
     * @param context
     */
//    public static void isApkUpdate(Activity context) {
//        //判断当前应用版本是否大于本地版本
//        SharedPrefUtil spUtil = new SharedPrefUtil(context, "EducationERP");
//        if (getApkNumber(context) > spUtil.getInt("ApkNum", 0)) {
//
//            //  当前版本大于本地版本，获取线上版本与本地版本对比,线上版本大于本地版本则下载更新
//            getApkInfo(context);
//
////            ToastUtil.show("去下载");
//
//        } else {
//
//            // 当前版本小于本地版本，则提示安装本地版本
////            updateDialog(context);
//        }
//    }

}