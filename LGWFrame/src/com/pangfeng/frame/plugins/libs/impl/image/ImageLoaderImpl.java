package com.pangfeng.frame.plugins.libs.impl.image;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.pangfeng.frame.plugins.libs.ImageLoaderManager;

/**
 * Created by wyouflf on 15/10/9.
 */
public final class ImageLoaderImpl implements ImageLoaderManager {

	public static ImageLoader imageLoader = ImageLoaderConfig.getInstance();
	public static DisplayImageOptions options = ImageLoaderConfig.getCommonConfig();
	private static final Object lock = new Object();
	public static ImageLoaderImpl instance;
	private ImageLoaderImpl() {
	}

	public static ImageLoaderImpl registerInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new ImageLoaderImpl();
					return instance;
				}
			}
		}
		return instance;
	}

	@Override
	public void getNetImage(String imageUrl, ImageView imageView) {
		imageLoader.displayImage(imageUrl, imageView, options);
	}

	@Override
	public void getSDImage(String sdPath, ImageView imageView) {
		String imagePath = sdPath;
		String imageUrl = Scheme.FILE.wrap(imagePath);
		imageLoader.displayImage(imageUrl, imageView, options);
	}

	@Override
	public void getDrawableImage(String drawableUrl, ImageView imageView) {

		String drawableUrls = Scheme.ASSETS.wrap(drawableUrl);
		imageLoader.displayImage(drawableUrls, imageView, options);
	}

	@Override
	public void getAssetsImage(String assetsUrl, ImageView imageView) {
		// TODO Auto-generated method stub
		String assetsUrls = Scheme.ASSETS.wrap(assetsUrl);
		imageLoader.displayImage(assetsUrls, imageView, options);
	}

	@Override
	public void getContentProviderImage(String contentprividerUrl,
			ImageView imageView) {
		// TODO Auto-generated method stub
		String contentprividerUrls = "content://media/external/audio/albumart/13";
		imageLoader.displayImage(contentprividerUrls, imageView, options);
	}

}
