package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.CustomerDiscountData;
import core.dto.mapper.CustomerDiscountMapper;
import core.model.CustomerDiscount;
import core.service.CustomerDiscountService;

@CrossOrigin
@RestController
@RequestMapping("/customerDiscount")
public class CustomerDiscountController {
	
	@Autowired private CustomerDiscountService service;
	
	private CustomerDiscountMapper MAPPER = CustomerDiscountMapper.INSTANCE;
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public List<CustomerDiscountData> list(@PathVariable Long customerId) {
		return MAPPER.toData(service.findByCustomer(customerId));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public CustomerDiscountData create(@RequestBody CustomerDiscountData customerDiscountData) {
		CustomerDiscount customerDiscount = MAPPER.fromData(customerDiscountData);
		return MAPPER.toData((CustomerDiscount) service.save(customerDiscount));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public CustomerDiscountData update(@RequestBody CustomerDiscountData customerDiscountData) {
		CustomerDiscount customerDiscount = MAPPER.fromData(customerDiscountData);
		return MAPPER.toData((CustomerDiscount) service.update(customerDiscount));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.deleteRecordById(id);
	}

}
