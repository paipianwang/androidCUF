package com.pangfeng.frame.plugins.kit;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import android.content.Context;

import com.tencent.stat.StatService;

public class Analysiskit {

	public static void AnalysisNoValue(Context ctx, String eventKey) {

		StatService.trackCustomEvent(ctx, eventKey, "");
	}

	public static void AnalysisSingle(Context ctx, String eventKey,
			String propertyKey, int propertyValue) {
		Properties prop = new Properties();
		prop.setProperty(propertyKey, propertyValue + "");
		StatService.trackCustomKVEvent(ctx, eventKey, prop);
	}

	public void AnalysisMap(Context ctx, String eventKey,
			Map<String, String> map) {

		Properties prop = new Properties();
		@SuppressWarnings("rawtypes")
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Entry entry = (Entry) it.next();
			Object key = entry.getKey(); // 返回与此项对应的键
			Object value = entry.getValue(); // 返回与此项对应的值
			prop.setProperty(key + "", value + "");
		}

		StatService.trackCustomKVEvent(ctx, eventKey, prop);
	}
}
