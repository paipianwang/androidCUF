package com.pangfeng.frame.utils.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskHandlerThreadPool {
	private static ExecutorService executorService;
	private static int THREAD_POOL_SIZE = 5;
	private static int DEFAULT = 0;

	public static void  init(int threadpoolsize) {
		create(threadpoolsize);
	}

	public static void  init() {
		create(DEFAULT);
	}
	
	// init thread pool
	private static void create(final int threadpoolsize) {
		if (threadpoolsize > 0)
			THREAD_POOL_SIZE = threadpoolsize;
		else {
			// default cpu core size +1
			int cpucoresize = Runtime.getRuntime().availableProcessors() + 1;
			if (cpucoresize > 0)
				THREAD_POOL_SIZE = cpucoresize;
		}
		executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}

	public static ExecutorService getInstance() {
		if (executorService == null)
			create(DEFAULT);
		return TaskHandlerThreadPool.executorService;
	}

	/**
	 * 关闭线程池，smooth为true则为平缓关闭，false为立即关闭
	 * 
	 * @param smooth
	 */
	public static void stopThreadPool(boolean smooth) {
		if (smooth)
			executorService.shutdown();
		else
			executorService.shutdownNow();

	}

}
