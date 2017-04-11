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

import core.dto.CustomerData;
import core.dto.mapper.CustomerMapper;
import core.model.Agent;
import core.model.Customer;
import core.service.AgentService;
import core.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired private CustomerService customerService;
	@Autowired private AgentService agentService;
	
	private CustomerMapper MAPPER = CustomerMapper.INSTANCE;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<CustomerData> list(
    		@RequestParam(value = "filter", required = false) String filter,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(customerService.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public CustomerData create(@RequestBody CustomerData customerData) {
		Customer customer = MAPPER.fromData(customerData);
		setReferences(customerData, customer);
		return MAPPER.toData((Customer) customerService.save(customer));
    }

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public CustomerData update(@RequestBody CustomerData customerData) {
		Customer customer = MAPPER.fromData(customerData);
		setReferences(customerData, customer);
		return MAPPER.toData((Customer) customerService.update(customer));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		customerService.deleteRecordById(id);
	}
	
	private void setReferences(CustomerData customerData, Customer customer) {
		if (customerData.getAgent() != null && customerData.getAgent().getId() != null) {
			Agent agent = (Agent) agentService.findById(customerData.getAgent().getId());
			customer.setAgent(agent);
		}
	}

}
