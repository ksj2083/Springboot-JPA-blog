package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import ch.qos.logback.core.encoder.Encoder;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌, 즉 IoC를 해줌
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional //
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); //해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}	
//	@Transactional(readOnly = true) // Select할 때  트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 ( 정합성)
//	public User 로그인(User user) {
//		return userRepository.login(user.getUsername(), user.getPassword());
//	}
}
