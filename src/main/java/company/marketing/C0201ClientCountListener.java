package company.marketing;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Administrator on 2018/5/16.
 */
public class C0201ClientCountListener extends JedisPubSub {
    private Jedis jedis;
    private String name;
    public C0201ClientCountListener(){
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost",6379);
        jedis = pool.getResource();
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        if(message.equals("ExamineSendId")){
            String esxpansionStatisticId=jedis.get("ExpansionStatisticId");
            String employeeId=jedis.get("employeeId");
            String clientCount=jedis.get("clientCount");
            System.out.println("拓展统计ID号:"+esxpansionStatisticId+"--"+" 雇员ID:"+employeeId+"--"+"拓展客户数:"+clientCount);
        }
    }


}

