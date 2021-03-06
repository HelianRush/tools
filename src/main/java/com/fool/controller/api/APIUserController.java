package com.fool.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.fool.entity.FoolUser;
import com.fool.service.FoolUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping(value = "/apiuser")
public class APIUserController {

    private static Logger logger = LoggerFactory.getLogger(APIUserController.class);

    @Autowired
    private FoolUserService foolUserService;

    /**
     * 查询用户是否存在
     *
     * @param username
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/checkName")
    public boolean CheckName(@RequestParam(value = "username") String username, @RequestParam(value = "signInTime") Date signInTime) {
        // System.out.println(signInTime);
        return foolUserService.CheckName(username);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/signIn")
    public String signIn(FoolUser user, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        if (foolUserService.CheckName(user.getUsername())) {
            boolean b = foolUserService.SignIn(user);
            json.put("code", "success");
        }
        return json.toJSONString();
    }

}
