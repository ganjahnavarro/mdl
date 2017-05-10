package core.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.dto.mapper.transaction.SalesOrderMapper;
import core.dto.transaction.SalesOrderData;
import core.model.Agent;
import core.model.Customer;
import core.model.transaction.SalesOrder;
import core.model.transaction.SalesOrderItem;
import core.service.AgentService;
import core.service.CustomerService;
import core.service.transaction.SalesOrderService;

@CrossOrigin
@RestController
@RequestMapping("/salesOrder")
public class SalesOrderController {
	
	@Autowired private SalesOrderService salesOrderService;
	@Autowired private CustomerService customerService;
	@Autowired private AgentService agentService;
	
	private SalesOrderMapper MAPPER = SalesOrderMapper.INSTANCE;
	
	@RequestMapping(value = "/previous", method = RequestMethod.GET)
	public SalesOrderData previous(@RequestParam(required = false) String documentNo) {
		SalesOrder salesOrder = salesOrderService.findSalesOrder(documentNo, false);
		return MAPPER.toData(salesOrder);
    }
	
	@RequestMapping(value = "/next", method = RequestMethod.GET)
    public SalesOrderData next(@RequestParam(required = false) String documentNo) {
		SalesOrder salesOrder = salesOrderService.findSalesOrder(documentNo, true);
		return MAPPER.toData(salesOrder);
    }
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public SalesOrderData create(@RequestBody SalesOrderData salesOrderData) {
		SalesOrder salesOrder = MAPPER.fromData(salesOrderData);
		setDefaultValues(salesOrder);
		validateExistingDocumentNo(salesOrder.getDocumentNo(), null);
		return MAPPER.toData((SalesOrder) salesOrderService.save(salesOrder));
    }

	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public SalesOrderData update(@RequestBody SalesOrderData salesOrderData) {
		SalesOrder salesOrder = MAPPER.fromData(salesOrderData);
		setDefaultValues(salesOrder);
		validateExistingDocumentNo(salesOrder.getDocumentNo(), salesOrder.getId());
		return MAPPER.toData((SalesOrder) salesOrderService.update(salesOrder));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		SalesOrder salesOrder = (SalesOrder) salesOrderService.findById(id);
		salesOrderService.delete(salesOrder);
	}
	
	private void setDefaultValues(SalesOrder salesOrder) {
		if (salesOrder.getCustomer() != null) {
			Customer customer = (Customer) customerService.findById(salesOrder.getCustomer().getId());
			salesOrder.setCustomer(customer);
		}
		
		if (salesOrder.getAgent() != null) {
			Agent agent = (Agent) agentService.findById(salesOrder.getAgent().getId());
			salesOrder.setAgent(agent);
		}
		
		if (salesOrder.getItems() != null) {
			for (SalesOrderItem item : salesOrder.getItems()) {
				item.setSalesOrder(salesOrder);
			}
		}
	}
	
	private void validateExistingDocumentNo(String documentNo, Long salesOrderId) {
		if (documentNo != null) {
			SalesOrder existing = salesOrderService.findByDocumentNo(documentNo);
			
			if (existing != null) {
				boolean newTransaction = salesOrderId == null;
				boolean notSameTransaction = !existing.getId().equals(salesOrderId);  
				
				if (newTransaction || notSameTransaction) {
					throw new IllegalArgumentException("Document No. " + documentNo + " already exists.");
				}
			}
		}
	}

}
