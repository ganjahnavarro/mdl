package core.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.Agent;

@Repository
@Transactional
public class AgentRepository extends AbstractRepository<Agent> {
	
	@SuppressWarnings("unchecked")
	public List<Agent> findFilteredItems(String orderBy, Integer pageSize, Integer pageOffset, String filter) {
		Criteria criteria = getPagedItemsCriteria(orderBy, pageSize, pageOffset);
		
		if (filter != null && !filter.isEmpty()) {
			criteria.add(Restrictions.ilike("name", filter, MatchMode.START));
		}
		
		return criteria.list();
	}

}
