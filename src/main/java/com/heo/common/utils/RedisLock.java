package com.heo.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Timer;
import java.util.concurrent.*;

@Component
public class RedisLock {

    private final String LOCK_SUCCESS = "OK";
    private final String SET_IF_NOT_EXIST = "NX";
    private final String SET_WITH_EXPIRE_TIME = "PX";
    private final int DEFAULT_EXPIRE_TIME = 20 * 1000;
    private final int TRY_TIME = 1 * 1000;
    @Autowired
    private JedisPool jedisPool;


    public boolean lock(Jedis jedis, String key, String value) {
        try {
            if (jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, DEFAULT_EXPIRE_TIME).equals(LOCK_SUCCESS)) {
                return true;
            }
            else {
                lock(jedis, key, value);
            }
        } catch (Exception e) {

        }

        return false;
    }

    public boolean tryLock(Jedis jedis, String key, String value) {
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

        }
        return false;
    }


    public boolean releaseLock(Jedis jedis,String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));

        if (result.equals(1L)) {
            return true;
        }
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
