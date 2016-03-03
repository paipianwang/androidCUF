package com.pangfeng.frame.application;

import com.pangfeng.frame.config.Constants;
import com.pangfeng.frame.config.Plugins;

public abstract class FrameworkInit {
	private static final Constants CONSTANTS = new Constants();
	private static final Plugins PLUGINS = new Plugins();

	/**
	 * Config constant
	 */
	public abstract void setConstant(Constants me);

	/**
	 * Config plugin
	 */
	public abstract void addPlugin(Plugins me);

	/**
	 * 框架初始化
	 */
	public void init() {
		setConstant(CONSTANTS);
		addPlugin(PLUGINS);
		Plugins.startPlugin(PLUGINS.getPluginList());
	}

}
