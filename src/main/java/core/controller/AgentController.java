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

import core.dto.AgentData;
import core.dto.mapper.AgentMapper;
import core.model.Agent;
import core.service.AgentService;

@CrossOrigin
@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired private AgentService service;
	
	private AgentMapper MAPPER = AgentMapper.INSTANCE;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<AgentData> list(
    		@RequestParam(value = "orderedBy", required = false) String orderedBy,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "filter", required = false) String filter) {
		return MAPPER.toData(service.findFilteredItems(orderedBy, pageSize, pageOffset, filter));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public AgentData create(@RequestBody AgentData agentData) {
		Agent agent = MAPPER.fromData(agentData);
		return MAPPER.toData((Agent) service.save(agent));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public AgentData update(@RequestBody AgentData agentData) {
		Agent agent = MAPPER.fromData(agentData);
		return MAPPER.toData((Agent) service.update(agent));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.deleteRecordById(id);
	}

}
