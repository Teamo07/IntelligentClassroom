package org.gec.controller;

import org.gec.model.User;
import org.gec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Map login(String username, String password, HttpSession session) {
        User user = userService.findUsers(username, password);
        if (user != null) {
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


    @RequestMapping(path="/editpass.do", produces="application/json;charset=utf-8")
    public Map editPass(HttpSession session,String word) {
        User user= (User) session.getAttribute("user");
        User resultUser = userService.findUsers(user.getName(), user.getPass());
        if(resultUser==null){
            return ajaxReturn(false, "无法获取当前用户");
        }
        resultUser.setPass(word);
        User newUser = userService.editPass(resultUser);

        return ajaxReturn(true, "修改成功");
    }
}
