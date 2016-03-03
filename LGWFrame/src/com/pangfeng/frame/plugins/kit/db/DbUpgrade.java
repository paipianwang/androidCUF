package com.pangfeng.frame.plugins.kit.db;


import org.xutils.DbManager;

import com.panfeng.shining.db.MyUpgradeDbInterface;

import android.util.Log;

public class DbUpgrade implements DbManager.DbUpgradeListener {

	/**
	 * arg1 数据库原始版本 arg2数据库现在版本
	 */
	@Override
	public void onUpgrade(DbManager arg0, int arg1, int arg2) {
		/**
		 * 1.在元数据库中拷贝所有数据 2.删除原表 3.新建表 4.插入原始数据以及给修改字段赋值
		 */
		// 需要更新
		if (arg1 < arg2) {
			for (int i = 0; i < arg2 - arg1; i++) {
				String className = "Upgrade_" + (arg1+i) + "to" + (1+ arg1+i);
				// 从现有版本数据库向高版本升级
				try {
					@SuppressWarnings("rawtypes")
					Class class1 = Class.forName("com.pai.db."+ className);
					if(class1!=null)
					{
						
//						MyUpgradeDbInterface mdf = (MyUpgradeDbInterface) class1.newInstance();
//						mdf.update(arg0);
						
					}
				} catch (Exception e) {
					//删除所有表
					e.printStackTrace();
					continue;
				}
			}
		}
	}

}
