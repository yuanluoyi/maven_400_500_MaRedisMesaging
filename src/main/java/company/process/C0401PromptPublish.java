package company.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/20.
 */
public class C0401PromptPublish {
    //数据从后台DB中获得,雇员拓展客户的统计:模拟半天/一天/三天/一周/15天/一个月/三个月及趋势分析实现
    public static void main(String[] args) throws  Exception {
        //1>声明
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost",6379);
        Jedis jedis = pool.getResource();
        Gson gson=new GsonBuilder().create();
        PromptInfo promptInfo=new PromptInfo("PI10001","回款催单提醒","订单ID:PO10001,联系人:孙亮,电话:13681253123");
        List<PromptInfo> list=new ArrayList<PromptInfo>();
        Map<String,PromptInfo> map=new HashMap<String,PromptInfo>();
        //2>初始化
        list.add(promptInfo);
        map.put(promptInfo.getId(),promptInfo);
        //3>对象或集合转为JSON
        String promptInfoDesc= gson.toJson(promptInfo);
        String promptInfoList = gson.toJson(list);
        String promptInfoMap = gson.toJson(map);
        //4>保存到Redis中
        jedis.select(0);//db为0
        jedis.set("promptInfoSendId","PIS30001");
        jedis.set("promptInfo",promptInfoDesc);
        jedis.set("promptInfoList", promptInfoList);
        jedis.set("promptInfoMap", promptInfoMap);
        jedis.expire("promptInfoSendId", 10);

    }
}

