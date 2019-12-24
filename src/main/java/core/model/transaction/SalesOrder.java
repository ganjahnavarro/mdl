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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = SalesOrder.ENTITY_NAME)
public class SalesOrder extends Record {

	public static final String ENTITY_NAME = "salesOrder";
	private static final long serialVersionUID = 1044962260130248646L;
	
	@NotNull(message = "Document No. is required")
	private String documentNo;
	
	@NotNull(message = "Customer is required")
	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@ManyToOne(targetEntity = Agent.class)
	@JoinColumn(name = "agentId")
	private Agent agent;

	private Date date;
	private String remarks;

	private BigDecimal discount1;
	private BigDecimal discount2;

	@OneToMany(targetEntity = SalesOrderItem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "salesOrder", orphanRemoval = true)
	private Set<SalesOrderItem> items;
	
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
