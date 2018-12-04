package org.gec.service.impl;

import org.gec.model.User;
import org.gec.mapper.UserMapper;
import org.gec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public User findUsers(String username, String userpass) {
		Map map = new HashMap();
		map.put("username", username);
		map.put("userpass", userpass);
		return userMapper.getUser(map);
	}

	@Override
	public int editPass(User user) {
		return userMapper.editPass(user);
	}
}
