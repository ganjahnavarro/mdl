package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.SupplierStockDiscountData;
import core.model.SupplierStockDiscount;

@Mapper(uses = { SupplierMapper.class, StockMapper.class })
public interface SupplierStockDiscountMapper {
	
	SupplierStockDiscountMapper INSTANCE = Mappers.getMapper(SupplierStockDiscountMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	SupplierStockDiscountData toData(SupplierStockDiscount supplierDiscount);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<SupplierStockDiscountData> toData(List<SupplierStockDiscount> supplierDiscounts);
	
	@InheritInverseConfiguration
	SupplierStockDiscount fromData(SupplierStockDiscountData supplierDiscount);

}
