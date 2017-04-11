package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.CustomerData;
import core.model.Customer;

@Mapper(uses = AgentMapper.class)
public interface CustomerMapper {
	
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	CustomerData toData(Customer customer);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<CustomerData> toData(List<Customer> customers);
	
	@InheritInverseConfiguration
	Customer fromData(CustomerData customerData);

}
