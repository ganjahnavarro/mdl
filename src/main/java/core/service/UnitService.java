package core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.repository.AbstractRepository;
import core.repository.UnitRepository;

@Service
@Transactional
public class UnitService extends AbstractService {

	@Autowired private UnitRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}

}
