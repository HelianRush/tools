package com.fool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/temp")
public class TempController {
    @RequestMapping(value = "/doAdd")
    public String doAdd() {
        return "doAdd";
    }

}
