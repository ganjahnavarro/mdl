package core.dto.mapper.transaction;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.mapper.SupplierMapper;
import core.dto.transaction.PurchaseOrderData;
import core.model.transaction.PurchaseOrder;

@Mapper(uses = { PurchaseOrderItemMapper.class, SupplierMapper.class })
public interface PurchaseOrderMapper {

	PurchaseOrderMapper INSTANCE = Mappers.getMapper(PurchaseOrderMapper.class);
	
	
	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	PurchaseOrderData toData(PurchaseOrder purchaseOrder);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<PurchaseOrderData> toData(List<PurchaseOrder> purchaseOrders);
	
	@InheritInverseConfiguration
	PurchaseOrder fromData(PurchaseOrderData purchaseOrderData);
	
}
