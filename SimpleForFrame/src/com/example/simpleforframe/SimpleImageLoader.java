package com.example.simpleforframe;

import com.pangfeng.frame.plugins.libs.impl.image.ImageLoaderImpl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class SimpleImageLoader extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		
		ImageView image = new ImageView(null);
		
		ImageLoaderImpl.instance.getContentProviderImage("contentprividerUrl",image );
		
		ImageLoaderImpl.instance.getDrawableImage("drawableImageUrl", image);
		
		ImageLoaderImpl.instance.getNetImage("webUrl", image);
		
		ImageLoaderImpl.instance.getSDImage("sdPath", image);
		
		/*
		 * 如果想使用imageloader原本的方法
		 * ImageLoaderImpl.imageLoader.method();
		 */
		
		ImageLoaderImpl.imageLoader.displayImage("uri", image);
		
		
	}

}
