package core.dto.transaction;

import java.util.Date;
import java.util.Set;

import core.dto.RecordData;
import core.dto.SupplierData;

public class PurchaseOrderData extends RecordData {

	private String documentNo;
	private SupplierData supplier;

	private Date date;
	private String remarks;

	private Set<PurchaseOrderItemData> items;

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public SupplierData getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierData supplier) {
		this.supplier = supplier;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<PurchaseOrderItemData> getItems() {
		return items;
	}

	public void setItems(Set<PurchaseOrderItemData> items) {
		this.items = items;
	}

}
