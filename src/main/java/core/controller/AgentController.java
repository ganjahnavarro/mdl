package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.dto.AgentData;
import core.dto.mapper.AgentMapper;
import core.service.AgentService;

@CrossOrigin
@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired private AgentService service;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<AgentData> list(
    		@RequestParam(value = "orderedBy", required = false) String orderedBy,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "filter", required = false) String filter) {
		return AgentMapper.INSTANCE.toData(service.findFilteredItems(orderedBy, pageSize, pageOffset, filter));
    }

}
