package org.gec.service;

import org.gec.domain.User;

import java.util.List;

public interface IUserService {

	void info();
	
	void addUser(User user);

    List<User> findUser(User user);
}
