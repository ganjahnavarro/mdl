package core.dto.transaction;

import java.math.BigDecimal;
import java.util.Set;

import core.dto.RecordData;
import core.dto.SupplierData;

public class PurchaseOrderData extends RecordData {

	private String documentNo;
	private SupplierData supplier;

	private String date;
	private String remarks;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getDiscount1() {
		return discount1;
	}

	public void setDiscount1(BigDecimal discount1) {
		this.discount1 = discount1;
	}

	public BigDecimal getDiscount2() {
		return discount2;
	}

	public void setDiscount2(BigDecimal discount2) {
		this.discount2 = discount2;
	}

	public BigDecimal getDiscount3() {
		return discount3;
	}

	public void setDiscount3(BigDecimal discount3) {
		this.discount3 = discount3;
	}
	
	public Set<PurchaseOrderItemData> getItems() {
		return items;
	}

	public void setItems(Set<PurchaseOrderItemData> items) {
		this.items = items;
	}
	
}
