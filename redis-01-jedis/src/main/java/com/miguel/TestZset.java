package com.miguel;

import redis.clients.jedis.Jedis;

public class TestZset {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println("zadd: 给zset集合增加一个元素: " + jedis.zadd("myzset", 100, "xiubaibai"));
        System.out.println("zadd: 给zset集合增加一个元素: " + jedis.zadd("myzset", 150, "xiuheihei"));
        System.out.println("zadd: 给zset集合增加一个元素: " + jedis.zadd("myzset", 2500, "xiukonglong"));
        System.out.println("zadd: 给zset集合增加一个元素: " + jedis.zadd("myzset", 800, "maomao"));
        System.out.println("zadd: 给zset集合增加一个元素: " + jedis.zadd("myzset", 1600, "mickey"));
        System.out.println("zrange: 查询zset在指定索引范围内的元素,分数从低到高: " + jedis.zrange("myzset", 0, -1));
        System.out.println("zrevrange: 查询zset在指定索引范围内的元素,分数从高到低: " + jedis.zrevrange("myzset", 0, -1));
        System.out.println("zrangebyscore: 查询zset中分数在指定范围内的元素: " + jedis.zrangeByScore("myzset", 0, 1000));
        System.out.println("zrem: 从zset中移除指定元素: " + jedis.zrem("myzset", "mickey"));
        System.out.println("zcard: 查看zset中的元素数量: " + jedis.zcard("myzset"));
        System.out.println("zcount: 统计zset在指定索引范围内的元素个数: " + jedis.zcount("myzset", 0, 800));
        // 3.断开连接
        jedis.close();
    }
}
