package org.gec.controller;

import org.gec.model.Asset;
import org.gec.model.PageModel;
import org.gec.model.Student;
import org.gec.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName StudentController
 * @Author LZM
 * @Date 2018/12/6 14:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/stu")
public class StudentController extends BaseController{

    @Autowired
    private IStuService stuService;

    @RequestMapping(path="/getStu.do", produces="application/json;charset=utf-8")
    public PageModel getAsset(Integer page, Integer rows) {
        System.out.println("page = " + page + ", rows = " + rows);
        return stuService.findStu(page, rows);
    }

    @RequestMapping(path="/addStu.do", produces="application/json;charset=utf-8")
    public Map addAsset(Student student) {
        try {
            stuService.addStu(student);
            return ajaxReturn(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发生错误，请稍后再试");
    }

    @RequestMapping(path="/loadStu.do", produces="application/json;charset=utf-8")
    public Student loadAsset(String id) {
        return stuService.getStu(id);
    }

    @RequestMapping(path="/updateStu.do", produces="application/json;charset=utf-8")
    public Map updateAsset(Student student) {
        try {
            stuService.updateStu(student);
            return ajaxReturn(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发送异常，请稍后再试");
    }

    @RequestMapping(path="/delStu.do", produces="application/json;charset=utf-8")
    public Map delAsset(String id) {
        try {
            stuService.deleteStu(id);
            return ajaxReturn(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发送异常，请稍后再试");
    }
}
