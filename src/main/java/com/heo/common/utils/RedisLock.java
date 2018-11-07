package com.heo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Timer;
import java.util.concurrent.*;

/**
 * @Auth justinniu
 * @Date 2018-11-01
 */
@Component
public class RedisLock {

    private final String LOCK_SUCCESS = "OK";
    private final String SET_IF_NOT_EXIST = "NX";
    private final String SET_WITH_EXPIRE_TIME = "PX";
    /**
     * 锁的过期时间
     */
    private final int DEFAULT_EXPIRE_TIME = 20 * 1000;
    /**
     * 尝试1s
     */
    private final int TRY_TIME = 1 * 1000;

    private final Logger logger = LoggerFactory.getLogger(RedisLock.class);


    /**
     * Redis锁
     * @param jedis
     * @param key 方法名称
     * @param value id
     * @return
     */
    public boolean lock(Jedis jedis, String key, String value) {
        String methodDesc = "获取Redis分布式锁";
        logger.info(methodDesc + "开始 >>>>>>>>>>key:{},    value:{}", key, value);
        try {
            if (jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, DEFAULT_EXPIRE_TIME).equals(LOCK_SUCCESS)) {
               logger.info(methodDesc + "完成>>>>>>>>>>>>已经获得{}锁", key);
                return true;
            }
            else {
                lock(jedis, key, value);
            }
        } catch (Exception e) {
            logger.error(methodDesc + "失败>>>>>>>>>>>>>{}", e);
        }
        logger.info(methodDesc + "失败>>>>>>>>>>>>>未获得{}锁", key);
        return false;
    }

    /**
     * 尝试获取redis分布式锁
     * @param jedis
     * @param key 方法名称
     * @param value id
     * @return
     */
    public boolean tryLock(Jedis jedis, String key, String value) {
        String methodDesc = "尝试获取Redis分布式锁";
        logger.info(methodDesc + "开始 >>>>>>>>>>key:{},    value:{}", key, value);
        try {
            ExecutorService service = Executors.newFixedThreadPool(2);
            service.execute(new Thread() {
                @Override
                public void run() {
                    long beginTime = System.currentTimeMillis();
                    try {
                        while (System.currentTimeMillis() - beginTime < TRY_TIME) {
                            lock(jedis, key, value);
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {

                    }

                }
            });
            service.awaitTermination(1200, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error(methodDesc + "失败>>>>>>>>>>>>>{}", e);
        }
        logger.info(methodDesc + "失败>>>>>>>>>>>>>未获得{}锁", key);
        return false;
    }

    /**
     * 释放锁
     * @param jedis
     * @param key 加锁的方法名
     * @param value id
     * @return
     */
    public boolean releaseLock(Jedis jedis,String key, String value) {
        String methodDesc = "释放锁";
        logger.info(methodDesc + "开始 >>>>>>>>>>key:{},    value:{}", key, value);
        //Lua 脚本
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        /**
         * 判断是否 是当前的value 获取到这个方法锁
         */
        try {
            Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));
            if (result.equals(1L)) {
                logger.info(methodDesc + "完成>>>>>>>>>>>>已经获得{}锁", key);
                return true;
            }
        } catch (Exception e) {
            logger.error(methodDesc + "失败>>>>>>>>>>>>>{}", e);
        }
        logger.info(methodDesc + "失败>>>>>>>>>>>>>未获得{}锁", key);
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        RedisLock redisLock = new RedisLock();
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();
        String key = "lock:test";
        String v1 = "1";
        ExecutorService service = Executors.newFixedThreadPool(5);
        Thread c = new Thread("C") {
            @Override
            public void run() {
                try {
                    if (redisLock.lock(jedis, key, v1)) {
                        System.out.println(Thread.currentThread().getName() + "------获得锁");
                        Thread.sleep(1000);
                        redisLock.releaseLock(jedis, key, v1);
                    }
                    else {
                        System.out.println(Thread.currentThread().getName() + "------未得锁");
                    }
                }catch (Exception e) {

                }

            }
        };
        Thread b = new Thread("B") {
            @Override
            public void run() {
                try {
                    if (redisLock.lock(jedis, key, v1)) {
                        System.out.println(Thread.currentThread().getName() + "------获得锁");
                        redisLock.releaseLock(jedis, key, v1);
                    }
                    else {
                        System.out.println(Thread.currentThread().getName() + "------未得锁");
                    }
                }catch (Exception e) {

                }

            }
        };
        b.start();
        Thread.sleep(100);
        c.start();

    }
}
