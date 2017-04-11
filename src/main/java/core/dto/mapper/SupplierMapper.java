package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.SupplierData;
import core.model.Supplier;

@Mapper
public interface SupplierMapper {

	SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	SupplierData toData(Supplier supplier);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<SupplierData> toData(List<Supplier> suppliers);
	
	@InheritInverseConfiguration
	Supplier fromData(SupplierData supplierData);

}
