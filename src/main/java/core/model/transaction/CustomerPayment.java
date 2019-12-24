package core.model.transaction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import core.model.Customer;
import core.model.Record;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = CustomerPayment.ENTITY_NAME)
public class CustomerPayment extends Record {

	public static final String ENTITY_NAME = "customerPayment";
	private static final long serialVersionUID = 6549619976107803128L;

	@NotNull(message = "Document No. is required")
	private String documentNo;
	
	@NotNull(message = "Customer is required")
	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customerId")
	private Customer customer;

	@NotNull(message = "Date is required")
	private Date date;
	
	private String remarks;

	private String bank;
	private String accountNo;

	@Transient
	@Override
	public String getDisplayString() {
		return "Customer Payment No. " + getDocumentNo();
	}
}
