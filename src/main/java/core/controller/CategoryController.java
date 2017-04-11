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

import core.dto.CategoryData;
import core.dto.mapper.CategoryMapper;
import core.model.Category;
import core.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired private CategoryService service;
	
	private CategoryMapper MAPPER = CategoryMapper.INSTANCE;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<CategoryData> list(
    		@RequestParam(value = "filter", required = false) String filter,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(service.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public CategoryData create(@RequestBody CategoryData categoryData) {
		Category category = MAPPER.fromData(categoryData);
		return MAPPER.toData((Category) service.save(category));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public CategoryData update(@RequestBody CategoryData categoryData) {
		Category category = MAPPER.fromData(categoryData);
		return MAPPER.toData((Category) service.update(category));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.deleteRecordById(id);
	}

}
