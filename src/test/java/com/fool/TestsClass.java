package com.fool;

import com.fool.entity.JimdoInvagation;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ToolsApplication.class)
public class TestsClass {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisTest() {

        String caches = stringRedisTemplate.opsForValue().get("caches");
        System.out.println(caches);


//        List<JimdoInvagation> list = new ArrayList<>();
        JimdoInvagation ji1 = new JimdoInvagation("1001", "1", "首页", "index", "1", "0",1);
//        list.add(ji1);
        JSONObject json1 = JSONObject.fromObject(ji1);
        stringRedisTemplate.opsForList().leftPush("JI", json1.toString());

        JimdoInvagation ji2 = new JimdoInvagation("1002", "2", "博客", "blog", "1", "0",2);
//        list.add(ji2);
        JSONObject json2 = JSONObject.fromObject(ji2);
        stringRedisTemplate.opsForList().leftPush("JI", json2.toString());

//        JSONArray jiList = new JSONArray();
//        jiList.put(ji1);
//        jiList.put(ji2);

        // stringRedisTemplate.opsForList().leftPush("JI"json2 );
        System.out.println("in Redis");

        JimdoInvagation ji3 = new JimdoInvagation("1003", "3", "GitHub", "GitHub", "1", "0",3);
        JSONObject json = JSONObject.fromObject(ji3);
        stringRedisTemplate.opsForList().leftPush("JI", json.toString());


        List<String> jiListStr = stringRedisTemplate.opsForList().range("JI", 0, -1);

        for (String s : jiListStr) {
            System.out.println(s);
            JSONObject stemp = JSONObject.fromObject(s);

            System.out.println(stemp);

            //JimdoInvagation temp = (JimdoInvagation) JSONObject.toBean(stemp, JimdoInvagation.class);
            // System.out.println(temp);
        }


    }
}
