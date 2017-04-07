package core.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.Unit;

@Repository
@Transactional
public class UnitRepository extends AbstractRepository<Unit> {

	@Override
	protected String getEntityName() {
		return Unit.ENTITY_NAME;
	}

}
