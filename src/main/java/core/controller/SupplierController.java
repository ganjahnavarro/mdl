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

import core.dto.SupplierData;
import core.dto.mapper.SupplierMapper;
import core.model.Supplier;
import core.service.SupplierService;

@CrossOrigin
@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired private SupplierService service;
	
	private SupplierMapper MAPPER = SupplierMapper.INSTANCE;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<SupplierData> list(
    		@RequestParam(value = "filter", required = false) String filter,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(service.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public SupplierData create(@RequestBody SupplierData supplierData) {
		Supplier supplier = MAPPER.fromData(supplierData);
		return MAPPER.toData((Supplier) service.save(supplier));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public SupplierData update(@RequestBody SupplierData supplierData) {
		Supplier supplier = MAPPER.fromData(supplierData);
		return MAPPER.toData((Supplier) service.update(supplier));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.deleteRecordById(id);
	}

}
