package com.pangfeng.frame.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class MessageNotifTask{





	public static void showNotification(Context context,int icon,String notificationWord,String contentTitle,String contentText,Class<?> cls){
		
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);

		// 定义Notification的各种属性
		@SuppressWarnings("deprecation")
		Notification notification = new Notification(icon, notificationWord,
				System.currentTimeMillis());

		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		Intent notificationIntent = new Intent(context, cls); // 点击该通知后要跳转的Activity
		PendingIntent contentItent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentItent);
		notificationManager.notify(0, notification);
	}


}
