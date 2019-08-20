package com.fool.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fool.redis_dao.JIRedisDao;
import com.fool.service.CommonService;
import com.fool.entity.JimdoInvagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    JIRedisDao jiRedisDao;

    /**
     * 获取导航资源
     */
    public JSONObject getJis() {
        List<JimdoInvagation> list = jiRedisDao.queryAll();
        JSONArray jsonList = JSONObject.parseArray(JSON.toJSONString(list));
        JSONObject body = new JSONObject();
        body.put("list", jsonList);
        return body;
    }

}


