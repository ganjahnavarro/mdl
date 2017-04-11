package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.CategoryData;
import core.model.Category;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	CategoryData toData(Category category);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<CategoryData> toData(List<Category> categorys);
	
	@InheritInverseConfiguration
	Category fromData(CategoryData categoryData);

}
