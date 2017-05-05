package org.itnavigator.test.task;

import org.itnavigator.test.task.config.Application;
import org.itnavigator.test.task.domain.model.Phone;
import org.itnavigator.test.task.domain.model.User;
import org.itnavigator.test.task.domain.repository.PhoneRepository;
import org.itnavigator.test.task.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ApplicationTest {
	
	@Resource
	private PhoneRepository phoneRepository;
	
	@Resource
	private UserRepository userRepository;
	
	@Test
	public void whenSelectUserThenCheckNameAndPhone() {
		List<User> result = userRepository.findAll();
		assertThat(result, notNullValue());
		assertEquals("Ivan", result.get(0).getFirstName());
		assertEquals("(096)123-45-68", result.get(0).getPhone().getValue());
	}
	
	@Test
	public void whenUpdateUserThenCheckResult() throws Exception {
		Phone phone = new Phone();
		phone.setId(1L);
		phone.setValue("1234567890");
		phone.setType(Phone.Type.NO_INFORMATION);
		phone.setComment("Test comment");
		assertTrue(phoneRepository.update(phone));
		
		User user = userRepository.findOneByPhoneId(1L);
		assertEquals(user.getPhone().getType(), Phone.Type.NO_INFORMATION);
		assertEquals(user.getPhone().getValue(), "1234567890");
	}
}
