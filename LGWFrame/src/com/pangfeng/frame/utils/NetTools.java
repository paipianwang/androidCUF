package com.pangfeng.frame.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetTools {
	
	
	public static boolean checkNetwork(Context ctx) {  
        ConnectivityManager conn = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);  
        NetworkInfo net = conn.getActiveNetworkInfo();  
        if (net != null && net.isConnected()) {  
            return true;  
        }  
        return false;  
    } 
	
	 /** 
     * 判断是否是wifi连接 
     */  
    public static boolean isWifi(Context context)  
    {  
        ConnectivityManager cm = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
  
        if (cm == null)  
            return false;  
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;  
  
    }  
  
    /** 
     * 打开网络设置界面 
     */  
    public static void openSetting(Activity activity)  
    {  
        Intent intent = new Intent("/");  
        ComponentName cm = new ComponentName("com.android.settings",  
                "com.android.settings.WirelessSettings");  
        intent.setComponent(cm);  
        intent.setAction("android.intent.action.VIEW");  
        activity.startActivityForResult(intent, 0);  
    }  

	
	
	
}
