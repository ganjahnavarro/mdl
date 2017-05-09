package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.Brand;
import core.repository.AbstractRepository;
import core.repository.BrandRepository;

@Service
@Transactional
public class BrandService extends AbstractService {
	
	@Autowired private BrandRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<Brand> findFilteredItems(String filter, Integer pageSize, Integer pageOffset, String orderBy) {
		return repository.findFilteredItems(filter, pageSize, pageOffset, orderBy);
	}

}
