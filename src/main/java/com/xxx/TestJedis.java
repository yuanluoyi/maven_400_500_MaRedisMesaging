package com.xxx;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2018/5/20.
 */
public class TestJedis  {
    public static void main(String[] args) throws  Exception {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost",6379);
        Jedis jedis = pool.getResource();
        //20180801005失效:设置orderError:NoPayTimeoutPeriodInvalidate并提供单号(长一点失效)
        jedis.select(0);//db为0
        jedis.set("orderError:NoPayTimeoutPeriodInvalidate", "订单错误:超期未支付失效");
        jedis.expire("orderError:NoPayTimeoutPeriodInvalidate", 10);
        jedis.set("orderNum", "20180801005");
        jedis.expire("orderNum", 15);
        Thread.sleep(50000);
        //20190901005失效:
        jedis.select(0);//db为0
        jedis.set("orderError:Other","订单错误:其他");
        jedis.set("orderNum", "20190901005");
        jedis.expire("orderError:Other", 20);
    }
}

