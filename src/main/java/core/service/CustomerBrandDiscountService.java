package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.CustomerBrandDiscount;
import core.repository.AbstractRepository;
import core.repository.CustomerBrandDiscountRepository;

@Service
@Transactional
public class CustomerBrandDiscountService extends AbstractService {

	@Autowired private CustomerBrandDiscountRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<CustomerBrandDiscount> findByCustomer(Long customerId) {
		return repository.findByCustomer(customerId);
	}
	
}
