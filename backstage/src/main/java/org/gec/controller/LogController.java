package org.gec.controller;

import org.gec.model.AirConditionerLog;
import org.gec.model.Attendance;
import org.gec.model.LightLog;
import org.gec.model.PageModel;
import org.gec.service.IAirConditionerLogService;
import org.gec.service.IAttendLogService;
import org.gec.service.ILightLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:
 **/

@RestController
@RequestMapping("/Log")
public class LogController  extends BaseController{

    @Autowired
    private IAirConditionerLogService airConditionerLogService;
    @Autowired
    private ILightLogService lightLogService;
    @Autowired
    private IAttendLogService attendLogService;

    @RequestMapping(path="/getAirConditionerLog.do", produces="application/json;charset=utf-8")
    @ResponseBody
    public PageModel getAirConditionerLog(Integer page, Integer rows) {
        int total = airConditionerLogService.getTotal();
        List<AirConditionerLog> data = airConditionerLogService.findAir(page, rows);
        PageModel pageModel = new PageModel();
        pageModel.setTotal(total);
        pageModel.setRows(data);
        return pageModel;
    }

    /***
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


    @RequestMapping(path="/getAttendLog.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    public PageModel getAttendLog(Integer page, Integer rows) {
        int total = attendLogService.getTotal();
        List<Attendance> data = attendLogService.findAttendLog(page, rows);
        PageModel pagemodel = new PageModel();
        pagemodel.setTotal(total);
        pagemodel.setRows(data);
        return pagemodel;
    }

}
