package org.itnavigator.test.task.service.mapper;

import org.itnavigator.test.task.domain.model.User;
import org.itnavigator.test.task.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper {
	
	public Function<User, UserDTO> toUserDTO() {
		return user ->
			UserDTO.builder()
				   .firstName(user.getFirstName())
				   .lastName(user.getLastName())
				   .patronymic(user.getPatronymic())
				   .phoneId(user.getPhone().getId())
				   .phone(user.getPhone().getValue())
				   .type(user.getPhone().getType().name().toLowerCase())
				   .comment(user.getPhone().getComment()).build();
	}
}
