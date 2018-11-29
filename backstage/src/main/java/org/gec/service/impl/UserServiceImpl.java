package org.gec.service.impl;

import org.gec.domain.User;
import org.gec.mapper.UserMapper;
import org.gec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void info() {
		System.out.println("userservice info....");
	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
	}

	@Override
	public List<User> findUser(User user) {
		return userMapper.findUser(user);
	}

}
