package com.fool.sys_config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import com.fool.sys_tool.SaltTools;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    /**
     * ShiroFilterFactoryBean
     * 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {

        //
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器
        /*
         *   Url = Value
         *   authc   : 必须认证通过才可以访问
         *   anon    : 可以匿名访问
         *   user    : 配置记住我或认证通过可以访问,如果使用rememberMe的功能可以直接访问
         *   perms   : 该资源必须得到资源授权才可以访问
         *   role    : 该资源必须得到角色权限才可以访问
         *
         *   LinkedHashMap 有序Map集合，Shiro会根据顺序拦截
         * */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();

//        filterMap.put("/apiuser/**", "anon");
//        filterMap.put("/apiCtl", "anon");
//        filterMap.put("/ocrCtl/**", "anon");
//        filterMap.put("/pdfCtl/**", "anon");
//        filterMap.put("/login", "anon"); // 登录页面
//        filterMap.put("/doLogin", "anon"); // 登录请求
//        filterMap.put("/index", "authc"); // 首页
//        filterMap.put("/logout", "logout"); // 退出
//        filterMap.put("/favicon.ico", "anon");

        // 授权过滤器
        // filterMap.put("/temp/*", "perms[user:admin]");
        // filterMap.put("/temp/**", "role[administrator]");

        // filterMap.put("/**", "authc");
        filterMap.put("/**", "anon");

        /*  */
        String loginUrl = "/login";
        String successUrl = "/index";
        String unauthorizedUrl = "/403";
        shiroFilterFactoryBean.setLoginUrl(loginUrl); // 登陆地址
        shiroFilterFactoryBean.setSuccessUrl(successUrl); // 登录成功访问地址
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl); // 未授权界面 /403
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager 安全管理器
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("realm") ShiroRealmConfig realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置Realm
        securityManager.setRealm(realm);
        // 设置Cookie
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * Realm
     */
//    @Bean(name = "realm")
//    public ShiroRealmConfig realm() {
//        ShiroRealmConfig realm = new ShiroRealmConfig();
//        return realm;
//    }
    @Bean(name = "realm")
    public ShiroRealmConfig realm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        ShiroRealmConfig realm = new ShiroRealmConfig();
        // 设置加密方式
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * at.pollux.thymeleaf.shiro.dialect.ShiroDialect
     * 配置ShiroDialect,用于Thymeleaf配合Shiro标签配合使用
     */
    @Bean // (name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 加密配置
     */
//    @Bean(name = "credentialsMatcher")
//    public CredentialsMatcher credentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName(SaltTools.HASH_ALGORITHM_SHA256);
//        hashedCredentialsMatcher.setHashIterations(SaltTools.HASH_ITERATIONS);
//        return hashedCredentialsMatcher;
//    }
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(SaltTools.HASH_ALGORITHM_SHA256);
        hashedCredentialsMatcher.setHashIterations(SaltTools.HASH_ITERATIONS);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * cookie对象
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //System.out.println("ShiroConfiguration.rememberMeCookie()");

        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

}
