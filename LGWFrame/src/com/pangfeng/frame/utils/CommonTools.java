package com.pangfeng.frame.utils;

import in.srain.cube.util.updateapk.Common;
import in.srain.cube.util.updateapk.ContextKeeper;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.pangfeng.frame.utils.pool.TaskHandlerThreadPool;

public class CommonTools {

	static public long ts = 0;

	@SuppressLint("SimpleDateFormat")
	public static boolean IsOtherDay(Context ctx) {
		SharedPreferences spf = ctx.getSharedPreferences("data",
				Context.MODE_PRIVATE);
		// 使用getString方法获得value，注意第2个参数是value的默认值
		long time = spf.getLong("time", 0);
		Date now = new Date();
		Date old = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!sdf.format(now).equals(sdf.format(old))) {
			ts = now.getTime();
			SharedPreferences.Editor editor = spf.edit();
			editor.putLong("time", ts);
			editor.commit();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * 获取sd卡路径
	 */
	public static String getSdcardPaht() {
		String sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();// 获取跟目录
		}
		return sdDir;
	}

	/**
	 * 检测版本
	 * 
	 */

	static public void checkNewVersion(final Activity aActivity) {
		final Context context = aActivity;

//		if (Common.isAnotherDay(context)) {
//
//			TaskHandlerThreadPool.getInstance().submit(new Runnable() {
//
//				@Override
//				public void run() {
//
//					final StringBuffer sb = new StringBuffer();
//					boolean res = UpdateManager.checkNewVersion(sb);
//					if (res) {
//						ContextKeeper.doUiTask(new Runnable() {
//							@Override
//							public void run() {
//								try {
//
//									UpdateManager.showUpdateInfo(aActivity,
//											sb.toString());
//
//								} catch (Exception e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//						});
//
//					}
//
//				}
//			});
//		}
	}

}
