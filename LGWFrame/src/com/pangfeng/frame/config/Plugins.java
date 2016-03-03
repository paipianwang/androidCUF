package com.pangfeng.frame.config;

import java.util.ArrayList;
import java.util.List;

import com.pangfeng.frame.plugins.interfaces.IPlugin;

/**
 * Plugins.
 */
final public class Plugins {

	private final List<IPlugin> pluginList = new ArrayList<IPlugin>();

	public Plugins add(IPlugin plugin) {
		if (plugin != null)
			this.pluginList.add(plugin);
		return this;
	}

	public List<IPlugin> getPluginList() {
		return pluginList;
	}

	public static void startPlugin(List<IPlugin> plList) {
		for (IPlugin iPlugin : plList) {
			if (!iPlugin.getInstance()) {
				System.out.println("xxx初始化失败" + iPlugin.getClass().getName());
			}
		}
	}
}
