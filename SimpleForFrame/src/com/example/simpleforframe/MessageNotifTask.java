package com.example.simpleforframe;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MessageNotifTask {

	private static int numMessage = 0;
	private static NotificationManager manager;
	private static Notification.Builder builder;

	public static void showNotification(Context context, int icon,
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
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentItent);
		manager.notify(0, notification);
	}

	public static void showToast(Context context, String word, int showTime,
			boolean hasGravity, int gravity, int x, int y) {

		Toast toast = Toast.makeText(context, word, showTime);
		if (hasGravity)
			toast.setGravity(gravity, x, y);
		toast.show();

	}

	public static void showMyToast(Context context, View view,
			boolean hasGravity, int gravity, int x, int y) {

		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_LONG);
		if (hasGravity)
			toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setView(view);
		toast.show();

	}

	public static void showNotif(Context context, Class<?> cls, int icon,
			String ticker, String title, String text, boolean autoCancel,
			RemoteViews remoteViews) {

		Intent intent = new Intent(context, cls);
		manager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		builder = new Notification.Builder(context);
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

	public static void showProgressNotif(Context context) {

		manager = (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		builder = new Notification.Builder(context);
		builder.setContentTitle("Download").setContentText("下载中...").setSmallIcon(R.drawable.ic_launcher);
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				int count;
//				for (count = 0; count <= 100; count += 5) {
					builder.setProgress(100, 10, false);
					manager.notify(0, builder.build());
//					try {
//						Thread.sleep(200);
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
//				}
//				builder.setContentText("下载完毕");
//				builder.setProgress(0, 0, false);
//				manager.notify(1002, builder.build());
//				manager.cancel(0);
//			}
//		}).start();
	}

}
