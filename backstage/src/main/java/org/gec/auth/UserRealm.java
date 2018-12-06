package org.gec.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.gec.model.User;
import org.gec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class UserRealm extends AuthorizingRealm {



    @Autowired
    IUserService userService;

    //执行授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

        return null;
    }

    //执行认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken arg0) throws AuthenticationException {
        //1.得到令牌
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        //2.调用业务组件进行登录判断
        String password = new String(token.getPassword());
        User user = userService.findUsers(token.getUsername(), password);
        //3.判断用户是否为空，如果不为空代表登录成功
        if (user != null) {
            return new SimpleAuthenticationInfo(
                    user, //priciple，使用当前登录用户对象
                    password,  //credentials
                    user.getName()); //realmName
        }
        //方法返回Null代表验证失败
        return null;
    }
}
