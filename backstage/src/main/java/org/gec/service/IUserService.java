package org.gec.service;

import org.gec.domain.User;

import java.util.List;

public interface IUserService {

	void info();
	
	void addUser(User user);

    List<User> findUser(User user);

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param userpass
	 * @return
	 */
	User findUsers(String username, String userpass);
}
