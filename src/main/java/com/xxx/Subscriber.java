package com.xxx;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2018/5/20.
 */
public class Subscriber {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost",6379);
        Jedis jedis = pool.getResource();
        //只订阅patten匹配在超时事件
        jedis.psubscribe(new KeyExpiredListener(), "__key*@0__:expired");
    }
}
