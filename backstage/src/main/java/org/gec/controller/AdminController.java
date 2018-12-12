package org.gec.controller;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.gec.model.Asset;
import org.gec.model.Environment;
import org.gec.model.PageModel;
import org.gec.model.Student;
import org.gec.service.IAssetsService;
import org.gec.service.IEnvironmentService;
import org.gec.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
