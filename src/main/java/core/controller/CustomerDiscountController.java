package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.CustomerBrandDiscountData;
import core.dto.mapper.CustomerBrandDiscountMapper;
import core.model.CustomerBrandDiscount;
import core.service.CustomerBrandDiscountService;

@CrossOrigin
@RestController
@RequestMapping("/customerDiscount")
public class CustomerDiscountController {
	
	@Autowired private CustomerBrandDiscountService service;
	
	private CustomerBrandDiscountMapper MAPPER = CustomerBrandDiscountMapper.INSTANCE;
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public List<CustomerBrandDiscountData> list(@PathVariable Long customerId) {
		return MAPPER.toData(service.findByCustomer(customerId));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public CustomerBrandDiscountData create(@RequestBody CustomerBrandDiscountData customerDiscountData) {
		CustomerBrandDiscount customerDiscount = MAPPER.fromData(customerDiscountData);
		return MAPPER.toData((CustomerBrandDiscount) service.save(customerDiscount));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public CustomerBrandDiscountData update(@RequestBody CustomerBrandDiscountData customerDiscountData) {
		CustomerBrandDiscount customerDiscount = MAPPER.fromData(customerDiscountData);
		return MAPPER.toData((CustomerBrandDiscount) service.update(customerDiscount));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.deleteRecordById(id);
	}

}
