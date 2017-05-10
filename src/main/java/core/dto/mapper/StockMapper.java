package core.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.StockData;
import core.model.Stock;

@Mapper(uses = { CategoryMapper.class, UnitMapper.class, BrandMapper.class })
public interface StockMapper {

	StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	StockData toData(Stock stock);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<StockData> toData(List<Stock> stocks);
	
	@InheritInverseConfiguration
	Stock fromData(StockData stockData);

}
