package com.example.simpleforframe.plugins;

import com.pangfeng.frame.plugins.interfaces.IPlugin;

public class MyPlugins implements IPlugin {
	private static final Object lock = new Object();

	public MyPlugins() {

	};

	public MyPlugins(String value) {

	};

	@Override
	public boolean getInstance() {
		if (MyPluginImpl.instance == null) {
			synchronized (lock) {
				if (MyPluginImpl.instance == null) {
					MyPluginImpl.instance = MyPluginImpl.registerInstance();
				}
			}
			return true;
		}

		return false;
	}

}
