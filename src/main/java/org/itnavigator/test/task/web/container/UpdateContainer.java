package org.itnavigator.test.task.web.container;

import lombok.Data;
import org.itnavigator.test.task.domain.model.Phone;

@Data
public class UpdateContainer {
	private Long id;
	private String phone;
	private Phone.Type type;
	private String comment;
}
