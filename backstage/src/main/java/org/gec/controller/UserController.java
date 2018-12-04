package org.gec.controller;

import org.gec.model.User;
import org.gec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author LZM
 * @Date 2018/11/28 17:11
 * @Version 1.0
 **/

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(path="/login.do", produces="application/json;charset=utf-8")
    @ResponseBody
    public Map login(User user, HttpServletResponse response) {
        List<User> list = userService.findUser(user);
        if (list.size() > 0) {
            return ajaxReturn(true, "登录成功");
        }
        return ajaxReturn(false, "用户名或密码不正确");
    }

    @RequestMapping(path="/addUser.do", produces="text/html;charset=utf-8")
    @ResponseBody
    public String addUser(User user) {
        try {
            userService.addUser(user);
            return "添加成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "添加失败";
    }

    protected Map ajaxReturn(boolean status, String message) {
        Map map = new HashMap();
        map.put("status", status);
        map.put("message", message);
        return map;
    }

}
