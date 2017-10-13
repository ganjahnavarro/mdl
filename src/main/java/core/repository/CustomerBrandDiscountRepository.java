package core.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.CustomerBrandDiscount;

@Repository
@Transactional
public class CustomerBrandDiscountRepository extends AbstractRepository<CustomerBrandDiscount> {
	
	@SuppressWarnings("unchecked")
	public List<CustomerBrandDiscount> findByCustomer(Long customerId) {
		Criteria criteria = getDefaultCriteria();
		criteria.add(Restrictions.eq("customer.id", customerId));
		return criteria.list();
	}
	
	@Override
	protected String getEntityName() {
		return CustomerBrandDiscount.ENTITY_NAME;
	}

}
