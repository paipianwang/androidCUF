package com.pangfeng.frame.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.pangfeng.frame.application.FrameApp;

public class FileTools {

	static final int needSize = 20;

	// 文件夹大小
	public static long getFolderSize(java.io.File file) {

		long size = 0;

		if (file == null)
			return 0L;

		java.io.File[] fileList = file.listFiles();

		if (fileList == null)
			return 0L;

		for (int i = 0; i < fileList.length; i++) {

			size += fileList[i].length();
		}
		return size;
	}

	/**
	 * 删除
	 */
	public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
		if (!TextUtils.isEmpty(filePath)) {
			try {
				File file = new File(filePath);
				if (file.isDirectory()) {// 处理目录
					File files[] = file.listFiles();
					for (int i = 0; i < files.length; i++) {
						deleteFolderFile(files[i].getAbsolutePath(), true);
					}
				}
				if (deleteThisPath) {
					if (!file.isDirectory()) {// 如果是文件，删除
						file.delete();
					} else {// 目录
						if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
							file.delete();
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void deleteFile(String path) {
		File f = new File(path);
		if (f.exists()) {
			f.delete();
		}
	}

	public static boolean hasSensor() {

		String service_name = Context.SENSOR_SERVICE;
		SensorManager sensorManager = (SensorManager) FrameApp
				.getApplicationContextInstatnce()
				.getSystemService(service_name);
		List<Sensor> sensors = sensorManager
				.getSensorList(Sensor.TYPE_ACCELEROMETER);

		for (int i = 0; i < sensors.size(); i++) {

			Log.i("slwslt", sensors.get(i) + "");

		}

		return false;
	}

	public static void saveBiamap(Bitmap bitmap, String outputPaht, int quality) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(outputPaht));
			bitmap.compress(Bitmap.CompressFormat.JPEG, quality, os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
