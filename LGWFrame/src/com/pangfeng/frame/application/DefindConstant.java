package com.pangfeng.frame.application;

import java.io.File;

import com.pangfeng.frame.utils.CommonTools;

public class DefindConstant {
	
    public static boolean DEBUG_CACHE = false;
    public static boolean DEBUG_IMAGE = false;
    public static boolean DEBUG_REQUEST = false;

    public static String DEBUG_REQUEST_LOG_TAG = "cube-request";

    public static String DEBUG_IMAGE_LOG_TAG = "cube-image";
    public static String DEBUG_IMAGE_LOG_TAG_TASK = "cube-image-task";
    public static String DEBUG_IMAGE_LOG_TAG_PROVIDER = "cube-image-provider";

    public static boolean DEBUG_SCROLL_HEADER_FRAME = false;
    public static boolean DEBUG_PAGE_INDICATOR = false;
    public static boolean DEBUG_LIST = false;

    // print lifecycle information by log tag "cube-lifecycle"
    public static boolean DEBUG_LIFE_CYCLE = false;

	/**
	 * 根路径
	 */
	public static String rootPath = CommonTools.getSdcardPaht() + File.separator
			+ ".com.panfeng.shining" + File.separator;
	/**
	 * 错误日志保存文件
	 */
	public final static String saveLogPath = rootPath + "error.txt";
	/**
	 * 下载视频保存路径
	 */
	public final static String saveDownaLoadVideoPath = rootPath
			+ "DownloadVideo" + File.separator;
	/**
	 * 下载图片保存路径
	 */
	public final static String saveDownaLoadImagePath = rootPath
			+ "DownloadImage" + File.separator;
	/**
	 * 数据库保存路径
	 */
	public final static String saveDatabasePath = rootPath + "Database"
			+ File.separator;
	/**
	 * 我的作品
	 */
	public final static String myProduction = rootPath + "myProduction"
			+ File.separator;

	/**
	 * 拍摄视频图片储存
	 */
	public final static String videoImage = rootPath + "videoImage"
			+ File.separator;

	/**
	 * 缓存图片储存
	 */
	public final static String cahceImage = rootPath + "cacheImage"
			+ File.separator;
	/**
	 * 收藏
	 */
	public final static String myFavorites = rootPath + "myFavorites"
			+ File.separator;
	/**
	 * 拍摄音乐保存
	 */
	public final static String videoMusic = rootPath + "videoMusic"
			+ File.separator;
	/**
	 * 我的
	 */
	public final static String myShinVideo = rootPath + "myShinVideo"
			+ File.separator;
	/**
	 * 默认视频
	 */
	public final static String baseVideo = rootPath + "baseVideo"
			+ File.separator;
	/**
	 * 引导视频
	 */
	public final static String cacheLeadVideo = rootPath + "cacheLeadVideo"
			+ File.separator;

	/**
	 * 主题路径
	 */

	public final static String videoTheme = rootPath + "videoTheme"
			+ File.separator;

	public final static String videoReadTheme = rootPath + "videoReadTheme"
			+ File.separator;

	/**
	 * db 配置相关
	 */
	public static int DBVERSION = 3;
	public static String DBNAME = "shiningslc.db";
	/**
	 * 用户信息
	 */
	static public String LOCALUSERPATH = "userInfo.dat";
	static public String LOCALPATH = "";

	/**
	 * 版本状态
	 */
	final static public boolean DEBUG = false;
	/**
	 * sdcaed state
	 */
	public static boolean SDCARDISOK = false;
	/**
	 * 应用服务器
	 */
	static public String xmppHost = DEBUG ? "123.59.75.62:8080"
			: "share.shiningmovie.com:8080";

	static public String HOST = "http://" + xmppHost + "/shiningCenterService/";

	static public String URL = HOST;

}
