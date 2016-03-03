package com.pangfeng.frame.simple;
//package com.lt.ltframe.simple;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.xutils.x;
//
//import android.app.Activity;
//import android.app.DownloadManager;
//import android.os.Bundle;
//
//import com.lt.ltframe.plugins.kit.http.MyCallBack;
//import com.lt.ltframe.plugins.kit.http.MyProgressCallBack;
//
//public class SimpleNet extends Activity {
//
//	private DownloadManager downloadManager;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//
//	}
//
//	// 下载有进度条信息
//
////	private void getDownLoadProgress() {
////
////		String url = "";
////		// 文件保存在本地的路径
////		String filepath = "";
////
////		x.netUtils().DownLoadFile(url, filepath,
////				new MyProgressCallBack<File>() {
////
////					public void onSuccess(File result) {
////						super.onSuccess(result);
////					}
////
////					public void onError(Throwable ex, boolean isOnCallback) {
////						super.onError(ex, isOnCallback);
////					}
////
////					public void onLoading(long total, long current,
////							boolean isDownloading) {
////						super.onLoading(total, current, isDownloading);
////					}
////
////				});
////
////	}
//
//	// 下载无进度条信息
//	private void getDownLoad() {
//
//		String url = "";
//		// 文件保存在本地的路径
//		String filepath = "";
//
//		x.netUtils().DownLoadFile(url, filepath, new MyCallBack<File>() {
//
//			public void onSuccess(File result) {
//				super.onSuccess(result);
//			}
//
//			public void onError(Throwable ex, boolean isOnCallback) {
//				super.onError(ex, isOnCallback);
//			}
//
//		});
//
//	}
//
//	/**
//	 * 上传文件(支持多文件上传)
//	 */
//	private void uploadfile() {
//		// 图片上传地址
//		String url = "";
//		Map<String, Object> map = new HashMap<>();
//		// value 可传入string,file,list等
//		// 传入自己的相应参数
//		// map.put(key, value);
//		// map.put(key, value);
//		x.netUtils().UpLoadFile(url, map, new MyCallBack<String>() {
//
//			@Override
//			public void onSuccess(String result) {
//				super.onSuccess(result);
//			}
//
//			@Override
//			public void onError(Throwable ex, boolean isOnCallback) {
//				super.onError(ex, isOnCallback);
//			}
//
//		});
//
//	}
//
//	private void GET() {
//
//		String url = "http://api.k780.com:88/?app=idcard.get";
//		Map<String, String> map = new HashMap<>();
//		map.put("appkey", "10003");
//		map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
//		map.put("format", "json");
//		map.put("idcard", "110101199001011114");
//		x.netUtils().Get(url, map, new MyCallBack<SimpleInfoBean>() {
//
//			@Override
//			public void onSuccess(SimpleInfoBean result) {
//				super.onSuccess(result);
//			}
//
//			@Override
//			public void onError(Throwable ex, boolean isOnCallback) {
//				super.onError(ex, isOnCallback);
//
//			}
//
//		});
//
//	}
//
//	private void POST() {
//		String url = "http://api.k780.com:88/?app=idcard.get";
//		Map<String, Object> map = new HashMap<>();
//		map.put("appkey", "10003");
//		map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
//		map.put("format", "json");
//		map.put("idcard", "110101199001011114");
//		x.netUtils().Post(url, map, new MyCallBack<SimpleInfoBean>() {
//
//			@Override
//			public void onSuccess(SimpleInfoBean result) {
//				super.onSuccess(result);
//			}
//
//			@Override
//			public void onError(Throwable ex, boolean isOnCallback) {
//				super.onError(ex, isOnCallback);
//			}
//		});
//	}
//
//}
