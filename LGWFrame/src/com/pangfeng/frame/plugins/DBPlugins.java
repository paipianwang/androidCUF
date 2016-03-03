package com.pangfeng.frame.plugins;

import java.io.File;

import org.xutils.DbManager;
import org.xutils.db.DbManagerImpl;

import android.annotation.SuppressLint;
import android.content.Context;

import com.pangfeng.frame.application.DefindConstant;
import com.pangfeng.frame.application.FrameApp;
import com.pangfeng.frame.plugins.interfaces.IPlugin;
import com.pangfeng.frame.plugins.kit.DBKit;
import com.pangfeng.frame.plugins.kit.db.DbUpgrade;
import com.pangfeng.frame.plugins.libs.impl.image.ImageLoaderConfig;
import com.pangfeng.frame.plugins.libs.impl.image.ImageLoaderImpl;

public class DBPlugins implements IPlugin {

	private static String dbname = DefindConstant.DBNAME;// 数据库名称
	private static String dbpath = DefindConstant.saveDatabasePath;// 数据库保存路径

	@SuppressWarnings("unused")
	public DBPlugins() {

	}

	@SuppressWarnings("null")
	public DBPlugins(String dbName, String dbPath) {

		if (dbName != null || !dbName.equals(""))
			dbname = dbName;

		if (dbPath != null || !dbPath.equals(""))
			dbpath = dbPath;

	}

	@SuppressWarnings("unused")
	public boolean getInstance() {

		DbUpgrade dbUpgradeListener = new DbUpgrade();
		DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
				.setDbName(dbname)
				// 不设置dbDir时, 默认存储在app的私有目录.
				.setDbDir(new File(dbpath)).setDbVersion(2)
				.setDbOpenListener(new DbManager.DbOpenListener() {
					@SuppressLint("NewApi")
					public void onDbOpened(DbManager db) {
						// 开启WAL, 对写入加速提升巨大
						db.getDatabase().enableWriteAheadLogging();
					}
				}).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
					@Override
					public void onUpgrade(DbManager db, int oldVersion,
							int newVersion) {
						// TODO: ...
						// db.addColumn(...);
						// db.dropTable(...);
						// ...
						// or
						// db.dropDb();
					}
				});

		if (dbUpgradeListener == null) {
			DBKit.mDBClient = DbManagerImpl.getInstance(daoConfig);
		}

		return true;

	}
}
