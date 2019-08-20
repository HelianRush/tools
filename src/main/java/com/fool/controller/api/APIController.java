package com.fool.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.fool.entity.FoolUser;
import com.fool.entity.api.JSON_DATA;
import com.fool.entity.api.JSON_HEAD;
import com.fool.service.FoolUserService;
import com.fool.sys_common.ApiCode;
import com.fool.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * API
 */
@Controller
//@RequestMapping(value = "api")
public class APIController {

    @Autowired
    private CommonService commonService;

    private static Logger logger = LoggerFactory.getLogger(APIController.class);

    @ResponseBody
    @RequestMapping(value = "/apiCtl")
    public String apiMain(@RequestBody JSONObject requestJosn) {

        JSON_DATA response = APIController.getJosn(requestJosn);

        final String apiCode = response.getHead().getApiCode();
        // Map<String, Object> parameters = data.getHead().getParameters();

        switch (apiCode) {
            case ApiCode.JI_LIST:
                JSONObject body = commonService.getJis();
                response.setBody(body);
                break;

            case ApiCode.SIGN_IN:
                // JSONObject body = commonService.getJis();
                // response.setBody(body);
                break;
        }

        JSON_HEAD head = new JSON_HEAD();
        head.setResponseCode(ApiCode.SUCCESS.getCode());
        head.setResponseMsg(ApiCode.SUCCESS.getMessage());
        response.setHead(head);

        return ((JSONObject) JSONObject.toJSON(response)).toJSONString();
    }

    public static JSON_DATA getJosn(JSONObject request) {
        JSON_DATA data = null;
        JSONObject dataJson = request.getJSONObject("data");
        if (null != dataJson) {
            data = JSONObject.parseObject(dataJson.toJSONString(), JSON_DATA.class);
            // System.out.println(data);
        } else {
            data = new JSON_DATA();
            JSON_HEAD head = new JSON_HEAD();
            head.setApiCode("URL_ERROR");
            head.setResponseCode("DATA_ERROR");
            head.setResponseMsg("请求数据不正确！");
            head.setParameters(new HashMap<String, Object>());
            data.setHead(head);
            data.setBody(new JSONObject());
            // System.out.println(data);
            logger.error(System.currentTimeMillis() + " " + logger.getName() + ":请求数据错误");
        }
        return data;
    }


}
