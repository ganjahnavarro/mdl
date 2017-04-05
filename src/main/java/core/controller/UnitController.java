package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.UnitData;
import core.dto.mapper.UnitMapper;
import core.service.UnitService;

@CrossOrigin
@RestController
@RequestMapping("/unit")
public class UnitController {

	@Autowired private UnitService service;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<UnitData> list() {
        return UnitMapper.INSTANCE.toData(service.findAll());
    }
	
}
