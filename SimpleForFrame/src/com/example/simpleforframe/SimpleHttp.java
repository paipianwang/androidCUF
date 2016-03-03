package com.example.simpleforframe;

import java.io.File;
import java.util.Map;

import org.xutils.ex.DbException;

import android.app.Activity;
import android.content.Entity;
import android.os.Bundle;

import com.pangfeng.frame.plugins.kit.DBKit;
import com.pangfeng.frame.plugins.kit.NetUtilsKit;
import com.pangfeng.frame.plugins.kit.http.HttpInfoBean;
import com.pangfeng.frame.plugins.kit.http.MyCallBack;
import com.pangfeng.frame.plugins.kit.http.MyProgressCallBack;

public class SimpleHttp extends Activity {

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Map map = null;
		Entity entity = null;

		NetUtilsKit.DownLoadFile("url", "filepath", new MyCallBack<File>());
		// 显示进度
		NetUtilsKit.DownLoadFile("url", "filepath",
				new MyProgressCallBack<File>() {
					public void onSuccess(File result) {
						super.onSuccess(result);
					}

					public void onError(Throwable ex, boolean isOnCallback) {
						super.onError(ex, isOnCallback);
					}

					public void onLoading(long total, long current,
							boolean isDownloading) {
						super.onLoading(total, current, isDownloading);
					}
				});

		NetUtilsKit.Get("url", map, new MyCallBack<HttpInfoBean>() {
			@Override
			public void onSuccess(HttpInfoBean result) {
				super.onSuccess(result);
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);

			}

		});

		NetUtilsKit.Post("url", map, new MyCallBack<HttpInfoBean>() {

			@Override
			public void onSuccess(HttpInfoBean result) {
				super.onSuccess(result);
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);

			}

		});

		/*
		 * 如果想使用DB原本的方法 DBKit.mDBClient.method();
		 */

		try {
			DBKit.mDBClient.dropDb();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
