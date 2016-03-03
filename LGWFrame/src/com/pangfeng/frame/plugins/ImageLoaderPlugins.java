package com.pangfeng.frame.plugins;

import com.pangfeng.frame.plugins.interfaces.IPlugin;
import com.pangfeng.frame.plugins.libs.impl.image.ImageLoaderConfig;
import com.pangfeng.frame.plugins.libs.impl.image.ImageLoaderImpl;

public class ImageLoaderPlugins implements IPlugin {

	@Override
	public boolean getInstance() {

		if (ImageLoaderImpl.instance == null) {
			ImageLoaderConfig.initImageLoader();
			ImageLoaderImpl.instance = ImageLoaderImpl.registerInstance();

		}
		return true;
	}
}
