package org.gec.smart.util;

public class CommonUtil {

	/**
	 * 计算校验值
	 */
	public static byte getXor(byte[] datas) {

		byte temp = datas[0];

		for (int i = 1; i < datas.length; i++) {
			temp ^= datas[i];
		}

		return temp;
	}


}
