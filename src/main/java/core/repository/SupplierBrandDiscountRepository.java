package core.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.SupplierBrandDiscount;

@Repository
@Transactional
public class SupplierBrandDiscountRepository extends AbstractRepository<SupplierBrandDiscount> {
	
	@SuppressWarnings("unchecked")
	public List<SupplierBrandDiscount> findByCustomer(Long customerId) {
		Criteria criteria = getDefaultCriteria();
		criteria.add(Restrictions.eq("customer.id", customerId));
		return criteria.list();
	}
	
	@Override
	protected String getEntityName() {
		return SupplierBrandDiscount.ENTITY_NAME;
	}

}
