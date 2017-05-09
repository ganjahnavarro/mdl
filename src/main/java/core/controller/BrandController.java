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

import core.dto.BrandData;
import core.dto.mapper.BrandMapper;
import core.model.Brand;
import core.service.BrandService;

@CrossOrigin
@RestController
@RequestMapping("/brand")
public class BrandController {
	
	@Autowired private BrandService service;
	
	private BrandMapper MAPPER = BrandMapper.INSTANCE;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<BrandData> list(
    		@RequestParam(value = "filter", required = false) String filter,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(service.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public BrandData create(@RequestBody BrandData brandData) {
		Brand brand = MAPPER.fromData(brandData);
		return MAPPER.toData((Brand) service.save(brand));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public BrandData update(@RequestBody BrandData brandData) {
		Brand brand = MAPPER.fromData(brandData);
		return MAPPER.toData((Brand) service.update(brand));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.deleteRecordById(id);
	}

}
