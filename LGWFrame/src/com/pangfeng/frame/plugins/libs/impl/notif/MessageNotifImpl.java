package com.pangfeng.frame.plugins.libs.impl.notif;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.pangfeng.frame.plugins.libs.MessageNotifManager;

public class MessageNotifImpl implements MessageNotifManager {

	private static int numMessage = 0;
	private static NotificationManager manager;
	private static Notification.Builder builder;
	private static final Object lock = new Object();
	public static MessageNotifImpl instance;


	public static MessageNotifImpl registerInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new MessageNotifImpl();
					return instance;
				}
			}
		}
		return instance;
	}

	public void showNotification(Context context, int icon,
			String notificationWord, String contentTitle, String contentText,
			Class<?> cls) {

		manager = (NotificationManager) context
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
		notification.setLatestEventInfo(context, contentTitle, contentText,contentItent);
		manager.notify(0, notification);
	}

	public void showToast(Context context, String word, int showTime,
			boolean hasGravity, int gravity, int x, int y) {

		Toast toast = Toast.makeText(context, word, showTime);
		if (hasGravity)
			toast.setGravity(gravity, x, y);
		toast.show();

	}

	public void showMyToast(Context context, View view,
			boolean hasGravity, int gravity, int x, int y) {

		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_LONG);
		if (hasGravity)
			toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setView(view);
		toast.show();

	}

	@SuppressLint("NewApi")
	public void showMyNotification(Context context, Class<?> cls, int icon,
			String ticker, String title, String text, boolean autoCancel,
			RemoteViews remoteViews) {

		Intent intent = new Intent(context, cls);
		manager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		builder = new Builder(context);
		PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
		builder.setContentIntent(pi);
		builder.setTicker(ticker);
		builder.setDefaults(Notification.DEFAULT_ALL);
		builder.setContentTitle(title);
		builder.setContentText(text).setNumber(++numMessage);
		builder.setSmallIcon(icon);
		builder.setContent(remoteViews);
		builder.setAutoCancel(autoCancel);
		long[] vibrate = { 0, 100, 200, 300 };

		Notification notification = builder.build();// 仅限4.1版本以上使用
		notification.vibrate = vibrate;
		notification.ledARGB = 0xff00ff00;
		notification.ledOnMS = 300;
		notification.ledOffMS = 1000;
		notification.flags = Notification.FLAG_SHOW_LIGHTS;
		manager.notify(1000, notification);

	}

	

}
