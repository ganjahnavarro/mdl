package core.model.transaction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import core.model.Record;
import core.model.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = SupplierPayment.ENTITY_NAME)
public class SupplierPayment extends Record {

	public static final String ENTITY_NAME = "supplierPayment";
	private static final long serialVersionUID = 5000775941337882357L;

	@NotNull(message = "Document No. is required")
	private String documentNo;
	
	@NotNull(message = "Supplier is required")
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	@NotNull(message = "Date is required")
	private Date date;
	
	private String remarks;
	private String bank;

	@Transient
	@Override
	public String getDisplayString() {
		return "Supplier Payment No. " + getDocumentNo();
	}

}
