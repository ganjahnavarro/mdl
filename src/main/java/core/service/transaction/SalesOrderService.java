package core.service.transaction;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.transaction.SalesOrder;
import core.repository.AbstractRepository;
import core.repository.transaction.SalesOrderRepository;
import core.service.AbstractService;

@Service
@Transactional
public class SalesOrderService extends AbstractService {
	
	@Autowired private SalesOrderRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public SalesOrder findByDocumentNo(String documentNo) {
		return repository.findByDocumentNo(documentNo);
	}
	
	public List<SalesOrder> findFilteredItems(String filter, Integer pageSize, Integer pageOffset, String orderBy) {
		return repository.findFilteredItems(filter, pageSize, pageOffset, orderBy);
	}
	
	public SalesOrder findSalesOrder(String documentNo, Boolean shouldGetNext) {
		return repository.findSalesOrder(documentNo, shouldGetNext);
	}

}