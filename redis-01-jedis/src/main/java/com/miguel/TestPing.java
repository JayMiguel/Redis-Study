package com.miguel;

import redis.clients.jedis.Jedis;

public class TestPing {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println(jedis.ping());
        // 3.断开连接
        jedis.close();
    }
}
