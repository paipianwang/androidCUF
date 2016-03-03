package in.srain.cube.util.updateapk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

public class HttpClientUtils {
	// mark
	// static public String HostPath = TyuDefine.HOST + "cc/";
	static final String boundary = "-------------------xx_woxnimei_xx";

	public static boolean isValidResult(String aRes) {
		return !TextUtils.isEmpty(aRes) && !aRes.startsWith("error");
	}

	static public interface TyuUploadListener {
		public void onStart(String aUrl);

		public void onError(String aUrl, String aInfo);

		public boolean stopped();

		/**
		 * 文件上传
		 * 
		 * @param aUrl
		 * @param aValue
		 *            字节流变化量
		 */
		public void onProgressChanged(String aUrl, long aValue);

		public void onEnd(String aUrl, String aInfo);
	}
//-1:下载失败 0：文件已经存在 1:下载成功
	public static int downLoadFile(String aUrl, String aLocalPath,
			String notification, DownloadListener listener) {

		try {
			File file = new File(aLocalPath);
			URL url = new URL(aUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int size = conn.getContentLength();
			if(size==file.length()){
				conn.disconnect();
				if (listener != null) {
					listener.onSucess(notification);
				}
				return 0;
			}
			
			if (file.exists()) {
				// return true;
				file.delete();
				// file.createNewFile();
			} else {
				String path = file.getParent();
				new File(path).mkdirs();
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(aLocalPath);
			byte[] buffer = new byte[1024];
			int len = 0;
			int temp = -1;
			int progress = 0;
			int hasRead = 0;
			while ((len = conn.getInputStream().read(buffer)) >= 0) {
				hasRead += len;
				progress = hasRead * 100 / size;
				if (temp != progress) {
					if (listener != null) {
						listener.onProgressChanged(notification, progress);
					}
				}
				temp = progress;
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
			if (listener != null) {
				listener.onSucess(notification);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			if (listener != null) {
				listener.onError(notification);
			}
			return -1;
		}
		return 1;
	}
	static public void writeZipBytes(OutputStream aOut, String aContent)
			throws IOException {
		ZipOutputStream zout = new ZipOutputStream(aOut);
		byte[] resss = aContent.getBytes();
		ZipEntry entry = new ZipEntry("resp");
		entry.setSize(resss.length);
		zout.putNextEntry(entry);
		zout.write(resss);
		zout.flush();
		zout.close();
	}
	static public String readZipBytes(InputStream aInput){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = aInput.read(buffer)) >= 0) {
				// sb.append(new String(buffer, 0, len,"UTF-8"));
				baos.write(buffer, 0, len);
			}
			// String res = new String(baos.toByteArray(), 0, baos.size());
			ZipInputStream zipin = new ZipInputStream(new ByteArrayInputStream(
					baos.toByteArray()));
			int raw_size = baos.size();
			baos.close();
			ZipEntry entry;
			String res = null;
			int content_size = 0;
			while ((entry = zipin.getNextEntry()) != null) {
				Log.v("printResponseGZip", entry.toString());
				baos = new ByteArrayOutputStream();
				while ((len = zipin.read(buffer)) >= 0) {
					baos.write(buffer, 0, len);
				}
				res = new String(baos.toByteArray(), 0, baos.size(),
						ChangeCharset.UTF_8);
				content_size = baos.size();
				baos.close();
				break;
			}
			zipin.close();
			Log.v("printResponseGZip",
					String.format("raw:%d,content:%d", raw_size, content_size));
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
	}
	public static PackageInfo getVersionInfo(Context context) {
		PackageInfo info = null;
		PackageManager pm = context.getPackageManager();
		try {
			String pack = context.getPackageName();
			info = pm.getPackageInfo(pack, PackageManager.GET_ACTIVITIES);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return info;
	}

	static public String getCommonData(Context context) {
		StringBuilder sb = new StringBuilder();
		try {
			TelephonyManager mTelephonyMgr = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imsi = mTelephonyMgr.getSubscriberId();
			if (imsi == null) {
				imsi = "00000";
			}
			sb.append("imsi=" + imsi + "|");

			String imei = mTelephonyMgr.getDeviceId();
			if (imei == null)
				imei = "00000000";
			sb.append("imei=" + imei + "|");
			PackageInfo info = getVersionInfo(context);
			String vcode = info.versionCode + "";
			sb.append("vcode=" + vcode + "|");
			SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyyMMddHHmmss");//
			Date now = new Date();
			String ts = myFmt2.format(now);
			sb.append("ts=" + ts + "|");

			String src = Common.getSrc(context);
			sb.append("src=" + src + "|");
			DisplayMetrics packageMetrics = context.getResources()
					.getDisplayMetrics();
			String screen = packageMetrics.widthPixels + "x"
					+ packageMetrics.heightPixels;
			sb.append("screen=" + screen + "|");
			sb.append("model=" + Common.getPhoneModel() + "|");
			sb.append("brand=" + android.os.Build.BRAND + "|");
			sb.append("display=" + android.os.Build.DISPLAY + "|");
			sb.append("tags=" + android.os.Build.TAGS + "|");
			sb.append("type=" + android.os.Build.TYPE + "|");
			sb.append("version_sdk=" + Common.getPhoneVesion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getComments(String aUrl, int offset, int length) {
		String res = null;
		try {
			String where = "&offset=%d&length=%d";
			URL url = new URL(aUrl + String.format(where, offset, length));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/octet-stream");
			conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			conn.connect();

			System.out.println("statecode:" + conn.getResponseCode()); // 得到服务器端响应码
			System.out.println("Type:" + conn.getContentType()); // 得到网页文件类型

			res = printResponse(conn);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static String printResponse(HttpURLConnection conn) {

		// StringBuilder sb = new StringBuilder();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = conn.getInputStream().read(buffer)) >= 0) {
				// sb.append(new String(buffer, 0, len,"UTF-8"));
				baos.write(buffer, 0, len);
			}
			String res = new String(baos.toByteArray(), 0, baos.size());
			baos.close();
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	public static String simpleGet(String aUrl, Map<String, String> aHeaders) {
		String res = null;
		try {
			URL url = new URL(aUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/octet-stream");
			// conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setConnectTimeout(1000 * 10);
			Set set = aHeaders.keySet();

			for (Iterator iter = set.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = aHeaders.get(key);
				conn.addRequestProperty(key, value);
			}

			conn.connect();

			StringBuilder sb = new StringBuilder();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = conn.getInputStream().read(buffer)) >= 0) {
				sb.append(new String(buffer, 0, len));
			}
			System.out.println(sb.toString());
			res = sb.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.w("simpleGet", "simpleGet failed");
			res = null;
		}
		return res;

	}

	public static String postInfo(String aUrl, String aPostInfo) {
		String res = null;
		try {
			URL url = new URL(aUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-length", ""
					+ aPostInfo.getBytes().length);
			conn.setRequestProperty("Content-Type", "application/octet-stream");
			// conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			String common = getCommonData(ContextKeeper.getInstance());
			conn.setRequestProperty("tyucommon", common);
			conn.connect();
			conn.getOutputStream().write(aPostInfo.getBytes());
			// conn.getOutputStream().write("\r\n\r\n".getBytes());
			// conn.getOutputStream().flush();
			// conn.getOutputStream().close();

			System.out.println("statecode:" + conn.getResponseCode()); // 得到服务器端响应码
			System.out.println("Type:" + conn.getContentType()); // 得到网页文件类型

			res = printResponse(conn);
			conn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static String postZippedInfo(String aUrl,String aPostInfo){
		String res = null;
		try {
			URL url = new URL(aUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-length", ""
//					+ aPostInfo.getBytes().length);
			conn.setChunkedStreamingMode(0);
			conn.setRequestProperty("Content-Type", "application/octet-stream");
			// conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			String common = getCommonData(ContextKeeper.getInstance());
			conn.setRequestProperty("tyucommon", common);
			conn.connect();
//			conn.getOutputStream().write(aPostInfo.getBytes());
			writeZipBytes(conn.getOutputStream(), aPostInfo);
			// conn.getOutputStream().write("\r\n\r\n".getBytes());
			// conn.getOutputStream().flush();
			// conn.getOutputStream().close();

			System.out.println("statecode:" + conn.getResponseCode()); // 得到服务器端响应码
			System.out.println("Type:" + conn.getContentType()); // 得到网页文件类型

			res = printResponse(conn);
			conn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static String postInfoGZip(String aUrl, String aPostInfo) {
		String res = null;
		try {
			URL url = new URL(aUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-length", ""
					+ aPostInfo.getBytes().length);
			conn.setRequestProperty("Content-Type", "application/octet-stream");
			// conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			String common = getCommonData(ContextKeeper.getInstance());
			conn.setRequestProperty("tyucommon", common);
			conn.connect();
			conn.getOutputStream().write(aPostInfo.getBytes());
			// conn.getOutputStream().flush();
			// conn.getOutputStream().close();

			// System.out.println("statecode:" + conn.getResponseCode()); //
			// 得到服务器端响应码
			// System.out.println("Type:" + conn.getContentType()); // 得到网页文件类型

			res = printResponseGZip(conn);
			conn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static String printResponseGZip(HttpURLConnection conn) {

		// StringBuilder sb = new StringBuilder();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = conn.getInputStream().read(buffer)) >= 0) {
				// sb.append(new String(buffer, 0, len,"UTF-8"));
				baos.write(buffer, 0, len);
			}
			// String res = new String(baos.toByteArray(), 0, baos.size());
			ZipInputStream zipin = new ZipInputStream(new ByteArrayInputStream(
					baos.toByteArray()));
			int raw_size = baos.size();
			baos.close();
			ZipEntry entry;
			String res = null;
			int content_size = 0;
			while ((entry = zipin.getNextEntry()) != null) {
				Log.v("printResponseGZip", entry.toString());
				// long length = entry.getSize();
				// zipin.re
				// byte[] tmp_zbuffer = new byte[(int) length+100];
				// len = zipin.read(tmp_zbuffer);
				// res = new String(tmp_zbuffer, 0, len);
				baos = new ByteArrayOutputStream();
				while ((len = zipin.read(buffer)) >= 0) {
					baos.write(buffer, 0, len);
				}
				res = new String(baos.toByteArray(), 0, baos.size(),
						ChangeCharset.GBK);
				content_size = baos.size();
				baos.close();
				break;
			}
			zipin.close();
			Log.v("printResponseGZip",
					String.format("raw:%d,content:%d", raw_size, content_size));
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	public static boolean pingServer(String aUrl) {
		boolean res = false;
		for (int i = 0; i < 3; i++) {
			String tt = postInfo(aUrl, "");
			if (!TextUtils.isEmpty(tt)) {
				res = true;
				break;
			}
		}
		return res;

	}

	public static String postMutiPart(String aUrl,
			Map<String, Object> aPostMap, TyuUploadListener aCallback) {
		String res = null;
		try {
			if (aCallback != null)
				aCallback.onStart(aUrl);
			String BOUNDARY = "xx_caonimei_xx"; // 定义数据分隔线
			URL url = new URL(aUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setChunkedStreamingMode(0);
			// conn.setUseCaches(false);
			conn.setConnectTimeout(1000 * 30);
			conn.setRequestMethod("POST");
			String common = getCommonData(ContextKeeper.getInstance());
			conn.setRequestProperty("tyucommon", common);

			conn.setRequestProperty("Cache-Control", "max-age=0");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			conn.connect();
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			byte[] end_data = ("--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
			Iterator<String> keys = aPostMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = aPostMap.get(key);
				if (value != null) {
					if (value instanceof File) {
						File file = (File) value;
						StringBuilder sb = new StringBuilder();
						sb.append("--");
						sb.append(BOUNDARY);
						sb.append("\r\n");
						sb.append("Content-Disposition: form-data;name=\""
								+ key + "\";" + "filename=\"" + file.getName()
								+ "\"\r\n");
						sb.append("Content-Type:application/octet-stream\r\n\r\n");
						byte[] data = sb.toString().getBytes();
						out.write(data);
						DataInputStream in = new DataInputStream(
								new FileInputStream(file));
						int bytes = 0;
						byte[] bufferOut = new byte[1024];
						while ((bytes = in.read(bufferOut)) != -1) {
							out.write(bufferOut, 0, bytes);

							if (aCallback != null){
								aCallback.onProgressChanged(aUrl, bytes);
								if(aCallback.stopped()){
									throw new Exception("task stopped");
								}
							}
						}
						out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
						in.close();
					} else if (value instanceof String) {
						String val = (String) value;

						StringBuilder sb = new StringBuilder();
						sb.append("--");
						sb.append(BOUNDARY);
						sb.append("\r\n");
						sb.append("Content-Disposition: form-data;name=\""
								+ key + "\"\r\n\r\n");
						byte[] data = sb.toString().getBytes();
						out.write(data);
						out.write(val.getBytes());
						out.write("\r\n".getBytes());
					}
				}
			}

			out.write(end_data);
			out.flush();
			res = printResponse(conn);
			conn.disconnect();
			if (aCallback != null)
				aCallback.onEnd(aUrl, res);
		} catch (Exception e) {
			e.printStackTrace();
			// ErrorReport.outException(e);
			if (aCallback != null)
				aCallback.onError(aUrl, e.toString());
		}
		return res;

	}

	public static String postMutiPartGZip(String aUrl,
			Map<String, Object> aPostMap, TyuUploadListener aCallback) {
		String res = null;
		try {
			if (aCallback != null)
				aCallback.onStart(aUrl);
			String BOUNDARY = "xx_caonimei_xx"; // 定义数据分隔线
			URL url = new URL(aUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setChunkedStreamingMode(0);
			// conn.setUseCaches(false);
			conn.setConnectTimeout(1000 * 30);
			conn.setRequestMethod("POST");
			String common = getCommonData(ContextKeeper.getInstance());
			conn.setRequestProperty("tyucommon", common);

			conn.setRequestProperty("Cache-Control", "max-age=0");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			conn.connect();
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			byte[] end_data = ("--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
			Iterator<String> keys = aPostMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = aPostMap.get(key);
				if (value != null) {
					if (value instanceof File) {
						File file = (File) value;
						StringBuilder sb = new StringBuilder();
						sb.append("--");
						sb.append(BOUNDARY);
						sb.append("\r\n");
						sb.append("Content-Disposition: form-data;name=\""
								+ key + "\";" + "filename=\"" + file.getName()
								+ "\"\r\n");
						sb.append("Content-Type:application/octet-stream\r\n\r\n");
						byte[] data = sb.toString().getBytes();
						out.write(data);
						DataInputStream in = new DataInputStream(
								new FileInputStream(file));
						int bytes = 0;
						byte[] bufferOut = new byte[1024];
						while ((bytes = in.read(bufferOut)) != -1) {
							out.write(bufferOut, 0, bytes);

							if (aCallback != null)
								aCallback.onProgressChanged(aUrl, bytes);
						}
						out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
						in.close();
					} else if (value instanceof String) {
						String val = (String) value;

						StringBuilder sb = new StringBuilder();
						sb.append("--");
						sb.append(BOUNDARY);
						sb.append("\r\n");
						sb.append("Content-Disposition: form-data;name=\""
								+ key + "\"\r\n\r\n");
						byte[] data = sb.toString().getBytes();
						out.write(data);
						out.write(val.getBytes());
						out.write("\r\n".getBytes());
					}
				}
			}

			out.write(end_data);
			out.flush();
			res = printResponseGZip(conn);
			conn.disconnect();
			if (aCallback != null)
				aCallback.onEnd(aUrl, res);
		} catch (Exception e) {
			e.printStackTrace();
			if (aCallback != null)
				aCallback.onError(aUrl, e.toString());
		}
		return res;

	}

	public static String formatFileName(String fileName) {
		if (fileName.equals("content_image.jpg")) {
			fileName = "content_image" + System.currentTimeMillis() + ".jpg";
		} else {
			fileName = "attach" + System.currentTimeMillis() + "_" + fileName;
		}
		return fileName;
	}

	public static void testApi() {
		// String url = "http://192.168.0.103:8080/web1/us/user_share";
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("content", "求交配~");
		// map.put("price", "15");
		// map.put("image", new File("/sdcard/2.jpg"));
		// for (int i = 0; i < 1; i++) {
		// String res = postMutiPart(url, map,null);
		// Log.e("testApi", res+"");
		// }

		String url = "http://192.168.1.102:8080/web1/tp/set_stall_name?stall_id=83&imei=354666055029771";
		postInfo(url, "中文");

	}

}
