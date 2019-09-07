package com.example.demo.cache;

import com.alibaba.fastjson.JSON;
import com.example.demo.utils.BeanUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * LRU算法缓存 && TTL(生存周期)
 * @author dg
 */
public class LruTTLCache {
    private static Logger logger = LoggerFactory.getLogger(LruTTLCache.class);
    Cache<String, Object> cache;

    public LruTTLCache(int cacheSize) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .expireAfterWrite(1, TimeUnit.HOURS)//最长不能超过1小时
                .build();
    }

    /**
     * @param key
     * @param value
     * @param expire 过期时间,单位(秒)
     */
    public void put(String key, Object value, long expire) {
        try {
            Object newObj = BeanUtils.clone(value);
            cache.put(key, new Entry(newObj, expire));
        } catch (Exception e) {
            logger.error("clone 对象时发生异常");
        }
    }

    /**
     * 放入缓存 ,不地过期，只有当缓存队列满时根据lru算法清除
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        try {
            Object newObj = BeanUtils.clone(value);
            cache.put(key, new Entry(newObj));
        } catch (Exception e) {
            logger.error("clone对象时发生异常", e.getMessage());
        }
    }

    public Object get(String key) {
        try {
            Entry entry = (Entry) cache.getIfPresent(key);
            if (entry == null) {
                return null;
            }
            if (entry.isExpired()) {
                remove(key);
                return null;
            }
            return BeanUtils.clone(entry.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(String key) {
        try {
            cache.invalidate(key);
            logger.info("msg1=缓存被清理,,cacheKey={}", key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long size() {
        return cache.size();
    }

    public void clearAll() {
        try {
            cache.invalidateAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索包含 "q" 的key
     *
     * @param qs
     * @return
     */
    public List<String> searchKeys(String... qs) {
        List<String> list = new ArrayList();
        try {
            ConcurrentMap map = cache.asMap();
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                boolean matched = true;//同一个key是否满足多个关键字
                for(String q : qs){
                    if (key.toLowerCase().indexOf(q.toLowerCase()) < 0) {
                        matched = false;
                    }
                }
                if(matched) {
                    list.add(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 清除全部过期的
     */
    public void clearExpried() {
        try {
            ConcurrentMap map = cache.asMap();
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Entry entry = (Entry) cache.getIfPresent(key);
                if (entry != null && entry.isExpired()) {
                    iterator.remove();
                    //System.out.println("缓存key={}已过期,清除"+entry.getKey());
                    logger.info("msg1=缓存已过期,清除,,cacheKey={}", key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取内存
     *
     * @return
     */
    public String printCache() {
        return JSON.toJSONString(cache.asMap());
    }

    /**
     * 获取缓存keys
     *
     * @return
     */
    public List<String> getCacheKeys() {
        List<String> list = new ArrayList();
        try {
            ConcurrentMap map = cache.asMap();
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                list.add(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public class Entry {
        private long expire = -1;//-1永不过期
        private Object value;
        private Long holdTimeTo;//保存至,单位秒

        public Entry(Object value) {
            this.value = value;
        }

        public Entry(Object value, long expire) {
            this.value = value;
            this.expire = expire;
            holdTimeTo = System.currentTimeMillis() / 1000L + expire;
        }

        public boolean isExpired() {
            return expire > 0 && holdTimeTo < System.currentTimeMillis() / 1000L;
        }

        public long getExpire() {
            return expire;
        }

        public Object getValue() {
            return value;
        }

        public Long getHoldTimeTo() {
            return holdTimeTo;
        }

    }

}
