package com.pangfeng.frame.plugins;

import org.xutils.http.HttpManagerImpl;

import com.pangfeng.frame.plugins.interfaces.IPlugin;
import com.pangfeng.frame.plugins.kit.NetUtilsKit;

public class NetUtilsPlugins implements IPlugin {



	@Override
	public boolean getInstance() {

		if (NetUtilsKit.httpManager == null) {
			NetUtilsKit.httpManager = HttpManagerImpl.registerInstance();
		}
		return true;
	}
}
