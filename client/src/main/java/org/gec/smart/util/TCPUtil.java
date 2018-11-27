package org.gec.smart.util;

public class TCPUtil {
	
	/**
	 * 按照协议进行编码
	 * @param in 需要进行编码的数据
	 * @return 返回编码后的字节数组
	 */
	public static byte[] stringToByte(String in) {
		byte[] b;
		int j = 0;
		String strin[] = in.split(" ");
		b = new byte[strin.length];
		in = "";
		for (int i = 0; i < strin.length; i++) {
			in = in + strin[i];
		}
		StringBuffer buf = new StringBuffer(2);
		for (int i = 0; i < in.length(); i++, j++) {
			buf.insert(0, in.charAt(i));
			buf.insert(1, in.charAt(i + 1));
			int t = Integer.parseInt(buf.toString(), 16);
			b[j] = (byte) t;
			i++;
			buf.delete(0, 2);
		}
		return b;
	}
	
	/**
	 * 按照协议进行解码
	 * @param b 待解码的字节数组
	 * @return 解码后的数据
	 */
	public static String printHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.substring(0, sb.length());

	}
}
