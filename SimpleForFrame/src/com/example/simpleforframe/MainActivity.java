package com.example.simpleforframe;

import com.pangfeng.frame.plugins.libs.impl.notif.MessageNotifImpl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
	Button bt;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
//		
//		
//		
//		MessageNotifImpl.instance.showNotification(context, R.drawable.ic_launcher, "notificationWord", "contentTitle", "contentText", MainActivity.class);
//		
//		
//		RemoteViews rv = new RemoteViews(getPackageName(),R.layout.custom_notification);
//		rv.setImageViewResource(R.id.image, R.drawable.ic_launcher);
//		rv.setTextViewText(R.id.titie, "这是标题");
//		rv.setTextViewText(R.id.text, "这里是内容");
//		MessageNotifImpl.instance.showMyNotification(context,  MainActivity.class,R.drawable.ic_launcher, "notificationWord", "contentTitle", "contentText", false, rv);
//		
		
		

	}

}
