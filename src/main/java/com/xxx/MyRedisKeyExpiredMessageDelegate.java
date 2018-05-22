package com.xxx;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/16.
 */
public class MyRedisKeyExpiredMessageDelegate implements  MyMessageDelegate {
    @Override
    public void handleMessage(String message) {
        System.out.println("void handleMessage(String message)被调动了....");
    }

    @Override
    public void handleMessage(Map message) {
        System.out.println("void handleMessage(Map message)被调动了....");
    }

    @Override
    public void handleMessage(byte[] message) {
        System.out.println("void handleMessage(byte[] message)被调动了....");
    }

    @Override
    public void handleMessage(Serializable message) {
        System.out.println("void handleMessage(Serializable message)被调动了....");
    }

    @Override
    public void handleMessage(Serializable message, String channel) {
        System.out.println("void handleMessage(Serializable message, String channel)被调动了....");
    }
}
