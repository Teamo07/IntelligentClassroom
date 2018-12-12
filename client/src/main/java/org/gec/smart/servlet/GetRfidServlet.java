package org.gec.smart.servlet;

import org.gec.smart.task.RefreshTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetRfidServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String rfid = "";
        if(RefreshTask.rfid != null && !RefreshTask.rfid.equals("")) {
            //先从rfid变量获取数据，然后再把rfid变量的值设置为null，最后把获取到的数据发送客户端
            rfid = RefreshTask.rfid;
            RefreshTask.rfid = null;
        }
        resp.getWriter().write(rfid);
    }



}
