package org.itnavigator.test.task.domain.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.itnavigator.test.task.domain.model.Phone;
import org.itnavigator.test.task.domain.repository.PhoneRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Slf4j
@Repository
@Transactional
public class PhoneRepositoryDefault implements PhoneRepository {
	
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;
	
	@Override
	public boolean update(Phone phone) {
		try {
			Query query = entityManager.createQuery("update Phone p set p.value = :val, p.type = :tp, p.comment = :com where p.id = :id");
			query.setParameter("val", phone.getValue());
			query.setParameter("tp", phone.getType());
			query.setParameter("com", phone.getComment());
			query.setParameter("id", phone.getId());
			return query.executeUpdate() > 0;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return false;
		}
	}
}
