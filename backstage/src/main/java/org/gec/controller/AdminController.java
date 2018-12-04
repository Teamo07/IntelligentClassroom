package org.gec.controller;


import org.gec.model.Environment;
import org.gec.model.PageModel;
import org.gec.service.IEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:何其钊
 **/
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @Autowired
    private IEnvironmentService environmentService;

    /**
     *
     * @param page 第几页
     * @param rows 每页的行数
     * @return
     */
    @RequestMapping(path="/getEnv.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    public PageModel getEnv(Integer page, Integer rows) {
        int total = environmentService.getTotal();
        List<Environment> data = environmentService.findEnv(page, rows);
        PageModel pagemodel = new PageModel();
        pagemodel.setTotal(total);
        pagemodel.setRows(data);
        return pagemodel;
    }

}
