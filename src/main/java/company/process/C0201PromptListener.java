package company.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/16.
 */
public class C0201PromptListener extends JedisPubSub {
    private Jedis jedis;
    public C0201PromptListener(){
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost",6379);
        jedis = pool.getResource();
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        //1>声明
             Gson gson=new GsonBuilder().create();
        if(message.equals("promptInfoSendId")){
            String promptInfoDesc= jedis.get("promptInfo");
            String promptInfoList = jedis.get("promptInfoList");
            String promptInfoMap = jedis.get("promptInfoMap");
            PromptInfo promptInfo=gson.fromJson(promptInfoDesc,PromptInfo.class);
            List<PromptInfo>       list=gson.fromJson(promptInfoList,new TypeToken<List<PromptInfo>>() {}.getType());
            Map        map=gson.fromJson(promptInfoMap,new TypeToken<Map<String,PromptInfo>>() {}.getType());
            System.out.println("提示信息ID号:"+promptInfo.getId()+"--"+"标题:"+promptInfo.getName()+"--"+"内容:"+promptInfo.getContent());
            System.out.println("list:"+list);
            System.out.println("map:"+map);
        }
    }


}

