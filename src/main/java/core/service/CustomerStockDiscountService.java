package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.CustomerStockDiscount;
import core.repository.AbstractRepository;
import core.repository.CustomerStockDiscountRepository;

@Service
@Transactional
public class CustomerStockDiscountService extends AbstractService {

	@Autowired private CustomerStockDiscountRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<CustomerStockDiscount> findByCustomer(Long customerId) {
		return repository.findByCustomer(customerId);
	}
	
}
