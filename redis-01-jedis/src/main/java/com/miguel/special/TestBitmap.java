package com.miguel.special;

import redis.clients.jedis.Jedis;

public class TestBitmap {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println("setbit: 在位图的指定位置设置一个值: " + jedis.setbit("mybitmap", 0, false));
        System.out.println("setbit: 在位图的指定位置设置一个值: " + jedis.setbit("mybitmap", 1, true));
        System.out.println("setbit: 在位图的指定位置设置一个值: " + jedis.setbit("mybitmap", 2, true));
        System.out.println("setbit: 在位图的指定位置设置一个值: " + jedis.setbit("mybitmap", 3, true));
        System.out.println("setbit: 在位图的指定位置设置一个值: " + jedis.setbit("mybitmap", 4, false));
        System.out.println("getbit: 查询位图中指定位置的值: " + jedis.getbit("mybitmap", 0));
        System.out.println("getbit: 查询位图中指定位置的值: " + jedis.getbit("mybitmap", 1));
        System.out.println("getbit: 查询位图中指定位置的值: " + jedis.getbit("mybitmap", 2));
        // 3.断开连接
        jedis.close();
    }
}
