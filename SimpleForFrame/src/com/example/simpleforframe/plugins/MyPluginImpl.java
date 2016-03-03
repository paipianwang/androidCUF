package com.example.simpleforframe.plugins;


public class MyPluginImpl implements MyPluginManager {

	private static final Object lock = new Object();
	public static MyPluginImpl instance;

	public static MyPluginImpl registerInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new MyPluginImpl();
					return instance;
				}
			}
		}
		return instance;
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
