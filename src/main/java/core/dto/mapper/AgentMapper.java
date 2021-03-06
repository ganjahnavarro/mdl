package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.AgentData;
import core.model.Agent;

@Mapper
public interface AgentMapper {

	AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	AgentData toData(Agent agent);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<AgentData> toData(List<Agent> agents);
	
	@InheritInverseConfiguration
	Agent fromData(AgentData agentData);

}
