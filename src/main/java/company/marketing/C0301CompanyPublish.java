package company.marketing;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2018/5/20.
 */
public class C0301CompanyPublish {
    //数据从后台DB中获得,雇员拓展客户的统计:模拟半天/一天/三天/一周/15天/一个月/三个月及趋势分析实现
    public static void main(String[] args) throws  Exception {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost",6379);
        Jedis jedis = pool.getResource();
        //雇员20001在当前时刻客户数统计:
        jedis.select(0);//db为0
        jedis.set("ExamineSendId", "EI50001");
        jedis.set("ExpansionStatisticId", "ES50001");
        jedis.set("employeeId", "20001");
        jedis.set("clientCount", "5");
        jedis.expire("ExamineSendId", 10);
        Thread.sleep(50000);//周期统计时间间隔,模拟半天/一天/三天/一周/15天/一个月/三个月及趋势分析实现
        //雇员20001在下一周期客户数统计:
        jedis.select(0);//db为0
        jedis.set("ExamineSendId", "EI50001");
        jedis.set("ExpansionStatisticId", "ES50002");
        jedis.set("employeeId", "20001");
        jedis.set("clientCount", "20");
        jedis.expire("ExamineSendId", 10);
    }
}

