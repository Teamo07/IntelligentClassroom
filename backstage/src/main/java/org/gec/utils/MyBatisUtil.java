package org.gec.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
	MyBatis工具类
*/
public class MyBatisUtil {
	static SqlSessionFactory sessionFactory;
	
	static {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			System.out.println("加载配置文件失败！");
		}
	}
	
	/**
	 * 获取Session对象
	 * @return
	 */
	public static SqlSession getSession() {
		return sessionFactory.openSession();
	}
	
}
