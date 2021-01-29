package com.miguel.special;

import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import java.util.List;

public class TestGeo {

    public static void main(String[] args) {
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.命令操作
        System.out.println("geoadd: 新增地理位置到geo集合中: " +jedis.geoadd("mygeo", 116.23128, 40.22077, "Beijing"));
        System.out.println("geoadd: 新增地理位置到geo集合中: " + jedis.geoadd("mygeo", 121.48941, 31.40527, "Shanghai"));
        System.out.println("geoadd: 新增地理位置到geo集合中: " + jedis.geoadd("mygeo", 113.27324, 23.15792, "Guangzhou"));
        System.out.println("geoadd: 新增地理位置到geo集合中: " + jedis.geoadd("mygeo", 113.88308, 22.55329, "Shenzhen"));
        System.out.println("geoadd: 新增地理位置到geo集合中: " + jedis.geoadd("mygeo", 112.69828, 22.37702, "Kaiping"));
        System.out.println("geopos: 查询geo集合中某个地理位置的经纬度: " + jedis.geopos("mygeo", "Guangzhou"));
        System.out.println("geodist: 查询geo集合中两个地理位置的直线距离: " + jedis.geodist("mygeo", "Beijing", "Kaiping"));
        System.out.print("georadius: 查询以某经纬度为中心，指定半径范围内的地理位置: [");
        List<GeoRadiusResponse> radiusGeoList = jedis.georadius("mygeo", 113.27324, 23.15792, 500, GeoUnit.KM);
        for (int i=0; i < radiusGeoList.size(); i++) {
            String member = radiusGeoList.get(i).getMemberByString();
            if (i == radiusGeoList.size() - 1) {
                System.out.println(member + "]");
            } else {
                System.out.print(member + ", ");
            }
        }
        System.out.print("georadiusbymembers: 查询以某地为中心，指定半径范围内的地理位置: [" );
        List<GeoRadiusResponse> geoList = jedis.georadiusByMember("mygeo", "Guangzhou", 500, GeoUnit.KM);
        for (int i=0; i < geoList.size(); i++) {
            String member = geoList.get(i).getMemberByString();
            if (i == geoList.size() - 1) {
                System.out.println(member + "]");
            } else {
                System.out.print(member + ", ");
            }
        }
        System.out.println("geohash: 计算某地的经纬度的哈希值: " + jedis.geohash("mygeo", "Beijing"));
        // 3.断开连接
        jedis.close();
    }
}
