package org.itnavigator.test.task.service.impl;

import org.itnavigator.test.task.domain.model.Phone;
import org.itnavigator.test.task.service.MainService;
import org.itnavigator.test.task.service.PhoneService;
import org.itnavigator.test.task.service.UserService;
import org.itnavigator.test.task.service.dto.UserDTO;
import org.itnavigator.test.task.service.mapper.PhoneMapper;
import org.itnavigator.test.task.web.container.UpdateContainer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MainServiceDefault implements MainService {
	
	@Resource
	private UserService userService;
	
	@Resource
	private PhoneService phoneService;
	
	@Resource
	private PhoneMapper phoneMapper;
	
	@Override
	public List<UserDTO> getUsers() {
		return userService.getUsers();
	}
	
	@Override
	public UserDTO updatePhone(UpdateContainer container) {
		if (container != null) {
			Phone phone = phoneMapper.toPhone().apply(container);
			if (phoneService.updatePhone(phone)) {
				return userService.getUser(phone.getId());
			}
		}
		return null;
	}
	
	@Override
	public List<UserDTO> updatePhones(List<UpdateContainer> containers) {
		List<UserDTO> result = containers.stream()
										 .map(this::updatePhone)
										 .filter(Objects::nonNull)
										 .collect(Collectors.toList());
		return result.size() == containers.size() ? result : Collections.emptyList();
	}
}
