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

import core.dto.UnitData;
import core.dto.mapper.UnitMapper;
import core.model.Unit;
import core.service.UnitService;

@CrossOrigin
@RestController
@RequestMapping("/unit")
public class UnitController {

	@Autowired private UnitService service;
	
	private UnitMapper MAPPER = UnitMapper.INSTANCE;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<UnitData> list(
    		@RequestParam(value = "filter", required = false) String filter,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(service.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public UnitData create(@RequestBody UnitData unitData) {
		Unit unit = MAPPER.fromData(unitData);
		return MAPPER.toData((Unit) service.save(unit));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public UnitData update(@RequestBody UnitData unitData) {
		Unit unit = MAPPER.fromData(unitData);
		return MAPPER.toData((Unit) service.update(unit));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.deleteRecordById(id);
	}
	
}
