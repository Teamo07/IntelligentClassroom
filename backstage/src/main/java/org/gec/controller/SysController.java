package org.gec.controller;

import org.gec.model.User;
import org.gec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public Map login(String username, String password, HttpSession session) {
        System.out.println("111111111111111111111111111");
        User user = userService.findUsers(username, password);
        System.out.println("222222222222222222222222222");
        if (user != null) {
            System.out.println("44444444444444444444444444444444");
            session.setAttribute("user", user);
            return ajaxReturn(true, "登录成功");
        }
        return ajaxReturn(false, "用户名或密码不正确");
    }

    @RequestMapping(path="/getName.do", produces="application/json;charset=utf-8")
    public Map getName(HttpSession session) {
        Object o = session.getAttribute("user");
        if (o == null) {
            return ajaxReturn(false, "请先登录");
        }
        User user = (User) o;
        return ajaxReturn(true, user.getName());
    }

    @RequestMapping(path="/logout.do", produces="application/json;charset=utf-8")
    public Map logout(HttpSession session) {
        session.invalidate();
        return ajaxReturn(true, "");
    }

}
