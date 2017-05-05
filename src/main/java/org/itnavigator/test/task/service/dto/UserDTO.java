package org.itnavigator.test.task.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	private String firstName;
	private String lastName;
	private String patronymic;
	private Long phoneId;
	private String phone;
	private String type;
	private String comment;
}
