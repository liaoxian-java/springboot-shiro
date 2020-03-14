package com.itcast.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
       /*
       anon:无需认证
       authc:必须认证
       user: 必须拥有 记住我 功能才能用
       perms:拥有某个资源才能访问              */
        Map<String, String> stringStringMap = new LinkedHashMap<>();
//        stringStringMap.put("/user/add","anon");
//        stringStringMap.put("/user/update","authc");
        stringStringMap.put("/user/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(stringStringMap);
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurrityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRelam") UserRelam userRelam) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Relam
        securityManager.setRealm(userRelam);
        return securityManager;
    }

    ;

    //创建Relam  ，自定义类
    @Bean
    public UserRelam userRelam() {
        return new UserRelam();
    }
}
