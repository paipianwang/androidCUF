package org.xutils;

import java.util.Map;

import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;

public interface NetUtilsManager {

	<T> Cancelable Get(String url, Map<String, String> map,
			CommonCallback<T> callback);

	<T> Cancelable Post(String url, Map<String, Object> map,
			CommonCallback<T> callback);

	<T> Cancelable UpLoadFile(String url, Map<String, Object> map,
			CommonCallback<T> callback);

	<T> Cancelable DownLoadFile(String url, String filepath,
			CommonCallback<T> callback);
  }
