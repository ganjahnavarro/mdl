package core.model.transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import core.model.Record;
import core.model.Supplier;

@Entity(name = PurchaseOrder.ENTITY_NAME)
public class PurchaseOrder extends Record {

	public static final String ENTITY_NAME = "purchaseOrder";
	private static final long serialVersionUID = -537886941592614540L;

	private String documentNo;
	private Supplier supplier;

	private Date date;
	private String remarks;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;
	
	private Set<PurchaseOrderItem> items;

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

	@Transient
	@Override
	public String getDisplayString() {
		return "Purchase Order No. " + getDocumentNo();
	}

	@OneToMany(targetEntity = PurchaseOrderItem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "purchaseOrder", orphanRemoval = true)
	public Set<PurchaseOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<PurchaseOrderItem> items) {
		this.items = items;
	}

}
