package core.dto.mapper.transaction;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import core.dto.mapper.StockMapper;
import core.dto.transaction.PurchaseOrderItemData;
import core.model.transaction.PurchaseOrderItem;

@Mapper(uses = { PurchaseOrderMapper.class, StockMapper.class })
public interface PurchaseOrderItemMapper {

	PurchaseOrderItemMapper INSTANCE = Mappers.getMapper(PurchaseOrderItemMapper.class);

	PurchaseOrderItemData toData(PurchaseOrderItem purchaseOrderItem);

	List<PurchaseOrderItemData> toData(List<PurchaseOrderItem> purchaseOrderItems);

	@InheritInverseConfiguration
	PurchaseOrderItem fromData(PurchaseOrderItemData purchaseOrderItemData);

}
