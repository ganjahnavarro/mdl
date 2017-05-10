package core.dto.mapper.transaction;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import core.dto.mapper.StockMapper;
import core.dto.transaction.SalesOrderItemData;
import core.model.transaction.SalesOrderItem;

@Mapper(uses = { SalesOrderMapper.class, StockMapper.class })
public interface SalesOrderItemMapper {

	SalesOrderItemMapper INSTANCE = Mappers.getMapper(SalesOrderItemMapper.class);

	SalesOrderItemData toData(SalesOrderItem salesOrderItem);

	List<SalesOrderItemData> toData(List<SalesOrderItem> salesOrderItems);

	@InheritInverseConfiguration
	SalesOrderItem fromData(SalesOrderItemData salesOrderItemData);

}
