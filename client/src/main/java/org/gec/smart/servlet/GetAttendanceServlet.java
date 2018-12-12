package org.gec.smart.servlet;

import com.alibaba.fastjson.JSON;
import org.gec.smart.bean.Attendance;
import org.gec.smart.bean.PageModel;
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

        int rows =10;
        if(req.getParameter("rows") != null){
            rows = Integer.parseInt(req.getParameter("rows"));
        }
        int page = 1;
        if(req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }
        //根据参数执行查询
        PageModel pageModel = findAttendance(rfid, startTime, endTime, page, rows);

        //把List集合转换成JSON格式字符串
        String json = JSON.toJSONString(pageModel);
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
     * @param page 第几页
     * @param rows 每页显示记录数
     * @return 返回一个分页对象
     */
    private PageModel findAttendance(String rfid, String startTime
            , String endTime, int page, int rows) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            //执行查询
            StringBuilder sql = new StringBuilder("select * ");
            sql.append(getSql(rfid, startTime, endTime));
            sql.append(" order by createtime desc ");
            sql.append(" limit " + page + ", " + rows);
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            List<Attendance> list = new ArrayList<Attendance>();
            while (rs.next()) {
                String id = rs.getString("id");
                Date createtime = rs.getTimestamp("createtime");
                boolean status = rs.getBoolean("status");
                list.add(new Attendance(id, rfid, createtime, status));
            }
            //查询总的结果数
            StringBuilder sql2 = new StringBuilder("select count(*) ");
            sql2.append(getSql(rfid, startTime, endTime));
            pstmt = conn.prepareStatement(sql2.toString());
            rs = pstmt.executeQuery();
            int total = rs.next() ? rs.getInt(1) : 0;
            //把结果封装成PageModel对象
            PageModel pageModel = new PageModel();
            pageModel.setData(list);
            pageModel.setCurPage(page);
            pageModel.setPageSize(rows);
            pageModel.setTotal(total);
            int totalPage= total%rows == 0 ? total/rows : (total/rows)+1;
            pageModel.setTotalPage(totalPage);
            return pageModel;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            DbUtil.release(rs, pstmt, conn);
        }
    }

    /**
     * 构造查询的SQL语句
     * @param rfid 学生姓名
     * @param startTime 开始查询时间
     * @param endTime 结束查询时间
     * @return
     */
    private String getSql(String rfid, String startTime, String endTime) {
        StringBuilder sql = new StringBuilder(" from attendance where rfid = '" + rfid + "'");
        List params = new ArrayList();
        if (startTime != null && !"".equals(startTime)) {
            sql.append(" and createtime >= " + startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            sql.append(" and createtime <= " + endTime);
        }
        return sql.toString();
    }



}
