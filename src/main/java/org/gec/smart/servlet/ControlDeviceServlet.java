package org.gec.smart.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gec.smart.task.RefreshTask;
import org.gec.smart.util.CommonUtil;
import org.gec.smart.util.TCPUtil;

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

	
}
