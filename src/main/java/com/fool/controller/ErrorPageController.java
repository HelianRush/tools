package com.fool.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController {

    @RequestMapping(value = "/400")
    public String error400(Model model) {
        model.addAttribute("errorCode", "400");
        return "error";
    }

    @RequestMapping(value = "/401")
    public String error401(Model model) {
        model.addAttribute("errorCode", "401");
        return "error";
    }

    @RequestMapping(value = "/403")
    public String error4403(Model model) {
        model.addAttribute("errorCode", "403");
        return "error";
    }

    @RequestMapping(value = "/404")
    public String error404(Model model) {
        model.addAttribute("errorCode", "404");
        return "error";
    }

    @RequestMapping(value = "/500")
    public String error500(Model model) {
        model.addAttribute("errorCode", "500");
        return "error";
    }
}
