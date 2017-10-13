package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.SupplierBrandDiscount;
import core.repository.AbstractRepository;
import core.repository.SupplierBrandDiscountRepository;

@Service
@Transactional
public class SupplierBrandDiscountService extends AbstractService {

	@Autowired private SupplierBrandDiscountRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<SupplierBrandDiscount> findByCustomer(Long customerId) {
		return repository.findByCustomer(customerId);
	}
	
}
