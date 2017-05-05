package org.itnavigator.test.task.service;

import org.itnavigator.test.task.service.dto.UserDTO;

import java.util.List;

public interface UserService {
	List<UserDTO> getUsers();
	UserDTO getUser(Long phoneId);
}
