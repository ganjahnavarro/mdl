package core.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.CustomerStockDiscount;

@Repository
@Transactional
public class CustomerStockDiscountRepository extends AbstractRepository<CustomerStockDiscount> {
	
	@SuppressWarnings("unchecked")
	public List<CustomerStockDiscount> findByCustomer(Long customerId) {
		Criteria criteria = getDefaultCriteria();
		criteria.add(Restrictions.eq("customer.id", customerId));
		return criteria.list();
	}
	
	@Override
	protected String getEntityName() {
		return CustomerStockDiscount.ENTITY_NAME;
	}

}

