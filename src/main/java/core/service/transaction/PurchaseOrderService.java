package core.service.transaction;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.transaction.PurchaseOrder;
import core.repository.AbstractRepository;
import core.repository.transaction.PurchaseOrderRepository;
import core.service.AbstractService;

@Service
@Transactional
public class PurchaseOrderService extends AbstractService {
	
	@Autowired private PurchaseOrderRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public PurchaseOrder findByDocumentNo(String documentNo) {
		return repository.findByDocumentNo(documentNo);
	}
	
	public List<PurchaseOrder> findFilteredItems(String filter, Integer pageSize, Integer pageOffset, String orderBy) {
		return repository.findFilteredItems(filter, pageSize, pageOffset, orderBy);
	}
	
	public List<PurchaseOrder> findFilteredItems(Long customerId, Date startDate, Date endDate) {
		return repository.findFilteredItems(customerId, startDate, endDate);
	}
	
	public PurchaseOrder findPurchaseOrder(String documentNo, Boolean shouldGetNext) {
		return repository.findPurchaseOrder(documentNo, shouldGetNext);
	}

}