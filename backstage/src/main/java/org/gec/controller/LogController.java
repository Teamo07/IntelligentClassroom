package org.gec.controller;

import org.gec.model.AirConditionerLog;
import org.gec.model.PageModel;
import org.gec.service.IAirConditionerLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:
 **/

@RestController
@RequestMapping("/admin")
public class LogController  extends BaseController{


        @Autowired
        private IAirConditionerLogService airConditionerLogService;

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



}
