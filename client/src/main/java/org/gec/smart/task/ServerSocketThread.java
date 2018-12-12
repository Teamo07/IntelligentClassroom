package org.gec.smart.task;

import org.gec.smart.util.DbUtil;
import org.gec.smart.util.TCPUtil;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.UUID;

public class ServerSocketThread extends Thread {

    private Socket socket = null;
    private DataInputStream dis = null;


    // 声明构造函数,接收客户端请求socket
    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 获取socket输入流， 获取到客户端所传数据
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            while (true) {
                int count = 0;
                while (count == 0) {
                    count = dis.available();
                }
                byte[] receive = new byte[count];
                int readLen = 0;
                while (readLen < count) {
                    readLen += dis.read(receive, readLen, count - readLen);
                }

                long current = System.currentTimeMillis();
                while (System.currentTimeMillis() - current < 100) {
                }
                String msg = TCPUtil.printHexString(receive);

                if (msg != null && !msg.equals("")) {
                    System.out.println("Client Socket Message:" + msg);
                    rfidCheck(msg);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public void rfidCheck(String rfid) {
        //获取获取到的RFID中的带有的设备号
        int deviceNo = Integer.valueOf(rfid.substring(2, 4));
        rfid = rfid.substring(4, 12);
        if (!"00000000".equals(rfid)) {
            RefreshTask.rfid = rfid;
            if (deviceNo == 1) { //设备号1为设备进出
                addAssetLog(rfid);
            } else if(deviceNo == 2) { //设备号2为考勤打卡
                addAttendance(rfid);
            }
        }

    }


    /**
     * 保存考勤记录
     * @param rfid
     */
    private void addAttendance(String rfid) {
        System.out.println("开始保存考勤记录....");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false); //启动事务

            System.out.println("RFID : "+ rfid);

            //根据rfid查询学生
            String sql = "select * from student where rfid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rfid);
            rs =  pstmt.executeQuery();
            System.out.println("正在保存考勤记录(" + rfid + ")....");
            if (rs.next()) {
                System.out.println("更新学生状态。。");
                boolean status = rs.getBoolean("status");
                sql = "update student set status = ? where rfid = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setBoolean(1, !status);
                pstmt.setString(2, rfid);
                pstmt.executeUpdate();

                System.out.println("插入考勤记录。。。");
                sql = "insert into attendance values(?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, UUID.randomUUID().toString());
                pstmt.setString(2, rfid);
                pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                pstmt.setBoolean(4, !status);
                pstmt.executeUpdate();
            }
            conn.commit(); //提交事务
            System.out.println("考勤记录保存成功！");
        } catch (SQLException e) {
            throw new RuntimeException("保存考勤记录失败！");
        } finally {
            DbUtil.release(rs, pstmt, conn);
        }
    }

    /**
     * 保存设备进出记录
     * @param rfid
     */
    private void addAssetLog(String rfid) {
        System.out.println("开始保存设备进出记录....");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false); //启动事务

            //根据rfid查询设备
            String sql = "select * from asset where rfid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rfid);
            rs =  pstmt.executeQuery();
            if (rs.next()) {
                boolean status = rs.getBoolean("status");
                sql = "update asset set status = ? where rfid = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setBoolean(1, !status);
                pstmt.setString(2, rfid);
                pstmt.executeUpdate();

                sql = "insert into asset_log values(?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, UUID.randomUUID().toString());
                pstmt.setString(2, rfid);
                pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                pstmt.setBoolean(4, !status);
                pstmt.executeUpdate();
            }
            conn.commit(); //提交事务
        } catch (SQLException e) {
            throw new RuntimeException("保存资产进出记录失败！");
        } finally {
            DbUtil.release(rs, pstmt, conn);
        }
    }


}
