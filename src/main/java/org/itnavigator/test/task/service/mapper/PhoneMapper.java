package org.itnavigator.test.task.service.mapper;

import org.itnavigator.test.task.domain.model.Phone;
import org.itnavigator.test.task.web.container.UpdateContainer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PhoneMapper {
	
	public Function<UpdateContainer, Phone> toPhone() {
		return container -> {
			Phone phone = new Phone();
			phone.setId(container.getId());
			phone.setValue(container.getPhone());
			phone.setType(container.getType());
			phone.setComment(container.getComment());
			return phone;
		};
	}
}
