package com.pangfeng.frame.plugins.kit;

import java.util.Map;
import java.util.Properties;

import org.xutils.DbManager;
import org.xutils.HttpManager;
import org.xutils.NetUtilsManager;
import org.xutils.x;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.HttpManagerImpl;
import org.xutils.http.RequestParams;

import android.util.Log;

import com.pangfeng.frame.utils.NetTools;

public class NetUtilsKit {

	public static HttpManager httpManager;


	/**
	 * 发送get请求
	 * 
	 * @param <T>
	 */
	public static <T> Cancelable Get(String url, Map<String, String> map,
			CommonCallback<T> callback) {
		RequestParams params = new RequestParams(url);
		if (null != map) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				params.addQueryStringParameter(entry.getKey(), entry.getValue());
			}
		}
		Cancelable cancelable = HttpManagerImpl.instance.get(params, callback);
		return cancelable;
	}

	/**
	 * 发送post请求
	 * 
	 * @param <T>
	 */
	public static <T> Cancelable Post(String url, Map<String, Object> map,
			CommonCallback<T> callback) {
		RequestParams params = new RequestParams(url);
		if (null != map) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				params.addParameter(entry.getKey(), entry.getValue());
			}
		}
		Cancelable cancelable = HttpManagerImpl.instance.get(params, callback);
		return cancelable;
	}

	/**
	 * 上传文件
	 * 
	 * @param <T>
	 */
	public static <T> Cancelable UpLoadFile(String url,
			Map<String, Object> map, CommonCallback<T> callback) {
		RequestParams params = new RequestParams(url);
		if (null != map) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				params.addParameter(entry.getKey(), entry.getValue());
			}
		}
		params.setMultipart(true);
		Cancelable cancelable = HttpManagerImpl.instance.get(params, callback);
		return cancelable;
	}

	/**
	 * 下载文件
	 * 
	 * @param <T>
	 */
	public static <T> Cancelable DownLoadFile(String url, String filepath,
			CommonCallback<T> callback) {
		RequestParams params = new RequestParams(url);
		params.setAutoResume(true);
		params.setSaveFilePath(filepath);
		Cancelable cancelable = HttpManagerImpl.instance.get(params, callback);
		return cancelable;
	}
}
