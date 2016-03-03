package com.pangfeng.frame.plugins.libs;

import android.content.Context;
import android.view.View;
import android.widget.RemoteViews;

public interface MessageNotifManager {

	void showNotification(Context context, int icon,String notificationWord, String contentTitle, String contentText,Class<?> cls);
	void showToast(Context context, String word, int showTime,boolean hasGravity, int gravity, int x, int y);
	void showMyToast(Context context, View view,boolean hasGravity, int gravity, int x, int y);
	void showMyNotification(Context context, Class<?> cls, int icon,String ticker, String title, String text, boolean autoCancel,RemoteViews remoteViews);
	
}


