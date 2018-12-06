package org.gec.auth;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.gec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置资源的访问规则
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        //以下规则必须加，不能因为我们将静态资源放在了resources/static文件夹下就不用加
        filterChainDefinitionMap.put("/html/login.html", "anon");
        filterChainDefinitionMap.put("/adminjs/**", "anon");
        filterChainDefinitionMap.put("/easyui/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/sys/login.do", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //指定认证失败后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/html/login.html");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }


    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }


}
