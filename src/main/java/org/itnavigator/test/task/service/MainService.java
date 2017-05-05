package org.itnavigator.test.task.service;

import org.itnavigator.test.task.service.dto.UserDTO;
import org.itnavigator.test.task.web.container.UpdateContainer;

import java.util.List;

public interface MainService {
	List<UserDTO> getUsers();
	UserDTO updatePhone(UpdateContainer container);
	List<UserDTO> updatePhones(List<UpdateContainer> containers);
}
