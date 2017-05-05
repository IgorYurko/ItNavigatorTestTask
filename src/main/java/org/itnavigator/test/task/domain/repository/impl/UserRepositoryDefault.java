package org.itnavigator.test.task.domain.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.itnavigator.test.task.domain.model.User;
import org.itnavigator.test.task.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class UserRepositoryDefault implements UserRepository {
	
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		try {
			return (List<User>) entityManager.createQuery("select u from User u join fetch u.phone order by u.id")
											 .getResultList();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return Collections.emptyList();
		}
	}
	
	@Override
	public User findOneByPhoneId(Long phoneId) {
		try {
			Query query = entityManager.createQuery("select u from User u join fetch u.phone where u.phone.id = :id order by u.id");
			query.setParameter("id", phoneId);
			return (User) query.getSingleResult();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}
}
