# androidCUF
AndroidCUF

Android Commonly Used Function Framework

this framework provide many tools and encapsulation method about Android.
Developers can fast and easy to build project.


  架构图
 ![image](https://github.com/paipianwang/androidCUF/blob/master/image/frame.png)
 主要模块图
 ![image](https://github.com/paipianwang/androidCUF/blob/master/image/modular.png)
 类图
 ![image](https://github.com/paipianwang/androidCUF/blob/master/image/classdiagram.png)
 结构图
 ![image](https://github.com/paipianwang/androidCUF/blob/master/image/build.png)
 流程图
 ![image](https://github.com/paipianwang/androidCUF/blob/master/image/init.png)
 
 使用时请先建立Config类并且继承FrameInit
 在addPlusgin里添加要使用的模块插件（或自己制作的插件）
  ![image](https://github.com/paipianwang/androidCUF/blob/master/image/config.png)
  
 
  
  
  网络（更多示例使用可以参考SimpleForFrame）
  
  		NetUtilsKit.DownLoadFile("url", "filepath", new MyCallBack<File>());
		// 显示进度
		NetUtilsKit.DownLoadFile("url", "filepath",
				new MyProgressCallBack<File>() {
					public void onSuccess(File result) {
						super.onSuccess(result);
					}

					public void onError(Throwable ex, boolean isOnCallback) {
						super.onError(ex, isOnCallback);
					}

					public void onLoading(long total, long current,
							boolean isDownloading) {
						super.onLoading(total, current, isDownloading);
					}
				});
				
				图片加载（更多示例使用可以参考SimpleForFrame）
				
				ImageLoaderImpl.instance.getNetImage("webUrl", image);
				
				数据库（更多示例使用可以参考SimpleForFrame）
				DBKit.delete(entitys);
				
				
  
  
  关于作者

Email： 694174250@qq.com, 18210367466@163.com
有任何建议或者使用中遇到问题都可以给我发邮件
  
  
 
 

