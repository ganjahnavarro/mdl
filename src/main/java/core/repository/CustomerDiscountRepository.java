package core.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.CustomerDiscount;

@Repository
@Transactional
public class CustomerDiscountRepository extends AbstractRepository<CustomerDiscount> {
	
	@SuppressWarnings("unchecked")
	public List<CustomerDiscount> findByCustomer(Long customerId) {
		Criteria criteria = getDefaultCriteria();
		criteria.add(Restrictions.eq("customer.id", customerId));
		return criteria.list();
	}
	
	@Override
	protected String getEntityName() {
		return CustomerDiscount.ENTITY_NAME;
	}

}
