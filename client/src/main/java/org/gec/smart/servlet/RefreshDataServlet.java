package org.gec.smart.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.gec.smart.task.RefreshTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RefreshDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        rtnMap.put("temperature", RefreshTask.temperature);
        rtnMap.put("humidity",RefreshTask.humidity);
        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(rtnMap));
        resp.getWriter().write(jsonObj.toString());
    }

}
