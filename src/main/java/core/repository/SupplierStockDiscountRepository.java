package core.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.SupplierStockDiscount;

@Repository
@Transactional
public class SupplierStockDiscountRepository extends AbstractRepository<SupplierStockDiscount> {
	
	@SuppressWarnings("unchecked")
	public List<SupplierStockDiscount> findByCustomer(Long customerId) {
		Criteria criteria = getDefaultCriteria();
		criteria.add(Restrictions.eq("customer.id", customerId));
		return criteria.list();
	}
	
	@Override
	protected String getEntityName() {
		return SupplierStockDiscount.ENTITY_NAME;
	}

}
