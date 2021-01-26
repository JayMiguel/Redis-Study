package com.miguel;

import redis.clients.jedis.Jedis;

public class TestString {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println("set: 设置<'name','Miguel'>键值对: " + jedis.set("name", "Miguel"));
        System.out.println("get: 查询name的值: " + jedis.get("name"));
        System.out.println("append: 在name后面拼接字符串: " + jedis.append("name", "_pan"));
        System.out.println("strlen: 查看name的字符串长度: " + jedis.strlen("name"));
        System.out.println("getrange: 截取name的前6个字符: " + jedis.getrange("name", 0, 5));
        System.out.println("setrange: 修改name第7个及后面的字符: " + jedis.setrange("name", 6, "&winnie"));
        System.out.println("设置<'age', '10'>键值对: " + jedis.set("age", "10"));
        System.out.println("incr: 使age的值自增1: " + jedis.incr("age"));
        System.out.println("incrby: 使age的值加上5: " + jedis.incrBy("age", 5));
        System.out.println("decr: 使age的值自减1: " + jedis.decr("age"));
        System.out.println("decrby: 使age的值减去5: " + jedis.decrBy("age", 5));
        // 3.断开连接
        jedis.close();
    }
}
