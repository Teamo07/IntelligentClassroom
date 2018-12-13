package org.gec.smart.servlet;

import com.alibaba.fastjson.JSON;

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
import java.util.List;

import org.gec.smart.bean.Asset;
import org.gec.smart.util.DbUtil;

public class GetAssetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("调用GET方法");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置req对象使用的码表
        req.setCharacterEncoding("UTF-8");
        //获取参数
        String name = req.getParameter("name");
        String number = req.getParameter("number");
        //根据参数执行查询
        List<Asset> assets = findAsset(name,number);
        //把List集合转换成JSON格式字符串
        String json = JSON.toJSONString(assets);
        //处理响应的中文乱码问题
        resp.setContentType("text/html;charset=utf-8");
        //向浏览器输入内容
        resp.getWriter().write(json);
    }

    /**
     * 条件查询学生信息
     * @param name 学生姓名
     * @param number 学号
     * @return
     */
    private List<Asset> findAsset(String name, String number) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();
            System.out.println("设备查询数据库连接获取成功，开始查询");
            StringBuilder sql = new StringBuilder("select * from asset where 1=1 ");
            List params = new ArrayList();
            if (name != null && !"".equals(name)) {
                sql.append("and name like ? ");
                System.out.println("Name : "+name);
                params.add("%" + name + "%");
            }
            if (number != null && !"".equals(number)) {
                sql.append("and number like ? ");
                System.out.println("Number : "+number);
                params.add("%" + number + "%");
            }

            System.out.println("sql : " +sql.toString());

            pstmt = conn.prepareStatement(sql.toString());

            for (int i = 1; i <= params.size(); i++) {
                pstmt.setObject(i, params.get(i - 1));
            }
            rs = pstmt.executeQuery();

            List<Asset> list = new ArrayList<Asset>();
            while (rs.next()) {
                String id = rs.getString("id");
                String nam = rs.getString("name");
                String num = rs.getString("number");
                String rfid = rs.getString("rfid");
                //int status = rs.getInt("status");
                boolean statu = rs.getBoolean("status");
                int status = (statu == true ? 1 : 0);
                list.add(new Asset(id, nam, num, rfid, status));
            }
            return list;
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DbUtil.release(rs, pstmt, conn);
        }
    }
}
