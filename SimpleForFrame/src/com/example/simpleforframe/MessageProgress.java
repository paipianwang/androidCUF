package com.example.simpleforframe;

import com.example.simpleforframe.R;

import android.annotation.SuppressLint;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Context;

@SuppressLint("NewApi")
public class MessageProgress {

	NotificationManager manager;
	Builder builder;

	public MessageProgress(Context context) {

		manager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		builder = new Builder(context);
		builder.setContentTitle("Download").setContentText("下载中...")
				.setSmallIcon(R.drawable.ic_launcher);
		builder.setProgress(100, 0, false);
		manager.notify(0, builder.build());
	}

	public void updateProgress(int count) {

		builder.setProgress(100, count, false);
		manager.notify(0, builder.build());
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void finishProgress() {

		builder.setContentText("下载完毕");
		builder.setProgress(0, 0, false);
		manager.notify(1002, builder.build());
		manager.cancel(0);

	}

}
