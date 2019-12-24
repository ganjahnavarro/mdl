package core.dto.transaction;

import java.math.BigDecimal;
import java.util.Set;

import core.dto.RecordData;
import core.dto.SupplierData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderData extends RecordData {

	private String documentNo;
	private SupplierData supplier;

	private String date;
	private String remarks;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

	private Set<PurchaseOrderItemData> items;

}
