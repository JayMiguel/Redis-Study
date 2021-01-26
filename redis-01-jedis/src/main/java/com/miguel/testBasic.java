package com.miguel;

import redis.clients.jedis.Jedis;

public class testBasic {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("select: 选择索引为3的数据库: " + jedis.select(3));
        System.out.println("选择索引为0的数据库: " + jedis.select(0));
        System.out.println("set: 设置<'username','Miguel'>键值对: " + jedis.set("username", "Miguel"));
        System.out.println("dbsize: 查看数据库的key数量: " + jedis.dbSize());
        System.out.println("keys: 查看所有的key: " + jedis.keys("*"));
        System.out.println("exists: 查看某个key是否存在: " + jedis.exists("username"));
        System.out.println("move: 移动username到数据库3: " + jedis.move("username", 3));
        System.out.println("del: 删除某个key: " + jedis.del("username"));
        System.out.println("flushdb: 清空当前数据库: " + jedis.flushDB());
        System.out.println("flushall: 清空所有数据库: " + jedis.flushAll());
        System.out.println("设置<'password','123456'>键值对: " + jedis.set("password", "123456"));
        System.out.println("expire: 设置key<'password'>的时效: " + jedis.expire("password", 30));
        System.out.println("ttl: 查看key<'password'>的时效: " + jedis.ttl("password"));
        System.out.println("type: 查看key<'password'>的类型: " + jedis.type("password"));
    }
}
