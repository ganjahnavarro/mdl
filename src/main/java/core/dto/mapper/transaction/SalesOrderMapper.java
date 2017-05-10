package core.dto.mapper.transaction;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.mapper.AgentMapper;
import core.dto.mapper.CustomerMapper;
import core.dto.transaction.SalesOrderData;
import core.model.transaction.SalesOrder;

@Mapper(uses = { SalesOrderItemMapper.class, CustomerMapper.class, AgentMapper.class })
public interface SalesOrderMapper {

	SalesOrderMapper INSTANCE = Mappers.getMapper(SalesOrderMapper.class);
	
	@Mapping(target = "date", source = "date", dateFormat = "MM/dd/yyyy")
	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	SalesOrderData toData(SalesOrder salesOrder);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<SalesOrderData> toData(List<SalesOrder> salesOrders);
	
	@InheritInverseConfiguration
	SalesOrder fromData(SalesOrderData salesOrderData);
	
}
