package com.fool.controller;

import com.fool.entity.FoolUser;
import com.fool.service.FoolUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FoolUserController {

    @Autowired
    private FoolUserService foolUserService;

    /**
     * 注册
     *
     * @param foolUser
     * @return
     */
    public String SignIn(FoolUser foolUser) {
        boolean flog = foolUserService.SignIn(foolUser);
        return "";
    }
}
