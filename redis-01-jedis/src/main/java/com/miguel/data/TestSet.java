package com.miguel.data;

import redis.clients.jedis.Jedis;

public class TestSet {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println("sadd: 给set集合增加一个元素: " + jedis.sadd("myset", "maomao"));
        System.out.println("sadd: 给set集合增加一个元素: " + jedis.sadd("myset", "dudu"));
        System.out.println("sadd: 给set集合增加一个元素: " + jedis.sadd("myset", "mickey"));
        System.out.println("sadd: 给set集合增加一个元素: " + jedis.sadd("myset", "xiubaibai"));
        System.out.println("smembers: 查询set集合中的全部元素: " + jedis.smembers("myset"));
        System.out.println("sismember: 判断set集合中是否存在某个元素: " + jedis.sismember("myset", "mickey"));
        System.out.println("scard: 查看set集合的大小: " + jedis.scard("myset"));
        System.out.println("srem: 移除set集合中的指定元素: " + jedis.srem("myset", "xiubaibai"));
        System.out.println("srandmember: 随机返回set集合中一个元素: " + jedis.srandmember("myset"));
        System.out.println("srandmember: 随机返回set集合中指定个数的元素: " + jedis.srandmember("myset", 2));
        System.out.println("spop: 随机移除set集合中一个元素并返回: " + jedis.spop("myset"));
        System.out.println("smove: 移动set集合中指定元素到另一个set集合: " + jedis.smove("myset", "newset", "dudu"));
        System.out.println("sdiff: 返回指定set集合与其他指定set集合的差集: " + jedis.sdiff("myset", "newset"));
        System.out.println("sinter: 返回指定set集合与其他指定set集合的交集: " + jedis.sinter("myset", "newset"));
        System.out.println("sunion: 返回指定set集合与其他指定set集合的并集: " + jedis.sunion("myset", "newset"));
        // 3.断开连接
        jedis.close();
    }
}
