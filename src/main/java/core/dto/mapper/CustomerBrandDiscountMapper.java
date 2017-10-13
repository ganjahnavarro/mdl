package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.CustomerBrandDiscountData;
import core.model.CustomerBrandDiscount;

@Mapper(uses = { CustomerMapper.class, BrandMapper.class })
public interface CustomerBrandDiscountMapper {
	
	CustomerBrandDiscountMapper INSTANCE = Mappers.getMapper(CustomerBrandDiscountMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	CustomerBrandDiscountData toData(CustomerBrandDiscount customerDiscount);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<CustomerBrandDiscountData> toData(List<CustomerBrandDiscount> customerDiscounts);
	
	@InheritInverseConfiguration
	CustomerBrandDiscount fromData(CustomerBrandDiscountData customerDiscount);

}
