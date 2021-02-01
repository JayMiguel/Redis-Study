package com.miguel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisGeoCommands.*;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        // 五大数据类型
        redisTemplate.opsForValue().set("name", "miguel");
        redisTemplate.opsForHash().put("myhash", "age", 10);
        redisTemplate.opsForList().leftPush("mylist", "redis");
        redisTemplate.opsForSet().add("myset", "java");
        redisTemplate.opsForZSet().add("myzset", "zhangsan", 100);
        // 三种特殊类型
        redisTemplate.opsForGeo().add("mygeo", new GeoLocation("Beijing", new Point(116.23, 40.22)));
        redisTemplate.opsForHyperLogLog().add("mylog", "redis", "mongodb", "mysql");
        redisTemplate.opsForValue().setBit("mybitmap", 0, false); //opsForValue包含操作String和Bitmap的方法
        // 获取连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
    }

}