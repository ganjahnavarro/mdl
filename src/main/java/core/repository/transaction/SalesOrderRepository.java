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

import core.model.transaction.SalesOrder;
import core.repository.AbstractRepository;

@Repository
@Transactional
public class SalesOrderRepository extends AbstractRepository<SalesOrder> {
	
	@SuppressWarnings("unchecked")
	public List<SalesOrder> findFilteredItems(Long customerId, Date startDate, Date endDate) {
		Criteria criteria = getOrderedCriteria("date");
		if (customerId != null) {
			criteria.add(Restrictions.eq("customer.id", customerId));
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
		
		List<SalesOrder> salesOrders = criteria.list();
		for (SalesOrder salesOrder : salesOrders) {
			initializeSalesOrder(salesOrder);
		}
		
		return salesOrders;
	}
	
	@SuppressWarnings("unchecked")
	public List<SalesOrder> findFilteredItems(String filter, Integer pageSize, Integer pageOffset, String orderBy) {
		Criteria criteria = getPagedItemsCriteria(pageSize, pageOffset, orderBy);
		
		if (filter != null && !filter.isEmpty()) {
			criteria.add(Restrictions.ilike("documentNo", filter, MatchMode.START));
		}
		
		return criteria.list();
	}
	
	public SalesOrder findSalesOrder(String documentNo, boolean shouldGetNext) {
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
		
		SalesOrder salesOrder = (SalesOrder) criteria.uniqueResult();
		
		if (salesOrder == null) {
			return findByDocumentNo(documentNo);
		}
		
		initializeSalesOrder(salesOrder);
		return salesOrder;
	}
	
	public SalesOrder findByDocumentNo(String documentNo) {
		Criteria criteria = getDefaultCriteria();
		criteria.add(Restrictions.eq("documentNo", documentNo));
		SalesOrder salesOrder = (SalesOrder) criteria.uniqueResult();
		initializeSalesOrder(salesOrder);
		return salesOrder;
	}
	
	private void initializeSalesOrder(SalesOrder salesOrder) {
		if (salesOrder != null) {
			Hibernate.initialize(salesOrder.getItems());
		}
	}
	
	@Override
	protected String getEntityName() {
		return SalesOrder.ENTITY_NAME;
	}

}