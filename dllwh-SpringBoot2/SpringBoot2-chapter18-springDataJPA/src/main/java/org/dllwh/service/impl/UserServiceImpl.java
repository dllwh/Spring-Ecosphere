package org.dllwh.service.impl;

import java.util.List;

import org.dllwh.dao.UserRepository;
import org.dllwh.model.User;
import org.dllwh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUserList() {
		return userRepository.findAll();
	}
}