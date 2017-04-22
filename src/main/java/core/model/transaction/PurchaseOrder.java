package core.model.transaction;

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
	
	private Set<PurchaseOrderItem> items;

	@NotNull
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	@NotNull
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@NotNull
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
