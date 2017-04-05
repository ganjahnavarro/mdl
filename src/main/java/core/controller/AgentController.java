package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.AgentData;
import core.dto.mapper.AgentMapper;
import core.service.AgentService;

@CrossOrigin
@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired private AgentService service;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<AgentData> list() {
        return AgentMapper.INSTANCE.toData(service.findAll());
    }

}
