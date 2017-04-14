package core.dto.transaction;

import java.util.Date;
import java.util.Set;

import core.dto.RecordData;
import core.model.Supplier;
import core.model.transaction.PurchaseOrderItem;

public class PurchaseOrderData extends RecordData {

	private String documentNo;
	private Supplier supplier;

	private Date date;
	private String remarks;

	private Set<PurchaseOrderItem> list;

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
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

	public Set<PurchaseOrderItem> getList() {
		return list;
	}

	public void setList(Set<PurchaseOrderItem> list) {
		this.list = list;
	}

}
