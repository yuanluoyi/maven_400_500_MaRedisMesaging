package company.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/20.
 */
public class GSonTest {
    public static void main(String[] args) {
        /*
        //1>声明
        //2>初始化
        //3>对象或集合转为JSON
        //4>JSON转化为对象或集合
        //5>显示结果
         */
        //1>声明
        Gson gson=new GsonBuilder().create();
        PromptInfo promptInfo=new PromptInfo("PI10001","回款催单提醒","订单ID:PO10001,联系人:孙亮,电话:13681253123");
        List<PromptInfo> list=new ArrayList<PromptInfo>();
        Map<String,PromptInfo> map=new HashMap<String,PromptInfo>();
        //2>初始化
        list.add(promptInfo);
        map.put(promptInfo.getId(),promptInfo);
        //3>对象或集合转为JSON
        String json = gson.toJson(promptInfo);
        String jsonList = gson.toJson(list);
        String jsonMap = gson.toJson(map);
        //4>JSON转化为对象或集合
        PromptInfo promptInfo05=gson.fromJson(json,PromptInfo.class);
        list=gson.fromJson(jsonList,new TypeToken<List<PromptInfo>>() {}.getType());
        map=gson.fromJson(jsonMap,new TypeToken<Map<String,PromptInfo>>() {}.getType());
        //5>显示结果
        System.out.println("json:"+json);
        System.out.println("promptInfo05:"+promptInfo05);
        System.out.println("list:"+list);
        System.out.println("map:"+map);
    }
}
