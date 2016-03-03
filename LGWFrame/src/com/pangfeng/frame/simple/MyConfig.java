package com.pangfeng.frame.simple;

import com.pangfeng.frame.application.FrameworkInit;
import com.pangfeng.frame.config.Constants;
import com.pangfeng.frame.config.Plugins;
import com.pangfeng.frame.plugins.DBPlugins;
import com.pangfeng.frame.plugins.ImageLoaderPlugins;
import com.pangfeng.frame.plugins.NetUtilsPlugins;

public class MyConfig extends FrameworkInit {

	@Override
	public void setConstant(Constants ct) {
		// TODO Auto-generated method stub
		ct.Debug(true);
	}

	@Override
	public void addPlugin(Plugins me) {
		me.add(new NetUtilsPlugins());
		me.add(new DBPlugins());
		me.add(new ImageLoaderPlugins());

	}

}
