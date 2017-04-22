package core.controller.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.dto.mapper.transaction.PurchaseOrderMapper;
import core.dto.transaction.PurchaseOrderData;
import core.model.transaction.PurchaseOrder;
import core.service.transaction.PurchaseOrderService;

@CrossOrigin
@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {
	
	@Autowired private PurchaseOrderService service;
	
	private PurchaseOrderMapper MAPPER = PurchaseOrderMapper.INSTANCE;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
    public PurchaseOrderData view(
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		PurchaseOrder purchaseOrder = service.findPurchaseOrder(pageOffset, orderedBy);
		return MAPPER.toData(purchaseOrder);
    }
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public List<PurchaseOrderData> list(
    		@RequestParam(value = "filter", required = false) String filter,
    		@RequestParam(value = "pageSize", required = false) Integer pageSize,
    		@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
    		@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(service.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public PurchaseOrderData create(@RequestBody PurchaseOrderData purchaseOrderData) {
		PurchaseOrder purchaseOrder = MAPPER.fromData(purchaseOrderData);
		return MAPPER.toData((PurchaseOrder) service.save(purchaseOrder));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public PurchaseOrderData update(@RequestBody PurchaseOrderData purchaseOrderData) {
		PurchaseOrder purchaseOrder = MAPPER.fromData(purchaseOrderData);
		return MAPPER.toData((PurchaseOrder) service.update(purchaseOrder));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.deleteRecordById(id);
	}

}
