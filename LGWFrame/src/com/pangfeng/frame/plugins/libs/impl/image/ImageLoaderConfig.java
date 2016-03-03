package com.pangfeng.frame.plugins.libs.impl.image;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.pangfeng.frame.application.DefindConstant;
import com.pangfeng.frame.application.FrameApp;

public class ImageLoaderConfig {

	public static void initImageLoader() {

		Context context = FrameApp.getApplicationContextInstatnce();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.threadPriority(Thread.NORM_PRIORITY - 1)
				.threadPoolSize(8)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(4000)
				// 缓存的文件数量
				.discCache(
						new UnlimitedDiscCache(new File(
								DefindConstant.cahceImage)))
				// 自定义缓存路径
				.memoryCacheSize(1024 * 1024 * 8)
				.memoryCache(new WeakMemoryCache()).build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
		ImageLoader.getInstance().handleSlowNetwork(true);
	}

	// 初始化
	public static ImageLoader getInstance() {

		ImageLoader imageLoader = ImageLoader.getInstance();
		return imageLoader;

	}

	// 参数配置项
	public static DisplayImageOptions getCommonConfig() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc()
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		return options;
	}

}
