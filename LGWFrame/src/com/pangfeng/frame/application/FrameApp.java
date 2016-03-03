package com.pangfeng.frame.application;

import android.app.Application;
import android.content.Context;

import com.pangfeng.frame.simple.MyConfig;

public class FrameApp extends Application {

	static Context appContext;

	public static boolean VideoShwo = false;

	public static boolean hasinitMediapalyer = false;

	public static boolean needCreate = true;

	@Override
	public void onCreate() {
		super.onCreate();

		appContext = getApplicationContext();

		FrameworkInit fi = new MyConfig();
		fi.init();

		// x.Ext.init(this);

	}

	public static Context getApplicationContextInstatnce() {
		return appContext;
	}

}
