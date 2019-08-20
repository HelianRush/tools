package com.fool;

import com.alibaba.fastjson.JSONObject;
import com.fool.entity.api.JSON_DATA;
import com.fool.entity.api.JSON_HEAD;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ToolsApplication.class)
public class JsonTest {
    @Test
    public void redisTest() {
        JSON_DATA data = new JSON_DATA();
        JSON_HEAD head = new JSON_HEAD();
        head.setRequestCode("200");
        head.setRequestMsg("成功");
        head.setResponseCode("200");
        head.setResponseMsg("成功");
        head.setApiCode("jis");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key", "null");
        head.setParameters(parameters);
        data.setHead(head);
        JSONObject body = new JSONObject();
        data.setBody(body);

        JSONObject request = new JSONObject();
        request.put("data",data);

        System.out.println(request.toJSONString());

    }
}
