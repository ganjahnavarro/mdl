package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.CustomerStockDiscountData;
import core.model.CustomerStockDiscount;

@Mapper(uses = { CustomerMapper.class, StockMapper.class })
public interface CustomerStockDiscountMapper {
	
	CustomerStockDiscountMapper INSTANCE = Mappers.getMapper(CustomerStockDiscountMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	CustomerStockDiscountData toData(CustomerStockDiscount customerDiscount);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<CustomerStockDiscountData> toData(List<CustomerStockDiscount> customerDiscounts);
	
	@InheritInverseConfiguration
	CustomerStockDiscount fromData(CustomerStockDiscountData customerDiscount);

}
