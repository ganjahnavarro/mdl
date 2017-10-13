package core.repository.transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.IRecord;
import core.model.transaction.PurchaseOrder;
import core.repository.AbstractRepository;

@Repository
@Transactional
public class PurchaseOrderRepository extends AbstractRepository<PurchaseOrder> {
	
	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> findFilteredItems(Long supplierId, Date startDate, Date endDate) {
		Criteria criteria = getOrderedCriteria("date");
		if (supplierId != null) {
			criteria.add(Restrictions.eq("supplier.id", supplierId));
		}
		
		if (startDate != null) {
			criteria.add(Restrictions.ge("date", startDate));
		}
		
		if (endDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.DATE, 1);
			criteria.add(Restrictions.lt("date", calendar.getTime()));
		}
		
		List<PurchaseOrder> purchaseOrders = criteria.list();
		for (PurchaseOrder purchaseOrder : purchaseOrders) {
			initializePurchaseOrder(purchaseOrder);
		}
		
		return purchaseOrders;
	}
	
	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> findFilteredItems(String filter, Integer pageSize, Integer pageOffset, String orderBy) {
		Criteria criteria = getPagedItemsCriteria(pageSize, pageOffset, orderBy);
		
		if (filter != null && !filter.isEmpty()) {
			criteria.add(Restrictions.ilike("documentNo", filter, MatchMode.START));
		}
		
		return criteria.list();
	}
	
	public PurchaseOrder findPurchaseOrder(String documentNo, boolean shouldGetNext) {
		Criteria criteria = getDefaultCriteria();
		criteria.setMaxResults(1);
		
		if (documentNo != null) {
			if (shouldGetNext) {
				criteria.add(Restrictions.gt("documentNo", documentNo));
				criteria.addOrder(Order.asc("documentNo"));
			} else {
				criteria.add(Restrictions.lt("documentNo", documentNo));
				criteria.addOrder(Order.desc("documentNo"));
			}
		}
		
		PurchaseOrder purchaseOrder = (PurchaseOrder) criteria.uniqueResult();
		
		if (purchaseOrder == null) {
			return findByDocumentNo(documentNo);
		}
		
		initializePurchaseOrder(purchaseOrder);
		return purchaseOrder;
	}
	
	public PurchaseOrder findByDocumentNo(String documentNo) {
		Criteria criteria = getDefaultCriteria();
		criteria.add(Restrictions.eq("documentNo", documentNo));
		PurchaseOrder purchaseOrder = (PurchaseOrder) criteria.uniqueResult();
		initializePurchaseOrder(purchaseOrder);
		return purchaseOrder;
	}
	
	public PurchaseOrder findLatestPurchaseOrder() {
		Criteria criteria = getDefaultCriteria();
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);
		return (PurchaseOrder) criteria.uniqueResult();
	}
	
	@Override
	protected void preProcess(IRecord record) {
		PurchaseOrder purchaseOrder = (PurchaseOrder) record;
		super.preProcess(record);
		
		if (purchaseOrder.getDocumentNo() == null) {
			PurchaseOrder latest = findLatestPurchaseOrder();
			Long latestDocumentNo = latest != null ? Long.valueOf(latest.getDocumentNo()) : 0;
			purchaseOrder.setDocumentNo(String.valueOf(latestDocumentNo + 1));
		}
	}
	
	private void initializePurchaseOrder(PurchaseOrder purchaseOrder) {
		if (purchaseOrder != null) {
			Hibernate.initialize(purchaseOrder.getItems());
		}
	}
	
	@Override
	protected String getEntityName() {
		return PurchaseOrder.ENTITY_NAME;
	}

}