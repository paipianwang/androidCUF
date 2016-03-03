package com.pangfeng.frame.plugins.kit;

import java.io.File;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.pangfeng.frame.application.FrameApp;

public class MediaPlayerKit {
	public static MediaPlayer mediaPlayer;
	static boolean initMedia;
	static boolean initView;
	public static SurfaceView surface;

	public static void registerInstance() {

		initMedia = false;
		initView = false;

	}

	/*
	 * 播放视频时才能使用，直接使用会报错 mediaPlayer=null
	 */
	public static void changeVideo(File file) {

		try {

			mediaPlayer.stop();
			mediaPlayer.reset();
			mediaPlayer.setDataSource(file.getAbsolutePath());
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void initMediaPlayRaw(SurfaceView surfaceView,
			final Context ctx, int raw) {

		surface = surfaceView;

		try {

			// 设置播放的视频源

			Uri uri = Uri.parse("android.resource://"
					+ FrameApp.getApplicationContextInstatnce().getPackageName()
					+ "/" + raw);

			mediaPlayer = new MediaPlayer();
			surface.getHolder().addCallback(callback);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(ctx, uri);
			mediaPlayer.prepareAsync();
			mediaPlayer
					.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {

							// 装载完成
							// 设置显示视频的SurfaceHolder
							initMedia = true;
							initView = true;
							if (initView) {

								FrameApp.hasinitMediapalyer = true;
								mediaPlayer.setDisplay(surface.getHolder());
								mediaPlayer.start();
								mediaPlayer.setLooping(true);

							}

						}
					});
			mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// 发生错误

					if (mediaPlayer != null)
						mediaPlayer.release();
					mediaPlayer = null;
					initMedia = false;
					return false;
				}
			});
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {

				}
			});

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void initMediaPlay(File file, final SurfaceView surfaceView) {

		surface = surfaceView;

		try {
			if (file != null) {
				// 设置播放的视频源

				mediaPlayer = new MediaPlayer();
				surface.getHolder().addCallback(callback);
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setDataSource(file.getAbsolutePath());
				mediaPlayer.prepareAsync();
				mediaPlayer
						.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

							@Override
							public void onPrepared(MediaPlayer mp) {

								// 装载完成
								// 设置显示视频的SurfaceHolder
								initMedia = true;
								if (initView) {

									FrameApp.hasinitMediapalyer = true;
									mediaPlayer.setDisplay(surfaceView
											.getHolder());
									mediaPlayer.start();
									mediaPlayer.setLooping(true);
								}

							}
						});
				mediaPlayer
						.setOnErrorListener(new MediaPlayer.OnErrorListener() {

							@Override
							public boolean onError(MediaPlayer mp, int what,
									int extra) {
								// 发生错误

								if (mediaPlayer != null)
									mediaPlayer.release();
								mediaPlayer = null;
								initMedia = false;
								return false;
							}
						});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
		// SurfaceHolder被修改的时候回调
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// 销毁SurfaceHolder
			initView = false;
			stopMediaPlay();

		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			initView = true;
			if (initMedia && FrameApp.needCreate) {
				mediaPlayer.setDisplay(surface.getHolder());
				mediaPlayer.start();
				mediaPlayer.setLooping(true);
				initMedia = false;
				FrameApp.needCreate = true;
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

	};

	public static void stopMediaPlay() {
		try {
			if (mediaPlayer != null) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
		} catch (IllegalStateException e) {

		}

	}

}
