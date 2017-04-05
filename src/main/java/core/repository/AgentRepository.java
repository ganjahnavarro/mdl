package core.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.model.Agent;

@Repository
@Transactional
public class AgentRepository extends AbstractRepository<Agent> {

}
