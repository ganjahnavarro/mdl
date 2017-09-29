package core.model.transaction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import core.model.Record;
import core.model.Supplier;

@Entity(name = SupplierPayment.ENTITY_NAME)
public class SupplierPayment extends Record {

	public static final String ENTITY_NAME = "supplierPayment";
	private static final long serialVersionUID = 5000775941337882357L;

	private String documentNo;
	private Supplier supplier;

	private Date date;
	private String remarks;

	private String bank;

	@NotNull(message = "Document No. is required")
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	@NotNull(message = "Supplier is required")
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@NotNull(message = "Date is required")
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return "Supplier Payment No. " + getDocumentNo();
	}

}
