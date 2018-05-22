package com.xxx;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Administrator on 2018/5/16.
 */
public class KeyExpiredListener extends JedisPubSub {
    private Jedis jedis;
    public KeyExpiredListener(){
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost",6379);
        jedis = pool.getResource();
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage pattern " + pattern + " (" + channel + ") " + message);
        if(message.equals("orderError:NoPayTimeoutPeriodInvalidate")){
            String value=jedis.get("orderNum");
            System.out.println("订单号:"+value+"--订单错误:超期未支付失效");
        }else if(message.equals("orderError:Other")){
            String value=jedis.get("orderNum");
            System.out.println("订单号:"+value+"--订单错误:其他");
        }
    }


}

