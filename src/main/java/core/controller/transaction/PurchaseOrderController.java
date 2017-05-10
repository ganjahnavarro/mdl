package core.controller.transaction;

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
import core.model.Supplier;
import core.model.transaction.PurchaseOrder;
import core.model.transaction.PurchaseOrderItem;
import core.service.SupplierService;
import core.service.transaction.PurchaseOrderService;

@CrossOrigin
@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {
	
	@Autowired private PurchaseOrderService purchaseOrderService;
	@Autowired private SupplierService supplierService;
	
	private PurchaseOrderMapper MAPPER = PurchaseOrderMapper.INSTANCE;
	
	@RequestMapping(value = "/previous", method = RequestMethod.GET)
	public PurchaseOrderData previous(@RequestParam(required = false) String documentNo) {
		PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrder(documentNo, false);
		return MAPPER.toData(purchaseOrder);
    }
	
	@RequestMapping(value = "/next", method = RequestMethod.GET)
    public PurchaseOrderData next(@RequestParam(required = false) String documentNo) {
		PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrder(documentNo, true);
		return MAPPER.toData(purchaseOrder);
    }
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public PurchaseOrderData create(@RequestBody PurchaseOrderData purchaseOrderData) {
		PurchaseOrder purchaseOrder = MAPPER.fromData(purchaseOrderData);
		setDefaultValues(purchaseOrder);
		validateExistingDocumentNo(purchaseOrder.getDocumentNo(), null);
		return MAPPER.toData((PurchaseOrder) purchaseOrderService.save(purchaseOrder));
    }

	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
    public PurchaseOrderData update(@RequestBody PurchaseOrderData purchaseOrderData) {
		PurchaseOrder purchaseOrder = MAPPER.fromData(purchaseOrderData);
		setDefaultValues(purchaseOrder);
		validateExistingDocumentNo(purchaseOrder.getDocumentNo(), purchaseOrder.getId());
		return MAPPER.toData((PurchaseOrder) purchaseOrderService.update(purchaseOrder));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseOrderService.findById(id);
		purchaseOrderService.delete(purchaseOrder);
	}
	
	private void setDefaultValues(PurchaseOrder purchaseOrder) {
		if (purchaseOrder.getSupplier() != null) {
			Supplier supplier = (Supplier) supplierService.findById(purchaseOrder.getSupplier().getId());
			purchaseOrder.setSupplier(supplier);
		}
		
		if (purchaseOrder.getItems() != null) {
			for (PurchaseOrderItem item : purchaseOrder.getItems()) {
				item.setPurchaseOrder(purchaseOrder);
			}
		}
	}
	
	private void validateExistingDocumentNo(String documentNo, Long purchaseOrderId) {
		if (documentNo != null) {
			PurchaseOrder existing = purchaseOrderService.findByDocumentNo(documentNo);
			
			if (existing != null) {
				boolean newTransaction = purchaseOrderId == null;
				boolean notSameTransaction = !existing.getId().equals(purchaseOrderId);  
				
				if (newTransaction || notSameTransaction) {
					throw new IllegalArgumentException("Document No. " + documentNo + " already exists.");
				}
			}
		}
	}

}
