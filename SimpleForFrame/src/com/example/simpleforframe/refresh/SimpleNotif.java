package com.example.simpleforframe.refresh;

import com.example.simpleforframe.Main2Activity;
import com.example.simpleforframe.R;
import com.pangfeng.frame.plugins.libs.impl.notif.MessageNotifImpl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleNotif extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Context context = this;

		View view = LayoutInflater.from(this).inflate(R.layout.custom_toast,
				null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imaget);
		imageView.setImageResource(R.drawable.ic_launcher);
		TextView textViewTitle = (TextView) view.findViewById(R.id.titiet);
		textViewTitle.setText("这里是标题");
		TextView textViewText = (TextView) view.findViewById(R.id.textt);
		textViewText.setText("这里是内容");
		MessageNotifImpl.instance.showMyToast(context, view, false,
				Gravity.CENTER, 0, 0);

		MessageNotifImpl.instance.showToast(context, "word", Toast.LENGTH_LONG,
				true, Gravity.CENTER, 0, 0);

		MessageNotifImpl.instance.showNotification(context,
				R.drawable.ic_launcher, "word", "content", "title",
				Main2Activity.class);

		RemoteViews rv = new RemoteViews(getPackageName(),
				R.layout.custom_notification);
		rv.setImageViewResource(R.id.image, R.drawable.ic_launcher);
		rv.setTextViewText(R.id.titie, "这是标题");
		rv.setTextViewText(R.id.text, "这里是内容");
		MessageNotifImpl.instance.showMyNotification(context,
				Main2Activity.class, R.drawable.ic_launcher, "ticker", "title",
				"text", true, rv);

	}

}
