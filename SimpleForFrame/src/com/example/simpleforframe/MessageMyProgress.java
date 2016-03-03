package com.example.simpleforframe;

import android.annotation.SuppressLint;
import android.app.Notification.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.RemoteViews;

@SuppressLint("NewApi")
public class MessageMyProgress {

	NotificationManager manager;
	Notification notification;

	public MessageMyProgress(Context context, RemoteViews remoteViews) {

		manager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		notification = new Notification(R.drawable.ic_launcher, "图标边的文字",
				System.currentTimeMillis());
		notification.contentView = new RemoteViews(context.getPackageName(),
				R.layout.progress);
		// 使用notification.xml文件作VIEW
		notification.contentView.setProgressBar(R.id.pb, 100, 50, false);
		manager.notify(0, notification);

	}

	public void updateProgress(int count) {

		notification.contentView.setProgressBar(R.id.pb, 100, 50, false);
		manager.notify(0, notification);
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

	public void finishProgress() {

	}

}
