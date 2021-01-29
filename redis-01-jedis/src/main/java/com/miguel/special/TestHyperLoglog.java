package com.miguel.special;

import redis.clients.jedis.Jedis;

public class TestHyperLoglog {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println("pfadd: 新增元素到一个HyperLoglog中: " + jedis.pfadd("myLog1", "redis", "mongodb", "mysql"));
        System.out.println("pfadd: 新增元素到另一个HyperLoglog中: " + jedis.pfadd("myLog2", "redis", "oracle", "mysql", "sqlserver"));
        System.out.println("pfcount: 统计HyperLoglog中的基数: " + jedis.pfcount("myLog1"));
        System.out.println("pfcount: 统计HyperLoglog中的基数: " + jedis.pfcount("myLog2"));
        System.out.println("pfmerge: 合并多个HyperLoglog到另一个HyperLoglog中: " + jedis.pfmerge("myLog", "myLog1", "myLog2"));
        System.out.println("pfcount: 统计HyperLoglog中的基数: " + jedis.pfcount("myLog"));
        // 3.断开连接
        jedis.close();
    }
}
