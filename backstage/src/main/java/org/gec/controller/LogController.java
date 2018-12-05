package org.gec.controller;

import org.gec.model.LightLog;
import org.gec.model.PageModel;
import org.gec.service.ILightLogService;
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
public class LogController extends BaseController {

    @Autowired
    private ILightLogService lightLogService;

    /**
     *
     * @param page 第几页
     * @param rows 每页的行数
     * @return
     */
    @RequestMapping(path="/getLightLog.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    public PageModel getLightLog(Integer page, Integer rows) {
        int total = lightLogService.getTotal();
        List<LightLog> data = lightLogService.findLightLog(page, rows);
        PageModel pagemodel = new PageModel();
        pagemodel.setTotal(total);
        pagemodel.setRows(data);
        return pagemodel;
    }

}
