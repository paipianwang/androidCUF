package com.example.simpleforframe.config;

import com.example.simpleforframe.plugins.MyPlugins;
import com.pangfeng.frame.application.FrameworkInit;
import com.pangfeng.frame.config.Constants;
import com.pangfeng.frame.config.Plugins;
import com.pangfeng.frame.plugins.DBPlugins;
import com.pangfeng.frame.plugins.ImageLoaderPlugins;
import com.pangfeng.frame.plugins.MessageNotifPlugins;
import com.pangfeng.frame.plugins.NetUtilsPlugins;

public class MyConfig extends FrameworkInit {

	@Override
	public void setConstant(Constants me) {
		me.Debug(true);
		
	}

	@Override
	public void addPlugin(Plugins me) {
		// TODO Auto-generated method stub
		me.add(new NetUtilsPlugins());
		me.add(new DBPlugins("dbname","dbSavePath"));
		me.add(new ImageLoaderPlugins());
		me.add(new MessageNotifPlugins());
		me.add(new MyPlugins("value"));
		
	}
	
	
	

}
