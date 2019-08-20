package com.fool.controller;

import com.fool.sys_common.SystemCode;
import com.fool.entity.JimdoInvagation;
import com.fool.service.JimdoInvagationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/MJICtl")
public class ManageJIController {

    @Autowired
    private JimdoInvagationService jiService;

    @RequestMapping(value = "/show")
    public String show(Model model) {
        List<JimdoInvagation> list = jiService.queryAll();
        model.addAttribute("list", list);
        // 返回
        return "manager_ji";
    }

    @RequestMapping(value = "/add")
    public String add(Model model, JimdoInvagation ji) {
        jiService.add(ji);
        List<JimdoInvagation> list = jiService.queryAll();
        model.addAttribute("list", list);
        // return "manage_ji";
        // return "redirect:/show";
        return "redirect:/MJICtl/show";
    }

    @RequestMapping(value = "/edit")
    public String edit() {
        return "manager_ji";
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String remove(Model model, @RequestParam double score) {
        Long flag = jiService.removeByScore(score);
        if (0 < flag)
            return SystemCode.SUCCESS.getCode();
        else
            return SystemCode.ERROR.getCode();
    }

    // 获取导航列表
    public List<JimdoInvagation> getList() {
        List<JimdoInvagation> list = null;
        return list;
    }

    // 获取父级列表
    public Map<String, JimdoInvagation> getParentMap() {
        Map<String, JimdoInvagation> map = null;
        return map;
    }
}
