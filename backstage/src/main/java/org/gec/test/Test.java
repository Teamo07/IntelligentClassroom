package org.gec.test;

import org.apache.ibatis.session.SqlSession;
import org.gec.domain.User;
import org.gec.mapper.UserMapper;
import org.gec.utils.MyBatisUtil;

public class Test {

	public static void main(String[] args) {
		try {
			//获得操作数据库的SqlSession
			SqlSession session = MyBatisUtil.getSession();
			//使用session获得UserMapper的代理对象getMapper
			UserMapper UserMapper = session.getMapper(UserMapper.class);
			//保存用户
			User user = new User();
			user.setId(110);
			user.setName("gec");
			user.setPass("123");
			user.setPhone("123456789");
			UserMapper.addUser(user);
			//操作完提交事务
			session.commit();
			//关闭Session
			session.close();
			System.out.println("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
