package com.madd.template.config.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//    @Bean(name="shiroFilterFactoryBean")
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        //设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        /**添加Shiro内置过滤器
//         * Shiro内置过滤器，可以实现权限相关的拦截器,常用的过滤器：
//         * anon: 无需认证（登录）可以访问,authc: 必须认证才可以访问,user: 如果使用rememberMe的功能可以直接访问,
//         * perms： 该资源必须得到资源权限才可以访问,role: 该资源必须得到角色权限才可以访问*/
//        Map<String, String> filterMap = new LinkedHashMap<String, String>();
//        // 放行login.html页面
//        filterMap.put("/login", "anon"); // 要将登陆的接口放出来，不然没权限访问登陆的接口
//        filterMap.put("/doLogin", "anon");
//        filterMap.put("/logout", "logout");
//        // 授权过滤器
//        // 注意：当前授权拦截后，shiro会自动跳转到未授权页面
//        filterMap.put("/**", "authc");
//        // TODO 此处我做过修改
//        //shiroFilterFactoryBean.setLoginUrl("/"); // 修改调整的登录页面
//        //shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized"); // 设置未授权提示页面
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//        return shiroFilterFactoryBean;
//    }

    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") MyRealm myShiroRealm) {
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm);
        return manager;
    }
    //配置自定义的权限登录器
    @Bean(name="myShiroRealm")
    public MyRealm authRealm() {
        MyRealm myShiroRealm=new MyRealm();
        return myShiroRealm;
    }

    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
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
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

//    @Bean(name="securityManager")
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
//        sm.setRealm(authRealm());
//        sm.setCacheManager(cacheManager());
//        //注入记住我管理器
//        sm.setRememberMeManager(rememberMeManager());
//        //注入自定义sessionManager
//        sm.setSessionManager(sessionManager());
//        return sm;
//    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        return new CustomSessionManager();
    }

    public CORSAuthenticationFilter corsAuthenticationFilter(){
        return new CORSAuthenticationFilter();
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //SecurityUtils.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置不会被拦截的链接，顺序判断
        filterChainDefinitionMap.put("/user/captcha", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/logout", "anon");
//        filterChainDefinitionMap.put("/static/js/**", "anon");
//        filterChainDefinitionMap.put("/static/css/**", "anon");
//        filterChainDefinitionMap.put("/static/fonts/**", "anon");
//        filterChainDefinitionMap.put("/login/**", "anon");
//        filterChainDefinitionMap.put("/corp/call_back/receive", "anon");
        //authc:所有url必须通过认证才能访问，anon:所有url都可以匿名访问
        filterChainDefinitionMap.put("/**", "corsAuthenticationFilter");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //自定义过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("corsAuthenticationFilter", corsAuthenticationFilter());
        shiroFilter.setFilters(filterMap);

        return shiroFilter;
    }


    /**
     * Shiro生命周期处理器 * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
