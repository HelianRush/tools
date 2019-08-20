package com.fool.controller;

import com.fool.entity.FoolUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Controller
public class ManagerIndexController implements Serializable {

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        FoolUser userinfo = (FoolUser) subject.getPrincipal();
        model.addAttribute("user", userinfo.getAccount());
        return "index";
    }

    @RequestMapping(value = "/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String login(FoolUser user, HttpServletRequest request, Model model) {

        boolean rememberMe = (request.getParameter("rememberMe") == null) ? false : true;

        // 获取Subject
        Subject subject = SecurityUtils.getSubject();

        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), rememberMe);

        // 执行登录
        try {
            subject.login(token);
            model.addAttribute("user", user.getAccount());
            return "redirect:index";
        } catch (UnknownAccountException e) {
            // 用户名不存在
            model.addAttribute("errorMsg", "用户名不存在");
            // e.printStackTrace();
            System.out.println("用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            // 密码错误
            model.addAttribute("errorMsg", "密码错误");
            // e.printStackTrace();
            System.out.println("密码错误");
            return "login";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "login";
        }

    }

    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            subject.logout();
        }
        return "login";
    }

}
