package org.gec.test;

import org.gec.domain.User;
import org.gec.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserServiceTest {
	@Autowired
	private IUserService userService;

	@Test
	public void testInfo() {
		userService.info();
	}
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setId(120);
		user.setName("gec666");
		user.setPass("123");
		user.setPhone("0123456789");
		userService.addUser(user);
		System.out.println("保存成功");
	}
}
