package com.pangfeng.frame.plugins.libs;

import android.widget.ImageView;

public interface ImageLoaderManager {

	void getNetImage(String imageUrl, ImageView imageView);
	void getSDImage(String sdPath, ImageView imageView);
	void getDrawableImage(String drawableUrl, ImageView imageView);
	void getAssetsImage(String assetsUrl, ImageView imageView);
	void getContentProviderImage(String assetsUrl, ImageView imageView);
	
}
