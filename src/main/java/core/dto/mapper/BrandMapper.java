package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.BrandData;
import core.model.Brand;

@Mapper
public interface BrandMapper {

	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	BrandData toData(Brand brand);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<BrandData> toData(List<Brand> brands);
	
	@InheritInverseConfiguration
	Brand fromData(BrandData brandData);

}
