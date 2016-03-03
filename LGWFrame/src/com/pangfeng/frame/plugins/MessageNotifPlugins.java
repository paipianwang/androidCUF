package com.pangfeng.frame.plugins;

import com.pangfeng.frame.plugins.interfaces.IPlugin;
import com.pangfeng.frame.plugins.libs.impl.image.ImageLoaderConfig;
import com.pangfeng.frame.plugins.libs.impl.image.ImageLoaderImpl;
import com.pangfeng.frame.plugins.libs.impl.notif.MessageNotifImpl;

public class MessageNotifPlugins implements IPlugin {

	@Override
	public boolean getInstance() {
		// TODO Auto-generated method stub
		if (MessageNotifImpl.instance == null) {
			MessageNotifImpl.instance = MessageNotifImpl.registerInstance();
		}
		return false;
	}


	
	
	
}
