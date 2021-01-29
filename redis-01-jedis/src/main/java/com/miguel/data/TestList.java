package com.miguel.data;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

public class TestList {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println("lpush: 向list的左侧新增元素: " + jedis.lpush("mylist", "redis", "mongodb", "mysql"));
        System.out.println("rpush: 向list的右侧新增元素: " + jedis.rpush("mylist", "oracle", "rabbitmq"));
        System.out.println("lpop: 从list的左侧移除元素: " + jedis.lpop("mylist"));
        System.out.println("rpop: 从list的右侧移除元素: " + jedis.rpop("mylist"));
        System.out.println("lindex: 通过索引查找list中的元素: " + jedis.lindex("mylist", 2));
        System.out.println("lrange: 遍历list中一定范围的元素: " + jedis.lrange("mylist", 0, 2));
        System.out.println("llen: 查看list的长度: " + jedis.llen("mylist"));
        System.out.println("lrem: 移除list中的指定元素: " + jedis.lrem("mylist", 1, "mongodb"));
        System.out.println("ltrim: 截取list中指定索引范围的元素: " + jedis.ltrim("mylist", 0, 1));
        System.out.println("lset: 修改list中指定索引的元素: " + jedis.lset("mylist", 1, "vue"));
        System.out.println("linsert: 向list中指定元素的前面或后面插入元素: " + jedis.linsert("mylist", ListPosition.BEFORE, "vue", "java"));
        // 3.断开连接
        jedis.close();
    }
}
