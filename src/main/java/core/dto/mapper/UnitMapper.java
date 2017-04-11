package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.UnitData;
import core.model.Unit;

@Mapper
public interface UnitMapper {

	UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	UnitData toData(Unit unit);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<UnitData> toData(List<Unit> units);
	
	@InheritInverseConfiguration
	Unit fromData(UnitData unitData);

}
