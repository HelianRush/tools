package com.fool.controller;

import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;
import com.fool.entity.FoolUser;
import com.fool.service.urpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class urpCtl {

    @Autowired
    urpService urpService;

    @RequestMapping(value = "/showUPR")
    public String showUPR(Model model) {
        List<FoolUser> fulist = urpService.queryFoolUser();
        model.addAttribute("fulist", fulist);

        List<FoolRole> frlist = urpService.queryFoolRole();
        model.addAttribute("frlist", frlist);

        List<FoolPermission> fplist = urpService.queryFoolPermission();
        model.addAttribute("fplist", fplist);

        return "manager_urp";
    }

    @RequestMapping(value = "/addUser")
    public String addUser(FoolUser foolUser) {
        urpService.addFoolUser(foolUser);
        return "manager_urp";
    }

    @RequestMapping(value = "/addRole")
    public String addFoolRole(FoolRole foolRole) {
        urpService.addFoolRole(foolRole);
        return "manager_urp";
    }

    @RequestMapping(value = "/addPermission")
    public String addFoolPermission(FoolPermission foolPermission) {
        urpService.addFoolPermission(foolPermission);
        return "manager_urp";
    }

    @RequestMapping(value = "/queryFoolUser")
    public String queryFoolUser(Model model) {
        model.addAttribute("fulist", urpService.queryFoolUser());
        return "redirect:manager_urp";
    }

    @RequestMapping(value = "/queryFoolRole")
    public String queryFoolRole(Model model) {
        model.addAttribute("frlist", urpService.queryFoolRole());
        return "redirect:manager_urp";
    }

    @RequestMapping(value = "/queryFoolPermission")
    public String queryFoolPermission(Model model) {
        model.addAttribute("fplist", urpService.queryFoolPermission());
        return "redirect:manager_urp";
    }

    /**
     * ********** ********** ********** ********** **********
     */

    @ResponseBody
    @RequestMapping(value = "/tryString")
    public boolean tryString(Model model) {
        //
        model.addAttribute("user", "admin");
        return true;
    }
}
