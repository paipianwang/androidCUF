package org.xutils;

import java.lang.reflect.Method;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.xutils.common.TaskController;
import org.xutils.common.task.TaskControllerImpl;
import org.xutils.db.DbManagerImpl;
import org.xutils.http.HttpManagerImpl;
import org.xutils.view.ViewInjectorImpl;

import android.app.Application;
import android.content.Context;

import com.pangfeng.frame.plugins.kit.MediaPlayerKit;

/**
 * Created by wyouflf on 15/6/10. 任务控制中心, http, image, db, view注入等接口的入口.
 * 需要在在application的onCreate中初始化: x.Ext.init(this);
 */
public final class x {

	private x() {
	}

	public static boolean isDebug() {
		return Ext.debug;
	}

	public static Application app() {
		if (Ext.app == null) {
			try {
				// 在IDE进行布局预览时使用
				Class<?> renderActionClass = Class
						.forName("com.android.layoutlib.bridge.impl.RenderAction");
				Method method = renderActionClass
						.getDeclaredMethod("getCurrentContext");
				Context context = (Context) method.invoke(null);
				Ext.app = new MockApplication(context);
			} catch (Throwable ignored) {
				throw new RuntimeException(
						"please invoke x.Ext.init(app) on Application#onCreate()"
								+ " and register your Application in manifest.");
			}
		}
		return Ext.app;
	}

	public static TaskController task() {
		return Ext.taskController;
	}

	public static HttpManager http() {
		if (Ext.httpManager == null) {
			HttpManagerImpl.registerInstance();
		}
		return Ext.httpManager;
	}

	public static ViewInjector view() {
		if (Ext.viewInjector == null) {
			ViewInjectorImpl.registerInstance();
		}
		return Ext.viewInjector;
	}

	

	

	public static DbManager getDb(DbManager.DaoConfig daoConfig) {
		return DbManagerImpl.getInstance(daoConfig);
	}

	public static class Ext {
		private static boolean debug;
		private static Application app;
		private static TaskController taskController;
		private static HttpManager httpManager;
		private static ViewInjector viewInjector;

		private Ext() {
			
		}

		static {
			TaskControllerImpl.registerInstance();
			// 默认信任所有https域名
			HttpsURLConnection
					.setDefaultHostnameVerifier(new HostnameVerifier() {
						@Override
						public boolean verify(String hostname,
								SSLSession session) {
							return true;
						}
					});
		}

		public static void init(Application app) {
			if (Ext.app == null) {
				Ext.app = app;
			}
		}

		public static void setDebug(boolean debug) {
			Ext.debug = debug;
		}

		public static void setTaskController(TaskController taskController) {
			if (Ext.taskController == null) {
				Ext.taskController = taskController;
			}
		}

		public static void setHttpManager(HttpManager httpManager) {
			Ext.httpManager = httpManager;
		}

		public static void setViewInjector(ViewInjector viewInjector) {
			Ext.viewInjector = viewInjector;
		}

		
	}

	private static class MockApplication extends Application {
		public MockApplication(Context baseContext) {
			this.attachBaseContext(baseContext);
		}
	}
}
