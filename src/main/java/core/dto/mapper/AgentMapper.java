package core.dto.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.AgentData;
import core.model.Agent;

@Mapper
public interface AgentMapper {

	AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

	@Mapping(source = "modifiedDate", target = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	AgentData toData(Agent item);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<AgentData> toData(List<Agent> items);

}
