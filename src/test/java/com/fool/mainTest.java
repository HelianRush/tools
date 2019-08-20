package com.fool;

import com.fool.entity.JimdoInvagation;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class mainTest {
    public static void main(String[] args) {
    }


    //spring boot to redis




    {
        /**
         * RushServer
         * 118.24.32.28 : 22
         * 0O0Ofeifly1220
         *
         * 命令：/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf
         * 查看redis进程：ps -ef | grep 6379
         */
        Jedis jedis = new Jedis("118.24.32.28", 6379);
        jedis.select(1);
        System.out.println("running" + jedis.ping());

        Set<String> keys = jedis.keys("name");


        jedis.set("name", "rush");
        String str = jedis.get("name");
        System.out.println(str);

        List<JimdoInvagation> list = new ArrayList<>();
        JimdoInvagation ji1 = new JimdoInvagation("1001", "1001", "首页", "index", "1", "0",1);
        list.add(ji1);

        JimdoInvagation ji2 = new JimdoInvagation("1002", "1002", "博客", "blog", "1", "0",2);
        list.add(ji2);

        JSONObject ji1Json = JSONObject.fromObject(ji1);
        JSONObject ji2Json = JSONObject.fromObject(ji2);

        jedis.zadd("JI", 1, ji1Json.toString());
        jedis.zadd("JI", 2, ji2Json.toString());


        Set<String> JIList = jedis.zrange("JI", 0, -1);

        for (String obj : JIList) {
//            JSONObject json = JSONObject.fromObject(obj);
//            JimdoInvagation ji = (JimdoInvagation) JSONObject.toBean(json, JimdoInvagation.class);

            System.out.println(obj);
        }
    }
}
