package org.itnavigator.test.task.service.impl;

import org.itnavigator.test.task.domain.repository.UserRepository;
import org.itnavigator.test.task.service.UserService;
import org.itnavigator.test.task.service.dto.UserDTO;
import org.itnavigator.test.task.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceDefault implements UserService {
	
	@Resource
	private UserRepository repository;
	
	@Resource
	private UserMapper userMapper;
	
	@Override
	public List<UserDTO> getUsers() {
		return repository.findAll()
						 .stream()
						 .map(userMapper.toUserDTO())
						 .collect(Collectors.toList());
	}
	
	@Override
	public UserDTO getUser(Long phoneId) {
		return Optional.ofNullable(repository.findOneByPhoneId(phoneId))
					   .map(userMapper.toUserDTO())
					   .orElse(null);
	}
}
