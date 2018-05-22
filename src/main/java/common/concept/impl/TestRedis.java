package common.concept.impl;

import redis.clients.jedis.Jedis;

import java.util.*;

public class TestRedis {
    protected static Jedis jedis = new Jedis("localhost", 6379);
    public static void main(String[] args) {
        //VariableDeclaring
          String opStatus=null;
          long   intStatus=0l;
        //findAll
           Set<String> set=jedis.keys("*");
           System.out.println("findAll:"+set);
        //findById
           String value=jedis.get("jedis");
           System.out.println("findById:"+value);
        //findByPaging
        //add
        opStatus=jedis.set("jedis", "jedis_1");
            System.out.println("addStatus:"+opStatus);
        //update
             opStatus=jedis.rename("jedis", "jedis_new");
        System.out.println("updateStatus:"+opStatus);
        //delete
             if(jedis.exists("jedis_new")){
                 intStatus=jedis.del("jedis_new");
                 System.out.println("delStatus:"+intStatus);
             }

        //添加集合并遍历:list
           //从左头添加/右尾添加
              jedis.lpush("list", "tom", "jack", "jason", "peter", "lc", "easy");
              jedis.rpush("list", "andy", "eleven");
           //返回List长度
              System.out.println("list长度:"+jedis.llen("list"));
            //遍历
              List<String> list = jedis.lrange("list", 0, -1);
              System.out.println("list:"+list);
        //添加集合并遍历:map
            Map<String, String> map = new HashMap<String, String>();
            map.put("c1", "桂林");
            map.put("c2", "天津");
            map.put("c3", "合肥");
            jedis.hmset("Mcity2", map);
            map= jedis.hgetAll("Mcity2");
            System.out.println("map:"+map);

        //添加集合并遍历:set及交集/并集/差集(左边集合减去交集生成的集合)
             jedis.sadd("key01", "北京", "上海", "深圳");
             jedis.sadd("key02", "北京", "成都", "哈尔滨");
             System.out.println("交集：" + jedis.sinter("key01", "key02"));
             System.out.println("并集：" + jedis.sunion("key01", "key02"));
             System.out.println("差集：" + jedis.sdiff("key01", "key02"));
    }




}