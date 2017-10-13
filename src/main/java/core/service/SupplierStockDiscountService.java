package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.SupplierStockDiscount;
import core.repository.AbstractRepository;
import core.repository.SupplierStockDiscountRepository;

@Service
@Transactional
public class SupplierStockDiscountService extends AbstractService {

	@Autowired private SupplierStockDiscountRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<SupplierStockDiscount> findByCustomer(Long customerId) {
		return repository.findByCustomer(customerId);
	}
	
}
