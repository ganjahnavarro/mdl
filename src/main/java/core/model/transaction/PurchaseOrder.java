package core.model.transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import core.model.Record;
import core.model.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = PurchaseOrder.ENTITY_NAME)
public class PurchaseOrder extends Record {

	public static final String ENTITY_NAME = "purchaseOrder";
	private static final long serialVersionUID = -537886941592614540L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String documentNo;
	
	@NotNull(message = "Supplier is required")
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	@NotNull(message = "Date is required")
	private Date date;
	
	private String remarks;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;
	
	@OneToMany(targetEntity = PurchaseOrderItem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "purchaseOrder", orphanRemoval = true)
	private Set<PurchaseOrderItem> items;

	@Transient
	@Override
	public String getDisplayString() {
		return "Purchase Order No. " + getDocumentNo();
	}
	
	@Transient
	public BigDecimal getAmount() {
		BigDecimal amount = BigDecimal.ZERO;
		
		for(PurchaseOrderItem item : items) {
			amount = amount.add(item.getAmount());
		}
		return amount;
	}
	
}
