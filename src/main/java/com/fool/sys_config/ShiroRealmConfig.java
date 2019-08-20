package com.fool.sys_config;

import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;
import com.fool.entity.FoolUser;
import com.fool.service.FoolUserService;
import com.fool.service.LoginService;
import com.fool.service.FoolPermissionService;
import com.fool.service.FoolRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShiroRealmConfig extends AuthorizingRealm {

    @Autowired
    LoginService loginService;

    @Autowired
    FoolUserService userService;
    @Autowired
    FoolRoleService roleService;
    @Autowired
    FoolPermissionService permissionService;


    /**
     * 授权用户权限
     * <p>
     * 用户进行权限验证时候Shiro会去缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行:AuthorizationInfo,权限授权");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 为当前用户授权
        // addStringPermission("授权字符串，与 filterChainDefinitionMap 中授权过滤器的值需一致")
        // authorizationInfo.addStringPermission("administrator");

        // 获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        FoolUser userInfo = (FoolUser) subject.getPrincipal();
        List<FoolRole> roles = roleService.SelectRoleByUserId(userInfo.getUserId());
        List<FoolPermission> permissions = permissionService.SelectPermissionByRoles(roles);

        Set<String> setRoles = new HashSet<>();
        for (FoolRole role : roles) {
            setRoles.add(role.getRoleName());
        }
        Set<String> setPermissions = new HashSet<>();
        for (FoolPermission permission : permissions) {
            setPermissions.add(permission.getPerName());
        }

        authorizationInfo.setRoles(setRoles);
        authorizationInfo.setStringPermissions(setPermissions);

        return authorizationInfo;
    }

    /**
     * 验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行:AuthenticationInfo,用户认证");

        // 获取页面用户信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 获取数据库用户信息
        FoolUser userInfo = loginService.QueryByUsername(token.getUsername());

        // 如果查询为空 Shiro抛出UnknownAccountException
        if (null == userInfo) {
            return null;
        }

        // 如果锁定，抛出异常
        if (!userInfo.getLockStatus()) {
            throw new LockedAccountException();
        }

        /*
         * 执行验证
         * SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
         *  principal   : 为Subject传入Principal，传入用户信息
         *  credentials : 数据库中的密码
         *  realmName   : Realm名称
         * */
        // 无盐验证
        // return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), this.getName());
        // 加盐验证
        // String salt = userInfo.getSalt(); // 盐
        ByteSource salt = ByteSource.Util.bytes(userInfo.getSalt());
        return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), salt, this.getName());
    }

}
