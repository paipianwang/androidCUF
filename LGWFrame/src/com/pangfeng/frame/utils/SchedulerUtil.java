package com.pangfeng.frame.utils;

import it.sauronsoftware.cron4j.Scheduler;
import android.content.Context;


public class SchedulerUtil {
	
	
	public static void setAlarm(String style,Runnable runnable) {
		//ps:style = "00 20 * * *"
		Scheduler scheduler = new Scheduler();
		scheduler.schedule(style, runnable);
		scheduler.start();
	}
	
	
 

}
