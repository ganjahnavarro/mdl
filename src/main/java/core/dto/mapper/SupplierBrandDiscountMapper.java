package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.SupplierBrandDiscountData;
import core.model.SupplierBrandDiscount;

@Mapper(uses = { SupplierMapper.class, BrandMapper.class })
public interface SupplierBrandDiscountMapper {
	
	SupplierBrandDiscountMapper INSTANCE = Mappers.getMapper(SupplierBrandDiscountMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	SupplierBrandDiscountData toData(SupplierBrandDiscount supplierDiscount);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<SupplierBrandDiscountData> toData(List<SupplierBrandDiscount> supplierDiscounts);
	
	@InheritInverseConfiguration
	SupplierBrandDiscount fromData(SupplierBrandDiscountData supplierDiscount);

}
