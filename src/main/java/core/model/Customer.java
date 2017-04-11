package core.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = Customer.ENTITY_NAME)
public class Customer extends Record {

	public static final String ENTITY_NAME = "customer";
	private static final long serialVersionUID = -7415963747435056871L;

	private String name;
	private Agent agent;
	
	private String address;
	private String contact;
	private String fax;
	private String tin;
	private BigDecimal commission;

	@NotEmpty(message = "Name is required")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(targetEntity = Agent.class)
	@JoinColumn(name = "agentId")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Column(columnDefinition = "text")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}


	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
