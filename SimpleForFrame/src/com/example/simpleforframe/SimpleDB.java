package com.example.simpleforframe;

import java.util.List;

import org.xutils.db.DbManagerImpl;
import org.xutils.ex.DbException;

import com.pangfeng.frame.plugins.DBPlugins;
import com.pangfeng.frame.plugins.kit.DBKit;
import com.pangfeng.frame.plugins.libs.impl.image.ImageLoaderImpl;

import android.app.Activity;
import android.content.Entity;
import android.os.Bundle;
import android.widget.ImageView;

public class SimpleDB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	
		Class<?> entity = null;
		List <?> entityList = null;
		Entity entitys = null;
		
		DBKit.delete(entitys);
		DBKit.deleteCriteria(entity,"1", "谁");
        DBKit.drop(entity);
        DBKit.save(entitys);
        DBKit.saveAll(entityList);
        DBKit.search(entity);
        DBKit.searchCriteria(entity, "1", "我");
        
		
		
		/*
		 * 如果想使用DB原本的方法
		 * DBKit.mDBClient.method();
		 */
		
        try {
			DBKit.mDBClient.dropDb();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
