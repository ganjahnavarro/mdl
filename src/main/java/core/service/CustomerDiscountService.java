package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.CustomerDiscount;
import core.repository.AbstractRepository;
import core.repository.CustomerDiscountRepository;

@Service
@Transactional
public class CustomerDiscountService extends AbstractService {

	@Autowired private CustomerDiscountRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<CustomerDiscount> findByCustomer(Long customerId) {
		return repository.findByCustomer(customerId);
	}
	
}
