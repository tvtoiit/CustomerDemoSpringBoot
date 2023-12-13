package com.example.CustomerSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CustomerSystem.entity.MstUser;
import com.example.CustomerSystem.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;
	
	public MstUser getUser(String userId, String password) {
		return userRepository.findByUserIdAndPasswordAndDeleteYmdIsNull(userId, password);
	}
}
