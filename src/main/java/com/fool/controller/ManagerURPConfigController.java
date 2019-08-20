package com.fool.controller;


import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;
import com.fool.entity.FoolUser;
import com.fool.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/urpCfgCtrl")
public class ManagerURPConfigController {

    @Autowired
    FoolUserService userService;
    @Autowired
    FoolRoleService roleService;
    @Autowired
    FoolPermissionService permissionService;
    @Autowired
    FoolUnUserRoleService unUserRoleService;
    @Autowired
    FoolUnRolePermissionService unRolePermissionService;

    /**
     * 访问 manager_urp_config.html
     *
     * @return
     */
    @RequestMapping(value = "/showUrpConfig")
    public String showUrpConfig(Model model) {
        List<FoolUser> userList = userService.queryUserListForConfig();

        List<FoolRole> roleList = new ArrayList<>();
        List<FoolPermission> permList = new ArrayList<>();

        model.addAttribute("userList", userList);
        model.addAttribute("roleList", roleList);
        model.addAttribute("permList", permList);

        return "manager_urp_config";
    }

    /**
     * 根据用户查询
     * 获取用户匹配与不匹配的角色集合
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getRoleListByUserId")
    public String getRoleListByUserId(@RequestParam(name = "userId") String userId, Model model) {
        List<FoolRole> roleList = roleService.SelectRolesByUser(userId);
//        System.out.println("userId ===== getRoleListByUserId ===== " + userId);
//        for (FoolRole temp : roleList) {
//            System.out.println(temp.toString());
//        }
        model.addAttribute("roleList", roleList);
        return "manager_urp_config::roleTable";
    }

    /**
     * 根据用户查询
     * 获取用户匹配的权限集合
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getPermListByUserId")
    public String getPermListByUserId(@RequestParam(name = "userId") String userId, Model model) {
        List<FoolPermission> permList = permissionService.SelectPermListByUserId(userId);
        model.addAttribute("permList", permList);
        return "manager_urp_config::permissionTable";
    }

    /**
     * 配置用户的角色
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/setRole2User")
    public String setRole2User(String userId, String rolesId, Model model) {

        // 如果 User为空，返回页面
        if (StringUtils.isBlank(userId))
            return "manager_urp_config";

        // 如果 Role[]为空，删除User下全部配置，返回页面
        if (StringUtils.isBlank(rolesId)) {
            unUserRoleService.DeleteRolesByUser(userId);
            return "manager_urp_config";
        }

        // 执行配置,返回页面
        String[] arrayRoles = null;
        if (null != userId && null != rolesId) {
            arrayRoles = rolesId.split(",");
        }
        boolean editFlag = unUserRoleService.EditRoleByUser(userId, arrayRoles);
        if (editFlag) {
            List<FoolRole> roleList = roleService.SelectRolesByUser(userId);
            model.addAttribute("roleList", roleList);
            return "manager_urp_config::roleTable";
        } else
            return "manager_urp_config";
    }
}
