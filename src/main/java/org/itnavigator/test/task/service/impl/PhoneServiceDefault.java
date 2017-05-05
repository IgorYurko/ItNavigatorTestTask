package org.itnavigator.test.task.service.impl;

import org.itnavigator.test.task.domain.model.Phone;
import org.itnavigator.test.task.domain.repository.PhoneRepository;
import org.itnavigator.test.task.service.PhoneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PhoneServiceDefault implements PhoneService {

	@Resource
	private PhoneRepository repository;
	
	@Override
	public boolean updatePhone(Phone phone) {
		return repository.update(phone);
	}
	
}
