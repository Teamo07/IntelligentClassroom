package org.gec.smart.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.gec.smart.bean.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gec.smart.task.RefreshTask;
import org.gec.smart.util.CommonUtil;
import org.gec.smart.util.TCPUtil;
import org.gec.smart.util.AnalysisUtil;
import org.gec.smart.util.DbUtil;
import sun.awt.image.DataBufferNative;

/*
	发送控制指令
*/
public class ControlDeviceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//接收发送过来的指令
		String order = req.getParameter("order");
		//对发送过来的指令进行相关的处理
		String xor = Integer.toHexString(CommonUtil.getXor(TCPUtil.stringToByte(
				order.substring(3, order.length()-3))) & 0xff);
		if(xor.length() == 1){
			xor = "0" + xor;
		}
		//把指令末尾的XX替换成校验值
		String sendOrder = order.replace("XX", xor);
		System.out.println(sendOrder);
		try {
			//把指令转换字节数组后写入到输出流中
			RefreshTask.dos.write(TCPUtil.stringToByte(sendOrder));
			//把输出流中缓存数据全部写出去
			RefreshTask.dos.flush();

			saveOrder(sendOrder);
			//读取中控返回的数据
			int count = 0;
			while(count == 0){
				count = RefreshTask.dis.available();
			}
			byte[] b = new byte[count];
			int readLen = 0;
			while(readLen < count){
				readLen += RefreshTask.dis.read(b,readLen,count - readLen);
			}
			String responseStr = TCPUtil.printHexString(b);
			if(responseStr.equals("sendOrder")){
				resp.getOutputStream().print(true);
			}
			
			System.out.println("control device end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.getOutputStream().print(false);
	}



	/**
	 * 记录发送过的指令
	 * @param orderId 发送的指令
	 */
	private void saveOrder(String orderId) {
		//解析指令，该方法返回一个Map对象。Map的键保存了操作名，Map的值保存了JavaBean对象
		Map<String,Object> map = AnalysisUtil.analysis(orderId);
		//遍历集合
		for (String key : map.keySet()) {
			if(key.equals("light")) { //控制灯光
				LightLog lightLog = (LightLog) map.get("light");
				doLog(lightLog);
			}else if(key.equals("airConditioner")) { //控制空调
				AirConditionerLog airConditionerLog = (AirConditionerLog) map.get("airConditioner");
				doLog(airConditionerLog);
			}
		}
	}

	private void doLog(LightLog lightLog) {
		Connection conn = null;
		PreparedStatement pstmt = null;


		try {

			//获取数据库连接
			conn = DbUtil.getConnection();
			//conn = DbUtil.getInitInstance().getConnection();
			System.out.println("灯光控制：成功获得数据库连接");
			String sql = "insert into light_log values(?, ?, ?, ?)";
			//创建Statement对象
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, lightLog.getId());
			pstmt.setBoolean(2, lightLog.getOperation());
			pstmt.setTimestamp(3, new java.sql.Timestamp(lightLog.getCreateTime().getTime()));
			pstmt.setInt(4, lightLog.getDeviceNo());

			//执行更新操作
			pstmt.executeUpdate();
			System.out.println("成功存储灯光控制日志！！");

		} catch (SQLException e) {
			throw new RuntimeException("记录灯光操作日志失败！");
		} finally {
			DbUtil.release(null, pstmt, conn);

		}
	}

	private void doLog(AirConditionerLog airConditionerLog) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//获取数据库连接
			conn = DbUtil.getConnection();
			System.out.println("空调控制：成功获得数据库连接");
			//conn = DbUtil.getInitInstance().getConnection();
			String sql = "insert into air_conditioner_log values(?, ?, ?, ?,?, ?, ?, ?,?, ?, ?)";
			//创建Statement对象
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, airConditionerLog.getId());
			pstmt.setBoolean(2, airConditionerLog.getOxygen());
			pstmt.setBoolean(3, airConditionerLog.getHumidification());
			pstmt.setBoolean(4, airConditionerLog.getHeating());
			pstmt.setBoolean(5, airConditionerLog.getAeration());
			pstmt.setBoolean(6, airConditionerLog.getSleeping());
			pstmt.setInt(7, airConditionerLog.getTimer());
			pstmt.setBoolean(8, airConditionerLog.getFreshness());
			pstmt.setInt(9, airConditionerLog.getTemperature());
			pstmt.setInt(10, airConditionerLog.getDeviceNo());
			pstmt.setTimestamp(11, new java.sql.Timestamp(airConditionerLog.getCreatetime().getTime()));
			//执行更新操作
			pstmt.executeUpdate();
			System.out.println("成功存储空调控制日志");

		} catch (SQLException e) {
			throw new RuntimeException("记录空调操作日志失败！");
		} finally {
		DbUtil.release(null, pstmt, conn);
		}
	}
}
