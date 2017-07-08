package com.chulung.tank.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @说明 线程池
 * @作者 chulung
 * @创建时间 2014年2月8日 下午7:47:43
 * @遗留问题
 */
public class ThreadPool {
	public static ExecutorService executors;
	
	static{
		executors = Executors.newCachedThreadPool();
	}

}
