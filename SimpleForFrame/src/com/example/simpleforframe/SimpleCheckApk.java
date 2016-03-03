package com.example.simpleforframe;

import android.app.Activity;

import com.pangfeng.frame.utils.CommonTools;

public class SimpleCheckApk extends Activity{
	
	
	   protected void onCreate(android.os.Bundle savedInstanceState) {
		   
		   
 
         //每日检查一次更新		   
		   CommonTools.checkNewVersion(this);
		   
		   
	   };
	
	
	
	

}
