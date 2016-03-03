package in.srain.cube.util.updateapk;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;

public class ContextKeeper {
	static Context mInstance = null;
	static Handler mHandler = null;
	public static Context getInstance() {
		return mInstance;
	}
	public static Handler getHandler() {
		return mHandler;
	}
	private static void setInstance(Context aInstance,Handler aHandler) {
		ContextKeeper.mInstance = aInstance.getApplicationContext();
		ContextKeeper.mHandler = aHandler;
	}
	static public void init(Context aInstance,Handler aHandler){
		setInstance(aInstance,aHandler);
	}
	public static String getSDCardPath(){
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); 
		if(sdCardExist){
			String sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();//获取跟目录 
			String file_path = sdDir + "/" +mInstance.getPackageName() + "/"; 
			return file_path;
		}
		return "";
	}
	public static void doUiTask(Runnable aTask){
		getHandler().post(aTask);
	}
	public static String getDataPath(){
		File path = ContextKeeper.getInstance().getFilesDir();
		return path.getAbsolutePath();
	}
}
