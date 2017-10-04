package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.CustomerDiscountData;
import core.model.CustomerDiscount;

@Mapper(uses = { CustomerMapper.class, SupplierMapper.class })
public interface CustomerDiscountMapper {
	
	CustomerDiscountMapper INSTANCE = Mappers.getMapper(CustomerDiscountMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	CustomerDiscountData toData(CustomerDiscount customerDiscount);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<CustomerDiscountData> toData(List<CustomerDiscount> customerDiscounts);
	
	@InheritInverseConfiguration
	CustomerDiscount fromData(CustomerDiscountData customerDiscount);

}
