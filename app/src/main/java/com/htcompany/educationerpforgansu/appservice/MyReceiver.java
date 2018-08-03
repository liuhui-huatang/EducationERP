package com.htcompany.educationerpforgansu.appservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.JsonUtils;
import com.htcompany.educationerpforgansu.messagepart.entity.MainMessageEntity;
import com.htcompany.educationerpforgansu.workpart.activitys.CommonNoticeActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MyMeetingNewActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MyUseCarsActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.OverWorkFlowDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.WorkFlowActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkCarsUseActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkMeetingActivity;
import com.htcompany.educationerpforgansu.workpart.entities.JpushWorkFlowEntity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeachingInteractionActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";
//	const TYPE_1 = 1; //工作流
//    const TYPE_2 = 2; //办公公告
//    const TYPE_3 = 3; //会议审核
//    const TYPE_4 = 4; //车辆使用审核
//    const TYPE_5 = 5; //教学互动
//	//工作流
//    const PROCESS_PROTYPE_1 = 1; //待办事项 （需要处理的）
//    const PROCESS_PROTYPE_2 = 2; //工作流处理反馈  （我发起的）
//    const PROCESS_PROTYPE_1_TITLE = '有新的工作需要审核';
//    const PROCESS_PROTYPE_2_TITLE = '工作流办理进度';
//	//办公公告
//    const BANGONG_PROTYPE_1 = 1; //部门公告
//    const BANGONG_PROTYPE_2 = 2; //全校公告
//	// 会议审核
//    const HUIYI_PROTYPE_1 = 1; //发起人->审核人
//    const HUIYI_PROTYPE_2 = 2; //审核人->发起人
//    const HUIYI_PROTYPE_3 = 3; //审核通过后给参会人发送通知
//	//车辆使用审核
//    const CARS_PROTYPE_1 = 1; //发起人->审核人
//    const CARS_PROTYPE_2 = 2; //审核人->发起人
//    const CARS_PROTYPE_3 = 3; //审核通过后给司机
//	//教学互动
//    const HUDONG_PROTYPE_1 = 1; //学生提问 老师收到通知
//    const HUDONG_PROTYPE_2 = 2; //老师回答 学生收到通知
   //type:1 工作流类型
	//type:2办公公告
	//type:3 全校公告
	public static String type="";
	public static JpushWorkFlowEntity workFlowEntity=null;
	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
		Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
		
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
           Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
			JSONObject jsonObject;
			try {
				String reciverJson=bundle.getString(JPushInterface.EXTRA_EXTRA);
				if(!"".equals(reciverJson)&&reciverJson!=null){
					jsonObject = new JSONObject(reciverJson);
					type=jsonObject.getString("type");
					workFlowEntity = JsonUtils.getObject(reciverJson,JpushWorkFlowEntity.class);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            if("1".equals(type)){
				//工作流类型
				if(workFlowEntity!=null){
					if("1".equals(workFlowEntity.getProtype())){
						    	//工作流待办事项
        	    Intent i = new Intent(context, WorkFlowActivity.class);
        	    i.putExtras(bundle);
        	    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        	    context.startActivity(i);
					}else if("2".equals(workFlowEntity.getProtype())){
						Intent i = new Intent(context, OverWorkFlowDetailsActivity.class);
						i.putExtras(bundle);
						i.putExtra("flowid",workFlowEntity.getFlow_id());
						i.putExtra("runid",workFlowEntity.getRun_id());
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
						context.startActivity(i);
					}
				}
			}else if("2".equals(type)){
				//办公公告推送
				if(workFlowEntity!=null){
					if("1".equals(workFlowEntity.getProtype())){
						//部门公告
						Intent i = new Intent(context, CommonNoticeActivity.class);
						i.putExtras(bundle);
						i.putExtra("noticetype","1");
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
						context.startActivity(i);
					}else if("2".equals(workFlowEntity.getProtype())){
						//全校公告
						Intent i = new Intent(context, CommonNoticeActivity.class);
						i.putExtras(bundle);
						i.putExtra("noticetype","2");
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
						context.startActivity(i);
					}
					}
			}else if("3".equals(type)){
				//会议审核推送
				if("1".equals(workFlowEntity.getProtype())){
					//调到会议审核界面
					Intent i = new Intent(context, WorkMeetingActivity.class);
					i.putExtras(bundle);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
					context.startActivity(i);
				}else if("2".equals(workFlowEntity.getProtype())){
					//跳到我的会议界面
					Intent i = new Intent(context, MyMeetingNewActivity.class);
					i.putExtras(bundle);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
					context.startActivity(i);
				}else if("3".equals(workFlowEntity.getProtype())){
					//跳到我的会议界面
					Intent i = new Intent(context, MyMeetingNewActivity.class);
					i.putExtras(bundle);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
					context.startActivity(i);
				}

			}else if("4".equals(type)){
				//车辆使用审核推送
				if("1".equals(workFlowEntity.getProtype())){
					//调到车辆审核界面
					Intent i = new Intent(context, WorkCarsUseActivity.class);
					i.putExtras(bundle);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
					context.startActivity(i);
				}else if("2".equals(workFlowEntity.getProtype())){
					//跳到我的车辆界面
					Intent i = new Intent(context, MyUseCarsActivity.class);
					i.putExtras(bundle);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
					context.startActivity(i);
				}
			}else if("5".equals(type)){
				//教学互动推送
				if(workFlowEntity!=null){
					if("1".equals(workFlowEntity.getProtype())){
						//教学互动列表
						Intent i = new Intent(context, TeachingInteractionActivity.class);
						i.putExtras(bundle);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
						context.startActivity(i);
					}else if("2".equals(workFlowEntity.getProtype())){
					}
				}
			}

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        	
        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        	Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
        	Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
					Log.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next().toString();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Log.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (null != extraJson && extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			context.sendBroadcast(msgIntent);
//		}
	}
}
