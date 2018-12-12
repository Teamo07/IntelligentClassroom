package org.gec.smart.servlet;

import com.alibaba.fastjson.JSON;
import org.gec.smart.bean.Attendance;
import org.gec.smart.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetAttendanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取参数
        String rfid = req.getParameter("rfid");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        //根据参数执行查询
        List<Attendance> attendances = findAttendance(rfid, startTime, endTime);
        //把List集合转换成JSON格式字符串
        String json = JSON.toJSONString(attendances);
        //处理响应的中文乱码问题
        resp.setContentType("text/html;charset=utf-8");
        //向浏览器输入内容
        resp.getWriter().write(json);
    }

    /**
     * 条件查询学生的考勤记录
     * @param rfid 学生姓名
     * @param startTime 开始查询时间
     * @param endTime 结束查询时间
     * @return
     */
    private List<Attendance> findAttendance(String rfid, String startTime, String endTime) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            System.out.println("学生考勤查询数据库连接获取成功，开始查询");
            StringBuilder sql = new StringBuilder("select * from attendance where rfid = ? ");
            //StringBuilder sql = new StringBuilder("select * from attendance where 1=1 ");
            List params = new ArrayList();
            if (startTime != null && !"".equals(startTime)) {
                sql.append(" and createtime >= ? ");
                params.add(startTime);
            }
            if (endTime != null && !"".equals(endTime)) {
                sql.append(" and createtime <= ? ");
                params.add(endTime);
            }
            sql.append(" order by createtime desc ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, rfid);
            for (int i = 1; i <= params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i - 1));
            }
            rs = pstmt.executeQuery();
            List<Attendance> list = new ArrayList<Attendance>();
            while (rs.next()) {
                String id = rs.getString("id");
                Date createtime = rs.getTimestamp("createtime");
                boolean status = rs.getBoolean("status");
                list.add(new Attendance(id, rfid, createtime, status));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DbUtil.release(rs, pstmt, conn);
        }
    }


}
