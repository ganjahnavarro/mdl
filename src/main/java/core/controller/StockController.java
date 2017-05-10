package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.dto.StockData;
import core.dto.mapper.StockMapper;
import core.model.Brand;
import core.model.Category;
import core.model.Stock;
import core.model.Unit;
import core.service.BrandService;
import core.service.CategoryService;
import core.service.StockService;
import core.service.UnitService;

@CrossOrigin
@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired private StockService stockService;
	@Autowired private UnitService unitService;
	@Autowired private CategoryService categoryService;
	@Autowired private BrandService brandService;
	
	private StockMapper MAPPER = StockMapper.INSTANCE;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<StockData> list(
    		@RequestParam(value = "filter", required = false) String filter,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(stockService.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public StockData create(@RequestBody StockData stockData) {
		Stock stock = MAPPER.fromData(stockData);
		setReferences(stockData, stock);
		return MAPPER.toData((Stock) stockService.save(stock));
    }

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public StockData update(@RequestBody StockData stockData) {
		Stock stock = MAPPER.fromData(stockData);
		setReferences(stockData, stock);
		return MAPPER.toData((Stock) stockService.update(stock));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		stockService.deleteRecordById(id);
	}
	
	private void setReferences(StockData stockData, Stock stock) {
		if (stockData.getUnit() != null && stockData.getUnit().getId() != null) {
			Unit unit = (Unit) unitService.findById(stockData.getUnit().getId());
			stock.setUnit(unit);
		}
		
		if (stockData.getCategory() != null && stockData.getCategory().getId() != null) {
			Category category = (Category) categoryService.findById(stockData.getCategory().getId());
			stock.setCategory(category);
		}
		
		if (stockData.getBrand() != null && stockData.getBrand().getId() != null) {
			Brand brand = (Brand) brandService.findById(stockData.getBrand().getId());
			stock.setBrand(brand);
		}
	}

}
