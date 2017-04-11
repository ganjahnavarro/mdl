package core.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.Category;

@Repository
@Transactional
public class CategoryRepository extends AbstractRepository<Category> {
	
	@SuppressWarnings("unchecked")
	public List<Category> findFilteredItems(String filter, Integer pageSize, Integer pageOffset, String orderBy) {
		Criteria criteria = getPagedItemsCriteria(pageSize, pageOffset, orderBy);
		
		if (filter != null && !filter.isEmpty()) {
			criteria.add(Restrictions.ilike("name", filter, MatchMode.START));
		}
		
		return criteria.list();
	}
	
	@Override
	protected String getEntityName() {
		return Category.ENTITY_NAME;
	}

}
