package com.xxx;

import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.JedisPool;

public class RedisInitSubscrib implements InitializingBean {
    JedisPool pool;
    @Override
    public void afterPropertiesSet() throws Exception {
        pool.getResource().psubscribe(new KeyExpiredListener(), "*");
    }

}