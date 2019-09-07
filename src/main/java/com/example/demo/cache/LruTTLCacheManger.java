package com.example.demo.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LruTTLCacheManger {

    private static final Logger logger = LoggerFactory.getLogger(LruTTLCacheManger.class);

	private static LruTTLCache lruCacheInstance = null;
	private static final int MAX_CACHE_SIZE = 20000;//最大2W条数据
	private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	public static LruTTLCache getLruCacheInstance() {
		if (lruCacheInstance == null) {
			synchronized (LruTTLCacheManger.class) {
				if (lruCacheInstance == null) {
					lruCacheInstance = new LruTTLCache(MAX_CACHE_SIZE);
                    startClearExpiredTask();
				}
			}
		}
		return lruCacheInstance;
	}

	public void shutdown() {
		this.scheduledExecutorService.shutdown();
	}

	/**
	 * 启动清理过期任务
	 */
	public static void startClearExpiredTask(){
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
				    logger.info("msg1=清理过期缓存开始");
					lruCacheInstance.clearExpried();
                    logger.info("msg1=清理过期缓存结束");
				} catch (Throwable t) {
				    logger.error(t.getMessage(),t);
				}
			}
		}, 2, 2, TimeUnit.MINUTES);
	}

}
