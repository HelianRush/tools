package com.fool;

import com.fool.redis_dao.JIRedisDao;
import com.fool.entity.JimdoInvagation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ToolsApplication.class)
public class TestClass2 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private JIRedisDao jiDao = new JIRedisDao();

    @Test
    public void redisTest() {
        JimdoInvagation ji1 = new JimdoInvagation("JI1001", "1001", "首页", "index", "1", "0", 1.0);
        jiDao.add(ji1);
    }

    @Test
    public void redisTest2() {
        JimdoInvagation ji1 = new JimdoInvagation("JI1001", "1001", "首页", "index", "1", "0", 1.0);
        jiDao.remove(ji1);
    }

    @Test
    public void redisTest3s() {
//        JSONArray jsonArray = jiDao.queryAll();

//        System.out.println(jsonArray);
    }
}
