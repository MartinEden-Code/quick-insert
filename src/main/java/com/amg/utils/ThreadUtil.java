package com.amg.utils;

import cn.hutool.core.thread.RejectPolicy;

import java.util.concurrent.*;

/**
 * 创建线程池工具类
 * @author Amg
 * @date 2021/11/17 11:01
 */
public class ThreadUtil {
	
	/**
	 * 创建一个线程池，线程核心数coreSize,最大线程数为maxSize，存活时间10秒
	 * @param coreSize	核心线程数
	 * @param maxSize	最大线程数
	 * @return			ThreadPoolExecutor
	 */
	public static ThreadPoolExecutor getThreadPoolExecutorDefault(int coreSize,int maxSize) {
		
		return new ThreadPoolExecutor(coreSize,
				maxSize,
				10,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(20) ,
				Executors.defaultThreadFactory(),
				RejectPolicy.CALLER_RUNS.getValue());
	}
}
