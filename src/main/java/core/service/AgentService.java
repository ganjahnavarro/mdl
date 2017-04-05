package core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.repository.AbstractRepository;
import core.repository.AgentRepository;

@Service
@Transactional
public class AgentService extends AbstractService {
	
	@Autowired private AgentRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}

}
