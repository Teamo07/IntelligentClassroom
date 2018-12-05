package org.gec.smart.task;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import java.sql.Connection;
import java.util.UUID;
import org.gec.smart.util.DbUtil;
import org.gec.smart.util.TCPUtil;

/*
	定义任务类，负责定时获取控制器的检测数据。
	
	ctrl + shift + o：一次性导入所有的包。
*/
public class RefreshTask extends TimerTask {
	public static Socket socket = null; //传感器的Socket
	public static DataOutputStream dos = null;
	public static DataInputStream dis = null;
	
	public static float temperature = 0; //温度
	public static float humidity = 0; //湿度 
	
	@Override
	public void run(){
		if(socket == null){
			System.out.println("task execute24.");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					getData();
				}
				
			}).start();
		}
	}
	
	private void getData(){
		try {
			//socket = new Socket(InetAddress.getByName("192.168.1.15"), 2017);
			//FE 0C 05 05 AD 88 00 02 FD 00 F5 23
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 2017);
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
			while(true){
				int count = 0;
				while(count == 0){
					count = dis.available();
				}
				byte[] receive = new byte[count];
				int readLen = 0;
				while(readLen < count){
					readLen += dis.read(receive, readLen, count - readLen);
				}
				System.out.println("start");
				long current = System.currentTimeMillis();
				//暂停3秒
				while(System.currentTimeMillis() - current < 3000){
				}
				String analysing = TCPUtil.printHexString(receive);
				System.out.println("end receive ->" + analysing);
				analysis(analysing);
				//存储数据
				store();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//解析数据
	public void analysis(String data) throws SQLException{
		int indexEF = data.indexOf("FE"); 
		if(indexEF == -1){
			System.out.println("return -1;");
			return;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		while(indexEF != -1){
			System.out.println("indexEF ->"+data.indexOf("FE"));
			int dataLen = Integer.valueOf(data.substring(indexEF + 2, indexEF + 4), 16) * 2;
			System.out.println("length ->"+dataLen);
			if(data.length() >= indexEF + dataLen){
				String dataRec = data.substring(indexEF, indexEF + dataLen);
				String device = dataRec.substring(4, 8);
				if(device.equals("0505")){
					String hh = dataRec.substring(14, 16);
					String hl = dataRec.substring(16, 18);
					Float hhv = Float.valueOf((float)(Integer.valueOf(hh, 16) * 256));
					Float hlv = Float.valueOf((float)(Integer.valueOf(hl,16)));
					humidity = (hhv + hlv) / 10;
					String th = dataRec.substring(18, 20);
					String tl = dataRec.substring(20, 22);
					Float thv = Float.valueOf((float)(Integer.valueOf(th, 16) * 256));
					Float tlv = Float.valueOf((float)(Integer.valueOf(tl,16)));
					temperature = (thv + tlv) / 10;
					System.out.println("湿度：" + humidity + ", 温度：" + temperature);
					map.put(device, humidity + "," + temperature);
				}
				System.out.println("dataRec ->" + dataRec);
				data = data.substring(indexEF + dataLen);
				indexEF = data.indexOf("FE");
			} else {
				System.out.println("return -1;");
				return;
			}
		}
	}

	/**
	 * 记录当前的温度和湿度
	 * @param temperature 温度
	 * @param humidity 湿度
	 */

	private void store() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			System.out.println("检测数据： 成功获得数据库连接");
			String sql = "insert into environment values(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, UUID.randomUUID().toString()); //uuid
			pstmt.setInt(2, 0); //有人
			pstmt.setFloat(3, humidity); //湿度
			pstmt.setInt(4, 0); //火焰
			pstmt.setInt(5, 0); //烟雾
			pstmt.setFloat(6, temperature); //温度
			pstmt.setInt(7, 0); //灯光
			pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.release(null, pstmt, conn);
		}
	}

}

