package org.itnavigator.test.task.domain.repository;

import org.itnavigator.test.task.domain.model.User;

import java.util.List;

public interface UserRepository {
	@SuppressWarnings("unchecked")
	List<User> findAll();
	User findOneByPhoneId(Long phoneId);
}
