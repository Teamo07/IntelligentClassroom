package org.gec.smart.servlet;

import com.alibaba.fastjson.JSONObject;
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
import java.util.UUID;

public class AddAssetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("name");
        String number = req.getParameter("number");
        String rfid = req.getParameter("rfid");
        JSONObject jsonObj = new JSONObject();

        if (checkRfid(rfid)) {
            jsonObj.put("status", false);
            jsonObj.put("message", "该RFID已经存在");
        } else {
            try {
                addAsset(name, number, rfid);
                jsonObj.put("status", true);
                jsonObj.put("message", "保存成功");
            } catch (Exception e) {
                e.printStackTrace();
                jsonObj.put("status", false);
                jsonObj.put("message", "保存失败");
            }
        }
        resp.getWriter().write(jsonObj.toString());
    }

    /**
     * 检查RFID是否已经存在
     * @param rfid RFID
     * @return true代表已经存在，false代表不存在
     */
    private boolean checkRfid(String rfid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            System.out.println("检查RFID数据库连接获取成功，开始查询");
            String sql = "select * from asset where rfid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rfid);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.release(rs, pstmt, conn);
        }
        return false;
    }

    /**
     * 保存设备信息
     * @param name 学生姓名
     * @param number 学号
     * @param rfid RFID
     */
    private void addAsset(String name, String number, String rfid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtil.getConnection();
            System.out.println("添加学生数据库连接获取成功，开始查询");
            String sql = "insert into asset values(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, name);
            pstmt.setString(3, number);
            pstmt.setString(4, rfid);
            pstmt.setBoolean(5, false);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DbUtil.release(null, pstmt, conn);
        }
    }

}
