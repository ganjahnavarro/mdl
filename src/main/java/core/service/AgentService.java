package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.Agent;
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
	
	public List<Agent> findFilteredItems(String orderBy, Integer pageSize, Integer pageOffset, String filter) {
		return repository.findFilteredItems(orderBy, pageSize, pageOffset, filter);
	}

}
