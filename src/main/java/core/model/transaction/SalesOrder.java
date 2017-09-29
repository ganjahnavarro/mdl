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

import core.model.Agent;
import core.model.Customer;
import core.model.Record;

@Entity(name = SalesOrder.ENTITY_NAME)
public class SalesOrder extends Record {

	public static final String ENTITY_NAME = "salesOrder";
	private static final long serialVersionUID = 1044962260130248646L;
	
	private String documentNo;
	private Customer customer;
	private Agent agent;

	private Date date;
	private String remarks;

	private BigDecimal discount1;
	private BigDecimal discount2;

	private Set<SalesOrderItem> items;
	
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

	@NotNull(message = "Agent is required")
	@ManyToOne(targetEntity = Agent.class)
	@JoinColumn(name = "agentId")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
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
	
	@OneToMany(targetEntity = SalesOrderItem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "salesOrder", orphanRemoval = true)
	public Set<SalesOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<SalesOrderItem> items) {
		this.items = items;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return "Sales Order No. " + getDocumentNo();
	}
	
	@Transient
	public BigDecimal getAmount() {
		BigDecimal amount = BigDecimal.ZERO;
		
		for(SalesOrderItem item : items) {
			amount = amount.add(item.getAmount());
		}
		return amount;
	}

}
