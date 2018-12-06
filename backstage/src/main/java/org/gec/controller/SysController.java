package org.gec.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.gec.model.User;
import org.gec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.security.x509.SubjectKeyIdentifierExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * @author:李远文
 **/
@RestController //@ResponseBody+@Controller
@RequestMapping("/sys")  //指定命名空间为“sys”。即url前缀
public class SysController extends BaseController{

    @Autowired
    IUserService userService;



    @RequestMapping(path="/login.do", produces="application/json;charset=utf-8")
    @ResponseBody
    public Map login(HttpServletRequest request, String username, String password) {
        //第一步：创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //第二步：获取Subject对象，该对象封装了一系列的操作
        Subject subject = SecurityUtils.getSubject();
        //第三步：执行认证
        try {
            subject.login(token);
            return ajaxReturn(true, "登录成功！");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "登录失败！");
    }

    @RequestMapping(path="/getName.do", produces="application/json;charset=utf-8")
    public Map getName(HttpSession session) {
        //获得当前登录用户，如无则返回null
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        
        if (user == null) {
            return ajaxReturn(false, "");
        }
        return ajaxReturn(true, user.getName());
    }

    @RequestMapping(path="/logout.do", produces="application/json;charset=utf-8")
    public Map logout(HttpSession session) {
        //注销当前用户
        SecurityUtils.getSubject().logout();
        return ajaxReturn(true, "");
    }


    @RequestMapping(path="/editpass.do", produces="application/json;charset=utf-8")
    public Map editPass(String word,HttpSession session) {
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            User resultUser = userService.findUsers(user.getName(), user.getPass());
            resultUser.setPass(word);
            userService.editPass(resultUser);
            return ajaxReturn(true,word);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ajaxReturn(false, "修改失败");
    }





   /* @RequestMapping(path="/editpass.do", produces="application/json;charset=utf-8")
    public Map editPass(String word,HttpSession session) {
        User user= (User) session.getAttribute("user");
        User resultUser = userService.findUsers(user.getName(), user.getPass());
        if(resultUser==null){
            return ajaxReturn(false, "无法获取当前用户");
        }
        resultUser.setPass(word);
        int i = userService.editPass(resultUser);
        if(i==1){
            return ajaxReturn(true, word);
        }
        return ajaxReturn(false, "修改失败");
    } */
}
