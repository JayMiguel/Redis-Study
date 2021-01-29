package com.miguel.data;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class TestHash {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println("hset: 给一个hash设置一个字段: "+jedis.hset("myhash", "username", "winnie"));
        System.out.println("hget: 查询hash中指定的字段: " + jedis.hget("myhash", "username"));
        Map<String, String> user = new HashMap<String, String>();
        user.put("name", "dudu");
        user.put("age", "2");
        System.out.println("hmset: 给一个hash设置多个字段: " + jedis.hmset("myhash", user));
        System.out.println("hmget: 查询hash中指定的多个字段: " + jedis.hmget("myhash", "name", "age"));
        System.out.println("hgetall: 查询hash中全部字段名和值: " + jedis.hgetAll("myhash"));
        System.out.println("hlen: 查看hash的字段数量: " + jedis.hlen("myhash"));
        System.out.println("hexist: 查看hash中是否存在某字段: " + jedis.hexists("myhash", "name"));
        System.out.println("hkeys: 查看hash中全部字段名: " + jedis.hkeys("myhash"));
        System.out.println("hvals: 查看hash中全部字段值: " + jedis.hvals("myhash"));
        System.out.println("hincrby: 给hash中指定字段加上一个数: " + jedis.hincrBy("myhash", "age", 5));
        // 3.断开连接
        jedis.close();
    }
}
