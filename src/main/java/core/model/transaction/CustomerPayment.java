package core.model.transaction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import core.model.Customer;
import core.model.Record;

@Entity(name = CustomerPayment.ENTITY_NAME)
public class CustomerPayment extends Record {

	public static final String ENTITY_NAME = "customerPayment";
	private static final long serialVersionUID = 6549619976107803128L;

	private String documentNo;
	private Customer customer;

	private Date date;
	private String remarks;

	private String bank;
	private String accountNo;

	@NotNull(message = "Document No. is required")
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	@NotNull(message = "Customer is required")
	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return "Customer Payment No. " + getDocumentNo();
	}
}
